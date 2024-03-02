#pragma once
#include "D:\Labs C++\labs\Lab4\headers\Strings.h"

class ChildString : public Strings
{
	private:
		char* firstWord;
		int firstWordSize;
	public:
		void DeleteSubstring(const char* subString);
		void PrintStr() override;
		ChildString();
		ChildString(int, const char*);
		ChildString(const ChildString&);
		~ChildString();
};

