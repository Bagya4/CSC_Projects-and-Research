/**
 * @file history.c
 * @author Bagya Maharajan(bmahara)
 * Implements the score history of a user
 */
 
#include "history.h"
#include <stdio.h>
#include <stdbool.h>
 
/** Guesses column consists of 10 rows */
#define GUESS_NUMBER 10
 
 /*
  * Reads from scores.txt and prints  new score
	* @param guessCount is number of guesses 
  */
void updateScore( int guessCount )
 {
     int scores[GUESS_NUMBER] = {};
     int current = 0;
     bool opens = false;
		 //reading current scores first
     FILE *fp;
     if((fp = fopen("scores.txt", "r"))) {
         opens = true;
     }
     if(!opens) {
         fp = fopen("scores.txt", "w");
     }
     for(int i = 0; i < GUESS_NUMBER; i++) {
         fscanf(fp, "%d", &current);
         scores[i] = current;
     }
     
     //close after reading
     fclose(fp);
     
     //update score array
     if(guessCount > GUESS_NUMBER)
         guessCount = GUESS_NUMBER;
         
     scores[guessCount - 1] += 1;
     
     //writing to scores file with a space after each score
     FILE *write = fopen("scores.txt", "w");
     for(int i = 0; i < GUESS_NUMBER - 1; i++) {
          fprintf(write, "%d ", scores[i]);
     }
     //writing last element without a space
     fprintf(write, "%d", scores[GUESS_NUMBER - 1]);
     fprintf(write, "\n");
     //closing file after writing 
     fclose(write);
     
     //printing to std output
     for(int i = 0; i < GUESS_NUMBER - 1; i++) {
          //space, number, 2 space, colon
          printf(" %d  :", i + 1);
          //number of guesses in 4 column field and the a new line
          printf("%5d\n", scores[i]);          
     }
     
     //printing last row
     printf("%d", GUESS_NUMBER);
     putchar('+');
     putchar(' ');
     putchar(':');
     printf("%5d", scores[GUESS_NUMBER - 1]); 
     printf("\n");
     
 }