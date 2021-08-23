'''
  Matthew Yackiel
  Python 115
  Mdule 6 Lab
  7 - 1 - 2021
'''

# 1

def inventory_prompt():
    inventory = dict()
    input_set = input("Input a coma space seperated list of items and their costs (item, cost, item, cost, . . .)\n").split(sep=", ")
    i = 0
    key = ""
    for x in input_set:
        if i % 2 == 0:
            key = x
        else:
            inventory.update({key: float(x)})
        i += 1
    return inventory

def sell_price(inventory):
    for x in inventory:
        sell = inventory[x] * 1.63
        print(x, ": $ %.2f\t" % sell)

#2

def object_prompt():
    objects = {}
    input_int = input("Input a list of numbers seperated by whitespace: ").split()
    i = 1
    for x in input_int:
        objects.update({"object" + str(i): int(x)})
        i += 1
    return objects

def count_odd_even(objects):
    # evens
    even_count = 0
    print("{", end="")
    for x in objects:
        if objects[x] % 2 == 0:
            even_count += 1
            out = x + ": "+ str(objects[x])
            print(out, end=", ")
    print("}")
    # odds
    odd_count = 0
    print("{", end="")
    for x in objects:
        if objects[x] % 2 == 1:
            odd_count += 1
            out = x + ": " + str(objects[x])
            print(out, end=", ")
    print("}")

    # print the counts
    print("There are", even_count, "even numbers.\nThere are", odd_count, "odd numbers.")
 
def main():
    inventory = inventory_prompt()
    sell_price(inventory)

    objects = object_prompt()
    count_odd_even(objects)

if __name__ == "__main__":
    main()
