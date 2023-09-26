#include <iostream>
#include <cstring>
#include "D:\Labs C++\labs\Lab1\headers\Strings.h"

int main()
{
    const char* str1 = "Hello world";
    Strings object(strlen(str1), str1);
    object.PrintStr();
    std::cout << std::endl;
    const char* str2 = "Hello bob";
    object.ChangeStr(strlen(str2), str2);
    object.PrintStr();
    std::cout << std::endl;
    Strings object1(object);
    object1.PrintStr();
    std::cout << std::endl;
    Strings object2;
    object2.PrintStr();
    std::cout << std::endl;
    std::cout << "Found substring at " << object.FindStr("bob") << " index" << std::endl;
    std::cout << std::endl;
    object.UnionStr(strlen(str1), str1);
    object.PrintStr();
    return 0;
}
