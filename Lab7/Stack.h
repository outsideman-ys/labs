#pragma once

#include <corecrt_memory.h>
#include <iostream>

#ifndef STACK_H
#define STACK_H
template <typename T>
class Stack {
private:
	int pointer;
	int capacity;
	T** stack;
	const int capacityMultiplier = 2;
public:

	void push(T* object) {
		if (isEmpty()) {
			stack = new T * [capacity];
			stack[pointer] = object;
			return;
		}
		pointer++;
		if (pointer >= capacity) {
			stack = resize();
		}
		stack[pointer] = object;
	}

	T* pop() {
		if (isEmpty()) {
			return nullptr;
		}
		T* object = stack[pointer];
		stack[pointer] = nullptr;
		pointer--;
		return object;
	}

	T** resize() {
		T** temp = new T * [capacity * capacityMultiplier];
		memcpy(temp, stack, pointer * sizeof(stack));
		capacity = capacity * capacityMultiplier;
		return temp;
	}

	bool isEmpty() {
		return stack == nullptr;
	}

	void print() {
		for (int i = pointer; i >= 0; i--) {
			std::cout << *stack[i] << std::endl;
		}
		std::cout << std::endl;
	}

	Stack() : pointer(0), capacity(1), stack(nullptr) {}
};

#endif