/**
 * @file filebuffer.h
 * @author Bagya Maharajan(bmahara)
 * Header file for filebuffer.c
 */

#ifndef _FILEBUFFER_H_
#define _FILEBUFFER_H_

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define INIT_CAP 50

/** A shorthand for talking about a byte. */
typedef unsigned char byte;

/** Representation of a resizable array of bytes, with functions to
    make it easy to read and write the contents of this array to a
    file. */
typedef struct {
  /** Resizable array of bytes stored in this filebuffer. */
  byte *data;
  
  /** capcity of data */
  long cap;
  
  /** length of data array */
  long length;

} FileBuffer;


/**
 * Initializes file buffer
 * @return pointer to a file buffer
 */
FileBuffer *makeFileBuffer();

/**
 * Frees memory used by file buffer
 * @param buffer to free
 */
void freeFileBuffer( FileBuffer *buffer );

/**
 * appends a byte to end of buffer
 * @param val to add
 * @param buffer in use
 */
void appendFileBuffer( FileBuffer *buffer, byte val );

/**
 * Reads a binary input file, stores it in the file buffer and returns it
 * @param filename of binary inout file
 * @return FileBuffer with contents of the input file
 */
FileBuffer *loadFileBuffer( char const *filename );

/**
 * Saves contents of file buffer to a binary file
 * @param buffer with file contents
 * @param filename to output to
 */
void saveFileBuffer( FileBuffer *buffer, char const *filename );



#endif
