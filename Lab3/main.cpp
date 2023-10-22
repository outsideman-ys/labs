﻿#include <iostream>
#include <fstream>
#include <cstring>
#include "D:\Labs C++\labs\Lab3\headers\Strings.h"

int main()
{
    std::cout << "Parameterized constructor: " << std::endl;
    const char* str1 = "Hello, world";
    Strings object(strlen(str1), str1);
    object.PrintStr();
    std::cout << std::endl;

    std::cout << "Changing values:" << std::endl;
    const char* str2 = "Hello, bob";
    object.ChangeStr(strlen(str2), str2);
    object.PrintStr();
    std::cout << std::endl;

    std::cout << "Copy constructor:" << std::endl;
    Strings object1(object);
    object1.PrintStr();
    std::cout << std::endl;

    std::cout << "Default constructor:" << std::endl;
    Strings object2;
    object2.PrintStr();
    std::cout << std::endl;

    std::cout << "Substring found at index " << object.FindStr("bob") << std::endl;
    std::cout << std::endl;

    std::cout << "Union strings:" << std::endl;
    object.UnionStr(strlen(str1), str1);
    object.PrintStr();
    std::cout << std::endl;

    std::cout << "+ overloading" << std::endl;
    object2 + object1;
    object2.PrintStr();
    std::cout << std::endl;

    const char* str3 = "Hello, world boba";
    Strings object3(strlen(str3), str3);
    const char* str4 = "world"; 
    Strings object4(strlen(str4), str4);

    std::cout << "- overloading:" << std::endl;
    object3 - object4;
    object3.PrintStr();
    std::cout << std::endl;

    std::ofstream ofs("text.txt", std::ios::out);
    if (!ofs) {
        std::cerr << "Ошибка записи в файл." << std::endl;
        exit(1);
    }

    ofs << object2;
    ofs.close();

    return 0;
}
