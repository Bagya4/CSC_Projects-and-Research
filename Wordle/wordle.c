/**
 * @file wordle.c
 * @author Bagya Maharajan(bmahara)
 * Main program that allows user to play the game wordle
 */

#include "history.h"
#include "io.h"
#include "lexicon.h"
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


/** Maximum arguments on command line */
#define MAX_ARGS 3

/** Large prime multiplier used to choose a word pseudo-randomly. */
#define MULTIPLIER 4611686018453
/** Maximum lengh of a word on the word list. */
#define WORD_LEN 5
/** Maximum number of words on the word list. */
#define WORD_LIMIT 100000

/** Guesses column consists of 10 rows */
#define GUESS_NUMBER 10

char toGuess[WORD_LEN + 1];

/**
 * Main function which receives inout from command line
 * @param argc is number of arguments
 * @param argv is an array of pounters to the args
 * @return 0 if executed successfully
 */
int main(int argc, char *argv[])
{
    
    //if no args or too many
		if(argc <= 1 || argc > MAX_ARGS) {
        fprintf(stderr, "usage: wordle <word-list-file> [seed-number]\n");
        return EXIT_FAILURE;
    }
    
    long seed = 0;
    //if no seed was provided
    if(argc == (MAX_ARGS - 1)) {
        seed = time(NULL);
    }
    
    if(argc == MAX_ARGS) {
        if( (atol(argv[MAX_ARGS - 1])) == 0 ) {
            //not a long
            fprintf(stderr, "usage: wordle <word-list-file> [seed-number]\n");
            exit(EXIT_FAILURE);
        } //if not a long- above
        seed = atol(argv[MAX_ARGS - 1]);
        //if negative seed
        if(seed < 0) {
            fprintf(stderr, "usage: wordle <word-list-file> [seed-number]\n");
            exit(EXIT_FAILURE);
        }
    } //end of if with 3 args

    
    readWords(argv[1]);
    //choosing word    
    chooseWord(seed, toGuess);    
    
    //loop 5 times to get user input
    char guess[WORD_LEN + 1];
    //number of guesses
    int i = 0;
    bool loop = true;
    while(loop) {
        int b = fscanf(stdin, "%s", guess);
        if(strcmp(guess, "quit") == 0 || b == EOF) {
            fprintf(stdout, "The word was \"%s\"\n", toGuess);
            exit(EXIT_SUCCESS);
        }
        if( (strlen(guess)) > WORD_LEN ) {
            fprintf(stdout, "Invalid guess\n");
        }
        
        else if(!(inList(guess))) {
            fprintf(stdout, "Invalid guess\n");
        }
        
        else {
            //target word is guessed
            bool blackC = true;
            if( strcmp(guess, toGuess) == 0 ) {
                loop = false;
                i++;
                
                if(i == 1) {
                    if(!blackC) {
                        colorDefault();
                    }
                fprintf(stdout, "Solved in 1 guess\n");
                }
                else {
                    if(!blackC) {
                        colorDefault();
                    }
                fprintf(stdout, "Solved in %d guesses\n", i);
								}
                updateScore(i);
                return EXIT_SUCCESS;
                                
            }
            i++;
            
            int green[WORD_LEN] = {};
            //all green char is 1
            for(int a = 0; guess[a] != '\0'; a++) {
                if(guess[a] == toGuess[a]) {
                    green[a] = 1;
                }
            }
            
            int nCopies[WORD_LEN] = {};
            for(int b = 0; toGuess[b]; b++) {
                
                for(int c = 0; toGuess[c]; c++) {
                    if(toGuess[b] == toGuess[c]) {
                        //number of copies of any given letter
                        nCopies[c] += 1;
                        
                    }
                }
            } // end of for b
            
            //nCopies of yellow, non-green characters
            for(int d = 0; toGuess[d] != '\0'; d++) {
                if(green[d] == 1) {
                    nCopies[d] = 0;
                    for(int e = 0; toGuess[e] != '\0'; e++) {
                        if(toGuess[d] == toGuess[e] && d != e) {
                            nCopies[e]--;
                        }
                    }
                }
            } //end of for d
            
            //copies of the guess made by user
            int inpCopies[WORD_LEN] = {};
            for(int f = 0; guess[f] != '\0'; f++) {
                for(int g = 0; guess[g] != '\0'; g++) {
                    if(guess[f] == guess[g]) {
                        //number of copies of any given letter
                        inpCopies[g] += 1;
                        
                    }
                }
            } //end of for f
            
            //n copies of user input
            for(int h = 0; guess[h] != '\0'; h++) {
                if(green[h] == 1) {
                    inpCopies[h] = 0;
                    for(int i = 0; guess[i] != '\0'; i++) {
                        if(guess[h] == guess[i] && h != i) {
                            inpCopies[i]--;
                        }
                    }
                }
            } //end of for h
            
						 															
            int yellow[WORD_LEN] = {};
            for(int j = 0; j < WORD_LEN; j++) {
                if(nCopies[j] > 0 && green[j] != 1) {
                    for(int k = 0; (k < WORD_LEN) && nCopies[j] > 0; k++) {
                        if(toGuess[j] == guess[k] && green[k] == 0) { 
                            yellow[k] = 1;
                            inpCopies[k] = 0;
                            nCopies[j]--;
                        }
                        
                    }
                }
            } //end of j for loop
            
            //printing
            bool greenC = false;
            bool yellowC = false;
            
            for(int l = 0; l < WORD_LEN; l++) {
                int ch = guess[l];
                if(green[l] == 1) {
                    
                    if(!greenC) {
                        colorGreen();
                        greenC = true;
                        yellowC = false;
                        blackC = false;
										}
                    fprintf(stdout, "%c", ch);
                    
                }
                else if(yellow[l] == 1) {
                    if(!yellowC) {
                        colorYellow();
                        yellowC = true;
                        greenC = false;
                        blackC = false;
                    }
                    fprintf(stdout, "%c", ch);
                   
                }
                else {
                    if(!blackC) {
                        colorDefault();
                        blackC = true;
                        greenC = false;
                        yellowC = false;
                    }
                    fprintf(stdout, "%c", ch);
               }
                
            }
            if(!blackC)
                colorDefault();
            fprintf(stdout, "\n");
            
        } //end of else
        
    } //end of while
    
    
    
    
}