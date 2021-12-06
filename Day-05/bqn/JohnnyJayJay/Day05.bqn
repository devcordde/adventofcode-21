# Line descriptor = list of starting and ending point, e.g. ⟨3‿9, 6‿9⟩
Direction ← ×-˜´ # For a line descriptor, return the directional vector
Steps ← 1+(⌈´|∘-˜´) # For a line descriptor, return how steps are required to get from the former to the latter ("0th" step included)
Points ← {(⊑𝕩)⊸+¨ (Direction 𝕩)⊸×¨ ↕Steps 𝕩} # For a line descriptor, return all the points the line traverses
MarkLine ← {+⟜1⌾((Points 𝕨)⊸⊑) 𝕩} # "Mark" the line given by line descriptor 𝕨 in the 2d-matrix 𝕩
MaxCoords ← ⌈˝∘>∾´ # For a list of line descriptors, find the maximum x and y coordinates
Straight ← ∨´=´ # Return 1 if a line is straight, 0 if it's diagonal
Init ← (1˙+MaxCoords)⥊0˙ # Initialise the 2d-grid filled with 0s based on a list of line descriptors
Count ← +´(⥊≥2˙) # Count the amount of points in a 2d-grid where at least 2 lines cross
PartTwo ← Count∘(Init MarkLine´⊢)
PartOne ← PartTwo∘(Straight¨/⊢)

Parse ← •BQN¨('⟨'⊸∾∾⟜'⟩')¨∘((⊑⊐⟜' ') (↑⋈(+⟜4∘⊣↓⊢))⊢) # "x1,y1 -> x2,y2" => ⟨x1‿y1, x2‿y2⟩

input ← Parse¨ •FLines ⊑ •args

•Out PartOne input
•Out PartTwo input
