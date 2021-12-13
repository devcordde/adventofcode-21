from typing import Union
import time

edges = [e.strip() for e in open("puzzle.txt").readlines()]


class Cave:
    def __init__(self, name: str):
        self.name = name
        self.small = name.islower()
        self.start = self.name == "start"
        self.end = self.name == "end"
        self.caves = set()
        self.hash = hash(self.name)

    def add_connection(self, cave):
        self.caves.add(cave)

    def __eq__(self, other):
        return other.name == self.name

    def __hash__(self):
        return self.hash

    def __repr__(self):
        return self.name


class Way:
    def __init__(self, start: Union[Cave, list[Cave]]):
        if isinstance(start, Cave):
            self.caves: list[Cave] = []
            self.caves.append(start)
        if isinstance(start, list):
            self.caves: list[Cave] = start
        self.hash = hash("".join([e.name for e in self.caves]))

    def last(self):
        return self.caves[-1]

    def split(self, double_small) -> set["Way"]:
        ways: set[Way] = set()
        for e in self.last().caves:
            if self.can_add(e, double_small):
                ways.add(Way([*self.caves, e]))
        return ways

    def has_double_small(self):
        return max([self.caves.count(e) for e in self.caves if e.small]) > 1

    def can_add(self, cave: Cave, double_small: bool):
        if self.last().end or cave.start:
            return False
        if not cave.small:
            return True
        if not double_small:
            return cave not in self.caves
        if not self.has_double_small():
            return True
        return cave not in self.caves

    def __eq__(self, other):
        return self.hash == other.hash

    def __hash__(self):
        return self.hash


class Graph:
    def __init__(self, caves: list[str], double_small: bool = False):
        self.caves: dict[str, Cave] = {}
        for e in caves:
            edge = e.split("-")
            first = edge[0]
            second = edge[1]
            self.get(first).add_connection(self.get(second))
            self.get(second).add_connection(self.get(first))
        self.ways = []
        self.double_small = double_small
        self.build_graph()

    def get(self, name: str):
        if name not in self.caves:
            self.caves[name] = Cave(name)
        return self.caves[name]

    def __repr__(self):
        return self.caves.__repr__()

    def build_graph(self):
        start = self.get("start")
        ways = {Way(start)}
        while True:
            new_ways = set()
            for way in ways:
                splitted = way.split(self.double_small)
                if len(splitted):
                    [new_ways.add(e) for e in splitted]
                else:
                    if way.last().end:
                        new_ways.add(way)
            if len(new_ways) == len(ways):
                break
            ways = new_ways
        self.ways = ways

    def get_ways(self) -> list[Way]:
        return self.ways


start = time.time()
graph = Graph(edges)
print(f"Part one {len(graph.get_ways())} took {round(time.time() - start, 4)} seconds")

start = time.time()
graph = Graph(edges, double_small=True)
print(f"Part two {len(graph.get_ways())} took {round(time.time() - start, 4)} seconds")
