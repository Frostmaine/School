#include <iostream>
#include "longest.h"
using namespace std;

int main(int argc, char** argv)
{
	cout << "Please enter 3 words: ";
	string sA, sB, sC;
	cin >> sA >> sB >> sC;
	
	cout << "Longest word is " << longest(sA, sB, sC);
	cout << endl;

	return EXIT_SUCCESS;
}

