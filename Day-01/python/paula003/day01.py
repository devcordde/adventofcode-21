def task1():
    count = -1
    previous = -1
    with open('./day01.txt') as file:
        for line in file:
            if int(line) > previous:
                count += 1
            previous = int(line)
    print('Lösung der 1. Aufgabe: {}'.format(count))


def task2():
    count = -1
    previous = -1
    with open('./day01.txt') as file:
        measurements = [int(i) for i in file.readlines()]
    for j, depth in enumerate(measurements[0:-2]):
        sum = depth + measurements[j + 1] + measurements[j + 2]
        if sum > previous:
            count += 1
        previous = sum
    print('Lösung der 2. Aufgabe: {}'.format(count))


task1()
task2()
