/**
 * @file input.c
 * @author Bagya Maharajan(bmahara)
 * The input component reads input from employee list files
 * and command line arguments
 */
#include "input.h"
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/**
 * reads single line of input of arbitrary length
 */
char *readLine( FILE *fp )
{
    int cap = 10;
    int len = 0;
		    
    int ch = fgetc(fp);
    
    //if no more input to read
    if(ch == EOF) return NULL;
    //line below will be FREEd in another component
    char *line = (char *) malloc(cap * sizeof(char));
    line[0] = ch;
    len++;
    
    while(ch != '\n') {
    
        if(len >= cap) {
            cap *= 2;
            char *newLine = (char *) malloc(cap * sizeof(char));
            memcpy(newLine, line, len * sizeof(char));
            free(line);
            line = newLine;
        } //end of if len >= cap
									 
        ch = fgetc(fp);
        if(ch != '\n')
          line[len++] = ch;
    } //end of while
			
    //adding null character to the end
    if(len != 0) {
    
        if(len < cap) {
            line[len] = '\0';
        }
        else {
            char *newLine = (char *) malloc((cap + 1) * sizeof(char));
            memcpy(newLine, line, len * sizeof(char));
            free(line);
            line = newLine;
            line[len] = '\0';
        }
        
    }
    //fprintf(stdout, "line 63 input file the line: %s\n", line);
    return line;
    
}