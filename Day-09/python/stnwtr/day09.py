class Cave:
    def __init__(self, grid: list[list[int]]):
        self.grid = grid
        self.smallest = None

    def __getitem__(self, item: int) -> list[int]:
        return self.grid[item]

    def __len__(self):
        return len(self.grid)

    def _is_smallest_adjacent(self, i: int, j: int) -> bool:
        return self[i][j] < min([self.get_or(i - 1, j, 9),
                                 self.get_or(i + 1, j, 9),
                                 self.get_or(i, j - 1, 9),
                                 self.get_or(i, j + 1, 9)])

    def get_or(self, i: int, j: int, default: int) -> int:
        if i < 0 or i > len(self) - 1 or j < 0 or j > len(self[i]) - 1:
            return default
        return self[i][j]

    def get_smallest_indices(self) -> list[tuple[int, int]]:
        if self.smallest is not None:
            return self.smallest

        self.smallest = []
        for i in range(len(self)):
            for j in range(len(self[i])):
                if self._is_smallest_adjacent(i, j):
                    self.smallest.append((i, j))

        return self.smallest

    # this looks bad - could probably be a 1-liner using list comprehension somehow ... I'm too lazy
    # did this after driving hundreds of kilometers and being tired for such a long time
    def get_basin_indices_at(self, i: int, j: int) -> set[tuple[int, int]]:
        indices = [(i, j)]
        value = self[i][j]
        a = self.get_or(i - 1, j, 9)
        b = self.get_or(i + 1, j, 9)
        c = self.get_or(i, j - 1, 9)
        d = self.get_or(i, j + 1, 9)

        if 9 > a > value:
            indices += self.get_basin_indices_at(i - 1, j)
        if 9 > b > value:
            indices += self.get_basin_indices_at(i + 1, j)
        if 9 > c > value:
            indices += self.get_basin_indices_at(i, j - 1)
        if 9 > d > value:
            indices += self.get_basin_indices_at(i, j + 1)

        return set(indices)


def part1(cave: Cave) -> int:
    smallest_values = [cave[i][j] for i, j in cave.get_smallest_indices()]
    return sum(smallest_values) + len(smallest_values)


def part2(cave: Cave) -> int:
    bs = sorted([len([cave.get_or(*x, 9) for x in cave.get_basin_indices_at(*si)]) for si in cave.get_smallest_indices()])
    return bs.pop() * bs.pop() * bs.pop()


def main():
    with open('input/09.txt', 'r') as f:
        cave = Cave([[int(x) for x in line.strip()] for line in f.readlines()])
    print(part1(cave))
    print(part2(cave))


if __name__ == '__main__':
    main()
