def count_increases(content: list[int]) -> int:
    result = 0
    for i in range(1, len(content)):
        if content[i] > content[i-1]:
            result += 1
    return result


def get_input(file: str) -> list[int]:
    lines = []
    with open(file) as f:
        for line in f.readlines():
            lines.append(int(line))
    return lines


if __name__ == '__main__':
    FILE = './input'
    INPUT = get_input(FILE)
    LIST = []
    LENGTH = len(INPUT)
    for line in range(LENGTH):
        if LENGTH < line + 3 or LENGTH < line + 2:
            break
        three_measurements = INPUT[line] + INPUT[line+1] + INPUT[line+2]
        LIST.append(three_measurements)
    print('Iterated over {length} lines. Final Result is: {result}'.format(
        length=LENGTH, result=count_increases(LIST)))
