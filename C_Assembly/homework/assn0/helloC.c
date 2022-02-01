/*
* Matthew Yackiel
* 08-31-1997
* "hello world" and return code
*/

#include <stdio.h>  // always need this!

int main(int argc, char **argv)
{
    char message[] = "Hello, world.\n";

    printf(message);

    return 0;   // required: a return code
}
