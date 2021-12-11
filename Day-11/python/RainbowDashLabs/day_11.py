from dataclasses import dataclass
from itertools import product

@dataclass
class Octopus:
    energy: int
    flashed = False
    flashes = 0

    def proceed(self):
        self.flashed = False
        self.energy += 1

    def add_energy(self):
        if not self.flashed:
            self.energy += 1

    def try_flash(self):
        if self.energy < 10:
            return False
        self.flashed = True
        self.flashes += 1
        self.energy = 0
        return True


class Ocean:
    def __init__(self, octopuses: list[list[Octopus]]):
        self.octopuses = octopuses
        self.x_dim = len(self.octopuses)
        self.y_dim = len(self.octopuses[0])
        self.coords = list(product(range(self.x_dim), range(self.y_dim)))

    def _exists(self, x, y):
        return 0 <= x < self.x_dim and 0 <= y < self.y_dim

    def _adjacent(self, x, y) -> list[(int, int)]:
        return [e for e in product(range(x - 1, x + 2), range(y - 1, y + 2)) if self._exists(*e) and e != (x, y)]

    def _try_flash(self, x, y):
        flashed = self.octopuses[x][y].try_flash()
        if flashed:
            [self.get(*point).add_energy() for point in self._adjacent(x, y)]
        return flashed

    def get(self, x, y):
        return self.octopuses[x][y]

    def proceed(self):
        for coord in self.coords:
            self.get(*coord).proceed()

        flashed = True
        while flashed:
            flashed = any([self._try_flash(*coord) for coord in self.coords])

    def flash_sum(self):
        return sum([sum([o.flashes for o in e]) for e in self.octopuses])

    def synced_flash(self):
        return sum([sum([o.energy for o in e]) for e in self.octopuses]) == 0


octopuses = [[Octopus(int(o)) for o in list(e.strip())] for e in open("puzzle.txt").readlines()]
ocean = Ocean(octopuses)

for day in range(100):
    ocean.proceed()

print(f'Part one {ocean.flash_sum()}')

octopuses = [[Octopus(int(o)) for o in list(e.strip())] for e in open("puzzle.txt").readlines()]
ocean = Ocean(octopuses)

day = 0
while True:
    day += 1
    ocean.proceed()
    if ocean.synced_flash():
        break

print(f'Part two {day}')


