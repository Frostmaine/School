'''
   Matthew Yackiel
   Python 115
   Module 4 Lab
   6-20-2021
'''

def isValid(number):
    if prefixMatched(number, 4) or prefixMatched(number, 5) or prefixMatched(number, 6) or prefixMatched(number, 37):
        return (sumOfOddPlace(number) + sumOfDoubleEvenPlace(number)) % 10 == 0
    return False

def sumOfDoubleEvenPlace(number):
    sum = 0
    print()
    for x in range(0, getSize(number), 2):
        sum += getDigit(int(str(number)[x])*2)
    return sum

def getDigit(number):
    if number > 9:
        return number % 10 + 1
    return number

def sumOfOddPlace(number):
    sum = 0
    for x in range(getSize(number)-1, 0, -2):
        sum += int(str(number)[x])
    return sum

def prefixMatched(number, d):
    return getPrefix(number, len(str(d))) == d

def getSize(d):
    return len(str(d))

def getPrefix(number, k):
    return int(str(number)[0:k])

def main():
    card_number = int(input("Enter a credit card number as a long integer: "))

    if isValid(card_number):
        print(card_number, "is valid")
    else:
        print(card_number, "is invalid")

if __name__ == "__main__":
    main()
