def task1():
    gamma = ''
    epsilon = ''
    with open('day03.txt') as file:
        values = [list(i) for i in file.readlines()]
        for column in list(zip(*values)):
            if column.count('0') > column.count('1'):
                gamma += '0'
                epsilon += '1'
            else:
                gamma += '1'
                epsilon += '0'
    print('Lösung der 1. Aufgabe: {}'.format(int(gamma, 2) * int(epsilon, 2)))


def task2():
    with open('day03.txt') as file:
        values = [list(i) for i in file.readlines()]
    for i, line in enumerate(values):
        line = list(filter(lambda j: j != '\n', line))
        values[i] = line
    print('Lösung der 2. Augabe: {}'.format(
        int(check_column(values, 0, True), 2) * int(check_column(values, 0, False), 2)
    ))


def check_column(values, column, more):
    zipped = list(zip(*values))
    if more:
        if zipped[column].count('0') > zipped[column].count('1'):
            values = filter(lambda row: row[column] == '0', values)
        else:
            values = filter(lambda row: row[column] == '1', values)
    else:
        if zipped[column].count('0') > zipped[column].count('1'):
            values = filter(lambda row: row[column] == '1', values)
        else:
            values = filter(lambda row: row[column] == '0', values)
    values = list(values)
    if len(values) > 1:
        column += 1
        if (len(values[0])) > column:
            return check_column(values, column, more)
    else:
        return "".join(str(i) for i in values[0])


task1()
task2()
