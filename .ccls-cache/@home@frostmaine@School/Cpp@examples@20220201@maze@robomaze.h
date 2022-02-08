/**
   declaration for a robot class that can
   be steered through a amze
 class pre-conditions:
   a valid maze has been created, and the robot
   can use the maze to "understand" where it is
 class post-conditions:
   robot will have found its way to the lower right
 value semantics:
   robot can be copied or assigned
public methods:
get_x()
  pre: robot is on a valid cell of the maze
  post: current x-coordinate is returned
get_y()
  pre: reobot is on a valid cell of the maze
  post: current y-coordinate is returned
get_direction()
  pre: robot is facing in a valid direction
  post: current direction is returned
is_blocked()
  pre:robot is on a valid cell of the maze
     rovot is facing in a valid direction
  post: returns the absence or presence of a wall in front
picture()
  pre: a valid maze has been created
  post: returns a std::string object that prints as a 
        representation of the maze
	 (Robot's position is not shown)

step_forward()
  pre: robot is on a valid cell of the maze
       robot is facing in a valid direction
  post: robot's position is changed by one cell
        in the 'forward' direction
turn_left()
  pre: robot is on a valid cell of the maze
       robot is facing in a valid direction
  post: robot is facing a new direction that is
   90 degrees CCW from previous direction
set_maze()
  pre: a robot has been created (with or without a maze)
  post: the robot has a new maze to explore

2022-02-03
 */
//macro guard
#ifndef _ROBOMAZE_
#define _ROBOMAZE_

#include <string>
#include <vector>

namespace project1 {
typedef std::vector<int> intvec;

class robomaze {
  
public:
  //Constructors
  inline robomaze() { x = 1; y = 1; direction = "north"; }
  inline robomaze(std::string mazefile) { set_maze(mazefile); }

  //info methods
  inline int get_x(void) const { return x; }
  inline int get_y(void) const { return y; }
  inline std::string get_direction(void) const { return direction; }
  bool is_blocked(void) const;
  std::string picture(void) const;

  //Modification methods
  void step_forward(void);
  void turn_left(void);

  void set_maze(std::string mazefile);
  
private:
  int x, y;
  std::string direction;
  std::vector<intvec> grid;
};
}
#endif
