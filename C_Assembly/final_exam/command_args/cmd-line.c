/*
  Matthew Yackiel
  CompSci 240
  Dc 7 2021
*/

#include <string.h> // for string operations
#include <stdlib.h> // malloc
#include <stdio.h> // standard io

int main(int argc, char **argv)
{

  char buff[256];

  if (argc < 2) // not enough args
  {
    printf("Provide atleast 1 command line args");
    return 0;
  }
  else if (argc == 2)
  {
    printf("The longest cmd-line argument is %s", argv[1]);
    return 0;
  }
  
    // solution
  strcpy(buff, argv[1]);
  for (int i = 2; i < argc; i++)
  {
    if (strlen(buff) < strlen(argv[i]))
      strcpy(buff, argv[i]);
  }

  printf("The longest cmd-line argument is %s", buff);
}
