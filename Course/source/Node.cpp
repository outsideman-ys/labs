#include "Node.h"

void Node::ChangeStr(std::string newStr) {
	str = newStr;
	str += '\n';
}