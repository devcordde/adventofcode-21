#include "iostream"
#include "vector"
#include "fstream"

void part1(std::vector<int> measurements);
void part2(std::vector<int> measurements);

int main() {
    std::vector<int> measurements;
                        // cmake-build-debug
    std::ifstream fileStream("../shared/Day1/Measurements.txt");

    if(!fileStream.is_open())
        return 0;

    std::string line;
    while(std::getline(fileStream, line))
    {
         measurements.push_back(std::stoi(line));
    }

    part1(measurements);
    part2(measurements);
    return 0;
}

void part1(std::vector<int> measurements)
{
    int increased = 0;
    for(int i = 1; i < measurements.size(); i++)
    {
        if(measurements[i] > measurements[i -1])
            increased++;
    }
    std::cout << "Part 1: " << increased << std::endl;
}

void part2(std::vector<int> measurements)
{
    if(measurements.size() < 3)
        return;

    int lastMeasuring = 0;
    int increased = 0;

    for(int i = 1; i < measurements.size() - 2; ++i)
    {
        int currentSize = measurements[i - 1] + measurements[i] + measurements[i + 1];

        if(lastMeasuring < currentSize)
            increased++;

        lastMeasuring = currentSize;
    }

    std::cout << "Part 2: " << increased << std::endl;
}



