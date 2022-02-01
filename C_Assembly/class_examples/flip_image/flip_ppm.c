/* image reader
* 2021-10-06
*/
#include<stdio.h>
#include<ctype.h>   // isspace()
#include<stdlib.h>  // malloc()
#include<string.h>      // strncpy()

struct Image {
    unsigned char id[3];
    unsigned width, height;
    unsigned maxval;    // could be unsigned short...
    unsigned char *pixels;
} ;

unsigned remove_comment(FILE *h)
{
    unsigned char byte;
    unsigned count = 0;
    while ( isspace( byte = fgetc(h) ) ) {
        count++ ;
    }
    ungetc(byte, h);
    if (byte == '#') {
        while ( '\n' != (byte = fgetc(h)) ) {
            count++ ;
        }
    }
    return count;
}

unsigned idx2(unsigned row, unsigned col, unsigned bpp, struct Image *img)
{
    return (bpp * (row * img->width + col));
}

void flip(struct Image *flipped, struct Image original)
{
    strncpy((char *)(flipped->id), (char *)original.id, 3);    // include the terminal NUL
    flipped->width = original.width;    // copy width into memory pointed to by "flipped"
    flipped->height = original.height;
    flipped->maxval = original.maxval;
    unsigned bytes_per_pixel = ( (original.maxval <= 255)  ?  3  :  6 );
    flipped->pixels = malloc( flipped->width * flipped->height * bytes_per_pixel );

    for (unsigned y = 0; y < original.height; y++) {    // each row...
        for (unsigned x = 0; x < original.width; x++) { // each column of each row...
           for (unsigned rgb = 0; rgb < 3; rgb++) {

                flipped->pixels[ idx2(y, (flipped->width - x), bytes_per_pixel, flipped) + rgb ] =
                    original.pixels[ idx2(y, x, bytes_per_pixel, &original) + rgb ];
            }
        }
    }
}


int main(int argc, char **argv)
{
    struct Image the_picture;
    struct Image flipped_picture;       // for the new version of the picture

    if (argc != 3) {
        fprintf(stderr, "usage: %s  <input-file> <output-file>\n", argv[0]);
        return 1;
    }

    FILE *inhandle = fopen(argv[1], "r");
    unsigned comment_length;

    // get the "magic number":
    fscanf(inhandle, "%2s", the_picture.id);
    the_picture.id[2] = '\0';
        while ( (comment_length = remove_comment(inhandle)) > 0 ) {
        printf("%u comment bytes found\n", comment_length);
    }

    // get the width:
    fscanf(inhandle, " %u", &the_picture.width);
    while ( (comment_length = remove_comment(inhandle)) > 0 ) {
        printf("%u comment bytes found\n", comment_length);
    }

    // get the height:
    fscanf(inhandle, " %u", &the_picture.height);
    while ( (comment_length = remove_comment(inhandle)) > 0 ) {
        printf("%u comment bytes found\n", comment_length);
    }

    // get the number of color values:
    fscanf(inhandle, " %u", &the_picture.maxval);
    while ( (comment_length = remove_comment(inhandle)) > 0 ) {
        printf("%u comment bytes found\n", comment_length);
    }

    printf("(%u X %u) image, %u colors\n",
        the_picture.width, the_picture.height, the_picture.maxval);

    unsigned bytes_per_pixel = ( (the_picture.maxval <= 255)  ?  3  :  6 ); // Replaces the below if block
    //if (the_picture.maxval <= 255) {
    //    bytes_per_pixel = 3;
    //} else {
    //    bytes_per_pixel = 6;
    //}

    the_picture.pixels =
        malloc( the_picture.width * the_picture.height * bytes_per_pixel );

    fread( the_picture.pixels,  // read the input file.
        bytes_per_pixel,
        the_picture.width*the_picture.height,
        inhandle );

    fclose(inhandle);

    flip(&flipped_picture, the_picture); // flips the picture, outputs into struct flipped_picture

    FILE *outhandle = fopen(argv[2], "w");
    fprintf(outhandle, "%2s\n%u %u\n%u\n",
        flipped_picture.id, flipped_picture.width,
        flipped_picture.height, flipped_picture.maxval);

    fwrite(flipped_picture.pixels,
        flipped_picture.bytes_per_pixel,
        flipped_picture.width * flipped_picture.height,
        outhandle );
    fclose(outhandle);  // important!

    return 0;
}
