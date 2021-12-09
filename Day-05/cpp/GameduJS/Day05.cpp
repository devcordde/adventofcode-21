#include "FileReader.h"
#include "iostream"
#include "algorithm"
#include "cmath"
#include "map"

using namespace std;

struct Point {
    int x;
    int y;
};

struct Coordinate {
    Point pointFrom;
    Point pointTo;

    bool equalX() const
    {
        return pointFrom.x == pointTo.x;
    }

    bool equalY() const
    {
        return pointFrom.y == pointTo.y;
    }

    vector<Point> calculatePoints() const
    {
        vector<Point> points;
        int x1 = pointFrom.x; int x2 = pointTo.x;
        int y1 = pointFrom.y; int y2 = pointTo.y;

        // Horizontal
        if(equalX() || equalY())
        {
            for(int i = min(pointFrom.x, pointTo.x); i <= max(pointFrom.x, pointTo.x); i++)
             {
                 for(int j = min(pointFrom.y, pointTo.y); j <= max(pointFrom.y, pointTo.y); j++)
                 {
                     points.push_back(Point{i, j});
                 }
             }
            return points;
        }

        int interval = abs(y1 - y2); // Since when it's a diagonal the x and y interval is the same
        for(int i = 0; i <= interval; ++i)
            points.push_back({(x1 < x2) ? x1 + i : x1 - i, (y1 < y2) ? y1 + i : y1 - i});
        return points;
    }

};

vector<string> split(string delimiter, string s);
vector<Coordinate> parseCoordinates(const vector<string>& readFile);

int part(const vector<Coordinate>& coordinates, bool diagonal);
void insertHeatMap(map<pair<int, int>, int>* heatMap, Coordinate* coordinate);

int main()
{
    FileReader fileReader("../shared/Puzzle.txt");
    vector<string> readFile = fileReader.readLines();

    vector<Coordinate> coordinates = parseCoordinates(readFile);

    cout << "Part 1: " << part(coordinates, false) << endl;
    cout << "Part 2: " << part(coordinates, true) << endl;

    return 0;
}

int part(const vector<Coordinate>& coordinates, bool diagonal)
{
    map<pair<int, int>, int> heatMap;
    for(Coordinate coordinate : coordinates)
    {
        if(diagonal)
        {
            insertHeatMap(&heatMap, &coordinate);
            continue;
        }

        if(coordinate.equalX() || coordinate.equalY())
        {
            insertHeatMap(&heatMap, &coordinate);
        }
    }

    int a = 0;
    for(auto & it : heatMap)
    {
        if(it.second >= 2)
            a++;
    }

    return a;
}

void insertHeatMap(map<pair<int, int>, int>* heatMap, Coordinate* coordinate)
{
    for(auto point : coordinate->calculatePoints()) {
        auto it = heatMap->find({point.x, point.y});
        if(it != heatMap->end())
            it->second=it->second+1;
        else
            heatMap->insert({{point.x, point.y}, 1});
    }
}

vector<string> split(string delimiter, string s)
{
    size_t pos_start = 0, pos_end, delim_len = delimiter.length();
    string token;
    vector<string> res;

    while ((pos_end = s.find (delimiter, pos_start)) != string::npos) {
        token = s.substr (pos_start, pos_end - pos_start);
        pos_start = pos_end + delim_len;
        res.push_back (token);
    }

    res.push_back (s.substr (pos_start));
    return res;
}
vector<Coordinate> parseCoordinates(const vector<string>& readFile)
{
    vector<Coordinate> coordinates;
    for(const auto& cords : readFile)
    {
        auto eachPoint = split(" -> ", cords);
        auto point1 = split(",", eachPoint[0]);
        auto point2 = split(",", eachPoint[1]);

        coordinates.push_back(Coordinate{stoi(point1[0]), stoi(point1[1]), stoi(point2[0]), stoi(point2[1])});
    }

    return coordinates;
}
