/**
   Matthew Yackiel
   February 5, 2022
   Ballistic Trajectory Program
 **/

#include <cmath>
#include <iostream>
#include <random>
#include <iomanip>

using namespace std;

/**
   pre-condition: 2 values, the first being less than the second both greater than 0
   post-condition: a random vlaue between the 2 inputs
 **/
double random_range(double first, double second) {
    srand(time(0));
    return first + (double) (rand()) / ((double)(RAND_MAX/(second - first)));
}

/**
   pre-condition: a double value representing an angle in degrees
   post-condition: a double value representing an angle in radians
**/
double degreesToRadians(double degrees) { return degrees * 2 * M_PI / 360; }

/**
   pre-condition:  velocity >= 0, angle between 0 and 90 degrees inclusive
   post-condition: returns the distance from the firing position
                   that the projectile lands
 **/
double range(double velocity, double angle) {
  double radians = degreesToRadians(angle);
  return velocity * velocity * sin(2 * radians) / 9.8;
}

/**
   pre-condition: the muzzle velocity of the cannon, the angle of the previous player shot
   post-condition: the firing angle of the npc
 **/
double npc_angle(double degrees) {
    return random_range(.98*degrees, 1.02 * degrees); 
}


/**
   pre-condition: continuous user input of firing angles
   post-condition: a game where you adjust the angle of the cannon to try and hit a pregenerated target
 **/
int main(int argc, char **argv) {
    // Generate a random muzzle velocity for the game
  double velocity = random_range(600, 1000);
  const int KILL_RADIUS = 100;

  // determine the NPC location
  double enemy_position = random_range(0, range(velocity, 45));

  double playerAngle;
  double projectileLocation;
  double missDistance;

  // set up the cout double precision
  cout << fixed << showpoint;
  cout << setprecision(1);
  
  while (true) {
    cout << "Firing angle (degrees)? ";
    cin >> playerAngle; // get the player angle

    // generate the projectile landing spot
    projectileLocation = range(velocity, playerAngle);

    // calculate miss distance
    missDistance = abs(projectileLocation - enemy_position);

    // output message
    cout << "You:  " << projectileLocation << ", missed by " << missDistance << " meters --- ";

    // check if the enemy is hit
    if (missDistance < KILL_RADIUS) {
      cout << "Hit! You win!" << endl;
      break;
    } else if (projectileLocation > enemy_position)
	cout << "Overshot" << endl;
    else
	cout << "Undershot" << endl;

    // enemy takes a shot
    playerAngle = npc_angle(playerAngle);
    missDistance = abs(projectileLocation - enemy_position);
    projectileLocation = range(velocity, playerAngle);
    
    cout << "NPC:  " << projectileLocation << ", missed by " << missDistance << " meters --- ";
    if (missDistance < KILL_RADIUS) {
	cout << "Hit! You lose!" << endl;
	break;
    } else if (projectileLocation > enemy_position)
	cout << "Overshot" << endl;
    else
	cout << "Overshot" << endl;
    cout << endl;
  }
  return EXIT_SUCCESS;
}
