#include "C:/Users/ysmir/source/repos/ConsoleApplication1/headers/AnotherChildString.h"
#include <iostream>

void AnotherChildString::PrintStr() {
    std::cout << charArray << std::endl;
    std::cout << "Current size: " << currSize - 1 << std::endl;
    if (!isSpaceCalculated) {
        std::cout << "Spaces are didn't counted yet" << std::endl;
    }
    else {
        std::cout << "Space count: " << spaceArraySize << std::endl;
        std::cout << "Spaces can be found at " << std::endl;
        for (int i = 0; i < spaceArraySize; i++) {
            std::cout << spaceArray[i] << " ";
        }
        std::cout << std::endl;
    }
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

void AnotherChildString::CalculateSpace() {
    for (int i = 0; i < currSize; i++) {
        if (charArray[i] == ' ') {
            spaceArray[spaceArraySize] = i;
            spaceArraySize++;
        }
    }
    isSpaceCalculated = true;
}

AnotherChildString::AnotherChildString() : Strings(){
    spaceArray = new int[currSize];
    spaceArray[0] = -1;
    spaceArray[1] = -1;
    spaceArraySize = 2;
    isSpaceCalculated = false;
}

AnotherChildString::AnotherChildString(int currSize, const char* charArray) : Strings(currSize, charArray) {
    spaceArray = new int[currSize];
    spaceArraySize = 0;
    isSpaceCalculated = false;
}

AnotherChildString::AnotherChildString(const AnotherChildString& object) : Strings(object) {
    this->spaceArraySize = object.spaceArraySize;
    this->spaceArray = new int[this->spaceArraySize];
    for (int i = 0; i < spaceArraySize; i++)
        this->spaceArray[i] = object.spaceArray[i];
    this->isSpaceCalculated = object.isSpaceCalculated;
}

AnotherChildString::~AnotherChildString() {
    delete[] spaceArray;
}
