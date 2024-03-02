#include "D:\Labs C++\labs\Lab6\headers\ChildString.h"
#include <iostream>

void ChildString::PrintStr() {
    //std::cout << charArray << std::endl;
    //std::cout << "Current size: " << currSize - 1 << std::endl;
    //std::cout << "First word is " << std::endl;
    //if (timeOfCreate->tm_hour < 10)
    //    std::cout << "0";
    //std::cout << timeOfCreate->tm_hour << ":";
    //if (timeOfCreate->tm_min < 10)
    //    std::cout << "0";
    //std::cout << timeOfCreate->tm_min << ":";
    //if (timeOfCreate->tm_sec < 10) 
    //    std::cout << "0";
    //std::cout << timeOfCreate->tm_sec << std::endl;
    Strings::PrintStr();
    for (int i = 0; i < firstWordSize; i++) {
        std::cout << firstWord[i];
    }
    std::cout << std::endl;
}

void ChildString::DeleteSubstring(const char* subString) {
    int subSize = strlen(subString);
    char* pointer = strstr(charArray, subString);
    if (pointer == NULL) {
        std::cout << "Didnt find sub string" << std::endl;
    }
    else {
        for (int i = pointer - charArray; i < pointer - charArray + subSize; i++) {
            charArray[i] = ' ';
        }
    }
}

ChildString::ChildString() : Strings() {
    firstWordSize = 2;
    firstWord = new char[firstWordSize];
    firstWord[0] = 'H';
    firstWord[1] = 'i';
}

ChildString::ChildString(int currSize, const char* charArray) : Strings(currSize, charArray) {
    firstWordSize = 0;
    firstWord = new char[currSize];
    for (int i = 0; i < currSize; i++) {
        if (charArray[i] != ' ') {
            firstWordSize++;
            firstWord[i] = charArray[i];
        }
        else break;
    }
}

ChildString::ChildString(const ChildString& object) : Strings(object) {
    this->firstWordSize = object.firstWordSize;
    this->firstWord = new char[this->firstWordSize];
    for (int i = 0; i < firstWordSize; i++)
        this->firstWord[i] = object.firstWord[i];
}

ChildString::~ChildString() {
    delete[] firstWord;
}