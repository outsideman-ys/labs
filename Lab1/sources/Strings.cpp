#define _CRT_SECURE_NO_WARNINGS
#include "D:\Labs C++\labs\Lab1\headers\Strings.h"
#include <cstring>
#include <iostream>

void Strings::PrintStr() {
    for (int i = 0; i < currSize; i++)
        std::cout << charArray[i];
    std::cout << std::endl;
    std::cout << "Current size: " << currSize << std::endl;
    if (timeOfCreate->tm_hour < 10)
        std::cout << "0";
    std::cout << timeOfCreate->tm_hour << ":";
    if (timeOfCreate->tm_min < 10)
        std::cout << "0";
    std::cout << timeOfCreate->tm_min << ":";
    if (timeOfCreate->tm_sec < 10)
        std::cout << "0";
    std::cout << timeOfCreate->tm_sec << std::endl;
}

void Strings::RegTime() {
    time_t timeInSec = time(NULL);
    timeOfCreate = localtime(&timeInSec);
}

void Strings::ChangeStr(int currSize, const char* charArray) {
    delete[] this->charArray;
    this->charArray = new char[currSize];

    for (int i = 0; i < currSize; i++)
        this->charArray[i] = charArray[i];
    this->currSize = currSize;
}

void Strings::UnionStr(int currSize, const char* addStr) {
    char* temp = new char[this->currSize+currSize];
    for (int i = 0; i < this->currSize; i++) {
        temp[i] = this->charArray[i];
    }
    for (int i = this->currSize, j = 0; i < this->currSize + currSize; i++, j++) {
        temp[i] = addStr[j];
    }
    delete[] this->charArray;
    this->currSize += currSize;
    this->charArray = new char[this->currSize];
    strcpy(this->charArray, temp);
    delete[] temp;
}

int Strings::FindStr(const char* subCharArray) {
    char* pointer = strstr(charArray, subCharArray);
    if (pointer == NULL) {
        return -1;
    }
    else {
        return pointer - charArray;
    }
}

Strings::Strings(int currSize, const char* charArray) {
    this->charArray = new char[currSize];
    for (int i = 0; i < currSize; i++)
        this->charArray[i] = charArray[i];
    this->currSize = currSize;
    RegTime();
}

Strings::Strings() {
    this->currSize = 2;
    this->charArray = new char[2];
    this->charArray[0] = 'H';
    this->charArray[1] = 'i';
    RegTime();
}

Strings::Strings(const Strings &object) {
    this->currSize = object.currSize;
    this->charArray = new char[this->currSize];
    for (int i = 0; i < this->currSize; i++) {
        this->charArray[i] = object.charArray[i];
    }
    RegTime();
}

Strings::~Strings() {
    delete[] charArray;
}