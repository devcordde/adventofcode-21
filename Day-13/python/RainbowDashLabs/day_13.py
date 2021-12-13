from collections import namedtuple

lines = [e.strip() for e in open("puzzle.txt")]
points = [list(map(int, e.split(","))) for e in lines if e and not e.startswith("fold along")]
folds = [e.split(" ")[-1].split("=") for e in lines if e.startswith("fold along")]

Dot = namedtuple("Dot", "x y")


class Paper:
    def __init__(self, dots: list[list[int, int]]):
        self.dots: list[Dot] = [Dot(*e) for e in dots]

    def fold(self, axis: str, index: str):
        i = int(index)
        if axis == "y":
            self.dots = set([Dot(e.x, i - (e.y - i)) for e in self.dots if e.y > i] + [e for e in self.dots if e.y < i])
        else:
            self.dots = set([Dot(i - (e.x - i), e.y) for e in self.dots if e.x > i] + [e for e in self.dots if e.x < i])

    def dot_count(self):
        return len(self.dots)

    def __str__(self):
        row = [' '] * (max([e.x for e in self.dots]) + 1)
        table = [list(row) for i in range(max([e.y for e in self.dots]) + 1)]
        for dot in self.dots:
            table[dot.y][dot.x] = "█"
        return "\n".join(["".join(e) for e in table])


paper = Paper(points)
paper.fold(*folds[0])
print(f"Part one {paper.dot_count()}")

paper = Paper(points)
for fold in folds:
    paper.fold(*fold)
print(f"Part two\n{paper}")
