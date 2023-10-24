#pragma once

#include <ctime>
#include <fstream> 


#ifndef STRINGS_H
#define STRINGS_H

class Strings {

private:
    char* charArray;
    int currSize;
    tm* timeOfCreate;

public:
    Strings();
    Strings(int, const char*);
    Strings(const Strings&);
    ~Strings();
    int FindStr(const char*);
    void ChangeStr(int, const char*);
    void PrintStr();
    void UnionStr(int, const char*);
    void RegTime();

    Strings operator + (const Strings& string);
    friend Strings operator - (Strings& object1, const Strings& object2);

    friend std::ofstream& operator<< (std::ofstream& os, Strings object);
};


#endif