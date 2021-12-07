from typing import Callable


def calc_fuel(center: int, distances: list[int], cost: Callable[[int], int] = lambda x: x * 1):
    return sum([cost(abs(center - distance)) for distance in distances])


nums = [int(e) for e in open("puzzle.txt").read().split(",")]
nums.sort()
lower_bound = int(len(nums) / 4)
upper_bound = len(nums) - lower_bound

# Part 1 solved by median. Might be an accident that it works.
costs = calc_fuel(nums[round(len(nums) / 2)], nums)
print(f"Part 1 {costs} by median. Probably correct")

# Part 1 solved by brute force
# We ignore the first and last quarter. It is very unlikely that we find the result there.
costs = min([calc_fuel(num, nums) for num in range(nums[lower_bound], nums[upper_bound])])
print(f"Part 1 {costs} by brute force. Correct")

# Precompute costs for faster calculation. 75 times faster.
cost_list = [sum(range(num + 1)) for num in range(min(nums), max(nums))]
# We ignore the first and last quarter. It is very unlikely that we find the result there.
costs = min([calc_fuel(num, nums, lambda x: cost_list[x]) for num in range(nums[lower_bound], nums[upper_bound])])

print(f"Part 2 {costs} by brute force")
