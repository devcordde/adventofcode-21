#include "FileReader.h"
#include "iostream"
#include "fstream"

void FileReader::init(std::string path) {
    this->path=path;
}


std::vector<std::string> FileReader::getLines() {
    std::ifstream fileStream(this->path);
    if(!fileStream.is_open())
        return this->collection;

    std::string line;
    while(std::getline(fileStream, line))
    {
        this->collection.push_back(line);
    }

    return this->collection;
}
