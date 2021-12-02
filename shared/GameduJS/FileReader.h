#ifndef AOC_21_FILEREADER_H
#define AOC_21_FILEREADER_H

#include "string"
#include "vector"
#pragma once

class FileReader {
public:
    void init(std::string path);
    std::vector<std::string> getLines();

private:
    std::vector<std::string> collection;
    std::string path;
};


#endif //AOC_21_FILEREADER_H
