#pragma once

#include <string>
#include <stdio.h>
#include "Node.h"
#include <iostream>
#include <fstream>
#include <string>
#include <io.h>
#include <sys/types.h> 
#include <fcntl.h> 
#include <stdlib.h>

#ifndef LIST_H
#define LIST_H

struct List {
private:
    Node* head;
    Node* last;
    int globalId;
public:
    int getGlobalId();
    Node* getHead();
    void setHead(Node* newNode);
    void setLast(Node* newNode);
    Node* getLast();
    List() : head(nullptr), last(nullptr), globalId(0) {}
    ~List();
    bool isEmpty();
    void PushBack(std::string str);
    void PushById(std::string str, int id);
    void PopBack();
    void PopById(int id);
    void ImportFromTxtFile();
    void ExportToTxtFile();
    void setGlobalId(int glblId);
    void PrintList();
    Node* FindNode(int id);
    void RecalculateId();
    void ChangeNodeStr(int id, std::string newStr);
};

#endif//LIST_H