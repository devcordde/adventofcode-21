from typing import Callable
from time import time_ns as time

def calc_fuel(center: int, distances: list[int], cost: Callable[[int], int] = lambda x: x * 1):
    return sum([cost(abs(center - distance)) for distance in distances])


nums = [int(e) for e in open("puzzle.txt").read().split(",")]
nums.sort()
# We ignore the first and last third. It is very unlikely that we find the result there.
lower_bound = nums[int(len(nums) / 3)]
upper_bound = nums[len(nums) - int(len(nums) / 3)]

start = time()
# Part 1 solved by brute force
costs = min([calc_fuel(num, nums) for num in range(lower_bound, upper_bound)])
print(f"Part 1 {costs} in {(time() - start) / 1000000} ms")

start = time()
# Precompute costs for faster calculation. 75 times faster.
cost_list = [sum(range(num + 1)) for num in range(min(nums), max(nums))]
costs = min([calc_fuel(num, nums, lambda x: cost_list[x]) for num in range(lower_bound, upper_bound)])

print(f"Part 2 {costs} in {(time() - start) / 1000000} ms")
