# A list of all brackets. Used to assign a number to each bracket (pair)
bracks ← "()[]{}<>"

# Given a "stack" 𝕩 and a bracket number 𝕨, updates the stack:
# - if it is a closing bracket, pops an element from the stack if it matches the bracket, otherwise appends the bracket to the end
# - if it is an opening bracket, adds it to the front of the stack
StepStack ← {
  2|𝕨 ? 1↓ 𝕨 (=⟜(1+⊑)◶(∾˜)‿⊢) 𝕩 ;
  𝕨∾𝕩
}

PartOne ← {+´ (⊑⟜3‿57‿1197‿25137(⌊÷⟜2)∘⊑)¨ (×≠¨)⊸/ (2⊸|⊸/)¨ 𝕩}

PartTwo ← {(⌊≠÷2˙)⊸⊑ ∧ {𝕨+5×𝕩}´¨ ⌽¨ (1+÷⟜2)⚇¯2 {¬∨´2|𝕩}¨⊸/ 𝕩}

Parse ← ⌽∘>(bracks⊸⊐)¨
input ← (⟨⟩⊸(StepStack´))¨  Parse¨ •FLines ⊑ •args

•Out PartOne input
•Out PartTwo input
