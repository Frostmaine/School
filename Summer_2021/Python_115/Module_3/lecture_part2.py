num1= 2
while num1>5:
    print(" hello Programmers")

# continue statement is used with an If statement within a loop to skip a particular iteration

# range() function generates  numbers between range of values example range(10); this will generates numbers between 0 and 9
"""
x= -1
while x<9:
    x = x + 1
    if x>=3 and x<=7: # is the same if we 3<=x<=7
        continue
    print(" the value of counter is  ", x)
"""
#break statement is used to break the loop even the condition is true
# break the loop at iteration 5
y=-1
while y<20:
    y+=1
    if y==10:
        break
    print(y)
else:
    print(" the loop is over")
# you can use else statement with while loop
# for variablename  in Valuesrange
str1="Bloomsburg University"
for x in str1:
    print(x)

# use for loop with numeric values
for  i in range(100):
    print("COMPSCI 115 at iteration", i)

print()
m=-1
while m<99:
    m+=1
    print("COMPSCI 115 at iteration", m)

# range() function could up to three arguments
# range(oneValue), it will generate numbers between 0 and oneValue excluding oneValue
# range(start, end) function receives two arguments, start value is the starting value of the range and end is the end value of the range
# example generate numbers between 50 and 100
print()
for  num3 in range(50,101):
    print(num3)

# if you want the step between numbers in the range(), you need add three arguments
for num4 in range(1000, 1200, 8):
    print(num4)
# nested for loops: we have two loops, one inside the other
color=["White","Black", "Blue"]
car=["Honda", "BMW", "Mercedes"]
for x in color:
    for y in car:
        print(x,y)

for s in range(80):
    if s==50:
        continue
    print(s)
# example show all values between 0 and 80 except odd values (i.e. display even values)
# dislay even number between 0 and 10
for j in range(10):
    if (j%2!=0):
        print(j," is an odd")


# display even numbers
for k in range(80):
    if k%2!=0:
        continue
    print(k," is an even number")
# display odd numbers
for l in range(80):
    if l%2==0:
        continue
    print(l," is an odd number")
