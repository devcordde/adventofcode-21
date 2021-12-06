class Ocean:
    def __init__(self, breed_rate, mature_delay, fishes: list[int] = None):
        self.breed_rate = breed_rate
        self.mature_delay = mature_delay
        self.new_breed_rate = breed_rate + mature_delay
        self.fishes = {}
        if fishes:
            self.set_fishes(fishes)

    def set_fishes(self, fishes: list[int]):
        self.clear()
        for fish in fishes:
            self.add_fish(fish)

    def add_fish(self, day: int, amount: int = 1):
        if day in self.fishes:
            self.fishes[day] += amount
        else:
            self.fishes[day] = amount

    def next_day(self):
        old_fishes = self.fishes
        self.clear()
        for day, amount in old_fishes.items():
            if day == 0:
                self.add_fish(self.breed_rate, amount)
                self.add_fish(self.new_breed_rate, amount)
                continue
            self.add_fish(day - 1, amount)

    def progress_days(self, days):
        for day in range(days):
            self.next_day()

    def fish_count(self):
        return sum(self.fishes.values())

    def clear(self):
        self.fishes = {}


nums = [int(e) for e in open("puzzle.txt").read().split(",")]

ocean = Ocean(6, 2)

ocean.set_fishes(nums)
ocean.progress_days(80)
print(f"Part one {ocean.fish_count()}")

ocean.progress_days(256 - 80)
print(f"Part two {ocean.fish_count()}")
