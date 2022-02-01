/*
 Matthew Yackiel
 Assignment 2
 9-16-2021
 */

#include<stdio.h>
#include<math.h>

int main(int argc, char** argv)
{
  int min_temp, max_temp, min_wind, max_wind;
  
  printf("Min, Max Temperature: ");
  scanf("%d %d", &min_temp, &max_temp);

  printf("Min, Max Wind: ");
  scanf("%d %d", &min_wind, &max_wind);

  // Header
  printf("T \\ W\t");
  for (int i = min_wind; i <= max_wind; i+=5)
  {
    printf("%2d.0\t", i);;
  }

  printf("\n   \\\t");

  // Bar
  for (int i = min_wind; i <= max_wind; i+=5)
  {
    printf("%-8s", "-----");
  }
  printf("\n");
  
  float windchill;
  // Generate outputs, with windchill equation
  for (int i = min_temp; i <= max_temp; i+=2)
  {
    printf("%-2d |   ", i);
    for (int j = min_wind; j <= max_wind; j+=5)
    {
      windchill = 35.74;
      windchill += .6215 * i;
      windchill -= 35.75 * pow(j, .16);
      windchill += .4275 * i * pow(j, .16);
      printf("%-3.2f\t", windchill);
    }
    printf("\n");
  }
  
  return 0;
}
