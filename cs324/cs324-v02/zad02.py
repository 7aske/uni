import math

def solve(a, b, c):
    discr = b**2 - 4 * a * c
    discr_sqrt = math.sqrt(abs(discr))

    if discr < 0:
        return None, None
    else:
        return (+b + discr_sqrt)/(2*a), (-b + discr_sqrt)/(2*a)

def main():
    # 3x^2 + 5x - 1 = 0
    print(solve(3, 5, 1))


if __name__ == "__main__":
    main()
