driver: driver.o map.o input.o integer.o text.o vtype.o
				gcc driver.o map.o input.o integer.o text.o vtype.o -o driver
										 					 
driver.o: driver.c map.h input.h integer.h text.h vtype.h
					gcc -Wall -std=c99 -c -g driver.c
										 					 
map.o: map.c map.h vtype.h
					gcc -Wall -std=c99 -c -g map.c

integer.o: integer.c integer.h vtype.h
					gcc -Wall -std=c99 -c -g integer.c
														 		
text.o: text.c text.h vtype.h
						gcc -Wall -std=c99 -c -g text.c

input.o: input.c input.h
				 gcc -Wall -std=c99 -c -g input.c

vtype.o: vtype.c vtype.h
				 gcc -Wall -std=c99 -c -g vtype.c
				 		 			 					 
clean:
			rm -f *.o driver output.txt stderr.txt stdout.txt
