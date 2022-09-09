import base64

def encode(text):
    return base64.b64encode(text)

def decode(text):
    return base64.b64decode(text)

def encrypt(cleartext, key):
    to_return = bytearray(len(cleartext))
    for i in range(len(cleartext)):
        to_return[i] = cleartext[i] ^ key
    return bytes(to_return)

def main():
    c1 = '5uLn+qP27eLh7+aj9+yj8Pbz8+zx96Pr6u7w5u/luKPr5qPv6ujm9Orw5qPz5vHg5ur15uej9+vi96P36+aj8ebw96P05vHmo/Dv4uDo5u3q7eSj9+vm6vGj5uXl7PH38K+j4u3no/fr4vej8Ozu5q+j5+bw5vH35uej4fqj9+vs8Oaj6u2j9+vmo/Hm4vGvo/Tm8eaj8eb36vHq7eSj5fHs7qP36+aj4eL39+/mo+Lt56Pi9ezq5+rt5KP36+aj9Obi8+zt8Lij9+vi96P36+aj5u3m7vqj2Ozto/fr5qPs'
    c2 = '9+vm8aPr4u3n3q+j9+vs9uTro+Ln9eLt4Ort5KPl8ezuo/fr5qPv7PTm8aPk8ez27eevo/Tm8eaj7ez3o/Hm7+L76u3ko+rto+Xx7O33r6Pi7eej9Obx5qPY4vej9+vmo/Di7uaj9+ru5t6j8/Hm8P'
    c3 = 'Dq7eSj6+Lx56Ps7aPh7Pfro+Xv4u3o8Lij6+aj4u/w7KPz5vHg5ur15uej9+vi96P36+aj4uXl4urxo/Ti8KPi96Pio+Dx6vDq8K+j4u3no/fr4vej9+vm8eaj9OLwo+3s96Pi7fqj8ebw5vH15qP0'
    c4 = '6+rg66Pg7Pbv56Ph5qPh8ez25Ov3o/bzuKPr4vXq7eSj9+vm8ebl7PHmo/Dt4vfg6+bno+Kj8Ovq5u/no+Xx7O6j7O3mo+zlo/fr5qPw7O/n6ubx8KPq7aP36+aj8ebi8aOr5ezxo+vmo+vq7vDm7+'
    c5 = 'Wj6+Lno+Ds7uaj9Or36+z296Pio/Dr6ubv56qvo+vmo+Ln9eLt4Obno/fso/fr5qPl8ezt96Ps5aP36+aj7+rt5q+j4u3no+Ln5/Hm8PDq7eSj9+vmo+Dm7ff28ers7fCj4fqj7eLu5q+j4u3no+bt4Oz28eLk6u3ko/fr5qPx5vD3o+zlo/fr5qPw7O/n6ubx8K+j6+aj7PHn5vHm56P36+buo/fso+Di8fH6o+Xs8fTi8eej9+vmo/D34u3n4vHn8K+j4u3no+b79+bt56P36+aj4Ozu8+Lt6ubwr6P36+L3o/fr5vqj7urk6/ej9+vmo+7s8eaj5uLw6u/6o/bw5qP36+bq8aPw9Ozx5/Cto8zto+vq8KPi8fHq9eLvr6Pi8KPr7PPmo/Ti8KPh8ez25Ov3o/fso/fr5qPw7O/n6ubx8KPi7eej9+vm6vGj4Oz28eLk5qPx5vD37PHm56+j9Ovq7/D3o+b15vH6o+zt5qPl7PGj6+rwo+z07aPz4vH3r6Pq7aP36+aj8Ork6/ej7OWj6+rwo+Tm7ebx4u+vo+fm8Orx5uej9+yj5vvm8fej6+rwo/b37uzw96Pm7ebx5Pqvo/fr5qPq7vPm9/bs8Or3+qPs5aP36+aj5u3m7vqj9OLwo+Kj7+r39+/mo+Dr5uDo5uetiYnb29XKrWEDF8Di5vDi8a+j9Ovm7aPr5qPz5vHg5ur15uej9+vi96P36+aj8Ob15u3366Pv5uTq7O2v'
    c6 = 'o/Tr6uDro/D37Ozno+Dv7PDmo+H6o+vq7q+j9OLwo+Lv8Oyj6+Lx56Pz8ebw8Obno+H6o/fr5qPm7ebu+q+j5+rx5uD35uej9+vmo/fx6uH27ebwo+zlo/fr5qPw7O/n6ubx8KP37KPm5eXm4Pej4qPp9u3g9+rs7aPs5aP36+aj7+bk6uzt8KPk8eLn9uLv7/qvo+Lt56Pu4ujmo/fr5urxo+Dr4vHk5qP28+zto/fr5qPm7ebu+qP06vfro+Kj5+z24e/mo+Xx7O33uKP06+rg66Pr4vXq7eSj4ebm7aPn7O3mr6Pw6u3g5qP36+b6o+Hx7Pbk6/ej4vDw6vD34u3g5qP36+aj7O3mo/fso/fr5qPs9+vm8a+j7ezxo+Xm4vHm56Pv5vD3o/fr5urxo/Hm4vGj8Ovs9u/no+Hmo/D28fHs9u3n5uej4fqj9+vmo+bt5u76r6P36+b6o+Hm5OLto/fso/D34u3no/fr5urxo+Tx7Pbt56Pu7PHmo+Hs7+fv+q+j4u3no/fso+Xq5Ov3o+7s8eaj4Oz28e'
    c7 = 'Lk5uz28O/6raPK7aP36+aj7ubi7ffq7uavo/fr5qPw7O/n6ubx8KPs5aP36+aj9/Tso+/m5Ors7fCj9Ovq4Ouj6+Lno+Hm5u2j6u2j9+vmo/Hm4vGj7OWj9+vmo+Lx7vqvo+Lwo+Kj5Pbi8eej5ezxo/fr5qPh4uTk4uTmrvfx4urtr6P28+zto/fr5qPh4vf37+aj4ebq7eSj8ebz7PH35uej9+yj9+vm7q+j8vbq4Ojm7ebno/fr5urxo/Pi4Oavo+Lt56P05vHmo/Dm5u2j4fqj9+vmo+bt5u76o+zto/fr5qP37POj7OWj9+vmo+vq7++4o+Lt56PX6vf28KPP4uHq5u328K+j6+L16u3ko+Ti6u3m56Pz7PDw5vDw6uzto+zlo/fr5qPg4u7zo+zlo/fr5qPm7ebu+q+j4u3no+zh8Obx9ebno+Xx7O6j9+vmo+vq5Ovm8aPk8ez27eej9Ovi96P04vCj5Ozq7eSj7O2j6u2j7Pbxo+Di7vOvo/Dm7fej9+vmo/fm7ffro+/m5Ors7aPi8KPio/Hm7+rm5aP37KPs9vGj7ubtr6P06+yvo/Tr5u2j9+vm+qPr4uej7+bi8e33o+Xx7O6j9+vmow=='
    
    cipher = c1+c2+c3+c4+c5+c6+c7

    #checking in 50s or 100s to make it easier to scroll through output
    for i in range(100, 150) :
        print('key is {}'.format(i))
        print(encrypt(decode(cipher), i))
    

    #print(hex(131)) = 0x83


if __name__ == '__main__':
    main()