/*
  Matthew Yackiel
  Compsci 240
  Dec 7 2021
*/

#include <stdio.h> // file input and such
#include <stdlib.h> // atoi and atof
#include <string.h> // for string operations



void flip_data(float *data, int size, float *out)
{
  // flip data
  int j = 0;
  for (int i = size - 1; i > 0; i--)
  {
    out[j] = data[i];
    j++;
  }
}


int main(int argc, char **argv)
{
  printf("Please input a file name");


  char filename[256];
  char buff[256];
  float data[1000];
  
  fgets(filename, 256, stdin);

  FILE *in = fopen(filename, "r");

  int index = 0;
  // read the file into data
  while ((fgets(buff, 256, in) != NULL))
    {
      data[index++] = atoi(buff);
    }
  float flipped[1000];
  flip_data(data, index, flipped);

  for (int i = 0; i < index; i++)
  {
    printf("%f \n", flipped[i]);
  }

  return 0;
}
