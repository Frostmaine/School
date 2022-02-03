/*
  Matthew Yackiel
  Assignment 2
  9-16-2021
 */

#include<stdio.h>

int main(int argc, char** argv)
{
  float value;
  char units;

  printf("Temperature: ");
  scanf("%f\n", &value);
  scanf("%c", &units);
  
  if (units == 'c' || units == 'C')
  {
    
    printf("%.2f %c\n", value*1.8+32, 'F');
    printf("%.2f %c\n", value+273.15, 'K');
  }
  else if (units == 'f' || units == 'F')
  {
    printf("%.2f %c\n", (value-32)/1.8, 'C');
    printf("%.2f %c\n", (value-32)/1.8 + 273.15, 'K');
  }
  else if (units == 'k' || units == 'K')
  {
    printf("%.2f %c\n", value-273.15, 'C');
    printf("%.2f %c\n", (value-273.15)*1.8, 'F');
  }

  return 0;
}
