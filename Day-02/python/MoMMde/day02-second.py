from enum import Enum, auto

submarine_x = 0  # Horizontal
submarine_y = 0  # Depth
submarine_aim = 0  # Aim


def get_input(file: str) -> list[str]:
    lines = []
    with open(file) as f:
        for line in f.readlines():
            lines.append(line)
    return lines


class Direction(Enum):
    DOWN = auto()
    UP = auto()
    FORWARD = auto()


def handle_direction(direction: Direction, x: int):
    global submarine_x, submarine_y, submarine_aim
    if direction == Direction.DOWN:
        submarine_aim += x
    elif direction == Direction.UP:
        submarine_aim -= x
    elif direction == Direction.FORWARD:
        submarine_x += x
        submarine_y -= x * submarine_aim


def parse_direction(command: str) -> [Direction, int]:
    direction_str, amount_str = command.split(' ')
    return [Direction[direction_str.upper()], int(amount_str)]


if __name__ == '__main__':
    FILE = './input'
    INPUT = get_input(FILE)
    for line in INPUT:
        direction_parsed = parse_direction(line)
        handle_direction(direction_parsed[0], direction_parsed[1])
    print('X: {x}'.format(x=submarine_x))
    print('Y: {y}'.format(y=submarine_y))
    print('Result: {r}'.format(
        r=str(submarine_x * -submarine_y)))
