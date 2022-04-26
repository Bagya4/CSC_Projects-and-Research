/**
    @file map.c
    @author Bagya Maharajan(bmahara)
    Hash table implementation of a map.
*/

#include "map.h"
#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>

#include "vtype.h"


/** Node containing a key / value pair. */
typedef struct NodeStruct {
  /** Pointer to the key part of the key / value pair. */
  VType *key;

  /** Pointer to the value part of the key / value pair. */
  VType *val;

  /** Pointer to the next node at the same element of this table. */
  struct NodeStruct *next;
} Node;

/** Representation of a hash table implementation of a map. */
struct MapStruct {
  /** Table of key / value pairs. */
  Node **table;

  /** Current length of the table. */
  int tlen;

  /** Current size of the map (number of different keys). */
  int size;
};


/** Make an empty map. */
Map *makeMap( int len )
{
  Map *m = (Map *) malloc(sizeof( Map ) );
  m->tlen = len;
  m->size = 0;

  //inititializing table
  m->table = (Node **) malloc((m->tlen) * sizeof(Node *) );
  for(int i = 0; i < m->tlen; i++) {
    m->table[i] = NULL;
  }

  return m;
}

/** Get the size of the given map. */
int mapSize( Map *m )
{
  return m->size;
}

/** 
 * grows map to twice its capacity and updates values
 * @param size of original map
 * @param tlen capacity of original map
 * @param original map pointer
 */
static void grow(int size, int tlen, Map *m1)
{
  //new table
  Node **list = (Node **) malloc( (tlen * 2) * sizeof(Node *) );
  //setting new table to null
  for(int i = 0; i < (tlen * 2); i++)
    list[i] = NULL;
  
  //old table
  Node **table = m1->table;
  
  for(int j = 0; j < m1->size; j++) {
    for(Node *curr = table[j]; curr; curr = curr->next) {
      VType *k = curr->key;
      int idx = (k->hash(k)) % (tlen * 2);      
      Node *add = (Node *) malloc(sizeof(Node));
      add->key = curr->key;
      add->val = curr->val;
      add->next = list[idx];
      list[idx] = add;
    }
  }
  
  //setting old table to new table
  m1->table = (Node **) malloc ((tlen * 2) * sizeof(Node *) );
  m1->table = list;
  
  m1->tlen = (tlen * 2);
  m1->size = size;

}

/**
 * Adds or replaces key-value pair in the map
 */
void mapSet( Map *m, VType *key, VType *val )
{

  unsigned int hashC = key->hash(key); 
  //checking if negative hash
  if(hashC < 0) hashC *= -1;
	unsigned int idx = hashC % (m->tlen); //fprintf(stderr, "line 131\n");
	
	for(Node *curr = m->table[idx]; curr; curr = curr->next) {
    if(key->equals(key, curr->key)) {
      if(curr->val) (curr->val)->destroy(curr->val);
			curr->val = val;
      return; //exists = true;
    }
  }

  Node *add = (Node *) malloc(sizeof(Node));
  add->key = key;
  add->val = val; 
  add->next = m->table[idx]; 
  m->table[idx] = add;
  m->size = m->size + 1;

  //checking if size of map =  #keys
  if(m->size >= m->tlen) {
    grow(m->size, m->tlen, m);
  }
  
}

/** Return the value associated with the given key. The returned VType
    is still owned by the map.
*/
VType *mapGet( Map *m, VType *key )
{
  unsigned int hashC = key->hash(key); 
  //checking if negative hash
  if(hashC < 0) hashC *= -1;

  unsigned int idx = hashC % (m->tlen); 
	
  for(Node *curr = m->table[idx]; curr; curr = curr->next) {
    if(key->equals(key, curr->key)) {
      return curr->val;
    }
  }


  return NULL;
}

/**
 * Removes key-value pair from map amd frees it */
bool mapRemove( Map *m, VType *key )
{

  unsigned int hashC = key->hash(key);
  //checking if negative hash
  if(hashC < 0) hashC *= -1;
  
  unsigned int idx = hashC % m->tlen;

  Node *curr = m->table[idx]; 
  if(curr == NULL) {
    return false; //no element at idx
  }

  //case 1: element is at head
  if(key->equals(key, curr->key)) {
    if(curr->key) curr->key->destroy(curr->key); 
    if(curr->val) curr->val->destroy(curr->val);
    
    if(curr->next == NULL) {
      m->table[idx] = NULL; 
      m->size = m->size - 1; //dec size 
      return true;  
    
    }
    
    m->table[idx] = curr->next;
    m->size = m->size - 1; //dec size 
    return true;
  }
  while(curr != NULL) {
    if(key->equals(key, curr->key)) {
      
      if(curr->key) curr->key->destroy(curr->key);
      if(curr->val) curr->val->destroy(curr->val);
      
      if(curr->next != NULL) {
        curr->key = curr->next->key;
        curr->val = curr->next->val;
        curr->next = curr->next->next;
        m->size = m->size - 1; //dec size 
        return true; 
      }
      

      curr = NULL;

      //decreasing size
      m->size = m->size - 1;
      return true;
    } //end of if

    curr = curr->next;

  } //end of while

  return false; //key could not be found;

}

/** Free all the memory used to store a map, including all the
    memory in its key/value pairs. */
void freeMap( Map *m )
{

  //free each entry too. free Vtype

  //freeing hash table first
  for(int i = 0; i < m->tlen; i++) {
    Node *n = m->table[i]; 
    while(n) {
      Node *next = n->next;
      if(n->key) n->key->destroy(n->key); //null would return false
      if(n->val) n->val->destroy(n->val);
      n = next;
    }


    free(m->table[i]);

  } //end of for loop

  free(m->table);

  free( m );
}
