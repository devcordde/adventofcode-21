#include "iostream"
#include "FileReader.h"
#include "string"

int main()
{
    FileReader fileReader;
    fileReader.init("../shared/Day03/Measurements.txt");
    std::vector<std::string> vector = fileReader.getLines();


}

