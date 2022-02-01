// harmonic-mean
//     get an array of values, calculate and display
//     their harmonic mean

#include <iostream>
#include "harmean.h"

using namespace std;

int main(int argc, char **argv)
{
  int size = atoi(argv[1]);
  double data[size];
  do {
    cin >> data[i]; // need a vector class
    if (cin.eof()) // scan to end of file
      break;
  } while(true);

  double hm = harmonic_mean(data, size);
  cout << hm << endl;
  return EXIT_SUCCESS;
}
