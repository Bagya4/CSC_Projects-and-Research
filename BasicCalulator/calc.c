#include "number.h"
#include "operation.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
/** Exit status indicating that the program was given invalid input. */
#define FAIL_INPUT 102
/** Exit status for overflow */
#define WRONG_INPUT 100
/** exit status for dividng by zero */
#define BAD_DIVIDE 101
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
/** maximum digit possible */
#define MAX_DIGIT_B10 9

/**
 * parses term between + and - and returns the answer
 * @return quotient or product
 */
static long parseTerm()
{
    long temp3 = 0;
    long temp4 = 0;
    bool loop = true;

    while(loop) {
        skipSpace();
        char ch = getchar();
        int check = ch - '0';
        if(check >= MIN_DIGIT_ASCII && check <= MAX_DIGIT_ASCII) {
    			 check = check - DIFFERENCE_CODE;
				}
        if(ch == '\n') {
            ungetc(ch, stdin);
            return temp3;
        }
        if(!(check >= 0 && check <= MAX_DIGIT) && ch != '/' && ch != '*' && ch != '+' && ch != '-') {
            exit(FAIL_INPUT);
        }
        else {
            if(ch == '+' || ch == '-') {
                ungetc(ch, stdin);
                return temp3;
            }
            if(ch == '/') {
                skipSpace();
                temp4 = parseValue();
                long dummy1 = divide(temp3, temp4);
                temp3 = dummy1;

            }
            else if(ch == '*') {
                skipSpace();
                temp4 = parseValue();
                long dummy = times(temp3, temp4);
                temp3 = dummy;
            }
            else {
                ungetc(ch, stdin);
                skipSpace();
                temp3 = parseValue();
            }
        }

    }

    return temp3;

}


int main()
{
    long temp1 = 0;
    long temp2 = 0;
    bool loop = true;

    while(loop) {
        skipSpace();
        char ch = getchar();
        int check = ch - '0';
        if(check >= MIN_DIGIT_ASCII && check <= MAX_DIGIT_ASCII) {
    			 check = check - DIFFERENCE_CODE;
				}
        if(ch == '\n') {
     			  if(temp1 == 0) {
					 		  putchar('0');
	 						  printf("\n");
	 						  return EXIT_SUCCESS;
			 			} 
			 		  else if(temp1 < 0) {
			         putchar('-');
			      }
            printValue(temp1);
            printf("\n");
            return EXIT_SUCCESS;
        }
        if(!(check >= 0 && check <= MAX_DIGIT) && ch != '+' && ch != '-') {
            return FAIL_INPUT;
        }
        else {
            if(ch == '+') {
                skipSpace();
                char oper = getchar();
                ungetc(oper, stdin);
                //checking for 2 operators in a row
                if(oper == '+' || oper == '/' || oper == '*') return FAIL_INPUT;
                temp2 = parseTerm();
                temp1 = plus(temp1, temp2);
            }
            else if(ch == '-') {
                skipSpace();
                char tempNxt = getchar();
                ungetc(tempNxt, stdin);
                if(tempNxt == ' ') {
                    
      					    long checkOverflow = parseTerm();
 						        
                    temp2 = checkOverflow * -1;
                    
                }
                else {
                    
                    temp2 = parseTerm();
                    
                    temp1 = minus(temp2, temp1);
                  
                }
            }
            else {
                ungetc(ch, stdin);
                temp1 = parseTerm();
                
            }
        }
    }

    printValue(temp1);
    return 0;
}
