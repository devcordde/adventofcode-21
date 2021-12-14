//
// Created by Yves on 02.12.2021.
//

#ifndef AOC_21_FILEREADER_H
#define AOC_21_FILEREADER_H

#include "string"
#include "vector"
#pragma once

class FileReader {
public:
    FileReader(std::string path);
    std::vector<std::string> readLines();
    std::vector<int> readLinesAsInt();
    std::vector<std::string> split(const std::string& delimiter, const std::string& s);
private:
    std::string path;
    std::vector<std::string> collection;
};


#endif //AOC_21_FILEREADER_H
