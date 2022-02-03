
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define COLUMNS 6
#define ROWS 11

    /* Heat Index = */
    /*     -42.379 */
    /*     + (2.04901523 × Temp ) */
    /*     + (10.14333127 × RH ) */
    /*     - (0.22475541 × Temp × RH )  */
    /*     - (0.00683783 × Temp 2 )  */
    /*     - (0.05481717 × RH 2 )  */
    /*     + (0.00122874 × Temp 2 × RH )  */
    /*     + (0.00085282 × Temp × RH 2 )  */
    /*     - (0.00000199 × Temp 2 × RH 2 )  */

double heatIndex(double temp, double rh)
{
  if (temp < 80.0) return -1.0;
  double temp_sq = temp*temp;
  double rh_sq = rh*rh;
  double hi = -42.379;
  hi += 2.04901523*temp;
  hi += 10.14333127 * rh;
  hi -= 0.22475541 * temp * rh;
  hi -= 6.83783e-3 * temp_sq;
  hi -= 5.481717e-2 * rh_sq;
  hi += 1.22874e-3 * temp_sq * rh;
  hi += 8.5282e-4 * temp * rh_sq;
  hi -= 1.99e-6 * temp_sq * rh_sq;  
  return hi;
}


/* - WindChill = */
/*         35.74 */
/*         + (0.6215 × Temp ) */
/*         - (35.75 × Wind 0.16 ) */
/*         + (0.4275 × Temp × Wind 0.16 )  */
double windchill(double temp, double wind)
{
  if (wind < 3 || wind > 60)
    exit(1);
  if (temp < -45 || temp > 45)
    exit(1);
  
  double wc = 35.74;
  double windpow = pow(wind, 0.16);
  wc += 0.6215 * temp;
  wc -= 35.75 * windpow;
  wc += 0.4275 * temp * windpow;
  return wc;
}

int main(int argc, char **argv)
{

  char choice;
  double temp1, temp2, wind1, wind2, rh1, rh2;
  printf("Heat Index, Windchill, or quit?");
  do
  {
  scanf("%c", &choice); // get choice
  }
  while(choice != 'q' && choice != 'Q' && choice != 'h' && choice != 'H' && choice != 'w' && choice != 'W');

  switch (choice)
  {
    case 'H' :
    case 'h' :
    {
      printf("h...\nTemperature Range: ");
      scanf(" %lf %lf", &temp1, &temp2);
      printf("Humidity Range: ");
      scanf(" %lf %lf", &rh1, &rh2);
      double temp_step = (temp2 - temp1) / (ROWS-1);
      double rh_step = (rh2 - rh1) / (COLUMNS-1);

      // print header
      printf("T \\ H    ");
      int i;
      double j;
      for (i = 0, j = rh1; i < COLUMNS; i++, j+= rh_step)
	printf("%3.1lf   ", j);
      printf("\n");
      for (i = 0; i <= COLUMNS; i++) // bars
	printf("%-5s  ", "-----");
      printf("\n");

      // table
      int l;
      double k;
      for (i = 0, j = temp1; i < ROWS; i++, j+= temp_step)
      {
	printf("%-3.1lf    ", j); // row header
	for (l = 0, k = rh1; l < COLUMNS; l++, k += rh_step)
	{
	  printf("%3.1lf   ", heatIndex(j, k));
	}
	printf("\n");
      }

      // more bars
        for (i = 0; i <= COLUMNS; i++) // bars
	  printf("%-5s  ", "-----");
    }
    case 'W':
    case 'w':
    {
     printf("w...\nTemperature Range: ");
      scanf(" %lf %lf", &temp1, &temp2);
      printf("Windspeed Range: ");
      scanf(" %lf %lf", &wind1, &wind2);
      double temp_step = (temp2 - temp1) / (ROWS-1);
      double wind_step = (wind2 - wind1) / (COLUMNS-1);

      // print header
      printf("T \\ W    ");
      int i;
      double j;
      for (i = 0, j = wind1; i < COLUMNS; i++, j+= wind_step)
	printf("%3.1lf   ", j);
      printf("\n");
      for (i = 0; i <= COLUMNS; i++) // bars
	printf("%-5s  ", "-----");
      printf("\n");

      // table
      int l;
      double k;
      for (i = 0, j = temp1; i < ROWS; i++, j+= temp_step)
      {
	printf("%-3.1lf    ", j); // row header
	for (l = 0, k = wind1; l < COLUMNS; l++, k += wind_step)
	{
	  printf("%3.1lf   ", windchill(j, k));
	}
	printf("\n");
      }

      // more bars
        for (i = 0; i <= COLUMNS; i++) // bars
	  printf("%-5s  ", "-----");
  
    }
    case 'q':
    case 'Q':
    {
      printf("q...");
    } 
  }
  return 0;
}
