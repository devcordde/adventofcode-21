Parse ← •BQN¨ ','((⊢-˜+`×¬)∘=⊔⊢)⊑ # Turn list of input lines into list of lanternfish timers

# 256 (+9) length array, where each element represents the fish count after that day (index)
# when starting with a single fish whose reproduction timer is at 6.
# E.g. the element at index 7 represents the amount of lanternfish after 7 days (2 fish).
fish ← (2‿2∾˜7⥊1) {𝕩∾((𝕨-7)⊑𝕩)+((𝕨-9)⊑𝕩)}´ 9+⌽↕256
# For a list of fish timers 𝕩 and a number of days 𝕨, calculate the cumulative amount of fish after that number of days.
Count ← +´ (⊑⟜fish¨)∘(⊣+(6⊸-⊢))

input ← Parse •FLines ⊑ •args
•Out 80 Count input
•Out 256 Count input
