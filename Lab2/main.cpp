#include <iostream>
#include <fstream>
#include <cstring>
#include "headers\Strings.h" 

int main()
{
    //      CONSTRUCTOR WITH PARAMETERS     //
    std::cout << "CONSTRUCTOR WITH PARAMETERS" << std::endl << std::endl;
    const char* str1 = "Hello world";
    Strings* object = new Strings(strlen(str1), str1);
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //      STRINGS CHANGING                //
    std::cout << "STRINGS CHANGING" << std::endl << std::endl;
    const char* str2 = "Hello bob";
    object->ChangeStr(strlen(str2), str2);
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //      COPY CONSTRUCTOR         //
    std::cout << "COPY CONSTRUCTOR" << std::endl << std::endl;
    Strings* object1 = new Strings(*object);
    object1->PrintStr();
    std::cout << std::endl << std::endl;

    //      FIND SUBSTRING         //
    std::cout << "FIND SUBSTRING" << std::endl << std::endl;
    std::cout << "Found substring at " << object->FindStr("bob") << " index" << std::endl;
    std::cout << std::endl << std::endl;

    //      UNION STRINGS           //
    std::cout << "UNION STRINGS" << std::endl << std::endl;
    object->UnionStr(strlen(str1), str1);
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //          PREFIX              //
    std::cout << "PREFIX" << std::endl << std::endl;
    ++(*object);
    object->PrintStr();
    --(*object);
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //          POSTFIX             //
    std::cout << "POSTFIX" << std::endl << std::endl;
    (*object)++;
    object->PrintStr();
    (*object)--;
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //          ASSIGMENT           //
    std::cout << "ASSIGMENT" << std::endl << std::endl;
    Strings* object2 = new Strings();
    object2->PrintStr();
    *object = *object2;
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //          MINUS               //
    const char* str3 = "Hello, world boba";
    Strings* object3 = new Strings(strlen(str3), str3);
    const char* str4 = "world";
    Strings* object4 = new Strings(strlen(str4), str4);
    std::cout << "- overloading:" << std::endl;
    *object3 - *object4;
    object3->PrintStr();
    std::cout << std::endl;

    //          PLUS                //
    std::cout << "+ overloading" << std::endl;
    *object3 + *object4;
    object3->PrintStr();
    std::cout << std::endl << std::endl;


    //          TYPE CAST           //
    std::cout << "TYPE CAST" << std::endl << std::endl;
    const char* bob = *object;
    for (int i = 0; i < object->getSize(); i++) {
        std::cout << bob[i];
    }
    std::cout << std::endl;

    delete object;
    delete object1;
    delete object2;
    delete object3;
    delete object4;
    return 0;
}