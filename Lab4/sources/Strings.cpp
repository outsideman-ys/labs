#define _CRT_SECURE_NO_WARNINGS
#include <cstring>
#include <iostream>
#include <fstream>
#include "C:\Users\ysmir\source\repos\ochko\headers\Strings.h"

void Strings::PrintStr() {
    std::cout << charArray << std::endl;
    std::cout << "Current size: " << currSize - 1 << std::endl;
    if (timeOfCreate->tm_hour < 10)
        std::cout << "0";
    std::cout << timeOfCreate->tm_hour << ":";
    if (timeOfCreate->tm_min < 10)
        std::cout << "0";
    std::cout << timeOfCreate->tm_min << ":";
    if (timeOfCreate->tm_sec < 10) {
        std::cout << "0";
    }
    std::cout << timeOfCreate->tm_sec << std::endl;
}

void Strings::RegTime() {
    time_t timeInSec = time(NULL);
    timeOfCreate = localtime(&timeInSec);
}

void Strings::ChangeStr(int currSize, const char* charArray) {
    delete[] this->charArray;
    this->charArray = new char[currSize + 1];
    this->charArray[currSize] = '\0';

    for (int i = 0; i < currSize; i++)
        this->charArray[i] = charArray[i];
    this->currSize = currSize;
}

void Strings::UnionStr(int currSize, const char* addStr) {
    char* temp = new char[this->currSize + currSize + 1];
    for (int i = 0; i < this->currSize; i++) {
        temp[i] = this->charArray[i];
    }
    for (int i = this->currSize, j = 0; i < this->currSize + currSize; i++, j++) {
        temp[i] = addStr[j];
    }
    delete[] this->charArray;
    this->currSize += currSize;
    this->charArray = new char[this->currSize + 1];
    strcpy(this->charArray, temp);
    this->charArray[this->currSize] = '\0';
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
    this->charArray = new char[currSize + 1];
    for (int i = 0; i < currSize; i++)
        this->charArray[i] = charArray[i];
    this->currSize = currSize + 1;
    this->charArray[currSize] = 0;
    RegTime();
}

Strings::Strings() {
    this->currSize = 3;
    this->charArray = new char[3];
    this->charArray[0] = 'H';
    this->charArray[1] = 'i';
    this->charArray[2] = '\0';
    RegTime();
}

Strings::Strings(const Strings& object) {
    this->currSize = object.currSize;
    this->charArray = new char[this->currSize + 1];
    for (int i = 0; i < this->currSize; i++) {
        this->charArray[i] = object.charArray[i];
    }
    this->charArray[this->currSize] = '\0';
    RegTime();
}

Strings Strings::operator++(int) {
    Strings temp(*this);
    this->charArray[currSize - 1]++;
    return temp;
}
//post
Strings Strings::operator--(int) {
    Strings temp(*this);
    this->charArray[currSize - 1]--;
    return temp;
}
//pre
Strings& Strings::operator++() {
    this->charArray[currSize - 1]++;
    return *this;
}
//pre
Strings& Strings::operator--() {
    this->charArray[currSize - 1]--;
    return *this;
}

Strings& Strings::operator=(const Strings& str) {
    if (&str != this) {
        currSize = str.currSize;
        delete[] charArray;
        charArray = new char[currSize + 1];
        strncpy(charArray, str.charArray, currSize);
        charArray[currSize] = '\0';
    }
    return *this;
}

Strings::operator const char* () const {
    return charArray;
}

int Strings::getSize() {
    return currSize;
}

Strings Strings::operator + (const Strings& object) {
    Strings result(*this);
    char* temp = new char[result.currSize + object.currSize + 1];
    strncpy(temp, result.charArray, currSize - 1);
    temp[result.currSize - 1] = ' ';
    for (int i = result.currSize, j = 0; i < result.currSize + object.currSize; i++, j++) {
        temp[i] = object.charArray[j];
    }

    delete[] result.charArray;
    result.currSize += object.currSize;
    result.charArray = new char[result.currSize];
    strcpy(result.charArray, temp);
    delete[] temp;
    return result;
}



Strings operator - (const Strings& object1, const Strings& object2) {
    char* temp = new char[object1.currSize + 1];
    strcpy(temp, object1.charArray);
    temp[object1.currSize] = '\0';
    char* subTemp = new char[object2.currSize + 1];
    strcpy(subTemp, object2.charArray);
    subTemp[object2.currSize] = '\0';
    char* pointer = strstr(temp, subTemp);
    if (pointer == NULL) {
        return object1;
    }
    else {
        int indStart = pointer - temp;
        int indEnd = pointer - temp + object2.currSize;
        for (int i = indStart, j = indEnd; j < object1.currSize; i++, j++) {
            temp[i] = temp[j];
        }
        int newCurrSize = object1.currSize - object2.currSize;
        Strings result(newCurrSize, temp);
        return result;
    }
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

std::fstream& operator<< (std::fstream& ofsBin, Strings object) {
    ofsBin.write((char*)&object.currSize, sizeof(object.currSize));
    for (int i = 0; i < object.currSize; i++) {
        ofsBin.write((char*)&object.charArray[i], sizeof(object.charArray[i]));
    }
    ofsBin.write((char*)&object.timeOfCreate, sizeof(tm*));
    return ofsBin;
}

std::fstream& operator>> (std::fstream& ifsBin, Strings& object) {
    if (object.charArray != nullptr) {
        delete[] object.charArray;
    }
    int size;
    ifsBin.read((char*)&size, sizeof(int));
    object.currSize = size;
    char* str = new char[size + 1];
    ifsBin.read(str, object.currSize+1);
    object.charArray = new char[object.currSize];
    for (int i = 0; i <= strlen(str); i++) {
        object.charArray[i] = str[i];
    }
    delete[] str;
    return ifsBin;
}

Strings::~Strings() {
    delete[] charArray;
    //std::cout << "Function Name: " << __func__ << std::endl;
}