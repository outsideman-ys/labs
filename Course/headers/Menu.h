#pragma once

#define NOMINMAX

#include <iostream>
#include <limits>
#include <Windows.h>
#include "List.h"
#include "BinFile.h"

#ifndef MENU_H
#define MENU_H

class Menu
{
private:
	enum MainMenuStates
	{
		quit = 0, add = 1,
		del = 2, edit = 3,
		imprt = 4, exprt = 5,
		show = 6
	};
	enum AddtionalMenuStates
	{
		back = 0,
		byId = 1,
		byLast = 2
	};
	List* list;
	BinFile binFile;
	bool isCreate;
	int state;
	bool isBinned;
public:
	void ShowMenu();
	void ShowCreateMenu();
	void ShowPrintMenu();
	void ShowImportMenu();
	void ShowExportMenu();
	void ShowDeleteMenu();
	void ShowAddMenu();
	void ShowEditMenu();
	Menu() : state(-1), isCreate(false), isBinned(false), list(nullptr) {}
};

#endif
