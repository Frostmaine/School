# [[file:../README.org::*What is a funciton?][What is a funciton?:1]]
"""
Module 4  function
this is the third week
COMPSCI 115
"""
# function is a block of codes that does a specific taks, a function  cannot be executed unless it is invoked (called)

# create a function
def displayInfo():
    print("The course is Python Programming COMPSCI 115")
    print("Summer 2021")

# when executing the codes, nothing will happen, you need to call the function in order the execute the bfunction's body

# Call a function to execute the statements in its body
# just use the function name followed by parentheses
displayInfo()
# What is a funciton?:1 ends here

# [[file:../README.org::*Example][Example:1]]
def averageNumber():
    x=78
    y=90
    z=177
    sum=78+90+177
    # compute the average
    average=sum/3

    print("The average is ", average)

# call the function
averageNumber()
# Example:1 ends here

# [[file:../README.org::*Passing Arguments][Passing Arguments:1]]
# arguments are values to be passed to a function in order to be used in its body
# parameters is variable used in the function when it is created
def multiplyNumbers(num1,num2, num3, num4): # num1 and num2 are variables and they are called parameters
    print("The multiplication of the two numbers is ", num1*num2)
    print(" the summation of ",num3, " and ", num4, " is ", num3+num4)

multiplyNumbers(45,89, 109,980) # 45 and 89 are called arguments
multiplyNumbers(100,200, 177,650)
# Passing Arguments:1 ends here

# [[file:../README.org::*Example][Example:1]]
# create function
def displayName(firstName, lastName):
    print(firstName, lastName)

displayName("Mahmoud","Darwich")
displayName("Robert", "Smith")
str1="Bloomsburg University".upper()
#return keyword used to return results from a calling function
def sumNumbers(num5, num6):
    sum=num5+num6
    #print("the sum of num5 and num6",sum)
   # return  sum

#summation=sumNumbers(300,1500) # the returned results is 1800
#print(" the sum is ", sumNumbers(300,1500))
#x1 =sumNumbers(1,4)*4
#print(x1)
sumNumbers(15,5)
#print(variable1)
print(sumNumbers(20,40))
def displaySummation():
    num7=sumNumbers(150,60)
    print(" the summation is ", num7)
displaySummation()
# Example:1 ends here

# [[file:../README.org::*Exercises][Exercises:1]]
# exercise, create a program with  two functions
#  1st function is called maxValue() that returns the maximum values of two floats
# 2 nd function called getInputAndCompare(): you need create two inputs to get the two floats and call the max function then print out
# the maximum value
def getInputAndCompare():
    # get the values from the users
    value1=float(input("Enter the first value"))
    value2= float(input(" Enter the second value"))

    # pass num1 and num2 to max() function
    #maxValue(value1,value2)

    # display the max value at the output console
    print(" the maximum is ",maxValue(value1,value2) )
def maxValue(variable1, variable2):
    # get the max
    if variable1>variable2:
        max=variable1
    elif variable1==variable2:
        max=variable1
    else:
        max=variable2
    # return the maximum value
    return max



# call the function getInputAndCompare()
getInputAndCompare()
x=10 # x is a global variable and its scope is within the program (known inside and ouside the functions)
def function1():
    x=99 # x is a local variable, its scope is only within the function(it is not know outside the function)

print(x)

def function2():
    x=250  # variable x in function2 is completely different from variable x in function


y= 1000 # global variable

def function3():
    y=50 # local variable
    print(" the value is ", y)

function3()

def function4():
    y=76 # y is also local variable
    print(" the value is ", y)
function4()

def fun5():
    x=5 # variable x is global variable for function 6 and is a local variable for function 5

    def fun6():
        # x=7
        print("the value of x  is ", x)


    fun6()


fun5()
# import the math package
import math
# create a function that accepts the dimensions of a room from the users,
# calculates the area of 6 walls and return the area value
def wall_area():
    length=float(input("Enter the wall length  "))
    width=float(input("Enter the wall width     "))
    height=float(input("Enter the wall height    "))
    wall1_area=height*length
    wall2_area=height*width
    total_walls_area= 2*wall1_area+2*wall2_area
    print("The total walls area is ", total_walls_area, "sqft")
    return total_walls_area;

# create a function that accepts a parameter in the header, calculates  needed gallons to paint the walls
# and show the price of the total gallons
def gallon_and_price(area): # accepts a parameter variable area (you can use different name)
    needed_gallons=math.ceil(area/350) # use the paramater variable to calculate needed gallons
    print("The needed number of gallons is ",needed_gallons, "gallons")
    price=32
    total_price= needed_gallons*price # calculates the total price of all needed gallons to paint the room
    print("the total price of the needed gallon is $", total_price,)
total_walls_area=wall_area() # call the method total_walls_area() and the returned value is assigned to the variable total_wallas_area
gallon_and_price(total_walls_area)  # call the function gallon_and_price and by passing to it the returned value from
# the function wall_area() which is total_walls_area
# Exercises:1 ends here
