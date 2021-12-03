#include "iostream"
#include "FileReader.h"
#include <cmath>
#include <algorithm>

int convert(long long n);
int getDominatingBit(const std::vector<std::string>& list, bool mostCommonBit, int index);
std::string matchDataPack(std::vector<std::string> &listBase, int index, bool mostCommonBit);

int main()
{
    FileReader fileReader("../shared/Puzzle.txt");
    std::vector<std::string> vector = fileReader.readLines();

    std::string gamma;
    std::string epsilon;
    for(int i = 0; i < vector[0].length(); ++i)
    {

        gamma+=std::to_string(getDominatingBit(vector, true, i));
        epsilon+=std::to_string(getDominatingBit(vector, false, i));
    }

    std::cout << "Part 1: " << convert(std::stoll(gamma)) * convert(std::stoll(epsilon)) << std::endl;
    std::cout << "Part 2: " << convert(std::stoll(matchDataPack(vector, 0, true))) * convert(std::stoll(matchDataPack(vector, 0, false))) << std::endl;

    return 0;
}

std::string matchDataPack(std::vector<std::string> &listBase, int index, bool mostCommonBit)
{
    std::vector<std::string> list(listBase);
    if(list.size() == 1)
        return list[0];
    int bit = getDominatingBit(list, mostCommonBit, index);

    list.erase(std::remove_if(list.begin(), list.end(), [&index, &bit](std::string s){
        return s.at(index) - '0' == bit;
    }), list.end());

    return matchDataPack(list, index + 1, mostCommonBit);
}

int getDominatingBit(const std::vector<std::string>& list, bool mostCommonBit, int index)
{
    int sum = 0;
    for(std::string dataPack : list)
    {
        char singleBit = dataPack.at(index);
        sum+=(singleBit - '0');
    }
    // Majority of ones
    auto ones = sum >= (double) list.size() / 2.0;
    return mostCommonBit ? ones ? 1 : 0 : ones ? 0 : 1;
}

int convert(long long n) {
    int dec = 0, i = 0, rem;
    while (n!=0) {
        rem = n % 10;
        n /= 10;
        dec += rem * pow(2, i);
        ++i;
    }
    return dec;
}
