def get_input(file: str) -> list[int]:
    lines = []
    with open(file) as f:
        for line in f.readlines():
            lines.append(int(line))
    return lines


def count_increases(content: list[int]) -> int:
    result = 0
    for i in range(1, len(content)):
        if content[i] > content[i-1]:
            result += 1
    return result


if __name__ == '__main__':
    INPUT_FILE = './input'
    INPUT = get_input(INPUT_FILE)
    print(count_increases(INPUT))
