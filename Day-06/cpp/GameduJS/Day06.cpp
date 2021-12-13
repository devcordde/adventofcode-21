#include "iostream"
#include "FileReader.h"
#include "map"

using namespace std;

vector<string> split(const string& delimiter, const string& s);
unsigned long long cycleDays(int daysToPass, map<int, unsigned long long>* fishMap);

int main()
{
    FileReader fileReader("../shared/Puzzle.txt");
    vector<string> input = fileReader.readLines();

    // Internal Time, Count of fishes
    map<int, unsigned long long> baseFishes;
    for(int i = 0; i < 9; i++)
        baseFishes.insert({i, 0l});

    for(const auto& inputLine : input)
        for(const auto& internalTime : split(",", inputLine))
        {
            int itnTime = stoi(internalTime);
            auto it = baseFishes.find(itnTime);

            if(it != baseFishes.end())
                it->second = it->second+1;
            else
                baseFishes.insert({itnTime, 1});
        }
    auto part1Map(baseFishes);
    cout << "Part 1: " << cycleDays(80, &part1Map) << endl;
    cout << "Part 2: " << cycleDays(256, &baseFishes) << endl;


    return 0;
}

unsigned long long cycleDays(int daysToPass, map<int, unsigned long long>* fishMap)
{
    if(daysToPass == 0) // Sum all fishes
    {
        unsigned long long a = 0;
        for(auto & fish : *fishMap)
        {
            a+=fish.second;
        }
        return a;
    }

    unsigned long long fishToSpawn = fishMap->find(0)->second;

    for(int i = 0; i < 8; ++i) {
        fishMap->find(i)->second = fishMap->find(i + 1)->second;
    }

    fishMap->find(6)->second +=fishToSpawn;
    fishMap->find(8)->second = fishToSpawn;

    return cycleDays(daysToPass - 1, fishMap);
}

vector<string> split(const string& delimiter, const string& s)
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