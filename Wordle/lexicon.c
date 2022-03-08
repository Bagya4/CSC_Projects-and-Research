/**
 * @file lexicon.c
 * @author Bagya Maharajan(bmahara)
 * Reads word list from a file and stores it for other functions to make use of.
 */
#include "lexicon.h"
#include "io.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>


/** Large prime multiplier used to choose a word pseudo-randomly. */
#define MULTIPLIER 4611686018453
/** Maximum lengh of a word on the word list. */
#define WORD_LEN 5
/** Maximum number of words on the word list. */
#define WORD_LIMIT 100000

/** stores list of words */
static char wordList[WORD_LIMIT][WORD_LEN + 1];
/** stores number of elements in array */
static long idx = 0;


/**
 * Reads the word list from the given file and detects errors in the word list file
 * to print an error message
 * @param filename as a string
 */
void readWords( char const filename[] ) 
{
    
    //if file cannot be opened
    FILE *fp;
    if( (fp = fopen(filename, "r")) == NULL ) {
        fprintf(stderr, "Can't open word list: %s\n", filename);
        exit(EXIT_FAILURE);
    }
    
    char word[WORD_LEN + 1];
    
    while( readLine(fp, word, WORD_LEN)) {
    
        //file has more than 100000 words
        if(idx >= WORD_LIMIT) {
            fprintf(stderr, "Invalid word file\n");
            
            fclose(fp);
            exit(EXIT_FAILURE);
        }

        int len = strlen(word);
        //check for length greater than 5
        if(len != 5) {
            fprintf(stderr, "Invalid word file\n");
            fclose(fp);
            exit(EXIT_FAILURE);
        }
        
        //check for any uppercase(invalid) letters
        for(int i = 0; word[i] != '\0'; i++) {
            if( !(word[i] >= 'a' && word[i] <= 'z' )) {
                 fprintf(stderr, "Invalid word file\n");
                 exit(EXIT_FAILURE);
             }
        } //end of for loop

        //add word to array
        strcpy(wordList[idx], word);
        char temp[WORD_LEN];
        strcpy(word, temp);
        idx++;
    } //end of while loop
    
    if(strlen(word) == WORD_LEN) {
        strcpy(wordList[idx], word);
        idx++;
    }

    //if file contains no words
    if(idx == 0 ) {
        fprintf(stderr, "Invalid word file\n");
        exit(EXIT_FAILURE);
		}
    
    //check for duplicates
    int arraySize = idx;
    for(int i = 0; i < arraySize && idx > 1; i++) {
        char temp[WORD_LEN + 1];
        strcpy(temp, wordList[i]);
        for(int j = i + 1; j < arraySize; j++) {
            if( strcmp(temp, wordList[j]) == 0) {
                fprintf(stderr, "Invalid word file\n");
                
                exit(EXIT_FAILURE);
            }
       }
    } //end of for loop for duplicates
    
    fclose(fp);
}

/**
 * Chooses a word from the current word list and copies that word to the word array
 * @param seed to (pseudo) randomly choose a word
 * @param word is the string that will hold the chosen word
 */
void chooseWord( long seed, char word[] )
{
    long size = idx;
    long index = ( seed % size) * MULTIPLIER % size;
    //fprintf(stderr, "sidx line 130 lex %ld", index); 
    strcpy(word, wordList[index]);
    
    
}

/**
 * Checks the given word to see if it's in the word list
 * @param word to check in list
 * @return true if word exists in list
 */
bool inList( char const word[] ) 
{
    int arraySize = idx;
    char temp[WORD_LEN + 1];
    strcpy(temp, word);
    //if(arraySize == 1) return true;
    for(int i = 0; i < arraySize; i++) {
        if( strcmp(temp, wordList[i]) == 0) {
            return true;
        }
    } //end of for loop 
    
    return false;
}