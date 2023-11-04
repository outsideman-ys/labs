#include "headers/Strings.h"

#pragma once

#ifndef NODE_H
#define NODE_H

struct Node {
public:
    Strings* object;
    Node* next;
    int id;
    Node(Strings* _object, int _id) : object(_object), next(nullptr), id(_id) {}
};

#endif//NODE_H
