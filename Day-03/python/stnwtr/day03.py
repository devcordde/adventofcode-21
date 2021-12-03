def part1(lines):
    iterable = list(zip(*list(map(int, list(line.strip())) for line in lines)))
    bits = [0 if x.count(0) > len(x) // 2 else 1 for x in iterable]
    return int("".join(map(str, bits)), 2) * int("".join(map(str, [1 - x for x in bits])), 2)


def part2(lines):
    oxygen = int(calculate(lines, 0, "oxygen")[0], 2)
    co2 = int(calculate(lines, 0, "co2")[0], 2)
    return oxygen * co2


def calculate(lines, x, rating):
    if len(lines) == 1:
        return lines

    zeros, ones = [], []
    for line in lines:
        if line[x] == "0":
            zeros.append(line)
        else:
            ones.append(line)

    if len(zeros) == len(ones):
        return calculate(ones if rating == "oxygen" else zeros, x + 1, rating)
    else:
        if len(zeros) > len(ones):
            return calculate(zeros if rating == "oxygen" else ones, x + 1, rating)
        else:
            return calculate(ones if rating == "oxygen" else zeros, x + 1, rating)


if __name__ == '__main__':
    with open('input/03.txt', 'r') as f:
        lines = f.readlines()
    print(part1(lines))
    print(part2(list(map(lambda s: s.strip(), lines))))
