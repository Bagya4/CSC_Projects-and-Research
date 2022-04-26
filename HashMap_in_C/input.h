/**
 * @file input.h
 * @author Bagya Maharajan(bmahara)
 * Header file for input component 
 */


#ifndef INPUT_H
#define INPUT_H

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

/**
 * reads single line of input of arbitrary length
 * @param fp file stream
 * @return a string dynamically allocated
 */
char *readLine( FILE *fp );

#endif