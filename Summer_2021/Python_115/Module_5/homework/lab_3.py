'''
  Matthew Yackiel
  Python 115
  Module 5 lab
  6/26/2021
'''

#3

def sort(word):
    sorted_list = list(word)
    sorted_list.sort()
    return str(sorted_list)
    
def isAnagram(word1, word2):
    return sort(word1) == sort(word2)
    
def main():
    word1 = input("enter the first word: ")
    word2 = input("enter the second word: ")
    if isAnagram(word1, word2):
        print(word1, "and", word2, "is an anagram")
    else:
        print("not an anagram")

if __name__ == "__main__":
    main()
