/*
* Read words from a file and elide any non-letters; collect into a (sorted) linked list.
* 2019-10-18, w/ more comments  -bob,mon.
*/
#include "wordlist.h"   // includes system header files

unsigned maxdepth = 0;  // keep track of how deeply the recursions go

int main(int argc, char **argv)
{
    if (argc < 2) {
        fprintf(stderr, "usage: %s <file>\n", argv[0]);
        return 1;
    }

    unsigned clockstart = clock();  // begin timing the program

    char *word;     // points to each word as it is read in
    unsigned totalwords = 0;
    maxdepth = 0;   // global variable

    wordnode *head;
    head = NULL;    // list is initially empty

    FILE *fp = fopen(argv[argc-1], "r");
    // "%ms" allocates "just enough" space for the string
    while (EOF != fscanf(fp, " %ms", &word)) {
        cleanword(word);
        if (strlen(word) == 0)  // Is there any "word" left?
            free(word); // free up the unused word's storage space
        else {
            totalwords++;

            if (NULL == head) {
                // this is the first word added to the list
                //      wordnode *node = newnode(word, NULL);
                //      head = node;
                head = newnode(word, NULL);

            } else if ( strcmp(head->word, word) > 0 ) {
                // this word is the new head of the list
                //      wordnode *node = newnode(word, head);
                //      head = node;
                head = newnode(word, head);

            } else {
                // this word goes later in the list
                append_after(head, word, 1);    // initial recursive call...
            }
        }
    }
    fclose(fp);
    printf("%u total words.\n", totalwords);
    printf("%u maximum recursion depth.\n", maxdepth);

    // How long did it take to build the list?
    unsigned clockprint = clock();
    fprintf(stderr, "Read words/build list: %.6lf seconds\n",
        (double)(clockprint-clockstart)/(double)CLOCKS_PER_SEC);

        displaylist(head);
        // How long did it take to display the list?
        unsigned clockend = clock();
        fprintf(stderr, "display list: %.6lf seconds\n",
                (double)(clockend-clockprint)/(double)CLOCKS_PER_SEC);

    return 0;
}
