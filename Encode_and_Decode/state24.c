/**
 * @file state24.c
 * @author Bagya Maharajan(bmahara)
 * Stores current state of encoding or decoding, represented up to 24 bits.
 * Converts between bytes and encoding characters for 24 bits at a time. 
 */


#include "state24.h"
#include "filebuffer.h"
#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>

#define MAX_CAP_BYTES 3
#define MAX_BITS 8
#define ASCII_A 65
#define ASCII_LOWER 97
#define ASCII_Z 90
#define CHAR_BITS 6
#define INT_Z 25
#define INT_LOW_A 26
#define ENC_PLUS 62
#define ENC_SLASH 63
#define ENC_Z 51
#define ASCII_ZERO 48

/** variable to keep track of buffer array charcters */
int buffIdx;
/** variable to keep track of buffer array bytes */
int getByteIdx;


/**
 * Intializes state to an empty state- empty 24 bits
 * @param state is a pointer to a State24 struct
 */
void initState( State24 *state )
{
  state->length = 0;
  state->cap = MAX_CAP_BYTES; //3 bytes = 24 bits
  
  //24 bits- empty
  state->bits = (byte *) malloc( (state->cap) * sizeof(byte) );
  
  buffIdx = -1;
  getByteIdx = -1;
  
}

/**
 * Checks if a character is one of the valid 64 encoidng characters
 * @param ch to check
 * @return true if ch is valid
 */
bool validChar( char ch ) 
{
  if(isalpha(ch) || isdigit(ch)) return true;
  
  if(ch == '+' || ch == '/') return true;
  
  return false;
}

/**
 * Adds 6 bits to the state given an encoding character.
 * Used in decoding
 * @param state to add to
 * @param ch to add
 */
void addChar( State24 *state, char ch )
{
  int num = 0;
  if(isupper(ch)) {
    num = ch - ASCII_A;
  }
  
  if(islower(ch)) {
    num = (ch - ASCII_LOWER) + ASCII_Z + 1 - ASCII_A;
  }
	
  if(isdigit(ch)) {
    num = ENC_Z + (ch - '0') + 1; // 51 + (ch - '0') + 1
  } //max 611
	
	if(ch == '+') {
	  num = ENC_PLUS; //62
  }
	
  if(ch == '/') {
    num = ENC_SLASH; //63
  }

  int idx = state->length;
  for(int i = (1 << (CHAR_BITS - 1)); i > 0; i /= 2) { //char_bits = 6
    state->bits[idx] = (num & i) ? 1 : 0;
    idx++;
    state->length += 1;
  }
  
}

/**
 * Fills in array given based on the bits present in the state
 * Used in decoding.
 * @param buffer to fill in with bytes
 * @param state with array of bits
 * @return number of bytes filled in
 */
int getBytes( State24 *state, byte buffer[] )
{
  int value = 0;

  int count = 0;
  int exp = MAX_BITS - 1; //7
  int maxBytes = state->length / MAX_BITS; //length / 8; rounding down
  for(int i = 0; i < (maxBytes * MAX_BITS); i++) {
    if(i % MAX_BITS == 0) {
      value = 0;
      getByteIdx++;
      exp = MAX_BITS - 1; //8 - 1
    }
    
    int power2 = 1;
    for(int i = 0; i < exp; i++) power2 *= 2;
    
    value += (state->bits[i] * power2);
    count++;
    exp--;
    if(count % MAX_BITS == 0) {
      buffer[getByteIdx] = (byte) value;
      //printf("line 117 s24 value: %d\n", value);
    }
    
  } //end of for loop
  
  
  //freeing bits array in state
  free(state->bits);
  //starting all over from length 0
  initState(state);
	
  

  return maxBytes;
  
  
}

/**
 * Adds 8 bits to the state.
 * Used in encoding
 * @param state to add 8 bits to
 * @param b is a byte whose bits are to be added
 */
void addByte( State24 *state, byte b )
{
  int ptr = 0;
  
  for(int i = (1 << (MAX_BITS - 1)); i > 0; i /= 2) {
      int idx = ptr + state->length;
      state->bits[idx] = (b & i) ? 1 : 0;
      state->length += 1;
  }
  
}

/**
 * Finds the character encoded from int decimal value.
 * Helper method for getChar
 * @param val decimal value to encode
 * @return character from the 64-bit encoding values table
 */
static char calcChar(int val)
{
  int charConv = 0;
  
  //uppercase letters
  if(val <= INT_Z) { //25
    charConv = val + ASCII_A;
  }
  
  else {
    
    // + sign
    if(val == ENC_PLUS) { //62
      return '+';  
    }
  
    // / sign
    if(val == ENC_SLASH) { //63
      return '/';
    }
    
    //digits
    if(val > ENC_Z && val < ENC_PLUS) { //52-61 incl
      charConv = ASCII_ZERO + (val - (ENC_Z + 1)); //48 + (val - 52)
    }
    
    //lower case letters
    else
      charConv = ASCII_LOWER + (val - INT_LOW_A); //97 + (val - 26)
    
  } //end of of else
  
  //converting from ascii to char
  return (unsigned char)(charConv);

}

/**
 * Fills given array with character codes based on sequence of bits in state.
 * Used in encoding.
 * @param state with array of bits
 * @param buffer to fill in with characters
 * @return number of characters filled
 */
int getChars( State24 *state, char buffer[] )
{
  int round = 1;
  
  if(state->length % CHAR_BITS == 0) {
    round = 0;
  }
	
  int maxChars = (state->length / CHAR_BITS) + round; //CHAR_BITS 6
  int finalInt = 0;
  //int buffIdx = -1;
  int count = 0;
  int exp = CHAR_BITS - 1; //5
  
  for(int i = 0; i < maxChars * CHAR_BITS; i++) {
    
    if(i % CHAR_BITS == 0) {
      finalInt = 0;
      buffIdx++;
      exp = CHAR_BITS - 1; //6 -1
    }
    
    //padding with 0
    int add = 0;
    //no padding if bit value in state's array is filled
    if(i < state->length) {
      add = state->bits[i];
    }
    int power2 = 1;
    for(int i = 0; i < exp; i++) power2 *= 2;
    
    finalInt += (add * power2);
    count++;
    exp--;
    
    if(count % CHAR_BITS == 0) {
      char ch = calcChar(finalInt);
      buffer[buffIdx] = ch;
    }
	
	
  } //end of for loop
	
  //freeing bits array in state
  free(state->bits);
  //starting all over from length 0
  state->bits = (byte *) malloc( (state->cap) * sizeof(byte) );
  state->length = 0;
  
	return maxChars;

}


