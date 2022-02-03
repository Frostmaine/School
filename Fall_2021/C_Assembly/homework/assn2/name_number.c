/*
  Matthew Yackiel
  Assignment 2
  9-13-2021
 */

#include<stdio.h>
#include<string.h>

int main(int argc, char **argv)
{
  printf("Enter your name: ");
  char* name;
  scanf("%s", name);

  // Get some numbers
  printf("Enter an integer, followed by a real number: ");
  int x;
  float y;
  scanf("%d %f", &x, &y);

  printf("name: %s; integer: %d; real: %.2f;\n", name, x, y);
}
