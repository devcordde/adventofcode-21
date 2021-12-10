def calculate(positions, mapper):
    return min(map(mapper, [[abs(x - pos) for x in positions] for pos in range(min(positions), max(positions))]))


def main():
    with open('input/07.txt', 'r') as f:
        positions = list(map(int, f.read().split(',')))
    print(calculate(positions, lambda x: sum(x)))
    print(calculate(positions, lambda x: sum(y * (y + 1) // 2 for y in x)))


if __name__ == '__main__':
    main()
