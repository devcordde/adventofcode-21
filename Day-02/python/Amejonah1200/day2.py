def part1(actions: list[(str, int)]) -> int:
    depth = 0
    horizontal = 0
    for (action, amount) in actions:
        match action:
            case "forward":
                horizontal += amount
            case "up":
                depth -= amount
            case "down":
                depth += amount
    return depth * horizontal


def part2(actions: list[(str, int)]) -> int:
    aim = 0
    depth = 0
    horizontal = 0
    for (action, amount) in actions:
        match action:
            case "forward":
                horizontal += amount
                depth += aim * amount
            case "up":
                aim -= amount
            case "down":
                aim += amount
    return depth * horizontal


if __name__ == '__main__':
    with open('../inputs/day2.txt', 'r') as f:
        actions: list[tuple[str, int]] = [(line.split(' ')[0], int(line.split(' ')[1])) for line in f.readlines()]
        print("Part1:", part1(actions))
        print("Part2:", part2(actions))
