#ifndef _LIST_
#define _LIST_

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
void append_after(wordnode *prev, char *word, unsigned depth);
void displaylist(wordnode *head);

extern unsigned maxdepth;  // keep track of how deeply the recursions go

#endif
