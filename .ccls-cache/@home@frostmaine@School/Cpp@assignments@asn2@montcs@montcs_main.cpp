#include<iostream>
#include<sstream>
#include<string>

#include "complex.h"

using namespace std;
using namespace complex_number;

bool is_number(const string);

int main(int argc, char **argv) {

    size_t stack_size, stack_top = 4;
    /*
    if (argc == 2) // cmd line args
	stack_size = stoi(argv[1]);
    else
	stack_size = 4; //  default stack size
    */
    stack_top = stack_size; // initially empty stack

    complex stack[stack_size];

    bool more = true;
    while (more) {
	cout << "Enter? ";
	string entry;
	cin >> entry;
        if (is_number(entry)) { // check input
	    double real = stod(entry);
	    cin >> entry;
	    double imag = stod(entry);
	    stack[--stack_top] = complex(real, imag);
        } else if (entry == "q")
	    more = false;
	cout << stack[stack_top];
    }

    return EXIT_SUCCESS;
}

bool is_number(const string e) {
    for (size_t i = 0; i < e.size(); ++i) {
	char c = e.data()[i];
	if (!isdigit(c) && c != '.' && c != '-')
	    return false;
    }
    return true;
}
