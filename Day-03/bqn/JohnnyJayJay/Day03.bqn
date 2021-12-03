Parse ← >('1'⊸=⚇¯2) # Turn list of strings into table of bits
Bits ← (÷⟜2∘⊑≢)≤+˝ # Get list of most common bits in columns of a table
Convert ← +´((2⊸⋆∘↕≠)×⌽) # List of bits -> number

PartOne ← ((Convert¬)×Convert) Bits # Solve part one

_step ← {((=⟜(𝕩⊑𝔽Bits 𝕨)𝕩⊸⊑)˘ 𝕨)/𝕨} # Create stepping function for filtering process depending on criteria
_calc ← {⥊∘⊑((=⟜1≠)¨/⊢)∘(<𝔽_step`(↕(1⊑≢)))} # Calculate the list of bits that survives for given criteria
PartTwo ← ⊢_calc(×○Convert)¬_calc # solve part two

input ← Parse •FLines ⊑ •args
•Out PartOne input
•Out PartTwo input
