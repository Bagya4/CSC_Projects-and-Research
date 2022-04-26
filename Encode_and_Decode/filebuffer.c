/**
 * @file filebuffer.c
 * @author Bagya Maharajan(bmahara)
 * Low-level component to access input files and build output files
 */

#include "filebuffer.h"
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define INIT_CAP 50

/**
 * Initializes file buffer
 * @return pointer to a file buffer
 */
FileBuffer *makeFileBuffer()
{
    FileBuffer *fb = (FileBuffer *) malloc( sizeof(FileBuffer) );
    fb->cap = INIT_CAP;
    fb->length = 0;
    fb->data = (byte *) malloc( (fb->cap) * sizeof(byte) );
    
    return fb;
}

/**
 * Frees memory used by file buffer
 * @param buffer to free
 */
void freeFileBuffer( FileBuffer *buffer )
{
    free(buffer->data);
    free(buffer);
}

/**
 * appends a byte to end of buffer
 * @param val to add
 * @param buffer in use
 */
void appendFileBuffer( FileBuffer *buffer, byte val )
{
    //growing buffer data if capacity is reached
    if((buffer->length + 1) >= buffer->cap) {
        buffer->cap *= 3;
        buffer->data = (byte *) realloc(buffer->data, buffer->cap);
    }

    long len = buffer->length;
    buffer->data[len] = val;
    buffer->length = len + 1;
}

/**
 * Reads a binary input file, stores it in the file buffer and returns it
 * @param filename of binary inout file
 * @return FileBuffer with contents of the input file
 */
FileBuffer *loadFileBuffer( char const *filename )
{
        
    FILE *fp = fopen(filename, "rb");
    if(fp == NULL) {
        fprintf(stderr, "%s: No such file or directory\n", filename);
        exit(EXIT_FAILURE);
    }
    
    //making file buffer
    FileBuffer *fb = makeFileBuffer();
    int len = 0;
    byte curr;
    
    //reading values from input file   
    while( (len = fread(&curr, sizeof(byte), sizeof(curr), fp)) != 0) {
      appendFileBuffer(fb, curr);
    
    }

    //closing buffer before returning fb
    fclose(fp);
    
    return fb;
    
    
}

/**
 * Saves contents of file buffer to a binary file
 * @param buffer with file contents
 * @param filename to output to
 */
void saveFileBuffer( FileBuffer *buffer, char const *filename )
{
    FILE *out;
    
    if((out = fopen(filename, "wb")) == NULL) {
        perror(filename);
        fclose(out);
        exit(EXIT_FAILURE);
    }
    
    fwrite(buffer->data, sizeof(byte), buffer->length, out);
    fclose(out);
    
}

