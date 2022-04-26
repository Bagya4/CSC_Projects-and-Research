/**
 * @file database.c
 * @author Bagya Maharajan(bmahara)
 * Reads and manages list of employees at startup
 */

#include "database.h"
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
 * Allocates storage for database. Similar to a construcutor
 * @return pointer to a database
 */
Database *makeDatabase() 
{
    Database *db = (Database *) malloc( sizeof(Database) );
    db->count = 0;
    db->cap = INIT_CAP;
    db->employees = (Employee **) malloc( (db->cap) * sizeof(Employee *) );
    return db;
}

/** 
 * Frees memory for the list of employees and the database
 * @param database which points to the struct
 */
void freeDatabase( Database *database )
{
    for(int i = 0; i < (database->count); i++) {
        free(database->employees[i]);
    }   
    
    free(database->employees);
    free(database);
}

/**
 * Reads all employees from a file, makes an instance for each employee,
 * stores a pinter to that struct in database's employee list
 * @param filename of file
 * @param database of employees 
 */
void readEmployees( char const *filename, Database *database )
{
    FILE *fp;
    if( (fp = fopen(filename, "r")) == NULL ) {
        fprintf(stderr, "Can't open file: %s\n", filename);
        //freeing db to prevent mem leak
        freeDatabase(database);
        exit(EXIT_FAILURE);
    }
    
    char *line;
    while( (line = readLine(fp) ) != NULL) {
        
        //employee ID
        char tempId[MAX_ID + 2] = {};
        
        sscanf(line, "%s", tempId);
        tempId[ strlen(tempId) ] = '\0';
        if(strlen(tempId) != MAX_ID ) {
           fprintf(stderr, "Invalid employee file: %s\n", filename);
           //freeing db to prevent mem leak
           freeDatabase(database);
           exit(EXIT_FAILURE);
        }
        
        
        //check for duplicate IDs
        for(int i = 0; i < database->count; i++) {
            Employee *check = database->employees[i];
            if( strcmp(tempId, check->id) == 0) {
                fprintf(stderr, "Invalid employee file: %s\n", filename);
                //freeing db to prevent mem leak
                freeDatabase(database);
                exit(EXIT_FAILURE);
            }
        }
        
        //employee first name
        char firstName[MAX_FIELDS + 2] = {};
        sscanf(line, "%s%s", tempId, firstName);
        if(strlen(firstName) > MAX_FIELDS ) {
           fprintf(stderr, "Invalid employee file: %s\n", filename);
           //freeing db to prevent mem leak
           freeDatabase(database);
           exit(EXIT_FAILURE);
        }
        firstName[strlen(firstName)] = '\0';
        
        //employee last name
        char lastName[MAX_FIELDS + 2] = {};
        sscanf(line, "%s%s%s", tempId, firstName, lastName);
        if(strlen(lastName) > MAX_FIELDS ) {
           fprintf(stderr, "Invalid employee file: %s\n", filename);
           //freeing db to prevent mem leak
           freeDatabase(database);
           exit(EXIT_FAILURE);
        }
        lastName[strlen(lastName)] = '\0';
         
        //employee skill
        char tempSkill[MAX_FIELDS + 2] = {};
        sscanf(line, "%s%s%s%s", tempId, firstName, lastName, tempSkill);
        if(strlen(tempSkill) > MAX_FIELDS || strlen(tempSkill) == 0) {
           fprintf(stderr, "Invalid employee file: %s\n", filename);
           //freeing db to prevent mem leak
           freeDatabase(database);
           exit(EXIT_FAILURE);
        }
        tempSkill[strlen(tempSkill)] = '\0';
        
        //creating an employee "object"
        Employee *e = (Employee *) malloc( sizeof(Employee) );
        strcpy(e->id, tempId);
        strcpy(e->first, firstName);
        strcpy(e->last, lastName);
        strcpy(e->skill, tempSkill);
        strcpy(e->assignment, "Available");
        
        long count = database->count;
        
        //checking for resize
        if(count >= database->cap) {
            database->cap = database->cap * 2;
            Employee **temp = realloc(database->employees, database->cap * sizeof(Employee*));
            database->employees = temp;
        } //end of resize 

        database->employees[count] = e;
        database->count = count + 1;
        
        //freeing line
        free(line);
        
     } //end of while loop
     
     fclose(fp);
}

/**
 * Sorts eemployees and prints them
 * @param database which contains all employees
 * @param compare pointer to a comparison function
 * @param test if employee should be printed
 * @param str to compare with
 */
void listEmployees( Database *database, int (*compare)( void const *va, void const *vb ), bool (*test)( Employee const *emp, char const *str ), char const *str )
{   
    
    size_t nmemb = database->count;
    qsort(database->employees, nmemb, sizeof(Employee *), compare);
		
    //printing header
    fprintf(stdout, "%-5s", "ID");
    fprintf(stdout, "%-16s", "First Name");
    fprintf(stdout, "%-16s", "Last Name");
    fprintf(stdout, "%-16s", "Skill");
    fprintf(stdout, "%s\n", "Assignment");
             
    for(int i = 0; i < database->count; i++) {
    
         Employee *e = database->employees[i];         
         if( test(database->employees[i], str) ) {
             fprintf(stdout, "%-5s", e->id);
             fprintf(stdout, "%-16s", e->first);
             fprintf(stdout, "%-16s", e->last);
             fprintf(stdout, "%-16s", e->skill);
             fprintf(stdout, "%-20s\n", e->assignment);
         }
         
    }
		
}


/**
 * Checks if a given ID exists in the database
 * @param db database
 * @param empId id
 * @return true if id is valid
 */
bool idExists(Database *db, char const empID[])
{
    for(int i = 0; i < db->count; i++) {
        Employee *e = db->employees[i];
        if(strcmp(e->id, empID) == 0) return true;
    }
    return false;
}

/**
 * Checks if an employee's assignments can be changed and changes it if so
 * @param db database
 * @param empId id
 * @param company to be assigned to
 * @return true if valid assignment
 */
bool validAssign(Database *db, char const empID[], char const company[])
{
    int i;
    for(i = 0; i < db->count; i++) {
        Employee *e = db->employees[i];
        if(strcmp(e->id, empID) == 0) break;
    }
		 
    Employee *e = db->employees[i];
    if( strcmp(company, e->assignment) == 0) return false;
    if( strcmp("Available", e->assignment) != 0) return false;
    
    strcpy(db->employees[i]->assignment, company);
    return true;
}

/**
 * Makes an employee available
 * @param db database
 * @param empId id
 * @return true if employee can be made available
 */
bool validUnassign(Database *db, char const empID[])
{
    int i;
    for(i = 0; i < db->count; i++) {
        Employee *e = db->employees[i];
        if(strcmp(e->id, empID) == 0) break;
    }
		 
    Employee *e = db->employees[i];
    if( strcmp("Available", e->assignment) == 0) return false;
    
    strcpy(db->employees[i]->assignment, "Available");
    return true;
}





