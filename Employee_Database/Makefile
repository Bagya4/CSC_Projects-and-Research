agency: agency.o database.o input.o
				gcc agency.o database.o input.o -o agency

agency.o: agency.c database.h input.h
					gcc -Wall -std=c99 -c -g agency.c

database.o: database.c database.h input.h
						gcc -Wall -std=c99 -c -g database.c

input.o: input.c input.h
				 gcc -Wall -std=c99 -c -g input.c
				 		 			 					 
clean:
			rm -f *.o agency output.txt stderr.txt
