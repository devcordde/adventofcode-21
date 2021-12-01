with open('puzzle.txt') as f:
    depths = [int(i) for i in f.readlines()]

last = None


def eval(curr_last, curr):
    global last
    last = curr
    return 0 if curr_last is None else (1 if curr_last < curr else 0)


increased = sum([eval(last, depth) for depth in depths])

print(f"Part one {increased}")

frame = []
last = 0
increased = 0

for depth in depths:
    last = sum(frame)
    frame.append(depth)
    if len(frame) > 3:
        frame.pop(0)
        increased += 1 if last < sum(frame) else 0

print(f"Part two {increased}")
