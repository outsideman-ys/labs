#pragma once

#include "Strings.h"
#include "Node.h"
#include <iostream>

#ifndef LIST_H
#define LIST_H

struct List {
private:
    Node* head;
    int globalId;
public:
    List() : head(nullptr), globalId(0) {}
    bool isEmpty();
    void Push(Strings* object);
    Strings* Pop();
    void Print();
    Strings* Find(const char* str);
    void PushById(Strings* object, int id);
    Strings* PopById(int id);

};

#endif//LIST_H