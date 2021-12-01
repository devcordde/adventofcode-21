from file_reader import read_numbers

lines = read_numbers('day01.txt')
result_count = 0

# Part one
for i in range(1, len(lines)):
  if lines[i] > lines[i - 1]:
    result_count += 1

print('Part one:', result_count)

# Part two
result_count = 0
segment_size = 3

for i in range(segment_size, len(lines)):
  a = sum(lines[i - segment_size:i])
  b = sum(lines[i - segment_size + 1:i + 1])
  if b > a:
    result_count += 1

print('Part two:', result_count)
