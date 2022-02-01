/*
  Matthew yackiel
  Compsci 240
  Dec 7 2021
*/

#include <stdio.h> // file input and such
#include <stdlib.h> // atoi and atof
#include <string.h> // for string operations

struct record {
  char* tag;
  unsigned count;
  struct record *next;
};

record *newnode(char *tag, unsigned count, record *next)
{
  record *node = malloc(sizeof(record));
    node->tag = tag;
    node->count = count;
    node->next = next;
    return node;
}

int main(int argc, char **argv)
{
  char *filename_in;
  char *filename_out;

  char *tag;
  unsigned count;
 
  if (argc >= 3)
  {
    filename_in = argv[1];
    filename_out = argv[2];
  }
  else {
    printf("usage: %s <filename> <filename>\n", argv[0]);
    return 0;
  }

  record *head;
  head = NULL;

  FILE *in = fopen(filename_in, "r");  // declare input file
  FILE *out = fopen(filname_out, "w");

  while (EOF != fscanf(in, " %s %u ", &tag, &count))
  {
    // process each record into linked list
    if (head = NULL)
      head = newnode(tag, count, NULL);
    else // append to front
      head = newnode(tag, count, head);
  }

  while (head->next != NULL)
  {
    printf(out, "%s %u \n", head->tag, head->count);
    head = next;
  }

  fclose(in);
  fclose(out);
  
  return 0;
}

