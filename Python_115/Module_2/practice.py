# Question 1
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

