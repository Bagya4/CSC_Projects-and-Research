wordle: wordle.o lexicon.o io.o history.o
				gcc wordle.o lexicon.o io.o history.o -o wordle

wordle.o: wordle.c lexicon.h io.h history.h
					gcc -Wall -std=c99 -c -g wordle.c

lexicon.o: lexicon.c lexicon.h io.h
					 gcc -Wall -std=c99 -c -g lexicon.c

io.o: io.c io.h
			gcc -Wall -std=c99 -c -g io.c

history.o: history.c history.h
					 gcc -Wall -std=c99 -c -g history.c
  
clean:
			rm -f *.o wordle output.txt stderr.txt scores.txt