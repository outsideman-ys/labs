#define _CRT_SECURE_NO_WARNINGS
#include "D:\Labs C++\labs\Lab3\headers\Strings.h"
#include <cstring>
#include <iostream>

void Strings::PrintStr() {
    for (int i = 0; i < currSize; i++)
        std::cout << charArray[i];
    std::cout << std::endl;

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
    char* temp = new char[this->currSize + currSize];
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
    this->charArray = new char[currSize];
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

Strings Strings::operator + (const Strings& object) {
    char* temp = new char[this->currSize + object.currSize];
    for (int i = 0; i < this->currSize; i++) {
        temp[i] = this->charArray[i]; 
    }
    for (int i = this->currSize, j = 0; i < this->currSize + object.currSize; i++, j++) {
        temp[i] = object.charArray[j];
    }
    delete[] this->charArray;
    this->currSize += object.currSize;
    this->charArray = new char[this->currSize];
    strcpy(this->charArray, temp); 
    delete[] temp;
    return object;
}

 
Strings operator - (Strings& object1, const Strings& object2) {
    char* temp = new char[object1.currSize + 1];
    strcpy(temp, object1.charArray);
    temp[object1.currSize] = '\0';
    char* subTemp = new char[object2.currSize + 1];
    strcpy(subTemp, object2.charArray);
    subTemp[object2.currSize] = '\0';
    char* pointer = strstr(temp, subTemp);
    if (pointer == NULL) {
        return object2;
    }
    else {
        int indStart = pointer - temp;
        int indEnd = pointer - temp + object2.currSize;
        char* temp = new char[object1.currSize];
        strcpy(temp, object1.charArray);
        for (int i = indStart, j = indEnd; j < object1.currSize; i++, j++) {
            temp[i] = temp[j]; 
        }
        delete[] object1.charArray;
        object1.currSize -= object2.currSize;
        object1.charArray = new char[object1.currSize];
        strncpy(object1.charArray, temp, object1.currSize);
        delete[] temp;
    }
    return object2;
}

std::ofstream& operator<< (std::ofstream& ofs, Strings object) {
    ofs << object.charArray << std::endl;
    ofs << "Size: " << object.currSize << std::endl;
    ofs << "Time of create: ";
    if (object.timeOfCreate->tm_hour < 10)
        ofs << "0";
    ofs << object.timeOfCreate->tm_hour << ":";
    if (object.timeOfCreate->tm_min < 10)
        ofs << "0";
    ofs << object.timeOfCreate->tm_min << ":";
    if (object.timeOfCreate->tm_sec < 10)
        ofs << "0";
    ofs << object.timeOfCreate->tm_sec << std::endl;
    return ofs;
}