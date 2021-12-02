from file_reader import read_lines

lines = read_lines('day02.txt')

depth = 0
depth2 = 0
position = 0
aim = 0

for line in lines:
  split = line.split()
  value = int(split[1])

  match split[0]:
    case 'forward':
      position += value
      depth2 += aim * value
    case 'down':
      depth += value
      aim += value
    case 'up':
      depth -= value
      aim -= value

# Part one
result = position * depth
print('Part one:', result)

# Part two
result = position * depth2
print('Part two:', result)
