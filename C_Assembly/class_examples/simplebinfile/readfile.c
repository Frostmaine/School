/*
| Open and read the binary file that was written by "writeseq.c".
| Read and print each character individually;
| read the unsigned integers as an entire block into an array,
| then print them out.
|   2020-09-15
*/
#include <stdio.h>

int main(int argc, char **argv)
{
    char *filename;

    if (argc ==2) // unsure a file name is passed
        filename = argv[1];
    else {
        printf("usage: %s <filename>\n", argv[0]);
        return 0;
    }

    FILE *h = fopen(filename, "rb"); // open the file

    printf("#--------------------------------\n");

    signed char c;
	 
    for (int i = 0; i < 256; i++) { // read the data as signed chars
        fread(&c, sizeof(signed char), 1, h);
        printf("%#04x  %5d  %c\n", (unsigned char)c, (signed char)c, (unsigned char)c);
    }
    printf("#--------------------------------\n");

    unsigned u[256];
    fread(u, sizeof(unsigned), 256, h);// read the data as unsigned ints

    for (int i = 0; i < 256; i++) {
        printf("%#010x  %12d  %12u\n", (unsigned)u[i], (int)u[i], (unsigned)u[i]);
    }
    printf("#--------------------------------\n");

    fclose(h);

    return 0;
}
