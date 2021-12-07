from collections import Counter


def grow(fish, amount):
    return {8: amount, 6: amount, 0: -amount} if fish == 0 else {fish: -amount, fish - 1: amount}


def calculate(initial_fish, days):
    fish_counter = Counter(initial_fish)
    for _ in range(days):
        for fish in {*fish_counter.items()}:
            fish_counter.update(grow(*fish))
    return sum(fish_counter.values())


def main():
    with open('input/06.txt', 'r') as f:
        initial_fish = list(map(int, f.read().split(',')))
    print(calculate(initial_fish, 80))
    print(calculate(initial_fish, 256))


if __name__ == '__main__':
    main()
