#include "List.h"

Node* List::getHead() {
	return head;
}

void List::PopBack() {
	if (isEmpty()) return;
	Node* temp = head;
	while (temp->next != last) {
		temp = temp->next;
	}
	temp->next = nullptr;
	delete last;
	last = temp;
	globalId--;
}

void List::PopById(int id) {
	Node* temp = head;
	Node* anotherTemp;
	while (temp->next->id != id) {
		temp = temp->next;
	}
	anotherTemp = temp->next;
	temp->next = anotherTemp->next;
	delete anotherTemp;
	std::ofstream importFile("text.txt", std::ios::in | std::ios::trunc);
	std::streampos pointer;
	importFile.seekp(0, std::ios::beg);
	importFile.clear();
	temp = head;
	while (temp) {
		importFile << temp->str;
		pointer = importFile.tellp();
		temp->pointer = pointer;
		temp = temp->next;
	}
	RecalculateId();
}

void List::RecalculateId() {
	int id = 0;
	Node* temp = head;
	while (temp) {
		temp->id = id;
		temp = temp->next;
		id++;
	}
	globalId = id-1;
}

void List::PushBack(std::string str) {
	std::ofstream importFile("text.txt", std::ios::in);
	std::streampos pointer;
	str += '\n';
	importFile.seekp(0, std::ios::end);
	importFile << str;
	pointer = importFile.tellp();
	importFile.close();
	globalId++;
	Node* newNode = new Node(str, globalId, pointer);
	last->next = newNode;
	last = newNode;
}

void List::PushById(std::string str, int id) {
	Node* temp = FindNode(id - 1);
	std::ofstream importFile("text.txt", std::ios::in);
	std::streampos pointer;
	str += '\n';
	importFile.seekp(temp->pointer, std::ios::beg);
	importFile << str;
	pointer = importFile.tellp();
	Node* old = temp->next;
	std::streampos newPointer;
	while (old->id < globalId)
	{
		importFile << old->str;
		newPointer = importFile.tellp();
		old->pointer = newPointer;
		old = old->next;

	}
	importFile.close();
	Node* newNode = new Node(str, id, pointer);
	Node* nextTemp = temp->next;
	temp->next = newNode;
	newNode->next = nextTemp;
	RecalculateId();
}

Node* List::FindNode(int id) {
	Node* temp = head;
	for (int i = 0; i < id; i++) 
	{
		temp = temp->next;
	}
	return temp;
}

void List::ChangeNodeStr(int id, std::string newStr) {
	Node* node = FindNode(id);
	node->ChangeStr(newStr);
	std::ofstream importFile("text.txt", std::ios::in | std::ios::trunc);
	std::streampos pointer;
	importFile.seekp(0, std::ios::beg);
	importFile.clear();
	Node* temp = head;
	while (temp) {
		importFile << temp->str;
		pointer = importFile.tellp();
		temp->pointer = pointer;
		temp = temp->next;
	}
}

bool List::isEmpty() {
	return head == nullptr;
}

int List::getGlobalId() {
	return globalId;
}

void List::ExportToTxtFile() {
	std::ofstream exportFile("out.txt", std::ios::in);
	if (!exportFile) 
	{
		std::cout << "Can't open file!" << std::endl;
		exit(1);
	}
	Node* node = head;
	while (node != NULL) {
		exportFile << node->str;
		exportFile.seekp(node->pointer, std::ios_base::beg);
		node = node->next;
	}
	exportFile.close();
}

void List::setGlobalId(int glblId) {
	globalId = glblId;
}

void List::setHead(Node* newNode) {
	head = newNode;
}

void List::setLast(Node* newNode) {
	last = newNode;
}

void List::ImportFromTxtFile() {
	std::ifstream importFile("text.txt", std::ios::out);
	if (!importFile) {
		std::cout << "Can't open file!" << std::endl;
		exit(1);
	}
	std::string temp;
	std::streampos pointer;
	if (isEmpty()) {
		globalId = 0;
		
		if (std::getline(importFile, temp)) {
			temp += '\n';
			pointer = importFile.tellg();
			Node* headNode = new Node(temp, globalId, pointer);
			head = last = headNode;
		}
		else {
			std::cout << "Empty file, fill it!" << std::endl;
			exit(1);
		}
	}
	while (std::getline(importFile, temp)) {
		temp += '\n';
		globalId++;
		pointer = importFile.tellg();
		Node* node = new Node(temp, globalId, pointer);
		last->next = node;
		last = node;
	}
	importFile.close();
}

void List::PrintList() {
	if (isEmpty()) return;
	Node* temp = head;
	while (temp) {
		std::cout << temp->id << " " << temp->str;
		temp = temp->next;
	}
}

Node* List::getLast() {
	return last;
}

List::~List() {
	Node* temp = head;
	while (temp) {
		head = head->next;
		delete temp;
		temp = head;
	}
}
