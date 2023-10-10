#include <iostream>
#include <cstring>
#include "D:\Labs C++\labs\Lab1\headers\Strings.h"

int main()
{
    std::cout << "Parameterized constructor:" << std::endl;
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

    std::cout << "Founding substring:" << std::endl;
    std::cout << "Substring found at index " << object.FindStr("bob") << std::endl;
    std::cout << std::endl;

    std::cout << "Union strings:" << std::endl;
    object.UnionStr(strlen(str1), str1);
    object.PrintStr();
    return 0;
}
