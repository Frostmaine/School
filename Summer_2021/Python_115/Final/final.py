'''
  Matthew Yackiel
  Python 115
  Final Exam
  7-1-2021
'''
#1

# def sumAndProduct():
#     number = input("input an integer")
#     tmp = int(number)
#     sum = 0
#     product = 1
#     while tmp != 0:
#         sum += tmp % 10
#         product *= tmp % 10
#         tmp = tmp // 10

#     for x in range(0, len(number)-1):
#         print(number[x], end = "+")
#     print(number[len(number)-1], end = "=")
#     print(sum)

#     for x in range(0, len(number)-1):
#         print(number[x], end = "*")
#     print(number[len(number)-1], end = "+")
#     print(product)

# def main():
#     sumAndProduct()
# 2

# from collections import OrderedDict

# def getEmployeesInfo():
#     info = dict()
#     temp = list(input("Input a set of employees and their respective salaries seperated by whitespace: ").split())
#     i = 0
#     key = ""
#     for x in temp:
#         if i % 2 == 0:
#             key = x
#         else:
#             info.update({key: int(x)})
#         i += 1

#     return info

# def employees(info):
#     print("Here is our roster", info)
#     info = OrderedDict(sorted(info.items(), key=lambda x: x[1], reverse=True))
#     print("Sorted Descending by salary", info)
#     sum = 0
#     for x in info:
#         sum += int(info[x])
#     average = sum / len(info)
#     print("These people made above average(", average, ") pay:", end = " ")
#     for x in info:
#         if int(info[x]) > average:
#             print(x, end=", ")

# def main():
#     info = getEmployeesInfo()
#     employees(info)
#3

def isSorted(lst):
    tmp = int(lst[0])
    for x in lst:
        if int(x) > tmp:
            tmp = int(x)
        elif int(x) != tmp:
            return False
    return True

def main():
    lst = input("Enter list: ").split()
    if isSorted(lst):
        print("the list is sorted")
    else:
        print("the list is not sorted")


main()
