import time


class Rule:
    def __init__(self, transformation: str):
        split = transformation.split(" -> ")
        self.pattern: str = split[0]
        self.insertion: str = split[1]
        self._transformed = (self.pattern[0] + self.insertion, self.insertion + self.pattern[1])

    def transformed(self) -> (str, str):
        return self._transformed


class Polymer:
    def __init__(self, template, rules: list[Rule]):
        self.rules = rules
        self.count = {}
        self.elements = {}
        window = [template[0]]
        self.add_count(template[0])
        for e in template[1:]:
            self.add_count(e)
            window.append(e)
            self.add_element("".join(window))
            window.pop(0)

    def add_count(self, element, count: int = 1):
        if element not in self.count:
            self.count[element] = 0
        self.count[element] += count

    def add_element(self, element, count: int = 1):
        if element not in self.elements:
            self.elements[element] = 0
        self.elements[element] += count

    def transform(self):
        old = self.elements
        self.elements = {}
        for rule in self.rules:
            if rule.pattern in old:
                count = old[rule.pattern]
                transformed = rule.transformed()
                self.add_element(transformed[0], count)
                self.add_element(transformed[1], count)
                self.add_count(rule.insertion, count)

    def progress(self, count):
        for i in range(0, count):
            self.transform()

    def most(self):
        return max(self.count.values())

    def least(self):
        return min(self.count.values())


lines = [e.strip() for e in open("puzzle.txt")]

polymer = Polymer(lines[0], [Rule(e) for e in lines if e and "->" in e])
start = time.time()
polymer.progress(10)
print(f"Part one {polymer.most() - polymer.least()} took {round((time.time() - start) * 1000, 4)} ms")

polymer.progress(30)
print(f"Part two {polymer.most() - polymer.least()} took {round((time.time() - start) * 1000, 4)} ms")

