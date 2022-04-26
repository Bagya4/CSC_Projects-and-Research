/**
 * @file agency.c
 * @author Bagya Maharajan(bmahara)
 * Top-level component that reads command line input and manages database
 */

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#include "input.h"
#include "database.h"

#define MAX_PARAM2_LIST 10
#define MAX_CMD 8
#define MAX_ASSIGN 20
#define MAX_SKILL 15
#define MAX_ID 4

/**
 * Checks if an employee's skill matches with the required skill
 * @param emp employee
 * @param str skill required
 * @return true if skill matches or str is null, else false
 */
static bool testSkill(Employee const *emp, char const *str) 
{
    if(str == NULL) return true;
    
    if( strcmp(emp->skill, str) == 0 )
        return true;
    
    return false;
}

/**
 * Checks if an employee's assignment matches with the required assignment
 * @param emp employee
 * @param str assignment
 * @return true if assignment matches or str is null, else false
 */
static bool testAssign(Employee const *emp, char const *str) 
{
    if(str == NULL) return true;
    
    if( strcmp(emp->assignment, str) == 0 )
        return true;
    
    return false;
}

/**
 * compares employee IDs to help with sorting
 * @param va pointer a
 * @param vb pointer b
 * @return 0 if equal, -1 if va should be before vb, else 1
 */
static int compare(void const *va, void const *vb)
{
    
    Employee const *a = *((Employee**)va);
    Employee const *b = *((Employee**)vb);
    
    if( (strcmp(a->id, b->id)) < 0) {
        return -1;
    }

    if( (strcmp(a->id, b->id)) > 0) {
        return 1;
    }
    
    return 0;
}

/**
 * compares employee skills to help with sorting in lexicographic order
 * @param va pointer a
 * @param vb pointer b
 * @return 0 if equal, -1 if va should be before vb, else 1
 */
static int compareSkillLex(void const *va, void const *vb)
{
    
    Employee const *a = *((Employee**)va);
    Employee const *b = *((Employee**)vb);
    
    if( strcmp(a->skill, b->skill) < 0) {
        return -1;
    }

    if( strcmp(a->skill, b->skill) > 0) {
        return 1;
    }
    
    if( strcmp(a->skill, b->skill) == 0 ) {
        return compare(va, vb);
    }
    
    return 0;
}


/**
 * Calls functions form other programs and processes command line input
 * @param argc is number of arguments
 * @param argv is an array of pointers to the arguments
 * @return 0 for sucessful execution of the program else 1
 */
int main(int argc, char *argv[])
{
    //if no filename mentioned
    if(argc <= 1) {
        fprintf(stderr, "usage: agency <employee-file>*\n");
        return EXIT_FAILURE;
    }
    
    //creating database
    Database *db = makeDatabase();
    
    //reading all files into database
    for(int i = 1; i < argc; i++) {
        readEmployees(argv[i], db);        
    }
    
    bool loop = true;
    char input[50];
    int firstCMD = 0;
    
    while(loop) { 
                
        if(firstCMD == 0) {
            fprintf(stdout, "cmd> ");
            firstCMD++;
        }
        else 
            fprintf(stdout, "\ncmd> ");

        int inp = fscanf(stdin, "%[^\n]%*c", input);
        if(inp == EOF) {
            freeDatabase(db);
            return EXIT_SUCCESS;
        }
							 				 
        input[ strlen(input) ] = '\0';
        
        if(strcmp(input, "quit") == 0) {
            freeDatabase(db);
            //echoing cmd 
            fprintf(stdout, "%s\n", input);
            return EXIT_SUCCESS;
        }
        
        char command[MAX_CMD + 1]; //8+1
        
        sscanf(input, "%s", command);
        command[ strlen(command) ] = '\0';
        
        //cmd all lists
        if( strcmp(command, "list") == 0) {
        
            if( strcmp(command, input) == 0 ) {
                //cmd list
                  //compare function
                int (* comp) (void const *va, void const *vb);
                comp = compare;
                  //dummy test function. returns true since str passed will be null
                bool (* test) (Employee const * emp, char const * str);
                test = testSkill;
                
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                //fprintf(stdout, "%ld count line 140 ag\n", db->count);
                
                listEmployees(db, comp, test , NULL);
                continue;
            } //end of cmd list
        
            char param2[MAX_PARAM2_LIST + 1]; //10+1
            sscanf(input, "%s%s", command, param2);
            param2[ strlen(param2) ] = '\0';
            
            //cmd list skill
            if( strcmp(param2, "skill") == 0) {
                
                char param3[MAX_SKILL + 2]; //15+2
                sscanf(input, "%s%s%s", command, param2, param3);
                param3[ strlen(param3) ] = '\0';
                
                if( strlen(param3) > MAX_SKILL || strlen(param3) == 0) {
                    fprintf(stdout, "Invalid command\n");
                    continue;
                }
                
                bool run = true;
                //param3 should not be more than 1 word
                for(int a = 0; param3[a]; a++) {
                    if(param3[a] == ' ') {
                        fprintf(stdout, "Invalid command\n");
                        run = false;
                        break;
                    }
                }
                
                if(run) {
                    //list skill x
                      //compare function
                    int (* comp) (void const *va, void const *vb);
                    comp = compare;
                     //test function for printing only emp with skill x
                    bool (* test) (Employee const *emp, char const *str);
                    test = testSkill;
                    
                    //echoing cmd
                    fprintf(stdout, "%s\n", input);
                
                    listEmployees(db, comp, test , param3);
                    continue;
                }
                
                
            } //end of cmd list skill x
            
            
            //cmd list assignment
            if( strcmp(param2, "assignment") == 0 ) {
                char param3[MAX_ASSIGN + 2]; //20+2          
                sscanf(input, "%s%s%s", command, param2, param3);
                param3[ strlen(param3) ] = '\0';
                
                if( strlen(param3) > MAX_ASSIGN || strlen(param3) == 0) {
                    //echoing cmd
                    fprintf(stdout, "%s\n", input);
                    fprintf(stdout, "Invalid command\n");
                    continue;
                }
                
                char extra[MAX_ASSIGN + 2]; //20+2          
                sscanf(input, "%s%s%s%s", command, param2, param3, extra);
                
                if( strlen(extra) > 0) {
                    //echoing cmd
                    fprintf(stdout, "%s\n", input);
                    fprintf(stdout, "Invalid command\n");
                    continue;
                }
						 	
                bool run = true; //dummy variable
                
                if(run) {
                    //list assignment x
                      //compare skill lex and then ID
                    int (* comp) (void const *va, void const *vb);
                    comp = compareSkillLex;
                    
                     //test function for printing only emp with skill x
                    bool (* test) (Employee const *emp, char const *str);
                    test = testAssign;
                    
                    //echoing cmd
                    fprintf(stdout, "%s\n", input);
                
                    listEmployees(db, comp, test , param3);
                    continue;
                }
						 	
		 				    
            } //end of cmd list assignment x
            
            
            else {
                //invalid input
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
            } //cmd list wrong
						
        } //end of cmd all list
        
        
        //cmd assign
        if( strcmp(command, "assign") == 0) {
            if( strcmp(command, input) == 0) {
                //only cmd assign is invalid
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
             }
					  
            //cmd assign ID
            char empId[MAX_ID + 2]; //4+2
            sscanf(input, "%s%s", command, empId);
            empId[ strlen(empId) ] = '\0';
            
            //ID != 4
            if( strlen(empId) != 4) {
                fprintf(stdout, "Invalid command\n");
                continue;
            }
			      
            if( !idExists(db, empId) ) {
                //ID DNE
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
            }
            
            //cmd assign ID company
            char company[MAX_ASSIGN + 2];
            sscanf(input, "%s%s%s", command, empId, company);
            company[ strlen(company) ] = '\0';
            
            if(strlen(company) > 20) {
                //company name > 20
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
            }
					  
            //company should not be more than 1 word
            bool run = true;
            for(int a = 0; company[a]; a++) {
                if(company[a] == ' ') {
                    fprintf(stdout, "Invalid command\n");
                    run = false;
                    break;
                 }
            }
            
            if(!run) continue;
            
            if( validAssign(db, empId, company) ) {
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                continue;
            }
            
            //could not assign
            else {
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
            }				 
            
        } //end of cmd assign id company
			  
			  
        //cmd unassign
        if( strcmp(command, "unassign") == 0) {

            //cmd unassign ID
            char empId[MAX_ID + 2]; //4+2
            sscanf(input, "%s%s", command, empId);
            empId[ strlen(empId) ] = '\0';
            
            //ID != 4
            if( strlen(empId) != 4) {
                fprintf(stdout, "Invalid command\n");
                continue;
            }
			      
 		        if( !idExists(db, empId) ) {
                //ID DNE
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
            }
            
            if( validUnassign(db, empId) ) {
               //valid ID where employee is not already available
               //echoing cmd 
               fprintf(stdout, "%s\n", input);
               continue;
            }
            
            else {
                //echoing cmd
                fprintf(stdout, "%s\n", input);
                fprintf(stdout, "Invalid command\n");
                continue;
            }
            
        } //end of cmd unassign id
			 
				 										
        //cmd invalid
        else {
            //echoing cmd
            fprintf(stdout, "%s\n", input);
            fprintf(stdout, "Invalid command\n");
        }
        
        
        
    } //end of while-loop
}