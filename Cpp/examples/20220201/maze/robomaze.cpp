// robomaze.cpp
#include <iostream>
#include "robomaze.h"

using namespace project1;
int main(int argc, char** argv)
{
  robomaze maze;
  std::string picture = maze.picture();
  std::cout << picture << endl;
}
