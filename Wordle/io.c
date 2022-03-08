/**
 * @file io.c
 * @author Bagya Maharajan(bmahara)
 * Helps with terminal input and output
 */
#include "io.h"
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/** Maximum lengh of a word on the word list. */
#define WORD_LEN 5


/**
 * Reads a line of text from the file, stores it in str for first n characters only.
 * @param fp is input stream
 * @param str is the word which is an array of characters
 * @param n is max length of word
 * @return true if a line could be read
 */
bool readLine( FILE *fp, char str[], int n ) 
{
    int i = 2;
    int ch = fgetc(fp);
    if(ch == EOF) return false;
    
    str[0] = ch;
    while( ch != '\n' && ch != '\0') {
        if(ch == EOF) return false;
        ch = fgetc(fp);
        if(i <= n) {
            str[i - 1] = ch;
        }
        
        if(ch != '\n') i++;
        //i++;
        
        if(ch == EOF) return false;
    }
    
    str[WORD_LEN] = '\0';
    
    if(i > n + 1) {
        fprintf(stderr, "Invalid word file\n");
        fclose(fp);
        exit(EXIT_FAILURE);
    }
    if(ch == '\n') return true;
    if(i == 0 && ch == EOF) return false;
    if(ch == EOF) return false;
    
    
    return true;
}

/**
 * Changes text color to green
 */
void colorGreen() 
{
    fprintf(stdout, "\033[32m");
    
}

/**
 * Changes text color to yellow
 */
void colorYellow() 
{
    fprintf(stdout, "\033[33m");
}

/**
 * Changes text color to black
 */
void colorDefault() 
{
    fprintf(stdout, "\033[0m");
}