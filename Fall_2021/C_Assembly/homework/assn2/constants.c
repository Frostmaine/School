/*
  Matthew Yackiel
  Assignment 2
  9-13-2021
 */

#include<math.h>
#include<stdio.h>

int main(int argc, char** argv)
{
  const double PI = M_PI;
  const double AVO = 6.02214076e23;
  const int TEN_DIGIT = 2902958801;
  const char* NAME = "Matthew";
  const char LETTER = 'a';

  printf("*************************************************************************************\n");
  printf("%.4f\t%.8f\t%.12f\n", PI, PI, PI);
  printf("%.4e\t%.8e\t%.12e\n", AVO, AVO, AVO);
  printf("*************************************************************************************\n");
  printf("%d\t%d\t%d\n", TEN_DIGIT, TEN_DIGIT, TEN_DIGIT);
  printf("%u\t%u\t%u\n", TEN_DIGIT, TEN_DIGIT, TEN_DIGIT);
  printf("*************************************************************************************\n");
  printf("%s\t%s\t%s\n", NAME, NAME, NAME);
  printf("*************************************************************************************\n");
  printf("%c\t%c\t%c\n", LETTER, LETTER, LETTER);
  printf("%d\t%d\t%d\n", LETTER, LETTER, LETTER);
  printf("*************************************************************************************\n");
  printf("%d\t%d\t%d\n", PI, PI, PI);
  printf("*************************************************************************************\n");
  printf("%p\t%p\t%p\n", NAME, NAME, NAME);
  printf("*************************************************************************************\n");  
}
