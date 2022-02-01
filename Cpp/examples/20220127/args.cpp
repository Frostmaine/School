// simple input and output

#include <iostream>
using namespace std;

int main(int argc, char** argv)
{
    cout << "Arguments: ";
    cout << argv[0];
    if (argc > 1)
    {
        cout << " " << argv[1];
    } 
    cout << endl;
    return EXIT_SUCCESS; // returned to the OS
}
