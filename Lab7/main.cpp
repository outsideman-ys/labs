#include <iostream>
#include "Stack.h"

int main()
{
    Stack<int> s;
    int numb1 = 2;
    int numb2 = 9;
    int numb3 = 5;
    int numb4 = -4;
    s.push(&numb1);
    s.push(&numb2);
    s.push(&numb3);
    s.push(&numb4);
    s.print();

    Stack<char> s1;
    char chr1 = 'a';
    char chr2 = 'g';
    char chr3 = 'p';
    char chr4 = 'r';
    s1.push(&chr1);
    s1.push(&chr2);
    s1.push(&chr3);
    s1.push(&chr4);
    s1.print();
    
    return 0;
}


