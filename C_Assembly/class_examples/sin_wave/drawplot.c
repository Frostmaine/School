
#include <stdio.h>
#include <unistd.h>

void drawplot(double *s, int n, int w)
{
  for (int r = 0; r < n; r++) {

        int length = w/2.0 *  (1 + s[r]);

        for (int c = 0; c < length; c++) {
            printf("*");
        }
        printf("  %.3lf\n", s[r]);

        usleep(1e5);    // 100,000 microseconds = 0.1 seconds
    }
}
