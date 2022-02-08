// robomaze.cpp
#include "robomaze.h"
#include <iostream>

using namespace project1;
int main(int argc, char **argv) {
  robomaze maze;
  std::string picture = maze.picture();
  std::cout << picture << std::endl;
}
