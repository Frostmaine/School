distance = int(input("Enter a Distance(miles): "))
time = int(input("Enter a time(hours): "))

mph = distance/time

print("The speed in knots is:", mph/1.15078)
print("The speed in miles per hour:", mph)
print("The speed in feet per second:", mph*5280/3600)
