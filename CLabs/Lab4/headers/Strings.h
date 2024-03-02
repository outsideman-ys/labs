#pragma once

#include <ctime>
#include <fstream>

#ifndef STRINGS_H
#define STRINGS_H

class Strings {

protected:
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
    virtual void PrintStr();
    void UnionStr(int, const char*);
    int getSize();
    void RegTime();
    Strings operator++(int);
    Strings operator--(int);
    Strings& operator++();
    Strings& operator--();
    Strings& operator=(const Strings&);
    operator const char* () const;
    Strings operator + (const Strings& string);
    friend Strings operator - (const Strings& object1, const Strings& object2);
    friend std::ofstream& operator<< (std::ofstream& ofs, Strings object);
    friend std::fstream& operator<< (std::fstream& ofsBin, Strings object);
    friend std::fstream& operator>> (std::fstream& ifsBin, Strings& object);
};


#endif