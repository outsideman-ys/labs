#include <iostream>
#include <fstream>
#include <cstring>
#include "C:\Users\ysmir\source\repos\ConsoleApplication1\headers\Strings.h" 
#include "C:\Users\ysmir\source\repos\ConsoleApplication1\headers\AnotherChildString.h" 
#include "C:\Users\ysmir\source\repos\ConsoleApplication1\headers\ChildString.h" 
#include "C:\Users\ysmir\source\repos\ConsoleApplication1\headers\List.h"

int main()
{
    //      CONSTRUCTOR WITH PARAMETERS     //
    std::cout << "CONSTRUCTOR WITH PARAMETERS" << std::endl << std::endl;
    const char* str1 = "Hello world";
    Strings* object = new Strings(strlen(str1), str1);
    object->PrintStr();
    std::cout << std::endl << std::endl;

    //      STRINGS CHANGING                //
    /*std::cout << "STRINGS CHANGING" << std::endl << std::endl;
    const char* str2 = "Hello bob";
    object->ChangeStr(strlen(str2), str2);
    object->PrintStr();
    std::cout << std::endl << std::endl;*/

    //      COPY CONSTRUCTOR         //
    /*std::cout << "COPY CONSTRUCTOR" << std::endl << std::endl;
    Strings* object1 = new Strings(*object);
    object1->PrintStr();
    std::cout << std::endl << std::endl;*/

    //      FIND SUBSTRING         //
    /*std::cout << "FIND SUBSTRING" << std::endl << std::endl;
    std::cout << "Found substring at " << object->FindStr("bob") << " index" << std::endl;
    std::cout << std::endl << std::endl;*/

    //      UNION STRINGS           //
    /*std::cout << "UNION STRINGS" << std::endl << std::endl;
    object->UnionStr(strlen(str1), str1);
    object->PrintStr();
    std::cout << std::endl << std::endl;*/

    //          PREFIX              //
    /*std::cout << "PREFIX" << std::endl << std::endl;
    ++(*object);
    object->PrintStr();
    --(*object);
    object->PrintStr();
    std::cout << std::endl << std::endl;*/

    //          POSTFIX             //
    //std::cout << "POSTFIX" << std::endl << std::endl;
    //(*object)++;
    //object->PrintStr();
    //(*object)--;
    //object->PrintStr();
    //std::cout << std::endl << std::endl;

    //          ASSIGMENT           //
    //std::cout << "ASSIGMENT" << std::endl << std::endl;
    //Strings* object2 = new Strings();
    //object2->PrintStr();
    //*object = *object2;
    //object->PrintStr();
    //std::cout << std::endl << std::endl;

    //          MINUS               //
    //const char* str3 = "Hello, world boba";
    //Strings* object3 = new Strings(strlen(str3), str3);
    //const char* str4 = "world";
    //Strings* object4 = new Strings(strlen(str4), str4);
    //std::cout << "- overloading:" << std::endl;
    //*object2 = *object3 - *object4;
    //std::cout << std::endl;
    //std::cout << std::endl;

    //          PLUS                //
    /*std::cout << "+ overloading" << std::endl;
    std::cout << std::endl;
    *object2 = *object3 + *object4;
    object2->PrintStr();
    std::cout << std::endl << std::endl;*/

    //          TYPE CAST           //
    /*std::cout << "TYPE CAST" << std::endl << std::endl; 
    const char* bob = *object;
    for (int i = 0; i < object->getSize(); i++) {
        std::cout << bob[i];
    }
    std::cout << std::endl << std::endl;*/

    //          OUT STRING FILE STREAM          //
    //std::ofstream ofs("text.txt", std::ios::out);					
    //if (!ofs) {
    //    std::cerr << "Error writing to text file." << std::endl;
    //    exit(1);
    //}
    //ofs << *object;
    //ofs.close();

    ////          OUT BINARY FILE STREAM          //
    //std::fstream ofsBinary("text.bin", std::ios::binary | std::ios::out);
    //if (!ofsBinary) {
    //    std::cerr << "Error writing to binary file." << std::endl;
    //    exit(1);
    //}
    //ofsBinary << *object;
    //ofsBinary.close();

    //const char* str2 = "Hello bob";
    //Strings* object5 = new Strings(strlen(str2), str2);

    ////          IN BINARY FILE STREAM          //
    //std::fstream ifsBinary("text.bin", std::ios::in | std::ios::binary);
    //if (!ifsBinary) {
    //    std::cerr << "Error reading to binary file." << std::endl;
    //    exit(1);
    //}
    //std::cout << "Reading form binary file: " << std::endl;
    //ifsBinary >> *object5;
    //object5->PrintStr();
    //ifsBinary.close();

    //CHILD STRING//
    //std::cout << "FIRST CHILD" << std::endl;
    const char* str3 = "Boba opa lala";
    Strings* opp;
    ChildString child = ChildString(strlen(str3), str3);
    child.DeleteSubstring("opa");
    opp = &child;
    //opp->PrintStr();
    std::cout << std::endl;
 
    //ANOTHER CHILD STRING//
    //std::cout << "SECOND CHILD" << std::endl;
    const char* str4 = "Opa opa Amerika Europa";
    Strings* bob;
    AnotherChildString anotherChild = AnotherChildString(strlen(str4), str4);
    anotherChild.CalculateSpace();
    bob = &anotherChild;
    //bob->PrintStr();
    std::cout << std::endl;
    

    List l;
    //std::cout << l.isEmpty() << std::endl;
    l.Push(object);
    l.Push(bob);
    l.Push(opp);
    std::cout << "List" << std::endl;
    //l.Print();
    std::cout << std::endl;
    //Strings* temp = l.Pop();
    //std::cout << "Pop" << std::endl;
    //temp->PrintStr();
    //std::cout << std::endl;
    //std::cout << std::endl;
    //std::cout << std::endl;
    //std::cout << "List" << std::endl;
    //l.Print();
    //std::cout << std::endl;
    //std::cout << std::endl;
    //std::cout << std::endl;
    //std::cout << "Find" << std::endl;
    //Strings* temp1 = l.Find("Hello world");
    //if (temp1 != nullptr) {
    //    temp1->PrintStr();
    //}
    const char* str5 = "Boba";
    Strings* newBob = new Strings(strlen(str5), str5);
    l.PushById(newBob, 0);
    l.Print();

    std::cout << "Pop by id" << std::endl << std::endl;
    Strings* nBob = l.PopById(1);
    l.Print();

    delete object;
    delete newBob;
    //delete object1;
    //delete object2;
    //delete object3;
    //delete object4;
    //delete object5;
    return 0;
}