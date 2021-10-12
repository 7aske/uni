def swap(a, b):
    return b, a;

def swap(a, b):
    tmp = a
    a = b
    b = tmp
    return a, b

def main():
    a = 3
    b = 5
    print(a, b)
    a, b = swap(3, 5)
    print(a, b)

    print("---")

    a = 2
    b = 4
    print(a, b)
    a, b = swap(2, 4)
    print(a, b)

    print("---")

    a = 1
    b = 3
    print(a, b)
    a, b = [b, a]
    print(a, b)


if __name__ == "__main__":
    main();
