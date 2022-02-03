/* image reader
* 2021-10-04
*/
#include<stdio.h>
#include<ctype.h>   // isspace()
#include<stdlib.h>  // malloc()
#include<string.h>  // strncpy()

struct Image {
    unsigned char id[3];
    unsigned width, height;
    unsigned maxval;
    unsigned char *pixels;
} ;

unsigned idx2(unsigned row, unsigned col, unsigned bpp, struct Image *img)
{
    return (bpp * (row * img->width + col));
}

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

void negate(struct Image *negated, struct Image original)
{
    strncpy((char *)(negated->id), (char *)original.id, 3);    // include the terminal NUL
    negated->width = original.width;    // copy width into memory pointed to by "flipped"
    negated->height = original.height;
    negated->maxval = original.maxval;

    unsigned bytes_per_pixel = ( (original.maxval <= 255)  ?  3  :  6 );
    negated->pixels = malloc( negated->width * negated->height * bytes_per_pixel );

    for (unsigned y = 0; y < original.height; y++) {    // each row...
        for (unsigned x = 0; x < original.width; x++) { // each column of each row...
           for (unsigned rgb = 0; rgb < 3; rgb++) {

	     negated->pixels[ idx2(y, x, bytes_per_pixel, negated) + rgb ] =
                    255 - original.pixels[ idx2(y, x, bytes_per_pixel, &original) + rgb ];
	     
	   }
	}
    }
    
}

int main(int argc, char **argv)
{
    struct Image the_picture;
    if (argc != 3) {
        fprintf(stderr, "usage: %s  <input-file> <output-file>\n", argv[0]);
        return 1;
    }

    FILE *inhandle = fopen(argv[1], "r");

    // get the "magic number":
    fscanf(inhandle, "%2s", the_picture.id);
    the_picture.id[2] = '\0';
    remove_comment(inhandle);

    // get the width:
    fscanf(inhandle, " %u", &the_picture.width);
    remove_comment(inhandle);

    // get the height:
    fscanf(inhandle, " %u", &the_picture.height);
    remove_comment(inhandle);

    // get the number of color values:
    fscanf(inhandle, " %u", &the_picture.maxval);
    remove_comment(inhandle);

    printf("(%u X %u) image, %u colors\n",
        the_picture.width, the_picture.height, the_picture.maxval);

    unsigned bytes_per_pixel = ( (the_picture.maxval <= 255)  ?  3  :  6 );
    the_picture.pixels =
        malloc( the_picture.width * the_picture.height * bytes_per_pixel );

    fread( the_picture.pixels,
        bytes_per_pixel,
        the_picture.width*the_picture.height,
        inhandle );

    fclose(inhandle);

    struct Image negated;
    
    negate(&negated, the_picture);

    FILE *outhandle = fopen(argv[2], "w");
    fprintf(outhandle, "%2s\n%u %u\n%u\n",
        negated.id, negated.width,
        negated.height, negated.maxval);

    fwrite(negated.pixels,
        bytes_per_pixel,
        negated.width * negated.height,
        outhandle );
    fclose(outhandle);  // important!
    
    
    return 0;
}
