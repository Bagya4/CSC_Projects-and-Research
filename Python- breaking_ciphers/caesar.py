

def main():
	x = 'WWJFQNWCQJCMRMWXCJPANNQNCXXTLJVXVRUNCNJCQJCWJBCHXUMVJWXOERNWWJRUUDBCAJRXWCQNANFJBJWXUMVJWRWJKXJCFQXB'
	
	

	for num in range (1, 27):
		word = ''
		
		for ch in x:
			n = ord(ch) + num
			if(n > 90):
				n = n - 90
				n = 64 + n

			word += chr(n)
			
		print( chr(num + 64) + ' is the key and word = ' + word )
		print('\n')

			# str += (ch + let)

if __name__ == '__main__':
	main()


