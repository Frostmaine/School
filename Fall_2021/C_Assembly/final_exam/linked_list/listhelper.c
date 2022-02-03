#include "list.h"

// Delete non-letters from "words"
void cleanword(char *word)
{
    while (*word) {     // point to each character in turn...

        // Accept letters, digits and apostrophes, and dashes:
        if (isalpha(*word) || isdigit(*word) || *word == '\'' || *word == '-') {
                *word = tolower(*word); // lowercase everything
                word++;  // advance "word" ptr to next character
        }
        else {    // overwrite this character with the rest of the word:
            char *p = word;
            do {
                *p = *(p+1);  // copy next character on top of this one
            } while (*(p++));   // test-before-increment gets '\0' copied too.
        }
    }
}

// Create and return a new node containing the new word
wordnode *newnode(char *word, wordnode *next)
{
    wordnode *node = malloc(sizeof(wordnode));
    node->word = word;
    node->count = 1;
    node->next = next;
    return node;
}

// Iteratively display each node of the list
void displaylist(wordnode *head)
{
    unsigned length = 0;
    wordnode *this;
    for (this = head; this != NULL; this = this->next) {
        printf("%5u  %s\n", this->count, this->word);
        length++;
    }
    printf("List length: %u\n", length);
}

// Recursively find the last list node that comes before (or is equal to)
// the new word.  Count the new word or insert after next, as appropriate.
void append_after(wordnode *prev, char *word, unsigned depth)
{
    if ( !strcmp(prev->word, word) ) {
        prev->count++;
        free(word);     // storage not needed anymore, so tidy up

    } else if ( prev->next == NULL || strcmp((prev->next)->word, word) > 0 ) {
        // Insert the new node between the previous node...
        // ...and whatever followed the previous node.
        wordnode *node = newnode(word, prev->next);
        prev->next = node;
        if (depth > maxdepth)
                maxdepth = depth;

    } else {
        // recursively try the rest of the list
        append_after(prev->next, word, depth+1);  // one more level of recursion
    }
}
