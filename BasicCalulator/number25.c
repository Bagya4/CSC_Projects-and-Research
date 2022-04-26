#include "number.h"
#include "operation.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/** Exit status indicating that the program was given invalid input. */
#define FAIL_INPUT 102
/** maximum digit possible for ascii */
#define MAX_DIGIT_ASCII 31
/** maximum digit possible */
#define MAX_DIGIT 24
/** minimum digit possible for ascii */
#define MIN_DIGIT_ASCII 17
/** minimum digit possible */
#define MIN_DIGIT 10
/** difference in ascii code */
#define DIFFERENCE_CODE 7

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
 * Coverts charcters into numbers in base 25
 * @return value in base 25
 */
long parseValue()
{
    long value = 0;
    // Get the next input character.
    char ch = getchar();
    int check = ch - '0';
    if(check >= MIN_DIGIT_ASCII && check <= MAX_DIGIT_ASCII) {
			 check = check - DIFFERENCE_CODE;
		}
    bool loop = false;
    bool minus = false;

    if(check >= 0 && check <= MAX_DIGIT) {
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
        if(tmpChck >= MIN_DIGIT_ASCII && tmpChck <= MAX_DIGIT_ASCII) {
   				 tmpChck = tmpChck - DIFFERENCE_CODE;
		    }
        if(tmpChck >= 0 && tmpChck <= MAX_DIGIT) {
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
        return FAIL_INPUT;
    }

    while(loop) {
        long val = ch - '0';
        if(val >= MIN_DIGIT_ASCII && val <= MAX_DIGIT_ASCII) {
    			 val = val - DIFFERENCE_CODE;
				}
        value = times(value, (MAX_DIGIT + 1));
        if(value == WRONG_INPUT) return WRONG_INPUT;
        value = plus(value, val);
        if(value == WRONG_INPUT) return WRONG_INPUT;

        ch = getchar();
        check = ch - '0';
        if(check >= MIN_DIGIT_ASCII && check <= MAX_DIGIT_ASCII) {
    			 check = check - DIFFERENCE_CODE;
				}

        loop = false;

        if(check >= 0 && check <= MAX_DIGIT)
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
 * Prints the value in base 25
 * @param val to be printed
 */
void printValue(long val)
{
    
    long d = 0;
    char ch = '\0';
    if(val == 0)  {
    //empty
		}
    else {
			 d = val % (MAX_DIGIT + 1);
 	     if(d < 0) d *= -1;
 	     if(d >= MIN_DIGIT && d <= MAX_DIGIT) {
 			      d = d + DIFFERENCE_CODE;
       }
 			 ch = d + '0';
 			 val = val / (MAX_DIGIT + 1);
	 	   printValue(val);
	     printf("%c", ch);
 	     
		}

}
