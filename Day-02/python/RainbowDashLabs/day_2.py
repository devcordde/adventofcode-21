values = [(e.split(" ")[0], int(e.split(" ")[1])) for e in open("puzzle.txt")]

depth = 0
horizontal = 0

for k, v in values:
    if k == "forward":
        horizontal += v
    else:
        depth += v if k == "down" else -v

print(f"Part one {depth * horizontal}")


depth = 0
horizontal = 0
aim = 0

for k, v in values:
    if k == "forward":
        horizontal += v
        depth += aim * v
    else:
        aim += v if k == "down" else -v

print(f"Part two {depth * horizontal}")
