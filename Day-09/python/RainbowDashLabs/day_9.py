import math


class HeightMap:
    def __init__(self, height_map: list[str]):
        self.height_map: list[list[int]] = [[int(e) for e in list(e.strip())] for e in height_map]
        self.x_dim = len(self.height_map)
        self.y_dim = len(self.height_map[0])
        self.risk_map = [[self._risk_score(x, y) for y in range(self.y_dim)] for x in range(self.x_dim)]

    def _risk_score(self, x: int, y: int) -> (int, int):
        origin = self._height(x, y)
        heights = [self._height(*e) for e in self._adjacent_points(x, y)]
        return 1 + origin, self._basin_score(x, y) if all([e > origin for e in heights]) else 0

    def _basin_score(self, x, y):
        done = set()
        queue: (int, int) = [(x, y)]
        while len(queue) != 0:
            coord = queue.pop()
            done.add(coord)
            for c in self._adjacent_points(*coord):
                if c in done:
                    continue
                if self._height(*c) != 9:
                    queue.append(c)
        return len(done)

    def _adjacent_points(self, x, y) -> list[(int, int)]:
        points = [c for c in [(x, y + y_o) for y_o in range(-1, 2, 2)] if self._exists(*c)]
        return points + [c for c in [(x + x_o, y) for x_o in range(-1, 2, 2)] if self._exists(*c)]

    def _exists(self, x, y):
        return 0 <= x < self.x_dim and 0 <= y < self.y_dim

    def _height(self, x: int, y: int):
        return self.height_map[x][y]

    def risk_sum(self):
        return sum([sum([num[0] for num in e]) for e in self.risk_map])

    def basins(self, limit: int = None):
        return [e for e in list(reversed(sorted([e[1] for inner in [e for e in self.risk_map] for e in inner])))[
                           :limit or len(self.risk_map)]]


height_map = HeightMap(list(open("puzzle.txt").readlines()))

print(f"Part one {height_map.risk_sum()}")

print(f"Part two {math.prod(height_map.basins(3))}")
