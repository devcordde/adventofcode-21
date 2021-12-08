class Panel:
    _panel = {
        "abcefg": 0,
        "cf": 1,
        "acdeg": 2,
        "acdfg": 3,
        "bcdf": 4,
        "abdfg": 5,
        "abdefg": 6,
        "acf": 7,
        "abcdefg": 8,
        "abcdfg": 9
    }

    def __init__(self, input: str):
        input = input.split(" | ")
        self.pattern = input[0].split(" ")
        self.output = [x.strip() for x in input[1].split(" ")]
        self.mapping = {}
        self.build_map()

    def num(self, length: int):
        return [x for x in self.pattern if len(x) == length][0]

    def add_mapping(self, curr: str, original: str):
        self.mapping[curr] = original

    def map(self, curr) -> int:
        return self._panel[("".join(sorted([self.mapping[x] for x in curr])))]

    def remove_mapped(self, pattern: str):
        return "".join([x for x in pattern if x not in self.mapping])

    def char_count(self):
        chars = list("".join(self.pattern))
        return {chars.count(x): x for x in chars}

    def build_map(self):
        counts = self.char_count()
        self.add_mapping(counts[9], "f")
        self.add_mapping(counts[4], "e")
        self.add_mapping(counts[6], "b")

        self.add_mapping(self.remove_mapped(self.num(2)), "c")
        self.add_mapping(self.remove_mapped(self.num(4)), "d")
        self.add_mapping(self.remove_mapped(self.num(3)), "a")
        self.add_mapping(self.remove_mapped(self.num(7)), "g")

    def transformed_output(self) -> list[int]:
        return [self.map(x) for x in self.output]


panels = [Panel(e) for e in open("puzzle.txt").readlines()]

nums = []
for panel in panels:
    nums = nums + panel.transformed_output()

print(f"Part one {nums.count(1) + nums.count(4) + nums.count(7) + nums.count(8)}")

nums = [int("".join(map(str, panel.transformed_output()))) for panel in panels]

print(f"Part two {sum(nums)}")
