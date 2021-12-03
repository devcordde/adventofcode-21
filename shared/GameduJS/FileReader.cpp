//
// Created by Yves on 02.12.2021.
//

#include "FileReader.h"
#include "fstream"
#include "iostream"
#include "cstring"

FileReader::FileReader(std::string path) {
    this->path=path;
}

std::vector<std::string> FileReader::readLines() {
    std::ifstream fileStream(path);

    if(!fileStream.is_open())
        return collection;

    std::string line;
    while(std::getline(fileStream, line))
    {
        collection.push_back(line);
    }

    return collection;
}

std::vector<int> FileReader::readLinesAsInt() {
    std::vector<std::string> strings = readLines();
    std::vector<int> intCollection;

    for(auto str : strings)
    {
        intCollection.push_back(std::stoi(str));
    }

    return intCollection;
}
