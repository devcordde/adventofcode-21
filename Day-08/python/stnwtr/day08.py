import itertools
from collections import Counter

"""
+---+---------------+-----------------------------------------------------------+
|   | A B C D E F G |     len     order         explanation                     |
+---+---------------+-----------------------------------------------------------+
| 1 | A B - - - - - |       2         1         only 2 segments                 |
| 7 | A B - D - - - |       3         2         only 3 segments                 |
| 4 | A B - - E F - |       4         3         only 4 segments                 |
| 2 | A - C D - F G |       5        10         last one with 5 segments        |
| 3 | A B C D - F - |       5         5         5 segments and both from 1      |
| 5 | - B C D E F - |       5         9         5 segments and 6/9 contains all |
| 0 | A B C D E - G |       6         8         last one with 6 segments        |
| 6 | - B C D E F G |       6         6         6 segments and only one from 1  |
| 9 | A B C D E F - |       6         7         6 segments and all from 3       |
| 8 | A B C D E F G |       7         4         only 7 segments                 |
+---+---------------+-----------------------------------------------------------+
"""


class Display:
    def __init__(self, line: str):
        self.mixed, self.result = map(lambda s: s.strip().split(' '), line.split('|'))
        self.mixed = [''.join(sorted(x)) for x in self.mixed]
        self.result = [''.join(sorted(x)) for x in self.result]

    def _solution_dict(self) -> dict[str, int]:
        digits = dict()
        digits[1] = [p for p in self.mixed if len(p) == 2][0]
        digits[7] = [p for p in self.mixed if len(p) == 3][0]
        digits[4] = [p for p in self.mixed if len(p) == 4][0]
        digits[8] = [p for p in self.mixed if len(p) == 7][0]
        digits[3] = [p for p in self.mixed if len(p) == 5 and all([b in p for b in digits[1]])][0]
        digits[6] = [p for p in self.mixed if len(p) == 6 and (digits[1][0] in p) ^ (digits[1][1] in p)][0]
        digits[9] = [p for p in self.mixed if len(p) == 6 and all([b in p for b in digits[3]])][0]
        digits[0] = [p for p in self.mixed if len(p) == 6 and p != digits[6] and p != digits[9]][0]
        digits[5] = [p for p in self.mixed if len(p) == 5 and all([b in digits[9] for b in p]) and p != digits[3]][0]
        digits[2] = [p for p in self.mixed if len(p) == 5 and p != digits[3] and p != digits[5]][0]
        return {v: k for k, v in digits.items()}

    def resolve(self) -> int:
        digits = self._solution_dict()
        return int(''.join([str(digits[x]) for x in self.result]))


def part1(displays: list[Display]) -> int:
    counter = Counter(itertools.chain(*[[len(r) for r in d.result] for d in displays]))
    return sum([i[1] for i in counter.items() if i[0] in (2, 3, 4, 7)])


def part2(displays: list[Display]) -> int:
    return sum([display.resolve() for display in displays])


def main():
    with open('input/08.txt', 'r') as f:
        displays = [Display(line) for line in f.readlines()]
    print(part1(displays))
    print(part2(displays))


if __name__ == '__main__':
    main()
