import sys


def fibonacci(n):
    if n == 1:
        return 1
    if n == 0:
        return 0
    return fibonacci(n-2) + fibonacci(n-1)


def main():
    print("The", sys.argv[1], "th number in the fibonacci sequence is",fibonacci(int(sys.argv[1])))


if __name__ == "__main__":
    main()
