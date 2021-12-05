class Point:
    def __init__(self, x, y):
        self.x = int(x)
        self.y = int(y)

    def __repr__(self):
        return f"{self.x},{self.y}"

    def __sub__(self, other):
        if isinstance(other, Point):
            return Point(self.x - other.x, self.y - other.y)
        return Point(self.x - other, self.y - other)

    def __add__(self, other):
        if isinstance(other, Point):
            return Point(self.x + other.x, self.y + other.y)
        return Point(self.x + other, self.y + other)

    def between(self, point):
        direction = (point.x - self.x, point.y - self.y)
        points = []

        steps = (abs(direction[0]) / (direction[0] or 1), abs(direction[1]) / (direction[1] or 1))

        for x in range(max((abs(direction[0]) or 1) + 1, (abs(direction[1]) or 1) + 1)):
            points.append(Point(self.x + x * steps[0], self.y + x * steps[1]))

        return points

    def is_diagonal(self, other):
        return not (self.x == other.x or self.y == other.y)

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __hash__(self):
        return hash((self.x, self.y))


class Line:
    def __init__(self, line: str):
        coords: list[list[str]] = [e.split(",") for e in line.split(" -> ")]
        self.start = Point(coords[0][0], coords[0][1])
        self.end = Point(coords[1][0], coords[1][1])

    def __repr__(self):
        return f"{self.start} -> {self.end} | Diagonal {self.is_diagonal()} | Points {self.points()}"

    def points(self):
        return self.start.between(self.end)

    def is_diagonal(self):
        return self.start.is_diagonal(self.end)


lines = [Line(e) for e in open("puzzle.txt") if e.strip()]

for line in lines:
    print(line)

points = {}

for line in lines:
    if line.is_diagonal():
        continue
    for point in line.points():
        if point in points:
            points[point] += 1
        else:
            points[point] = 1

count = [(k, v) for k, v in points.items() if v > 1]

print(f"Part one {len(count)}")

points = {}

for line in lines:
    for point in line.points():
        if point in points:
            points[point] += 1
        else:
            points[point] = 1

count = [(k, v) for k, v in points.items() if v > 1]

print(f"Part two {len(count)}")
