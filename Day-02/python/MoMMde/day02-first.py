from enum import Enum, auto

submarine_x = 0  # Forward
submarine_y = 0  # Depth


def get_input(file: str) -> list[str]:
    lines = []
    with open(file) as f:
        for line in f.readlines():
            lines.append(line)
    return lines


class Direction(Enum):
    UP = auto()
    DOWN = auto()
    FORWARD = auto()


def handle_direction(direction: Direction, x: int):
    global submarine_x, submarine_y
    if direction == Direction.DOWN:
        submarine_y -= x
    elif direction == Direction.UP:
        submarine_y += x
    elif direction == Direction.FORWARD:
        submarine_x += x


def parse_direction(command: str) -> [Direction, int]:
    direction_str, amount_str = command.split(' ')
    return [Direction[direction_str.upper()], int(amount_str)]


if __name__ == '__main__':
    FILE = 'input'
    CONTENT = get_input(FILE)
    for line in CONTENT:
        direction_parsed = parse_direction(line)
        handle_direction(direction_parsed[0], direction_parsed[1])
    print('X: {x}'.format(x=submarine_x))
    print('Y: {y}'.format(y=submarine_y))
    print('Result: {r}'.format(
        r=str(submarine_x * -submarine_y)))
