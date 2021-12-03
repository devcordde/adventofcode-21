# input parsing
inputLines ← •file.Lines ⊑ •args

Split ← (⊑⊐⟜' ')(↑⋈↓)⊢

# The idea behind this solution is that the instructions can be split into commands and values.
# Each command has a unit value associated with it that defines how it affects the state of the submarine.
# To calculate the end result, a delta is calculated for each instruction representing what it changes.
# Then those deltas are folded.

cmds ← "forward"‿"down"‿"up"

input ← Split¨ inputLines
inputCmds ← ((cmds⊸⊐⌾<)∘⊑)¨ input
inputVals ← (•BQN∘(1⊸⊑))¨ input

# Part 1
changes ← ⟨1‿0, 0‿1, 0‿¯1⟩ # units for part one. first is the horizontal position, second is the depth.

Steps1 ← (⊑⟜changes)¨×⊢ # multiplies instruction values with corresponding units to get a the deltas
PartOne ← ×´∘(+´)∘Steps1 # fold it to get the end result

# Part 2
plainChanges ← ⟨1‿0, 0‿0, 0‿0⟩ # units for part two that are simply multiplied by the instruction value
aimFactorChanges ← ⟨0‿1, 0‿0, 0‿0⟩ # units for part two that are multiplied by the instruction value and the current aim
aimChanges ← 0‿1‿¯1 # units for how the aim is affected

# calculate states by applying both state change types and incorperating the running sum (scan) of the aim changes
Steps2 ← ((+`∘((⊑⟜aimChanges)¨×⊢))×((⊑⟜aimFactorChanges)¨×⊢))+((⊑⟜plainChanges)¨×⊢)
PartTwo ← ×´∘(+´)∘Steps2

•Out (inputCmds PartOne inputVals)
•Out (inputCmds PartTwo inputVals)
