'''
  Matthew Yackiel
  Python 115
  Lab #5
  6-26-2021
'''

#1

def evenList(numbers):
    new_list = []
    for x in numbers:
        if x % 2 == 0:
            new_list.append(x)

    return new_list

def oddList(numbers):
    new_list = []
    for x in numbers:
        if x % 2 == 1:
            new_list.append(x)

    return new_list

def main():
    numbers = [1, 5, 8, 2, 8, 100, 3, 7, 72, 5, 89, 100, 75, 13, 10, 41, 31, 57]
    
    even_list = evenList(numbers)
    odd_list = oddList(numbers)

    even_sum = 0
    odd_sum = 0
    
    for x in even_list:
        even_sum += x

    for x in odd_list:
        odd_sum += x

    print(even_list)
    print(odd_list)
    print("The even sum is:", even_sum, "The even average is", even_sum / len(even_list))
    print("The odd sum is:", odd_sum, "The odd average is", odd_sum / len(odd_list))

if __name__ == "__main__":
    main()
