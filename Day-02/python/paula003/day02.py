def task1():
    depth = 0
    horizontal = 0
    with open('./day02.txt') as file:
        for line in file:
            cmd = line.split(' ')[0]
            value = int(line.split(' ')[1])
            if cmd == 'forward':
                horizontal += value
            elif cmd == 'up':
                depth -= value
            elif cmd == 'down':
                depth += value
    print('Lösung der 1. Aufgabe: {}'.format(depth * horizontal))


def task2():
    depth = 0
    horizontal = 0
    aim = 0
    with open('./day02.txt') as file:
        for line in file:
            cmd = line.split(' ')[0]
            value = int(line.split(' ')[1])
            if cmd == 'forward':
                horizontal += value
                depth += aim * value
            elif cmd == 'up':
                aim -= value
            elif cmd == 'down':
                aim += value
    print('Lösung der 2. Aufgabe: {}'.format(depth * horizontal))


task1()
task2()
