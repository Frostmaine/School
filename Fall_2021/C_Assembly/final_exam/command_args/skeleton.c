
/*
  Skeleton code for any problem involving command line args
*/
#include <stdio.h> // file input and such
#include <stdlib.h> // atoi and atof
#include <string.h> // for string operations

int main(int argc, char **argv)
{
  char *filename;

  // below illustrates command line args, if you need to read a numerical
  // value use atoi() for ints and atof() for floats

  if (argc >= 2) // unsure a file name is passed
    filename = argv[1];
  else {
    printf("usage: %s <filename>\n", argv[0]);
    return 0;
  }

  FILE *in = fopen(filename, "rb");  // declare input file
  FILE *out = fopen("filename here", "wb"); // declare output file b is for binary file( ommit for plain text )

  // to read data of a binary file we use fread
  fread (<an array of primatives/structs, sizeof(primitive/struct), how many elements are in the array, the file being read from);

  // to write a binary file use the below
  fwrite(<an array of primitives/structs, sizeof(primitive/struct), how many elements are in the array, the file being written to); // write the entire array at once
  // one value at a time
  for ( int i = 0; i < array size; i++)
    fwrite(&i, sizeof(int), 1, h);
  
  
  // reading a text file line by line invloves looping as such
  char read[256];
  while (fgets(read, 256, in) != NULL)
  {
    // then use sscanf to break up lines into different records
    sscanf(read, "format string here", arguments to store value in primitives denoted by &);
    // process data then go to iteration
  }

  switch(expression) { // just in case i need one

   case constant-expression  :
      statement(s);
      break; /* optional */
	
   case constant-expression  :
      statement(s);
      break; /* optional */
  
   /* you can have any number of case statements */
   default : /* Optional */
   statement(s);
}

  fclose(in); // ensure files are closed when done with data processing
  fclose(out); 
  
}
