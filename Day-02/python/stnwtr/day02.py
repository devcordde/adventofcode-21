def part1(commands):
    depth = 0
    forward = 0
    for command in commands:  # not unpacking here because of trying cool 3.10 features
        match command:
            case ("down", value):
                depth += value
            case ("up", value):
                depth -= value
            case ("forward", value):
                forward += value
    return depth * forward


def part2(commands):
    depth = 0
    forward = 0
    aim = 0
    for command, value in commands:  # unpacking here but still use match
        match command:
            case "down":
                aim += value
            case "up":
                aim -= value
            case "forward":
                forward += value
                depth += aim * value
    return depth * forward


def lines_mapper(line):
    command, value = line.split(" ")
    return command, int(value)


if __name__ == '__main__':
    with open('input/02.txt', 'r') as f:
        lines = list(map(lines_mapper, f.readlines()))
    print(part1(lines))
    print(part2(lines))
