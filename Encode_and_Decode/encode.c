/**
 * @file encode.c
 * @author Bagya Maharajan
 * Top-level component encode reads and input binary file and outputs
 * encoded value to a text file.
 */

#include "filebuffer.h"
#include "state24.h"

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define MIN_ARGS 3
#define MAX_ARGS 5
#define MAX_STATE_BITS 24
#define LINE_BREAK_COUNT 76

/**
 * Prints characters to a txt file after successful encoding
 * @param buffer- contents of array
 * @param filename - output file to print to
 * @param b line break disabling
 */
void outputTxt(char const buffer[], char const *filename, bool b)
{
  FILE *fout;
  if((fout = fopen(filename, "w")) == NULL) {
    perror(filename);
    fclose(fout);
    exit(EXIT_FAILURE);  
  }
  //line break count
  int count = 0;
  
  for(int i = 0; buffer[i]; i++) {
    
    if(b) { //if line break should be there
    
      if(count == LINE_BREAK_COUNT) { //76
        fprintf(fout, "\n");
        count = 0;      
      }
      
      count++;
    
    } //end of !b
    
    //printing char
    fprintf(fout, "%c", buffer[i]);
  
  } //end of for i
  
  if(count != 0 || (!b)) fprintf(fout, "\n");
  
  fclose(fout);
  
}


/** Helper function to print out error message for incorrect 
    command line  input */
static void UsageErr()
{
  fprintf(stderr, "usage: encode [-b] [-p] <input-file> <output-file>\n");
  exit(EXIT_FAILURE);
}

/**
 * Main function which receives user input and makes use of other components in the program
 * @param argc is number of arguments
 * @param argv is an array of pointers to the arguments
 * @return 0 for sucessful execution of the program else 1
 */
int main(int argc, char *argv[])
{
  if(argc < MIN_ARGS) { //argc < 3
    UsageErr();
  }

	
  char *inp;
  char *out;
	
  bool b = true;
  bool p = true;
	
  if(argc == MIN_ARGS) {
    inp = argv[1];
    out = argv[2];
  }
  
  
  else if(argc == MAX_ARGS) { //5 , 5 && dore == ENC_STATE
    //argv[1] = -b
    if(strcmp(argv[1], "-b") != 0) UsageErr();
    //argv[2] = -p
    if(strcmp(argv[2], "-p") != 0) UsageErr();
    
    b = false;
    p = false; 
    
    inp = argv[MIN_ARGS]; //3
    out = argv[MIN_ARGS + 1]; //4
    
  }
  
  //invalid number of args
  else UsageErr();
	
  FileBuffer *fb = loadFileBuffer(inp);
  
  //initializing state
  State24 state;
  initState(&state);
  
  //encoding
  
  int fileLength = fb->length;
    
    //opening an empty file for an empty input file
    if(fileLength == 0) {
      FILE *fout;
      if((fout = fopen(out, "w")) == NULL) {
        perror(out);
        fclose(fout);
        
        //freeing malloced mem
        freeFileBuffer(fb);
        
        exit(EXIT_FAILURE);  
      } //end if fout = null
      
      //freeing malloced mem
      freeFileBuffer(fb);
        
      fclose(fout);
      exit(EXIT_SUCCESS);
    
    }
    
    
    
    int remain = MAX_CAP_BYTES - (fileLength % MAX_CAP_BYTES); //3 - (length % 3)
    if(remain == MAX_CAP_BYTES) remain = 0; //3
    int getCharLen = ((fileLength + remain) * MAX_BITS) / CHAR_BITS; //8, 6

    int getCReturn = 0;
    
    //declaring char buffer
    char charBuff[getCharLen + 1]; //for null termination + 1
    
    for(int i = 0; i < fileLength; i++) { //adding one byte at a time
    
      //adding byte
      addByte(&state, fb->data[i]);
      
      //freeing state if max length of 24 reached and adding the character to buffer
      if((&state)->length == MAX_STATE_BITS) { //24
        getCReturn += getChars(&state, charBuff);
      }

    } //end of for loop i
    
    
    if(remain != 0) {
      //adding remaining bits
      getCReturn += getChars(&state, charBuff);
      
      if(p) { //if p option is disabled
      
        for(int j = 0; j < (getCharLen - getCReturn); j++) {
          //padding end with =        
          charBuff[getCharLen - remain + j] = '=';
        
        } //end of for loop j
      
      } //end of if !p
      
    
    
    } //end of if remain is 0
    
    if(!p) remain = 0;
    //null terminating char buffer depending on where it ends from padding or not
    charBuff[getCReturn + remain] = '\0';

    
    //writing to a txt output file 
    outputTxt(charBuff, out, b);
    
    //freeing malloced mem
    freeFileBuffer(fb);
    free((&state)->bits);

	
  exit(EXIT_SUCCESS);
}