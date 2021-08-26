/*
 The venerable Hello, World! program
 Matthew Yackiel
 */
#include <stdio.h>
int main(int argc, char **argv)
{
  int nthreads, nprocs;
  int nrunning, threadnum;

  printf("Hello, World!\n");
  printf("%d threads available on %d processors.\n", nthreads, nprocs);
  printf("%d threads are running.\n", nrunning);  
  
  return 0;
}
