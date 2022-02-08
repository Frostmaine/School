#include <string>
#include <vector>

typedef std::vector<int> intvec;
typedef std::vector<intvec> rowvec;

std::string& build_south_wall(const intvec);

std::string &draw_grid(const rowvec &grid) {
  std::string *gridstring = new std::string;
  *gridstring = "";
  unsigned height = grid.size();

  // loop through the grid
  for (unsigned h = 0; h < height-1; ++h) {
    intvec row = grid.data()[h];
    // east wall
    for (unsigned w = 0; w < row.size()-1; ++w) {
      unsigned cell = row.data()[w];
      std::string eastwall = (cell & 0b0100) == 0b0100 ? "|" : " ";
      std::string cellbody = "   "; // 3 spaces
      *gridstring += cellbody + eastwall;
    }
    *gridstring += "\n";

    // south wall
    *gridstring += build_south_wall(row);
  }
  return *gridstring;
}

std::string& build_south_wall(const intvec row) {
  std::string *wallstring = new std::string;
  *wallstring = "";
  for (unsigned w = 0; w < row.size()-1; ++w) {
    unsigned cell = row.data()[w];
    std::string southwall = (cell & 0b0010) == 0b0010 ? "---" : "   ";
    *wallstring += southwall + "+";
  }
  *wallstring += "\n";
  return *wallstring;
}
