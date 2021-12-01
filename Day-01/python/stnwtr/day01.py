def calculate(depths, window_size):
    increased = 0
    for i in range(len(depths) - window_size):
        if depths[i] < depths[i + window_size]:
            increased += 1
    return increased


if __name__ == '__main__':
    with open('input/01.txt', 'r') as f:
        lines = list(map(int, f.readlines()))
    print(calculate(lines, 1))
    print(calculate(lines, 3))
