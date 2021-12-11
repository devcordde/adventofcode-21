def is_illegal(line: str):
    last = []
    for e in list(line):
        if e in chunk_marker:
            last.append(e)
            continue
        close = chunk_marker[last.pop()][0]
        if close == e:
            continue
        return e, close


def repair(line: str):
    last = []
    for e in list(line):
        if e in chunk_marker:
            last.append(e)
            continue
        last.pop()
    return [chunk_marker[e][0] for e in reversed(last)]


def score_illegal(chars: list[str]):
    return sum([chars.count(marker[0]) * marker[1] for marker in chunk_marker.values()])


def score_repair(chars: list[str], scores: dict):
    score = 0
    for char in chars:
        score = score * 5 + scores[char]
    return score


lines = [e.strip() for e in open("puzzle.txt").readlines()]
chunk_marker = {"(": (")", 3, 1), "[": ("]", 57, 2), "{": ("}", 1197, 3), "<": (">", 25137, 4)}

result = score_illegal([is_illegal(line)[0] for line in lines if is_illegal(line)])
print(f'Part one {result}')

scores = {e[0]: e[2] for e in chunk_marker.values()}
repaired = sorted([score_repair(repair(line), scores) for line in lines if not is_illegal(line)])
print(f'Part two {repaired[int(len(repaired) / 2)]}')
