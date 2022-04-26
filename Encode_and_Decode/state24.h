/**
 * header file for state 24 which handles encoding and decoding
 */

#ifndef _STATE24_H_
#define _STATE24_H_

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>

// Include filebuffer to get the byte type.
#include "filebuffer.h"

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

/** Struct for State24 keeps track of current state and bits */
typedef struct {

  /** capacity of bits array */
  int cap;
	
  /** current length of bits array */
  int length;

  /** array of bits */
  byte *bits;
  
} State24;

/**
 * Intializes state to an empty state- empty 24 bits
 * @param state is a pointer to a State24 struct
 */
void initState( State24 *state );

/**
 * Checks if a character is one of the valid 64 encoidng characters
 * @param ch to check
 * @return true if ch is valid
 */
bool validChar( char ch ) ;

/**
 * Adds 6 bits to the state given an encoding character.
 * Used in decoding
 * @param state to add to
 * @param ch to add
 */
void addChar( State24 *state, char ch );

/**
 * Fills in array given based on the bits present in the state
 * Used in decoding.
 * @param buffer to fill in with bytes
 * @param state with array of bits
 * @return number of bytes filled in
 */
int getBytes( State24 *state, byte buffer[] );

/**
 * Adds 8 bits to the state.
 * Used in encoding
 * @param state to add 8 bits to
 * @param b is a byte whose bits are to be added
 */
void addByte( State24 *state, byte b );

/**
 * Fills given array with character codes based on sequence of bits in state.
 * Used in encoding.
 * @param state with array of bits
 * @param buffer to fill in with characters
 * @return number of characters filled
 */
int getChars( State24 *state, char buffer[] );

#endif
