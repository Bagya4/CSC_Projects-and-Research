calc10: calc.o number10.o operation.o
				gcc calc.o number10.o operation.o -o calc10
  
calc25: calc.o number25.o operation.o
				gcc calc.o number25.o operation.o -o calc25

calc.o: calc.c number.h operation.h
				gcc -Wall -std=c99 -c calc.c

operation.o: operation.c operation.h
						 gcc -Wall -std=c99 -c operation.c
						 		 			 						 						 
number10.o: number10.c number.h operation.h
						gcc -Wall -std=c99 -c number10.c
  
number25.o: number25.c number.h operation.h
						gcc -Wall -std=c99 -c number25.c


  

  


clean:
			rm -f *.o calc10 calc25 output.txt
