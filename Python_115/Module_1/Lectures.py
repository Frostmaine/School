print("Hello Python Programmers")

print("-----------------------------------")
print(" this is a python programming class")
print("-----------------------------------")

# variable: is a container that holds values

x = 100 # numeric value
# display the variable
print(x)

# + operator is used with numbers to perform summation
print(195 + 61)

# Example in double quotes 
y = "Hello"
print(y)
# Example in single quotes
z = "Mahmoud"
print(z)

# + operator is used to concatenate 2 strings
first_name = "Mahmoud"
last_name = "Darwich"
print(first_name + " " + last_name)
initial =" K "
print(first_name + initial + last_name)
print(first_name + " L " + last_name)

# using + operator with a string and a number, is illegal
mother = "Christi is"
# print(mother + 45) is illegal

# if it is required to combine a string with a number
# the above is fixed with
print(mother, 45, "years old")

name="Mahmoud"
Name='Laura'
NAME='Lisa'  # these are all valid unique variables
# variabls name should be started with letters or _ only
# variables cannot be started with numbers
# variables should contain only letters, numbers, and _

# 8name="Darwich" is illegal, cannot start with number
_name = "Chris" # is legal
cat24 = "Mikky" # is legal
# first name = "Joe" spaces are illegal
# # first-name = "Robert" is illegal no hyphen

# Assigning multiple values to variables in one statement
Fruit1 = Fruit2 = Fruit3 = "Apple", "Orange", "Banana"
print(Fruit1)
print(Fruit2)
print(Fruit3)

# assign on single value to multiple variables
fruit4 = fruit5 = fruit6 = "Kiwi"
print(fruit5)

# Example: 100x = 250 syntax error because the variable names start with a number. it generates red underline

# Example: print(100/0) this is a mathematical impossibility, we cannot divide numbers by 0

# add 45 + 5 and then divide by 5.
print(45+5/ 10)
# python takes order of operations very literally.  follow PEMDAS
print((45+5)/10)

# input() is a function used to get space seperated values from the user from the keyboard (always returns the value as a string)
  get_input = input("Enter your name")
r
  print("get_input is:", get_input)

'''
  number1 = input("Enter a number")
  number2 = input("Enter another number")
  # add number1 and number2
  print(number1 + number2) # this will output 55 because number1 and number2 are strings returned by the input function
'''
  # add number1 to number 2 properly
  # the int function is used to convert the string from input to a number
  number1 = int(input("Enter a number"))
  number2 = int(input("Enter another number"))
  print(number1 + number2)
