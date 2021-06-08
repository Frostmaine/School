'''
  Author: Matthew Yackiel
  Date: 06-06-21
  Description: Module 2 Lab
'''

#1

print(3 * (9 - 2) + (24 / (6 / 3)) * 3)


#2

days = int(input("Days you've been driving: "))
years = int(days / 365)
weeks = int(days / 7)

print("You've been driving for:", "\nYears:", years, "\nWeeks:", weeks, "\nDays:", days)

#3

word = input("Word to convert: ")
count = int(input("How many letters at the end of the word should be converted? "))

print(word[:count] + word[-count:].upper())

#4

sentence = input("Sentence: ")
search = input("Word to look for in sentence: ")

occurences = sentence.count(search)

print("There are", occurences, "occurences of \'", search, "\' in the sentence")
