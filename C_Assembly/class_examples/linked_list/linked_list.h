#ifndef _WORDLIST_
#define _WORDLIST_

#include <stdio.h>
#include <string.h> // strcmp()
#include <ctype.h>  // isalpha(), etc.
#include <stdlib.h> // malloc(), free()
#include <time.h>   // clock()

typedef struct WordNode {
    char *word;
    unsigned count;
    struct WordNode *next;
} wordnode;

void cleanword(char *word);

wordnode *newnode(char *word, wordnode *next);

extern unsigned maxdepth;  // Globally track how deeply the recursions go.

#endif
