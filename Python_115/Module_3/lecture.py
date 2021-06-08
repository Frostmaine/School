# if control  statement

num1 = 8
num2 = 8
if num1 > num2:
    print(" this is true 9>8")
    print(" that's great!")
# if the condition of the if statement is not true, then I want my program to go in another direction, in this case we use elif statement
elif num1 < num2:
    print( num1, " is less than ", num2)
elif num1==num2:
    print(num1, " is equal to ", num2)
print(" the program execution is completed!")

# else conditional statement is used  if none of the above conditions work
num3 =int(input(" Enter the first number >>>> "))
num4 =int(input(" Enter the second number >>>>"))
if num3 < num4:
    print(num3,"  is less than", num4)
elif num3> num4:
    print(num3,"  is greater than", num4  )
else:
    print(" both", num3,  num4,  " are  equals")

num5=600
num6=700
if num5<num6: print(" num5 is less than num6")

# shorthand for if ... else statement

num7=100
num8= 170
print( num7, "is less than ", num8) if num7< num8 else print(num7,"is not less than  ",num8)

# while loop to execute the statements many times through iterations as long as the condition true
# you need to create a counter and initiliaze it

i=0
while i <50:

    print(" Hello Programmers", " at iteration", i)
    i+=7 #  this means i=i-7

# break statement is used to stop the while loop execution even the condition still

j=-1
while j<50:
    j+=1
    if j==10:
        break
    print(j)

# for loop is used to iterate a block of codes, you dont need to use any counter
print()
list =(2,5,6,8,9,10)
for i in list:
    print(i) # i represent each character in the string

for i in range(10):
    print("Hello Programmers at iteration", i)

inputNumber=input(" Enter a float number and make sure to take two digits after decimal points: ")
for i in inputNumber:
    if i==".":
        decimalPoint=inputNumber.index(i)
        while len(inputNumber[decimalPoint+1:])>2:
            inputNumber=input("try again to enter two digits after decimal point")
            if len(inputNumber[decimalPoint+1:])<2:
                break

print(float(inputNumber))
