#include "harmean.h"

double harmonic_mean(double* d_array, int size)
{
  double mean = 0;
  for (int i = 0; i < size; i++)
    mean += 1 / d_array[i];
  return size / mean;
}
 
