#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "io.h"


/** Large prime multiplier used to choose a word pseudo-randomly. */
#define MULTIPLIER 4611686018453
/** Maximum lengh of a word on the word list. */
#define WORD_LEN 5
/** Maximum number of words on the word list. */
#define WORD_LIMIT 100000

//static char * wordList[WORD_LIMIT];
extern char toGuess[WORD_LEN + 1];
void readWords( char const filename[] );
void chooseWord( long seed, char word[] );
bool inList( char const word[] );