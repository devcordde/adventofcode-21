Parse ← •BQN¨ ','((⊢-˜+`×¬)∘=⊔⊢)⊑ # Turn list of input lines into list of lanternfish timers

# 256 (+9) length array, where each element represents the fish count after that day (index)
# when starting with a single fish whose reproduction timer is at 6.
# E.g. the element at index 7 represents the amount of lanternfish after 7 days (2 fish).
fish ← {𝕩∾+´¯7‿¯9⊏𝕩}⍟256 1+/7‿2
# For a list of fish timers 𝕩 and a number of days 𝕨, calculate the cumulative amount of fish after that number of days.
Count ← +´ (⊏⟜fish)∘(6+-)

input ← Parse •FLines ⊑ •args
•Out 80 Count input
•Out 256 Count input
