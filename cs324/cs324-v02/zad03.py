def fact(n):
    if n <= 1:
        return 1;
    return n * fact(n-1)

def main():
    print(fact(4))


if __name__ == "__main__":
    main()
