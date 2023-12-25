#include "Menu.h"

void Menu::ShowCreateMenu() {
	isCreate = true;
	list = new List();
	list->ImportFromTxtFile();
	system("cls");
	std::cout << "Upload is complete!" << std::endl;
	ShowMenu();
}

void Menu::ShowPrintMenu() {
	list->PrintList();
	std::cout << "0 - back" << std::endl;
	std::cin >> state;
	switch (state)
	{
	case back:
		ShowMenu();
		break;
	default:
		system("cls");
		std::cout << "Bad input!, try again" << std::endl << std::endl;
		ShowPrintMenu();
		break;
	}
}

void Menu::ShowEditMenu() {
	std::cout << "Choose what string do you want to change? From 0 to " << list->getGlobalId() - 1
		<< " and -1 for back" << std::endl;
	int id;
	std::cin >> id;
	if ((id < 0 || id > list->getGlobalId()) && id != -1) 
	{
		system("cls");
		std::cout << "Bad ID!" << std::endl;
		ShowEditMenu();
	}
	else if (id == -1) 
	{
		system("cls");
		ShowMenu();
	}
	else {
		std::string temp;
		std::cout << "Input the string" << std::endl;
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
		std::getline(std::cin, temp);
		system("cls");
		list->ChangeNodeStr(id, temp);
		std::cout << "Operation is complete!" << std::endl << std::endl;
		ShowMenu();
	}
}

void Menu::ShowAddMenu() {
	std::cout << "Choose where do you want to add string From 0 to " << list->getGlobalId() - 1
		<< " or -2 for push string back and -1 for back" << std::endl;
	int id;
	std::cin >> id;
	if ((id < 0 || id > list->getGlobalId()) && id != -2) {
		system("cls");
		std::cout << "Bad ID!" << std::endl;
		ShowAddMenu();
	}
	else if (id == -1) {
		system("cls");
		ShowMenu();
	}
	else if (id >= 0 || id <= list->getGlobalId() || id == -2) {
		std::string temp;
		std::cout << "Input the string" << std::endl;
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
		std::getline(std::cin, temp);
		system("cls");
		if (id == -2) {
			list->PushBack(temp);
		}
		else {
			list->PushById(temp, id);
		}
		std::cout << "Operation is complete!" << std::endl << std::endl;
		ShowMenu();
	}
}

void Menu::ShowDeleteMenu() {
	std::cout << "Choose where do you want to delete string From 0 to " << list->getGlobalId() - 1
		<< " or -2 for delete string back and -1 for back" << std::endl;
	int id;
	std::cin >> id;
	if ((id < 0 || id > list->getGlobalId()) && id != -2 && id != -1) {
		system("cls");
		std::cout << "Bad ID!" << std::endl;
		ShowDeleteMenu();
	}
	else if (id == -1) {
		system("cls");
		ShowMenu();
	}
	else if (id >= 0 || id <= list->getGlobalId() || id == -2) {
		system("cls");
		if (id == -2) {
			list->PopBack();
		}
		else {
			list->PopById(id);
		}
		std::cout << "Operation is complete!" << std::endl << std::endl;
		ShowMenu();
	}
}

void Menu::ShowImportMenu() {
	binFile.WriteListToBin(list, "list.bin");
	system("cls");
	std::cout << "Operation is Complete!" << std::endl << std::endl;
	ShowMenu();
}

void Menu::ShowExportMenu() {
	list = binFile.WriteBinToList(list, "list.bin");
	system("cls");
	std::cout << "Operation is Complete!" << std::endl << std::endl;
	list->ExportToTxtFile();
	ShowMenu();
}

void Menu::ShowMenu() {
	std::cout << "What do you want?\n" <<
		"0 - exit\n1 - add your string\n" <<
		"2 - delete string\n3 - edit string\n" <<
		"4 - import list to bin\n5 - export list to txt\n" <<
		"6 - show list\n7 - create list from txt"<< std::endl;

	std::cin >> state;
	switch (state)
	{
	case quit:
		delete list;
		exit(0);
	case add:
		if (isCreate) {
			ShowAddMenu();
		}
		else {
			system("cls");
			std::cout << "List didn't created!" << std::endl << std::endl;
			ShowMenu();
		}
		break;
	case del:
		if (isCreate) {
			ShowDeleteMenu();
		}
		else {
			system("cls");
			std::cout << "List didn't created!" << std::endl << std::endl;
			ShowMenu();
		}
		break;
	case edit:
		if (isCreate) {
			ShowEditMenu();
		}
		else {
			system("cls");
			std::cout << "List didn't created!" << std::endl << std::endl;
			ShowMenu();
		}
		break;
	case 7:
		system("cls");
		if (!isCreate) {
			ShowCreateMenu();
		}
		else {
			system("cls");
			std::cout << "List was already created!" << std::endl << std::endl;
			ShowMenu();

		}
		break;
	case imprt:
		system("cls");
		if (isCreate) {
			isBinned = true;
			ShowImportMenu();
		}
		else {
			system("cls");
			std::cout << "List was didn't create!" << std::endl << std::endl;
			ShowMenu();
		}
		break;
	case exprt:
		if (isBinned) 
		{
			ShowExportMenu();
		}
		else {
			system("cls");
			std::cout << "List is not binned, export txt to bin first" << std::endl << std::endl;
			ShowMenu();
		}
		break;
	case show:
		if (isCreate) {
			system("cls");
			ShowPrintMenu();
			break;
		}
		else {
			system("cls");
			std::cout << "List was didn't create!" << std::endl << std::endl;
			ShowMenu();
		}
	default:
		system("cls");
		std::cout << "Bad input, try again" << std::endl << std::endl;
		ShowMenu();
		break;
	}
}
