# Line descriptor = list of starting and ending point, e.g. ⟨3‿9, 6‿9⟩
Direction ← ×-˜´ # For a line descriptor, return the directional vector
Steps ← 1+(⌈´|∘-˜´) # For a line descriptor, return how steps are required to get from the former to the latter ("0th" step included)
Points ← {(⊑𝕩)⊸+¨ (Direction 𝕩)⊸×¨ ↕Steps 𝕩} # For a line descriptor, return all the points the line traverses
Straight ← ∨´=´ # Return 1 if a line is straight, 0 if it's diagonal
# Collect all points all lines traverse, group by equality, filter out groups of only 1 point, count remaining
PartTwo ← ≠ (2⊸≤∘≠¨⊸/)∘(⊐⊸⊔)∘(∾´)∘(Points¨)
PartOne ← PartTwo∘(Straight¨/⊢)

Parse ← •BQN¨('⟨'⊸∾∾⟜'⟩')¨∘((⊑⊐⟜' ') (↑⋈(+⟜4⊸↓))⊢) # "x1,y1 -> x2,y2" => ⟨x1‿y1, x2‿y2⟩

input ← Parse¨ •FLines ⊑ •args

•Out PartOne input
•Out PartTwo input
