all: encode decode
										 					 
encode: encode.o state24.o filebuffer.o
				gcc encode.o state24.o filebuffer.o -o encode
										 					 
encode.o: encode.c state24.h filebuffer.h
					gcc -Wall -std=c99 -c -g encode.c

decode: decode.o state24.o filebuffer.o
				gcc decode.o state24.o filebuffer.o -o decode
										 					 
decode.o: decode.c state24.h filebuffer.h
					gcc -Wall -std=c99 -c -g decode.c


state24.o: state24.c state24.h filebuffer.h
						gcc -Wall -std=c99 -c -g state24.c

filebuffer.o: filebuffer.c filebuffer.h
				 gcc -Wall -std=c99 -c -g filebuffer.c
				 		 			 					 
clean:
			rm -f *.o encode decode output.txt stderr.txt stdout.txt
