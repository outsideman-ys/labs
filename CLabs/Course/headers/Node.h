#pragma once

#include <string>
#include <iostream>

#ifndef NODE_H
#define NODE_H

struct Node {
public:
    std::string str;
    Node* next;
    int id;
    std::streampos pointer;
    Node(std::string _str, int _id, std::streampos _pointer) : str(_str), next(nullptr), id(_id), pointer(_pointer) {}
    
    void ChangeStr(std::string newStr);

};

#endif//NODE_H