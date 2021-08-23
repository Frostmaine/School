'''
  Matthew Yackiel
  Python 115
  Lab #5
  6-26-2021
'''

# 2

def getListElements():
    my_list = []
    values = input("enter the list of elements in a single line seperated by white space >>> ")
    integers = values.split()
    for x in integers:
        my_list.append(int(x))
    return my_list

def reverseList(list_elements):
    return list_elements[::-1]

def main():
    my_list = reverseList(getListElements())
    print(my_list)

if __name__ == "__main__":
    main()
