# input parsing
inputLines ← •file.Lines ⊑ •args

Split ← (⊑⊐⟜' ')(↑⋈↓)⊢

cmds ← "forward"‿"down"‿"up"

input ← Split¨ inputLines
inputCmds ← ((cmds⊸⊐⌾<)∘⊑)¨ input
inputVals ← (•BQN∘(1⊸⊑))¨ input

# Part 1
changes ← ⟨1‿0, 0‿1, 0‿¯1⟩

Steps1 ← (⊑⟜changes)¨×⊢
PartOne ← ×´∘(+´)∘Steps1

# Part 2
plainChanges ← ⟨1‿0, 0‿0, 0‿0⟩
aimFactorChanges ← ⟨0‿1, 0‿0, 0‿0⟩
aimChanges ← 0‿1‿¯1

Steps2 ← ((+`∘((⊑⟜aimChanges)¨×⊢))×((⊑⟜aimFactorChanges)¨×⊢))+((⊑⟜plainChanges)¨×⊢)
PartTwo ← ×´∘(+´)∘Steps2

•Out (inputCmds PartOne inputVals)
•Out (inputCmds PartTwo inputVals)
