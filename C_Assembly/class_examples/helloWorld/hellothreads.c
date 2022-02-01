/*
  The venerable Hello, World! program
  Matthew Yackiel
*/
#include <stdio.h>
#include <omp.h>

int main(int argc, char **argv)
{
	int nthreads, nprocs;
	int nrunning, threadnum;

	nthreads = omp_get_max_threads();
	nprocs = omp_get_num_procs();
	nrunning = omp_get_num_threads();

	printf("Hello, World!\n");
	printf("%d threads available on %d processors.\n", nthreads, nprocs);
	printf("%d threads are running.\n", nrunning);  
 
	printf("#--------\n");
#pragma omp parallel private(nrunning, threadnum)
	{
		nrunning = omp_get_num_threads();
		threadnum = omp_get_thread_num();
		printf("Thread %d of %d total threads now.\n", threadnum, nrunning);
    
		if (threadnum < nrunning/2)
		{
			printf("  First half: thread %d\n", threadnum);
		}
		else
		{
			printf("  Second half: thread %d\n", threadnum);
		}
	} 
	return 0;
}
