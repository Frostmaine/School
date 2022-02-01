/*
 * Matthew Yackiel
 * 09-03-21
 *  basic input and output - strings
 */
#include <stdio.h>

int main(int argc, char **argv)
{
    char buffer[60];    // up to 60 characters of text in this array.

    fputs("Enter a string: ", stdout);  // a "prompt" display

    fgets(buffer, 60, stdin);           // everything up to end of line

    fputs("You said:\n  -->", stdout);
    fputs(buffer, stdout);
    fputs("<--\n", stdout);

    return 0;
}
