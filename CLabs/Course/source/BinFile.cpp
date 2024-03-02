#include "BinFile.h"

void BinFile::WriteListToBin(List* list, const std::string& filename) {
	std::ofstream outputFile("text.dat", std::ios::binary);
	if (!outputFile.is_open()) {
		std::cerr << "Unable to open the file for writing." << std::endl;
		return;
	}
	Node* temp = list->getHead();
	int size;
	while (temp) 
	{
		size = temp->str.size();
		outputFile.write((char*)&size, sizeof(size));
		outputFile.write(temp->str.c_str(), temp->str.size() + 1);
		outputFile.write(reinterpret_cast<char*>(&temp->pointer), sizeof(std::streampos));
		temp = temp->next;
	}
	outputFile.close();
}

List* BinFile::WriteBinToList(List* list, const std::string& filename) {
	int tempId = list->getGlobalId();
	if (!list->isEmpty()) {
		delete list;
	}
	List* newList = new List();
	std::ifstream importFile("text.dat", std::ios::binary);
	if (!importFile) {
		std::cerr << "Can't open file!" << std::endl;
		exit(1);
	}
	int globalId = 0;
	std::streampos pointer;
	newList->setGlobalId(globalId);

	int size;
	importFile.read((char*)&size, sizeof(int));
	char* temp = new char[size + 1];
	importFile.read(temp, size+1);
	std::string tempStr = std::string(temp);

	importFile.read(reinterpret_cast<char*>(&pointer), sizeof(std::streampos));
	Node* headNode = new Node(tempStr, newList->getGlobalId(), pointer);
	newList->setHead(headNode); newList->setLast(headNode);
	delete[] temp;

	for (int i = 0; i < tempId; i++) {
		globalId++;
		newList->setGlobalId(globalId);
		importFile.read((char*)&size, sizeof(int));
		temp = new char[size + 1];
		importFile.read(temp, size+1);
		importFile.read(reinterpret_cast<char*>(&pointer), sizeof(std::streampos));
		tempStr = std::string(temp);
		Node* node = new Node(tempStr, newList->getGlobalId(), pointer);
		newList->getLast()->next = node;
		newList->setLast(node);
		delete[] temp;
	}
	importFile.close();
	return newList;
}