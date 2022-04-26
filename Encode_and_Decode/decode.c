/**
 * @file decode.c
 * @author Bagya Maharajan(bmahara)
 * Decodes a text file back to its original binary file
 */

#include "filebuffer.h"
#include "state24.h"

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define MIN_ARGS 3
#define MAX_STATE_BITS 24
#define MAX_BYTES 3

/** Helper function to print out error message for incorrect 
    command line  input */
static void UsageErr()
{
  fprintf(stderr, "usage: encode [-b] [-p] <input-file> <output-file>\n");
  exit(EXIT_FAILURE);
}

/** Helper function for printing a message to standard error for an 
    invalid inout file */
static void InvInpFile()
{
  fprintf(stderr, "Invalid input file\n");
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
  char *inp;
  char *out;
	
  if(argc == MIN_ARGS) {
    inp = argv[1];
    out = argv[2];
  }
  
  //invalid number of args
  else UsageErr();
	
  //reading input from txt file
  FILE *fp;
  if((fp = fopen(inp, "r")) == NULL) {
    perror(inp);
    fclose(fp);
    exit(EXIT_FAILURE);
  }
  
  //initializing state
  State24 state;
  initState(&state);
  
  //decoding
  
  
  char ch;
  //creating file buffer
  FileBuffer *fb = makeFileBuffer();
  
  bool add = false;
  int equal = 0;
  
  while((ch = fgetc(fp)) != EOF) {
  
    if(isspace(ch)) continue;
    
    //ignoring =
    if(ch == '=') {
      equal++;
      continue;
    } //end of if ch == =
    
    //checking if valid char
    if(!validChar(ch) && ch != '\n') InvInpFile();
    if(validChar(ch) && equal != 0) InvInpFile();
    
    addChar(&state, ch);
    add = false;
    if((&state)->length == MAX_STATE_BITS) { //24
      byte byteBuff[MAX_BYTES]; //3
      getBytes(&state, byteBuff);
      
      //adding to file buffer
      appendFileBuffer(fb, byteBuff[0]);
      appendFileBuffer(fb, byteBuff[1]);
      appendFileBuffer(fb, byteBuff[2]);
      add = true;
    }
  
  
  } //end of while
  
  if(!add) {
    //if there are remaining characters
    byte byteBuff[MAX_BYTES];
    int actual = getBytes(&state, byteBuff);
    
    for(int i = 0; i < actual; i++)
      appendFileBuffer(fb, byteBuff[i]);
      
      
  } //end of if !add
  
  
  saveFileBuffer(fb, out);
  
  freeFileBuffer(fb);
  fclose(fp);
  
  exit(EXIT_SUCCESS);
  
  
}



