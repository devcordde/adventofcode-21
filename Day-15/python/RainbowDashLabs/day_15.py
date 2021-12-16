import time
from collections import namedtuple
from queue import PriorityQueue
from typing import Union

Pos = namedtuple("Pos", "x y")


class Grid:
    def __init__(self, grid: list[list[int]]):
        self.queue = PriorityQueue()
        self.x_dim = len(grid)
        self.y_dim = len(grid[0])
        self.grid = grid
        self.visited = [[False for e in range(self.y_dim)] for e in range(self.x_dim)]
        self.dist = [[float('inf') for e in range(self.y_dim)] for e in range(self.x_dim)]
        self.start = Pos(0, 0)
        self.end = Pos(self.x_dim - 1, self.y_dim - 1)

    def search(self):
        self.set_weight(0, self.start)
        self.visit(self.start)
        point = self.next_point()
        while point != self.end:
            self.visit(point)
            point = self.next_point()

    def visit(self, pos: Pos):
        self.visited[pos.x][pos.y] = True
        [self.queue_point(p) for p in self.unvisited_adjacent(pos) if self.set_weight(self.get_weight(pos), p)]

    def queue_point(self, pos: Pos):
        self.queue.put((self.get_weight(pos), pos))

    def next_point(self) -> Pos:
        return self.queue.get()[1]

    def exists(self, pos: Pos):
        return 0 <= pos.x < self.x_dim and 0 <= pos.y < self.y_dim

    def unvisited_adjacent(self, pos: Pos):
        points = [c for c in [Pos(pos.x, pos.y + y_o) for y_o in range(-1, 2, 2)] if self.exists(c)]
        points = points + [c for c in [Pos(pos.x + x_o, pos.y) for x_o in range(-1, 2, 2)] if self.exists(c)]
        return [c for c in points if not self.visited[c.x][c.y]]

    def set_weight(self, parent_weight: int, pos: Pos):
        new_weight = parent_weight + self.grid[pos.x][pos.y]
        if new_weight < self.get_weight(pos):
            self.dist[pos.x][pos.y] = new_weight
            return True
        return False

    def get_weight(self, pos: Pos) -> Union[int, float]:
        return self.dist[pos.x][pos.y]

    def shortest_path_weight(self) -> int:
        return self.get_weight(self.end) - self.get_weight(self.start)


cave = [list(map(int, list(e.strip()))) for e in open("puzzle.txt") if e.strip()]

start = time.time()
grid = Grid(cave)
grid.search()
print(f"Part one {grid.shortest_path_weight()} took {round((time.time() - start) * 1000, 4)} ms")


def add_clipped(num, addition):
    return addition + num - 9 if num + addition > 9 else num + addition


new_cave = list(cave)
for addition in range(1, 5):
    for i in range(len(cave)):
        new_cave[i] = new_cave[i] + [add_clipped(num, addition) for num in cave[i]]

for addition in range(1, 5):
    for i in range(len(cave)):
        new_cave.append([add_clipped(num, addition) for num in new_cave[i]])

start = time.time()
grid = Grid(new_cave)
grid.search()
print(f"Part two {grid.shortest_path_weight()} took {round((time.time() - start) * 1000, 4)} ms")
