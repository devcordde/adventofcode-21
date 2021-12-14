#include "FileReader.h"
#include "iostream"
#include "cmath"

using namespace std;

int calc_fuel(const vector<int>& positions, int max_pos, bool crabEngineering);

int main()
{
    FileReader fileReader("../shared/Puzzle.txt");

    vector<int> input;
    int max_pos = 0;
    for(const string & line : fileReader.readLines())
        for(const string & rawNumber : fileReader.split(",", line))
        {
            int a = stoi(rawNumber);
            input.push_back(a);
            max_pos = max(a, max_pos);
        }

    cout << "Part 1: " << calc_fuel(input, max_pos, false) << endl;
    cout << "Part 2: " << calc_fuel(input, max_pos, true) << endl;

    return 0;
}

int calc_fuel(const vector<int>& positions, int max_pos, bool crabEngineering)
{
    int fuel = 0;
    for(int i = 0; i < max_pos; i++)
    {
        int fuelUsed = 0;
        for (const auto &pos: positions)
        {
            int baseFuelUsed = abs(pos - i);

            if(crabEngineering)
            {
                for(int j = 1; j < baseFuelUsed + 1; ++j)
                    fuelUsed+=j;
            } else
            {
                fuelUsed+=baseFuelUsed;
            }
        }
        fuel = fuel == 0 ? fuelUsed : min(fuelUsed, fuel);
    }

    return fuel;
}
