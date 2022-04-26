/**
 * @file text.c
 * @author Bagya Maharajan(bmahara)
 * Implementation of text- subclass of VType
 */

#include "vtype.h"
#include "text.h"

#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

#define HASH_3 3
#define HASH_6 6
#define HASH_10 10
#define HASH_11 11
#define HASH_15 15

// print method for text.
static void print( VType const *v )
{
  // Convert the VType pointer to a more specific type.
  Text const *this = (Text const *) v;
  printf( "\"%s", this->val );
  printf( "\"");
}

// equals method for text.
static bool equals( VType const *a, VType const *b )
{
  // Make sure the b object is also a text.
  // (i.e., it uses the same print funtion)
  if ( b->print != print )
    return false;

  // Compare the val fields inside a and b.
  Text const *this = (Text const *) a;
  Text const *that = (Text const *) b;

  if( strcmp(this->val, that->val) == 0 ) return true;

  return false;
}

// hash method for text.  It hashes to the int value,
// descriv=bed by Jenkins hash code
static unsigned int hash( VType const *v )
{ 
  
  // Convert the VType pointer to a more specific type.
  Text const *this = (Text const *) v;
  this->val[strlen(this->val)] = '\0';
  
  int i = 0;
  unsigned int hash = 0;
  int len = strlen(this->val); 
  char *str = this->val;
  while(i != len) {
    hash += (int) str[i++];
    hash += hash << HASH_10;
    hash ^= hash >> HASH_6;
  
  }
  
  hash += hash << HASH_3;
  hash ^= hash >> HASH_11;
  hash += hash << HASH_15;
  
  return hash;
  
}

// destroy method for text.
static void destroy( VType *v )
{
  // text is just one block of heap memory.
  free( v );
}

VType *parseText( char const *init, int *n )
{
  // Make sure the string is in the right format.

  char *val = (char *) malloc( (strlen(init)) * sizeof(char));
  int len = 0; //idx
  int nval = 0;
  int quote = 0;  
  
  for(int i = 0; init[i]; i++) {
    if(init[i] == '\"') { 
      quote++;
      continue;
    } 
    
    if(quote < 2) {
      if((isspace(init[i]) && quote == 1) || (!isspace(init[i]))) {
        val[len] = init[i]; 
        len++;
      }
      nval++;
     } 
  
  } //end of for loop
  
  if((quote % 2) != 0 || quote == 0) {
    free(val);
		return NULL;
	}
  val[len] = '\0';
  
  if(n) *n = nval + 2;
  
  // Allocate an Integer on the heap and fill in its fields.
  Text *this = (Text *) malloc( sizeof( Text ) );

  this->val = val; 
  this->print = print;
  this->equals = equals;
  this->hash = hash;
  this->destroy = destroy;
  
  // Return it as a poitner to the superclass.
  return (VType *) this;
}
