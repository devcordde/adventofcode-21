def part1(measurements: list[int]) -> int:
    count = 0
    for i in range(len(measurements) - 1):
        if measurements[i] < measurements[i + 1]:
            count += 1
    return count


def part2(measurements: list[int]) -> int:
    return part1([sum(t) for t in zip(measurements, measurements[1:], measurements[2:])])


if __name__ == '__main__':
    with open('../inputs/day1.txt', 'r') as f:
        measurements = [int(line) for line in f.readlines()]
        print("Part1:", part1(measurements))
        print("Part2:", part2(measurements))
