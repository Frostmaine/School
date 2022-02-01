/*
 * Matthew Yackiel
 * 09-03-2021
 *  basic input and output - integers
 */

#include <stdio.h>

int main(int argc, char **argv)
{
    int a, b, c;    // three integer variables

    printf("Enter 3 integers: ");   // a "prompt" display

    scanf(" %d", &a);   // Get the first integer

    scanf(" %d", &b);   // Get the second integer

    scanf(" %d", &c);   // Get the third integer

    printf( "( %d + %d ) / %d equals  %d ???\n",  a, b, c,  ((a + b) / c) );

    return 0;
}
