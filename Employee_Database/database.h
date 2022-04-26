#include "input.h"
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ID 4
#define INIT_CAP 50
#define MAX_FIELDS 15
#define MAX_ASSIGN 20

/**
 * Struct for employee
 */
typedef struct {
    char id[MAX_ID + 1];
    char first[MAX_FIELDS + 1];
    char last[MAX_FIELDS + 1];
    char skill[MAX_FIELDS + 1];
    char assignment[MAX_ASSIGN + 1];
} Employee;

/**
 * Struct for database
 */
typedef struct {
    Employee **employees;
    long count;
    long cap;
} Database;


Database *makeDatabase() ;
void freeDatabase( Database *database );
void readEmployees( char const *filename, Database *database );
void listEmployees( Database *database, int (*compare)( void const *va, void const *vb ), bool (*test)( Employee const *emp, char const *str ), char const *str ); 

bool idExists(Database *db, char const empID[]);
bool validAssign(Database *db, char const empId[], char const company[]);
bool validUnassign(Database *db, char const empID[]);