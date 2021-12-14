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

    for(const auto& str : strings)
    {
        intCollection.push_back(std::stoi(str));
    }

    return intCollection;
}

std::vector<std::string> FileReader::split(const std::string& delimiter, const std::string& s)
{
    size_t pos_start = 0, pos_end, delim_len = delimiter.length();
    std::string token;
    std::vector<std::string> res;

    while ((pos_end = s.find (delimiter, pos_start)) != std::string::npos) {
        token = s.substr (pos_start, pos_end - pos_start);
        pos_start = pos_end + delim_len;
        res.push_back (token);
    }

    res.push_back (s.substr (pos_start));
    return res;
}
