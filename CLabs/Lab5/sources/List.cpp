#include "C:/Users/ysmir/source/repos/ConsoleApplication1/headers/List.h"

bool List::isEmpty() {
    return head == nullptr;
}

void List::Push(Strings* object) {
    if (isEmpty()) {
        globalId = 0;
        Node* node = new Node(object, globalId);
        globalId++;
        return;
    }
    Node* node = new Node(object, globalId);
    globalId++;
    node->next = head;
    head = node;
}

Strings* List::Pop() {
    Strings* temp = head->object;
    Node* pv = head;
    head = head->next;
    delete pv;
    return temp;
}

void List::Print() {
    if (isEmpty()) return;
    Node* p = head;
    while (p) {
        p->object->PrintStr();
        std::cout << "Id is " << p->id << std::endl << std::endl;
        p = p->next;
    }
    std::cout << std::endl;
}

Strings* List::Find(const char* str) {
    if (isEmpty()) return nullptr;
    Node* p = head;
    while (p) {
        if (!strcmp(str, p->object->getCharArray())) {
            return p->object;
        }
        p = p->next;
    }
    return nullptr;
}

void List::PushById(Strings* object, int id) {
    List temp;
    for (int i = globalId - 1; i >= id; i--) {
        temp.Push(head->object);
        head = head->next;
    }
    Node* node = new Node(object, id);
    head = node;
    globalId = id + 1;
    while (!temp.isEmpty()) {
        Push(temp.Pop());
    }
}

Strings* List::PopById(int id) {
    List temp;
    for (int i = globalId - 1; i > id; i--) {
        temp.Push(head->object);
        head = head->next;
    }
    Strings* obj = Pop();
    globalId = id;
    while (!temp.isEmpty()) {
        Push(temp.Pop());
    }
    return obj;
}

List::~List() {
    Node* temp;
    while (head) {
        temp = head;
        head = head->next;
        delete temp;
    }
}