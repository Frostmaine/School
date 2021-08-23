import sys

def hanoi(n=3, start='A', spare='B', end='C'):
    if n == 1:
        print("Move disk", n,"from", start, "to", end)
    else:
        hanoi(n-1, start, end, spare)
        print("Move disk", n, "from", start, "to", end)
        hanoi(n-1, spare, start, end)


def main():
    hanoi(int(sys.argv[1]))

if __name__ == "__main__":
    main()
