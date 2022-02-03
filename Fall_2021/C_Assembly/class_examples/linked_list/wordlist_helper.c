#include "wordlist.h"

// Delete non-letters from the "word" buffer
void cleanword(char *word)
{
    while (*word) {     // point to each character in turn...

        // Accept letters, digits and apostrophes, and dashes:
        if (isalpha(*word) || isdigit(*word)
            || *word == '\'' || *word == '-') { 
                *word = tolower(*word); // lowercase everything
                word++;  // advance "word" ptr to next character

        } else {    // overwrite this character with the rest of the word:
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
    wordnode *node = malloc(sizeof(wordnode));  // space for the new node
    node->word = malloc(strlen(word)+1);        // space for the word in the new node
    strncpy(node->word, word, strlen(word));
    node->count = 1;
    node->next = next;
    return node;
}
