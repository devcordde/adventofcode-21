Prefix ← 1⊸+⊸×˜÷2˙ # n(n+1)/2. Distance -> Fuel function for part 2
# Minimum fuel needed, given a function 𝔽 that defines the fuel needed to move a given distance and a list of crab positions 𝕩.
_fuel ← {⌊´+´𝔽¨|(↕1+⌈´𝕩)⊸-¨ 𝕩}
Parse ← •BQN¨ ','((⊢-˜+`×¬)∘=⊔⊢)⊑
input ← Parse •FLines ⊑ •args
•Out ⊢_fuel input
•Out Prefix _fuel input
