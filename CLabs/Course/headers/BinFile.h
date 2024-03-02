#pragma once

#include <fstream>

#include "List.h"

class BinFile
{
private:
	//std::ofstream ofsBin;
public:

	void WriteListToBin(List* list, const std::string& filename);
	List* WriteBinToList(List* list, const std::string& filename);
	/*friend std::fstream& operator<< (std::fstream& ofsBin, List& list);
	friend std::fstream& operator>> (std::fstream& ifsBin, List& list);*/
};

