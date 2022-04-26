#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/** Maximum lengh of a word on the word list. */
#define WORD_LEN 5

bool readLine( FILE *fp, char str[], int n );
void colorGreen();
void colorYellow();
void colorDefault();