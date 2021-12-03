#include <cstring>
#include "iostream"
#include "fstream"
#include "vector"

int x = 0;
int y = 0;
int aim = 0;

std::vector<std::string> split(std::string string, std::string delimiter);

int main()
{
    std::vector<std::string> commandCollection;

    std::ifstream stream("../shared/Day02/CommandInput.txt");

    if(!stream.is_open())
        return 0;

    std::string line;
    while(std::getline(stream, line))
    {
        commandCollection.push_back(line);
    }

    for(int i = 0; i < commandCollection.size(); ++i)
    {
        std::string commands = commandCollection[i];
        std::vector<std::string> commandSplit = split(commands, " ");

        std::string command = commandSplit[0];
        int commandValue = std::stoi(commandSplit[1]);

        if(command == "forward")
        {
            x+=commandValue;
        }
        if(command == "down")
        {
            y+=commandValue;
        }
        if(command == "up")
        {
            y-=commandValue;
        }

    }

    std::cout << " Part 1 solution: " << x * y << std::endl;
    x = 0;
    y = 0;

    // Part 2
    for(int i = 0; i < commandCollection.size(); ++i)
    {
        std::string commands = commandCollection[i];
        std::vector<std::string> commandSplit = split(commands, " ");

        std::string command = commandSplit[0];
        int commandValue = std::stoi(commandSplit[1]);

        if(command == "down")
            aim+=commandValue;
        if(command == "up")
            aim-=commandValue;
        if(command == "forward")
        {
            x+=commandValue;
            y+=aim * commandValue;
        }
    }

    std::cout << "Part 2 solution: " << x * y << std::endl;




    return 0;
}

std::vector<std::string> split(std::string string, std::string delimiter)
{
    std::vector<std::string> vector;

    size_t pos = 0;
    std::string token;
    if ((pos = string.find(delimiter)) != std::string::npos) {
        token = string.substr(0, pos);
        vector.push_back(token);
        vector.push_back(string.substr(token.size() + 1));
    }

    return vector;
}