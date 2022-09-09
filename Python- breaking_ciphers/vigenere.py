import string

# IOC formula from practicalcryptography.com
def index_of_coincidence(c):
	freq = [0] * 26
	for ch in c:
		freq[ord(ch) - 65] += 1
	f_i = 0
	N = 0
	for i in range(26):
		f_i += freq[i] * (freq[i] - 1)
		N += freq[i]
	return 26 * (f_i / (N * (N - 1)))


def main():
	c1 = 'ZJABRJQEAQDCTVAFKABYLEKLWEKRUABYLEKLWEKFNNNZYEZJORPZLXZKOQZQQIKBRJQEAQDCTVAFKABYLEKLWEKRUABYLRUDZKOMTMAFVZSXLVYZIRXYLDSJKYPZKBXIVPALMGVXEJTGOMHNBZJOOY'
	c2 = 'PVYEZQIKLYVUWPTURUQFUEUOKZWTKAICWTGKMKZMSXVXATKBGJAQSBWWZGHBBZAGZRWEJAJNQJKUYCQENGOBPVKRXXUTPBNNZRJA'
	c3 = 'JNKFUEOBBJVRZQWLZMTMAFMIUALJWTXJAVZXKCBVYEGWLJFXRJJCLECRBYATKLWULIUALJVDIXLVUGSKMIZAXVWILSKWMIHXRHBY'
	c4 = 'LOUMMXYAAYAKOMZAMGSMINBYLEKYTRPZZNFKLXKVMEAEOWIJLZYNITVPKLWDWDOBMJHSOPIEAUILQGOQXJTGOMHNBZUINRKYATKK'
	c5 = 'IJPOVUIZUFKGBLUUZRAKOQCXZUVDZQMGODGBMJFXRJJCLEGWLC'

	ciphertext = c1+c2+c3+c4+c5
	#ciphertext_example = 'NWAIWEBBRFQFOCJPUGDOJVBGWSPTWRZ'

	# period calculation formula from https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-IOC.html#English-Freq-Table

	key_found = False
	period = 1
	list_IOCs = []
	dictn = {}
	while period <= 10:
		period += 1
		splits = [''] * period
		for idx in range(len(ciphertext)):
			splits[idx % period] += ciphertext[idx]
		sum_ioc = 0
		for i in range(period):
			sum_ioc += index_of_coincidence(splits[i])
		ioc = sum_ioc / period
		if ioc > 1.73:
			list_IOCs.append(ioc)
			dictn[ioc] = period

	actual_IOC = max(list_IOCs)
	actual_prd = dictn[actual_IOC]
	# print("period is: {}".format(actual_prd))


	#counting freq in each n=6 block. vary what mod is equal to as needed
	idx = 0
	str1 = ''
	for let in ciphertext:
		if(idx % actual_prd == 5) :
			str1 += let
		idx += 1

	# using the keyword recovery method with the chi-squared method
	# chi-squared method from https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Recover.html

	shift = 1

	while(shift <= 25) :
		#shifting by shift
		shiftedC = ''

		for ch in str1:
				n = ord(ch) - shift
				if(n < 65):
					n = 91 - (65 - n)
					#n = 64 + n

				shiftedC += chr(n)


		# print('string 1 is ' + str1)
		# print('shifted string is ' + shiftedC)

		#freq of letters in new c
		alpha_freq = [0]*26
		for char in shiftedC:
			alpha_freq[ord(char) - 65] += 1

		letter_frequency = {'A' : 8.15, 'B' : 1.44, 'C' : 2.76, 'D':3.79, 'E':13.11, 'F' : 2.92 , 'G' : 1.99, 'H':5.26, 'I':6.35, 'J':0.13, 'K':0.42, 'L':3.39, 'M':2.54, 'N':7.10, 'O':8.00, 'P':1.98, 'Q': 0.12, 'R': 6.83, 'S':6.10, 'T':10.47, 'U':2.46, 'V':0.92, 'W':1.54, 'X': 0.17, 'Y':1.98, 'Z' : 0.08}

		#sum from 0 to idx 25
		lamda2 = 0
		for idx in range(0, 26) :
			key_let = chr(65 + idx)
			numerator = ( (alpha_freq[idx] / 10.0) - (letter_frequency[key_let] / 100.0)  )
			numerator *= numerator	
			denominator = letter_frequency[key_let] / 100.0
			value = numerator / denominator
			lamda2 += value;

		# print('the lamda2 anser for shift of {} is : {}'.format(shift, lamda2))
		shift += 1


	#key found from table = IRHMGJ
	temp_key = ['I', 'R', 'H', 'M', 'G', 'J']
	plaintxt = ''
	i = 0

	for ch in ciphertext:
		mod = i % 6
		k = temp_key[mod]
		#ch moves by temp_key[i]
		n = ord(ch) - ord(k)
		if(n < 0):
			
			n = 91 + n 

		else :
			n = 65 + n
		
		plaintxt += chr(n)
		i += 1


	print(plaintxt)


if __name__ == '__main__':
	main()





