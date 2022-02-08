// Check the maze datafile

#include <bitset>
#include <iomanip>
#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>

typedef std::vector<int> intvec;
typedef std::vector<intvec> rowvec;

rowvec& read_file(std::istream&);
std::string& draw_grid(const rowvec&);

int main(int argc, char **argv) {
  // retrieve the filename from cmd line or prompt
  std::string filename;
  if (argc == 2) // auto detect command line
    filename = argv[1];
  else {
    std::cout << "Please input a file name: ";
    std::cin >> filename;
  }

  // open the file
  std::ifstream input;
  input.open(filename);
  if (!input.is_open()) {
      std::cerr << "INVALID FILENAME" << std::endl;
      return 1;
  }

  // Read the file and output the grid
  rowvec grid = read_file(input);
  std::cout << draw_grid(grid);

  return EXIT_SUCCESS;
}
