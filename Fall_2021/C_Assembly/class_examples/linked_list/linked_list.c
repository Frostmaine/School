/*
* Read words from a file and elide any non-letters; collect into a (sorted) linked list.
* 2019-10-21, don't depend on "%ms" in the fscanf().
*/
#include "wordlist.h"   // includes system header files

#define BUFSIZE 1024
#define BUFFMT " %1024s"

unsigned maxdepth = 0;  // GLOBAL variable is initialized to 0

int main(int argc, char **argv)
{
    if (argc < 2) {
        fprintf(stderr, "usage: %s <file>\n", argv[0]);
        return 1;
    }

    wordnode *head = NULL;    // list is initially empty
    long unsigned clockstart = clock();  // begin timing the program

    char wordbuffer[BUFSIZE];   // hold words as they are read in
    unsigned totalwords = 0;

    FILE *fp = fopen(argv[argc-1], "r");

    while (EOF != fscanf(fp, BUFFMT, wordbuffer)) {
        cleanword(wordbuffer);
        if (strlen(wordbuffer) > 0) { // Is there any "word" left?
            totalwords++;

            //printf("%u %s\n", totalwords, wordbuffer);
	    if (NULL == head) { // read the list
                // this is the first word added to the list
                head = newnode(wordbuffer, NULL);

            } else if ( strcmp(wordbuffer, head->word) < 0 ) {
                // this word is the new head of the list
                head = newnode(wordbuffer, head);

            } else {
                // this word goes later in the list
                append_after(head, wordbuffer, 1);
            }
        }
    }
    fclose(fp);
    printf("%u total words.\n", totalwords);
    printf("%u maximum depth.\n", maxdepth);

    // How long did it take to build the list?
    long unsigned clockprint = clock();
    fprintf(stderr, "Read words/build list: %.6lf seconds\n",
        (double)(clockprint-clockstart)/(double)CLOCKS_PER_SEC);

    return 0;
}
