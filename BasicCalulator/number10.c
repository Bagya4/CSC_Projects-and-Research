#include "number.h"
#include "operation.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/** Exit status indicating that the program was given invalid input. */
#define FAIL_INPUT 102
/** maximum digit possible */
#define MAX_DIGIT_B10 9

/** 
 * Skips all whitespaces
 * @return int value of the first nonwhitespace character
 */
int skipSpace()
{
    char ch = getchar();
    if(ch == ' ') {
        while(ch == ' ') {
            ch = getchar();
        } //end of while
    } //end of if
    ungetc(ch, stdin);
    return (int)ch;

}

/** 
 * Coverts charcters into numbers in base 10
 * @return value in base 10
 */
long parseValue()
{
    long value = 0;
    // Get the next input character.
    char ch = getchar();
    int check = ch - '0';
    bool loop = false;
    bool minus = false;

    if(check >= 0 && check <= MAX_DIGIT_B10) {
        loop = true;
	}
    else if(ch == '/' || ch == '*' || ch == '+') {
       ungetc(ch, stdin);
       return value;
	}
    else if(ch == '-') {
        char tmpChar = getchar();
        ungetc(tmpChar, stdin);
        int tmpChck = tmpChar - '0';
        if(tmpChck >= 0 && tmpChck <= MAX_DIGIT_B10) {
            ch = getchar();
            minus = true;
            loop = true;
        }
        else {
            ungetc(ch, stdin);
            return value;
        }
    }
    else {
        ungetc(ch, stdin);
        exit(FAIL_INPUT);
    }

     while(loop) {
        
         int val = ch - '0';
         value = times(value, (MAX_DIGIT_B10 + 1));
         value = value + val;

         ch = getchar();
         check = ch - '0';

         loop = false;

         if(check >= 0 && check <= MAX_DIGIT_B10)
            loop = true;
         else if(ch == '/' || ch == '*' || ch == '+' || ch == '-') {
            ungetc(ch, stdin);
            if(minus) return value * -1;
            return value;
		     }
         else {
             ungetc(ch, stdin);
         }
     }
     if(minus) return value * -1;
     return value;


}

/** 
 * Prints the value in base 10
 * @param val to be printed
 */
void printValue(long val)
{
    //negative values are handled in main
    if(val < 0) {
			 val = val * -1;
		}
    printf("%ld", val);
}
