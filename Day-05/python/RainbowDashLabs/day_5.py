from typing import Callable, Union


class Point:
    def __init__(self, x, y):
        self.x = int(x)
        self.y = int(y)

    def __repr__(self):
        return f"{self.x},{self.y}"

    def __sub__(self, other: Union["Point", int, float]):
        if isinstance(other, Point):
            return Point(self.x - other.x, self.y - other.y)
        return Point(self.x - other, self.y - other)

    def __add__(self, other: Union["Point", int, float]):
        if isinstance(other, Point):
            return Point(self.x + other.x, self.y + other.y)
        return Point(self.x + other, self.y + other)

    def __mul__(self, other: Union["Point", int, float]):
        if isinstance(other, Point):
            return Point(self.x * other.x, self.y * other.y)
        return Point(self.x * other, self.y * other)

    def between(self, point: "Point"):
        direction = point - self
        steps = (abs(direction.x) / (direction.x or 1), abs(direction.y) / (direction.y or 1))
        max_range = max((abs(direction.x)) + 1, (abs(direction.y)) + 1)
        return [self + (Point(steps[0], steps[1]) * x) for x in range(max_range)]

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


class Map:
    def __init__(self):
        self.coords = {}

    def add(self, point: Point):
        if point in self.coords:
            self.coords[point] += 1
        else:
            self.coords[point] = 1

    def add_all(self, lines: list[Line], predicate: Callable[[Line], bool] = lambda x: True):
        for line in lines:
            if not predicate(line):
                continue
            for point in line.points():
                self.add(point)

    def __iter__(self):
        return self.coords.items().__iter__()

    def count(self, predicate: Callable[[Point, int], bool]):
        return len([v for k, v in self if predicate(k, v)])


lines = [Line(e) for e in open("puzzle.txt") if e.strip()]

my_map = Map()
my_map.add_all(lines, lambda point: not point.is_diagonal())

print(f"Part one {my_map.count(lambda k, v: v > 1)}")

my_map = Map()
my_map.add_all(lines)

print(f"Part two {my_map.count(lambda k, v: v > 1)}")
