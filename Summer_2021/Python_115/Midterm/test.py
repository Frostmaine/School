def hifive():
    while True: 
        integer = int(input("Enter an integer to check if it is divisible by 12 or 5:"))
        if type(integer) != int:
            print("Try again!")
            continue
        else:
            break

    if integer % 5 == 0:
        print("HiFive!")
    if integer % 12 == 0:
        print("Hi Georgia!")

def table():
    print("Kilograms\tPounds")
    for x in range(200):
        print(x, "\t\t", round(x * 2.2, 0), sep="")

def usToCanadianConversion():
    us = float(input("Enter the US dollar to convert to Canadian Dollars: "))
    print(us, "US dollar is", round(us / .82, 2), "Canadian Dollars")

def canadianToUsConversion():
    canadian = float(input("Enter the canadian dollar to convert to US Dollars: "))
    print(canadian, "Canadian dolalr is", round(canadian * .82, 2), "US dollars")

def userInput():
    choice = -1
    while choice != 0 and choice != 1:
        choice = int(input("Enter 0 to convert Canadian dollars to US dollars and 1 to convert US dollars to Canadian Dollars: "))
    if choice == 0:
        canadianToUsConversion()
    elif choice == 1:
        usToCanadianConversion()
    else:
        print("Incorrect Input")
        
def main():
    hifive()
    table()
    userInput()

if __name__ == "__main__":
    main()
