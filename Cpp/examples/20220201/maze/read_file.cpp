#include <vector>
#include <fstream>
#include <istream>
#include <sstream>

typedef std::vector<int> intvec;
typedef std::vector<intvec> rowvec;

rowvec& read_file(std::istream& input) {
    std::string rowstring;
    rowvec* grid = new rowvec;

    // loop each line
    while (getline(input, rowstring)) {
	intvec* row = new intvec;
	std::stringstream rowstream(rowstring);
	std::string cellstring;

	// loop through each line
        while (true) {
	    rowstream >> cellstring;
	    if (rowstream.eof())
		break;
	    unsigned cell = std::stoi(cellstring, nullptr, 2);
	    row->push_back(cell);
        }
	grid -> push_back(*row);
    }
    return *grid;
}
