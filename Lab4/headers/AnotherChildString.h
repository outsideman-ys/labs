#pragma once
#include "Strings.h"

class AnotherChildString : public Strings
{
	private:
		bool isSpaceCalculated;
		int* spaceArray;
		int spaceArraySize;
	public:
		void CalculateSpace();
		AnotherChildString();
		AnotherChildString(int, const char*);
		AnotherChildString(const AnotherChildString&);
		~AnotherChildString();
		void PrintStr() override;
};

