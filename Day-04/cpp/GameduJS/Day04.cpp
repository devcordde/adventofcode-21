#include "iostream"
#include "FileReader.h"
#include "map"
#include <algorithm>
#include <utility>


void extractNumbers(char delimiter, std::string *string, std::vector<int> *writeTo);
std::string replaceAll(std::string str, const std::string& from, const std::string& to);

int test(std::map<int, std::vector<int>> *boards, std::vector<int> marked, bool returnBest)
{
    for(const auto& tables : *boards)
    {
        int tableNum = tables.first;
        std::vector<int> table = tables.second;

        for(int q = 0; q < 2; ++q)
        {
            /* 0 = rows, 1 = Columns */
            for(int a = 0; a < 5; ++a)
            {
                //std::cout << "-----------" << std::endl;
                std::vector<int> bingoSet;
                for(int b = 0; b < 5; ++b)
                {
                    int index = (q == 0) ? (5*a + b) : (a + 5*b);
                    if(std::find(marked.begin(), marked.end(), table[index]) != marked.end()) {
                        bingoSet.push_back(table[index]);
                    }

                }

                if(bingoSet.size() == 5) {
                    int k = 0;
                    for(auto t : table)
                    {
                        if(std::find(marked.begin(), marked.end(), t) == marked.end()) {
                            k = k + t;
                        }
                    }
                    bingoSet.clear();
                    if(!returnBest)
                        boards->erase(tableNum);
                    return returnBest ? k : boards->empty() ? k : test(boards, marked, returnBest);
                }
            }
        }
    }
    return -1;
}

int main()
{
    FileReader fileReader("../shared/Puzzle.txt");
    std::vector<std::string> readFile = fileReader.readLines();
    std::map<int, std::vector<int>> boards;
    std::vector<int> marker_numbers;
    std::vector<int> marked;

    extractNumbers(',', &readFile[0], &marker_numbers);

    // 0*6 + x[1-6]; 1*6 + x[1-6];...
    for(int tIndex = 0; tIndex < (readFile.size() - 1) / 6; tIndex++)
    {
        std::vector<int> table;

        for(int lIndex = 1; lIndex < 7; lIndex++)
        {
            int index = tIndex * 6 + lIndex;
            if(readFile[index].empty())
                continue;
            std::vector<int> in;
            std::string input = replaceAll(readFile[index], "  ", " ");
            extractNumbers(' ', &input, &in);

            for(int item : in)
            {
                //lIndex*5 + i
                table.push_back(item);
            }
        }

        boards.insert({tIndex, table});

    }

    // Part 1
    auto part1Boards(boards);
    for(int i = 0; i < marker_numbers.size(); i++)
    {
        int item = marker_numbers[i];
        marked.push_back(item);

        bool found = false;
        // Bingo = 5 in der Reihe
        if(i > 4) {
            int summa = test(&part1Boards, marked, true);
            if(summa != -1)
            {
                std::cout << summa * item << std::endl;
                found = true;
            }
        }
        if(found)
            break;
    }

    // Part2
    auto part2Boards(boards);
    for(int i = 0; i < marker_numbers.size(); i++)
    {
        int item = marker_numbers[i];
        marked.push_back(item);

        bool found = false;
        // Bingo = 5 in der Reihe
        if(i > 4) {
            int summa = test(&part2Boards, marked, false);
            if(summa != -1)
            {
                std::cout << summa * item << std::endl;
                found = true;
            }
        }
        if(found)
            break;
    }

    return 0;
}

void extractNumbers(char delimiter, std::string *string, std::vector<int> *writeTo) {
    std::string number;
    for(int i = 0; i < string->size(); ++i)
    {
        char c = string->at(i);
        if(c != delimiter)
            number+=c;
        if(c == delimiter || i == string->size() - 1)
        {
            if(!number.empty())
                writeTo->push_back(std::stoi(number));
            number = "";
        }
    }
}

std::string replaceAll(std::string str, const std::string& from, const std::string& to) {
    size_t start_pos = 0;
    while((start_pos = str.find(from, start_pos)) != std::string::npos) {
        str.replace(start_pos, from.length(), to);
        start_pos += to.length();
    }
    return str;
}