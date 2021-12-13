Surround ← {⍉⌽𝕨∾˘𝕩}⍟4 # Surround a rank 2 matrix 𝕩 with 𝕨, increasing its dimensions by 2 on each axis
adj ← -⊸∾⟨1‿0, 0‿1⟩ # The directional vectors to get adjacent positions
Positions ← ⥊∘(⊑((1+⋈⌜)○(↕-⟜2))1⊸⊑)≢ # Returns a flat list of all x,y positions in a given matrix
LowPoints ← { (∧´⊑⟜𝕩<(⊑⟜𝕩¨adj+<))¨ Positions 𝕩}/Positions # Returns a list of low point positions

# A breadth-first-search implementation that sums the amount of nodes it encounters
Bfs ← { g B ⟨f, v⟩ :
  {
     0=≠f ? 0 ;
     (≠f) + g B ⟨⍷((9≠⊑⟜g)¨∧(¬∊⟜v))⊸/(⥊f+⌜adj), ⍷f∾v⟩
  }
}

PartOne ← +´1+{⊑⟜𝕩¨ 𝕨} # Given a list of low points 𝕨 and a matrix 𝕩, return their sum of risk levels
PartTwo ← {×´3↑∨(𝕩⊸Bfs ⋈⋈⟨⟩˙)¨ 𝕨} # Given a list of low points 𝕨 and a matrix 𝕩, calculate the product of the 3 largest basin sizes

Parse ← > -⟜'0'⚇¯2 # Parse a list of input lines to a matrix
input ← 9 Surround Parse •FLines ⊑ •args
l ← LowPoints input
•Out l PartOne input
•Out l PartTwo input
