/** 
    @file text.h
    @author Bagya Maharajan(bmahara)
    Header for the Text subclass of VType
*/

#ifndef TEXT_H
#define TEXT_H

#include "vtype.h"

#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

/** constant 3 for Jenkins hash calculation */
#define HASH_3 3
/** constant 6 for Jenkins hash calculation */
#define HASH_6 6
/** constant 10 for Jenkins hash calculation */
#define HASH_10 10
/** constant 11 for Jenkins hash calculation */
#define HASH_11 11
/** constant 15 for Jenkins hash calculation */
#define HASH_15 15

/** Subclass of VType for storing integers. */
typedef struct {
  /** Inherited from VType */
  void (*print)( struct VTypeStruct const *v );

  /** Inherited from VType */
  bool (*equals)( struct VTypeStruct const *a,
                  struct VTypeStruct const *b );

  /** Inherited from VType */
  unsigned int (*hash)( struct VTypeStruct const *b );

  /** Inherited from VType */
  void (*destroy)( struct VTypeStruct *v );

  /** Value stored by this text. */
  char *val;
} Text;

/** Make an instance of text holding a value parsed from the init string.
    @param init String containing the initializaiton value as text.
    @param n Optional return for the number of characters used from init.
    @return pointer to the new VType instance.
*/
VType *parseText( char const *init, int *n );


#endif