#ifndef _WORDLIST_
#define _WORDLIST_

#include <stdio.h>
#include <string.h> // strcmp()
#include <ctype.h>  // isalpha(), etc.
#include <stdlib.h> // malloc(), free()
#include <time.h>   // clock()

void cleanword(char *word);

extern unsigned maxdepth;  // Globally track how deeply the recursions go.

#endif
