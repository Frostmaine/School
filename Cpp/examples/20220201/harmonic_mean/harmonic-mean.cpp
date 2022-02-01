// harmonic-mean
//     get an array of values, calculate and display
//     their harmonic mean

#include <iostream>
#include <vector>
#include "harmean.h"

using namespace std;

int main(int argc, char **argv)
{
  vector<double> values;
  do {
    double tmp;
    cin >> tmp; // need a vector class, basically a variable length array
    if (cin.eof()) // scan to end of file
      break;
    values.push_back( tmp );
  } while(true);

  cout << values.size() << endl;
  double hm = harmonic_mean(values.data(), values.size());
  cout << hm << endl;
  return EXIT_SUCCESS;
}
