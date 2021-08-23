# to identify any data type, you can use a function type()
x = 100
print("The type of x is:", type(x))
y = 100.56
print("The type of x is:", type(y))

# example of integer
m = -238947020934
print(type(m))

# example of a float number (rational number)
n = 35.239847
print(type(n))

# example of complex numbers
z = 1 + 100j
print(type(z))

num4 = 45 + 10j # j is the imaginary part

num1 = 145e5 # 145*10^5
print(num1)

num2 = 3.4567
num3 = 34567e-4
print(num3)

num5 = 14.67
# convert to integer
print(int(num5))
num6 = 178
#convert to float
print(float(num6))
#convert num5 and num6 to complex
print(complex(num5))
print(complex(num6))
num7 = 23 + 65j
# converting to int of float produces an error
#print(int(num7))

# decimal numbers are 0 to 9
# binary numbers are 0 and 1 only
# Octal numbers are 0 to 7
# Hexadecimal numbers are 0 - 9 and then A - F
# to represent a binary number we preceed the number by 0b
# to represent an octal number we preceed the number by 0o
# to represent a hexadecimal number we preceed the number hy 0x
num9 = 0b1110111
print(num9) # this will print the binary number in a decimal form
num10 = 0o1763
print(num10)
num11 = 0xABEF1
print(num11)

number1 = 755
# convert decimal to binary, use bin() function
num12 = bin(number1)
print(num12)
# convert decimal number to octal use oct() function
num13 = oct(number1)
print(num14)
# convert decimal number to hexadecimal, use hex() function
# example number2=ox12ac56, convert it to an octal number
number2 = 0x12ac56
print(oct(number2))
# convert number 2 to a binary number
print(bin(number2))
# convert number 2 to a decimal number
print(number2)

# floor division, it removes desimal precision from the numbers
num14 = 111//10
print(num14)

# modulus computes the remainder of the division between 2 number, and is represented by %
num15 = 111 % 6
print(num15)
print(10 % 4)

# abs() returns positive numbers
print(abs(-1553))

# returns the qutient and remainder of the division between 2 numbers
divmod(120, 6)

# += assignment operator, increment the number by a value
num15 = 100
num15 += 1
print(num15)

# decrementing num15 by 5: num15 -= 5
num16 = 200
num16 -= 5 # num16= num16 - 5
print(num16)

# multiplicative assignment
num17 = 2
num17 *= 3
print(num17)

# power assignment
num18 = 4
num18 **= 3
print(num18)

# quotient assignment
num20 = 10
num20 /= 5
print(num20)

str1 = "Bloomsburg University of Pennsylvania"
# access the string index 0 to 6
print(str1[0:6])
# access the characters starting at index 6 and ending at index 15
print(str1[6:15])
# display the first 20 characters
print(str1[:20])
# some equivalent statements
print(str1[:])
print(str1)
# displaying of string characters starting from the end of the string, we use negative indices
print(str1[-5:]) # last 5 characters
# display the characters at index 1 from the end
print(str1[-12:-1])

# to count the characters of a string, we apply the length function len()
str2="Bloomsburg University! of Pennsylvania"
# get the length of the string
getLength= len(str2)
print("the length of the str2 is ", getLength)

# strip() function is used to remove the white spaces from the beginning and the end of a string
str3=" Hello Python Progammers "
print("The first character is ", str3[0])
# apply strip() function
str4=str3.strip()
print(" the first character or letter in the string str4 is ", str4[0])

str5="BLOOMSBURG UNISVERSITY"
# convert upper case letters to lower case letter, low() is applied on the string
str6=str5.lower()
print(str6)
# to convert lower case letters to upper case letters, we apply upper() function
print(str2.upper())

# replace("old character", "new character") function replaces old character with a new character
# exercise replace the character "o" in str2 by a character "D"
print(str2.replace("o", "G"))
# replace Bloomsburg by Bloom
print(str2.replace("Bloomsburg", "Bloom"))

# split() split the string into substrings
print(str2.split())
# split the string based on a specific character
print(str2.split("!"))
# split  str2 when it finds character "y"
print(str2.split("y"))

# in operator is used to search a string
searchStr="Bloom "  not in str2
print(searchStr)

print("COMPSCI 115" + " "+ "is a Python Progamming course")
str7="{} COMPSCI "
num16= 115
# print(str7+num16) this will cause an error because we cannot concatenate a string with an integer
# in this case we apply a format() function, place {} where you want to add the number to the string
print(str7.format(num16))
str8= "COMPSCI {1}  is given in Fall {0}  and Spring {2}  "
courseNum=115
year1= 2021
year2=2022
print(str8.format(year1, courseNum, year2)) # year1 argument is at position 0, courseNum is at position 1 and year2 is at position2

x = 100 # we assign 100 to a variable x
x==100 # x is equal to 100
# Comparison operators
# < less than
# > great than
# < = less than or equal
# >= greater than or equal
# == equal
# Boolean data type has only two values False and True only
print(" the comparison of the values returns ",1000 != 1500)

p# Question 1
# getting the input form the user and converting it to an integer
"""
for i in range(3):
    num= int(input("Enter an integer: "))
    print(num, " in the binary system is ", bin(num))
# convert the integer to a binary
"""

"""
# Question 2
# getting float values from the users and convert them to integers
floatNumber=float(input("Enter a float Number: "))

#convert FloatNumber to an integer
integerNumber= int(floatNumber)
print(floatNumber, " is  ",integerNumber, " as an integer")
"""

# Question 3
# a) create a variable and get the length of the string
string1="Department of Mathematical and Digital Sciences, Bloomsburg University of Pennsylvania"
print("the length of the string1 is ",len(string1), " characters")
# b) display characters between indices 10 and 30
print("the characters between indices 10 and 30 are:\n", string1[10:30])
print("the lower case letters of the string are as follow: \n", string1.lower())
# c) display all characters in upper case
print(" the upper case letters of the string are as follow: \n", string1.upper())
# d) replace letter "e" by letter "O"
print("we replace letter \"e\" by letter \"O\": \n ", string1.replace("e","O"))

# Question 4
string2="Bloomsburg University of Pennsylvania was established as Bloomsburg Academy in {}. In {}  it was renamed {}Bloomsburg Literary Institute {}"
year1=1839
year2=1856
year3=1990
print(string2.format(year1, year1, year1, year2 ))
# Question 5
# calculate the surface area of a cube
# get the side length from the user
side= input("enter the side length of a cube ")
for i in side:
    if i==".":
        decimalPoint=side.index(".")
        print(" the index of decimal is ", decimalPoint)
        while len(side[decimalPoint+1:])>2:
            side = input(" enter again the side length of a cube and you should use 2 digits after decimal point ")
            if len(side[decimalPoint+1:])<2:
                break;


floatinput=float(side)

area = pow(floatinput, 2)
surface = 6 * area
print(" the total surface areas of a cube of edge length", side, " is ", round(surface, 2))

#  area of one side
#area= pow(side,2)
# surface of the cube
