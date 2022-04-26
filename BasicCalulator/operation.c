#include "operation.h"
#include <limits.h>
#include <stdlib.h>
#include <stdbool.h>

/** Exit status indicating that the program was given invalid input. */
#define WRONG_INPUT 100
/** exit status for dividing by zero */
#define BAD_DIVIDE 101

/** 
 * Checks if two values fit within long data type
 * @param a value 1
 * @param b value 2
 * @return integer -1 if invalid
 */
int checkRange(long a, long b)
{
   if(a < LONG_MIN || a > LONG_MAX) return -1;
   if(b < LONG_MIN || b > LONG_MAX) return -1;

   return 0;
}

/** 
 * Adds two values
 * @param a value 1
 * @param b value 2
 * @return sum of the numbers
 */
long plus(long a, long b)
{
   if(checkRange(a, b) == -1) return WRONG_INPUT;

   long sum = a + b;
   //two positive and sum negative
   if((a >= 0 && b >= 0) && sum < 0)
      exit(WRONG_INPUT);

   //two -ve and +ve sum
   if((a < 0 && b < 0) && sum > 0)
      exit(WRONG_INPUT);

   if(sum < LONG_MIN || sum > LONG_MAX)
      exit(WRONG_INPUT);

   return sum;

}

/** 
 * Subtracts two values
 * @param a value 1
 * @param b value 2
 * @return difference of the numbers
 */
long minus(long a, long b)
{
   if(checkRange(a, b) == -1) exit(WRONG_INPUT);
   //negative - +ve
   /**if(b < 0 && a > 0)
      exit(WRONG_INPUT); */

   long diff = b - a;

   if(diff < LONG_MIN || diff > LONG_MAX)
      exit(WRONG_INPUT);

   return diff;

}

/** 
 * Multiplies two values
 * @param a value 1
 * @param b value 2
 * @return product of the numbers
 */
long times(long a, long b)
{
    //if(a == 0 || b == 0) return 0;
    if(checkRange(a, b) == -1) exit(WRONG_INPUT);
    
    if(a < 0 && b < 0) {
 	       long x = LONG_MIN / b;
         if(a > x) {
            exit(WRONG_INPUT);
				 }    		 
		}
    if(a > 0 && b > 0) {
    		 long x = LONG_MAX / b;
   			 if(a > x) exit(WRONG_INPUT);
		}
	  if(a < 0 && b > 0) {
		  	 long x = LONG_MIN / b;
         if(a < x) exit(WRONG_INPUT);
		}
		 
	  long product = a * b;
    if((a > 0 && b > 0) && product > LONG_MAX)
       exit(WRONG_INPUT);
    if((a < 0 && b < 0) && (product < 0 || product > LONG_MAX))
       exit(WRONG_INPUT);
    if(a != 0 && product / a != b) exit(WRONG_INPUT);
    if(b > 0 && a > LONG_MAX / b) exit(WRONG_INPUT);
    
    return product;

}

/** 
 * Divides two values
 * @param a value 1
 * @param b value 2
 * @return quotient of the numbers
 */
long divide(long a, long b)
{
    if(a == 0) return 0;

    if(checkRange(a, b) == -1) exit(WRONG_INPUT);

    if(b == 0)exit(BAD_DIVIDE);

    long quotient = a / b;
    if(quotient < LONG_MIN || quotient > LONG_MAX)
       exit(WRONG_INPUT);
    if(((a > 0 && b < 0) || (a < 0 && b > 0)) && quotient > 0)
       exit(WRONG_INPUT);


    return quotient;

}
