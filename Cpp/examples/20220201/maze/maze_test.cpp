// Test file for maze data
// 2022-02-2022

#include <iostream>
#include <iomanip> // setw
#include <fstream>
#include <vector>
#include <sstream>


typedef std::vector<int> intvec;

using namespace std;

int main(int argc, char** argv)
{
  cout << "data file name?";
  string dtafile;
  cin >> datafile;

  if (!input.is_open()) // check for bad files
  {
    cerr << "Bad datafile";
    return 1;
  }

  ifstream input;
  input.open( datafile );

  vector<intvec> grid;

  do
  {
    string rowstr;
    intvec row;
    getline(input, rowstr);
    if (input.eof()) // end of file reached
      break;
    
    stringstream<rowstr> rowstream;
    do
    { // parse the row into ineger values
      int cell;
      rowstream >> cell;
      if (rowstream.eof())
	break;
      row.push_back(cell);
    } while (true);
    grid.push_back(row);
  } while (true);

  input.close(); // done reading file

  //process information

  for (int y = 0; y < grid.size(); ++y)
  {
    for (int x = 0; x < grid.data()[y]; ++x)
    {
      cout << setw(4) << grid.data()[y][x] << ' ';
    }
    cout << endl;
  }
  
  return EXIT_SUCCESS;
  
}
