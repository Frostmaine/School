/*
| Simple program to demonstrate
| writing of binary data.
| A. write an array of characters
| B. write unsigned values, one at a time.
|   2020-09-15
*/
#include <stdio.h>

int main(int argc, char **argv)
{
    char *filename;
    char c[256];

    // Obtain a file name:
    if (argc ==2)
        filename = argv[1];
    else
        filename = "fakedata.out";

    FILE *h = fopen(filename, "wb"); // file pointer

    // Build an array of characters:
    for (int i = 0; i < 256; i++) // fill the array with character values
        c[i] = (char)i;

    // Write the whole array at once:
    fwrite(c, sizeof(char), 256, h);

    // Write more values, one at time:
    for (int i = -128; i < 128; i++)
        fwrite(&i, sizeof(int), 1, h);

    fclose(h);

    return 0;
}
