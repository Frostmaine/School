
#include <stdio.h>
#include <math.h> // sin and cos
#include <stdlib.h> // atoi() and atof()

#define MINRADIANS 0.0
#define NUMSTEPS 60

double trigfunct(double x);

void drawplot(double *s, int n, int w);

int main(int argc, char **argv)
{
  if (argc != 3) // ensure proper user input
  {
    printf("usage: %s <maxradians> <maxcolumns>\n", argv[0]);
    return 1;
  }

  double maxradians = atof(argv[1]);
  int width = atoi(argv[2]);

  double fvalues[ NUMSTEPS ];
  double stepsize = (maxradians - MINRADIANS) / NUMSTEPS;

  int i;
  double x;
  for (i = 0, x = MINRADIANS; i < NUMSTEPS; i++, x += stepsize)
  {
    fvalues[i] = trigfunct(x);
  }

  drawplot(fvalues, NUMSTEPS, width);
  return 0;
}

double trigfunct(double x)
{
  double beats = 4.5;
  return sin(x) * cos(beats * x); // or some other interesting function
}
