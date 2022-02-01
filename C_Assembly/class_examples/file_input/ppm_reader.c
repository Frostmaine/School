/* read-ppm-headers.c
 * Read a PPM/PGM/PBM image file into a struct.
 * Used by negate-pnm, laplacians-pgm, zero-crossings-pgm, etc.
 * 2018-06-12
 */
#include<stdio.h>
#include<stdlib.h>  // malloc(), abs(), exit()
#include<string.h>  // strlen()
#include<ctype.h>   // isspace()
#include<errno.h>

void retrieve_comment(char *comment_buffer, int cblength, FILE *src);

int main(int argc, char **argv)
{
    if (argc != 3) {
        fprintf(stderr, "usage: %s infile outfile\n", argv[0]);
        return 1;
    }

    // Open, read/process, and close image file; open, write, and close output file.
    FILE *infile = fopen(argv[1], "r");
    FILE *outfile = fopen(argv[2], "w");
    if (infile == NULL || outfile == NULL)
    {
      fprintf(stderr, "these files do no exist", argv[0]);
      return 1;
    }

    char magicnum[3];
    unsigned width, height, maxval = 0;
    char comment[4096];     // not needed at first...

    // Grab all the header fields at once.  Any comments will break this:
    /* fscanf(infile, " %2s %u %u %u", magicnum, &width, &height, &maxval); */
    fscanf(infile, " %2s", magicnum);
    // Grab as many comments as there may be:
    do {
        retrieve_comment(comment, 4096, infile);
        if (comment[0] == '#') {
            fprintf(outfile, "%s", comment);
            printf("comment: %s\n", comment);
        }
    } while (comment[0] != '\0');

    fscanf(infile, " %u", &width);
    do {
        retrieve_comment(comment, 4096, infile);
        if (comment[0] == '#') {
            fprintf(outfile, "%s", comment);
            printf("comment: %s\n", comment);
        }
    } while (comment[0] != '\0');

    fscanf(infile, " %u", &height);
    do {
        retrieve_comment(comment, 4096, infile);
        if (comment[0] == '#') {
            fprintf(outfile, "%s", comment);
            printf("comment: %s\n", comment);
        }
    } while (comment[0] != '\0');

    fscanf(infile, " %u", &maxval);



    // Copy header tokens to output file.  Also report token values to the console:
    fprintf(outfile, "%2s\n%u %u\n%u\n", magicnum, width, height, maxval);

      
    printf("magic number: %2s\nwidth x height: %u x %u\nmaximum: %u\n",
        magicnum, width, height, maxval); 

    fclose(infile);
    fclose(outfile);
    return 0;
}

void retrieve_comment(char *comment_buffer, int cblength, FILE *src)
{
      char c;
    comment_buffer[0] = '\0';   // assume no-comment

    while ( isspace( c = getc(src) ) ) {   // read & discard each whitespace
        ;   // null statement
    }
    // found a non-whitespace:
    ungetc(c, src); // put it back into the stream, to be processed

    if ('#' == c)   // found a comment!
        fgets(comment_buffer, cblength, src);   // including the "#"
    // else it's a non-comment, presumably the start of a token...

    return;

    /* comment_buffer[0] = '\0';   // assume no-comment */

    /* for ( ; ; ) {   // "infinite" loop */

    /*     char c = getc(src); */
    /*     if (isspace(c)) {   // discard this whitespace and continue */
    /*         continue; */

    /*     } else { */
    /*         ungetc(c, src); // put it back into the stream, to be processed */
    /*         if ('#' == c)   // found a comment! */
    /*             fgets(comment_buffer, cblength, src);   // including the "#" */

    /*         // found whatever non-whitespace there is, abandon the infinite loop */
    /*         return; */
    /*     } */
    /* } */
}
