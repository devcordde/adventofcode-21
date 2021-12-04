Split ← (¬-˜⊢×·+`»⊸>)∘≠⊔⊢ # Split list 𝕩 at element 𝕨
RmEmpty ← 1⊸⌊∘≠¨⊸/ # Remove empty lists from depth 2 array 𝕩
DrawOrder ← •BQN∘('⟨'⊸∾)∾⟜'⟩'∘⊑ # Parse number order from input lines 𝕩
Boards ← >¨((5˙/(↕∘÷⟜5∘≠))⊔(•BQN⚇¯2' '⊸Split¨))∘RmEmpty∘(1⊸↓) # Parse bingo boards from input lines 𝕩
Winner ← (+˝∨○(∨´=⟜5)+˝˘) =⟜¯1 # Determine if the board 𝕩 has a bingo
Mark ← (÷0-=)⌊⊣ # "Replace" 𝕩s in board 𝕨 with ¯1
Step ← (¬∘Winner¨/⊣)Mark¨⊢ # Advance a set of boards (𝕨) by playing a number (𝕩)
Play ← (<⊣)Step`⊢ # Calculate all game states given a list of numbers to draw 𝕩 and an array of boards 𝕨
Winners ← Winner⚇¯2/¨⊢ # For a series of game states 𝕩, only keep the winning boards in each one
Score ← (+´⥊)∘(⌈⟜0) # Calculate final score of a board (winning number already included) by adding up all unmarked entries
FinalBoards ← ∾´(Winners∘Play×⊢)
PartOne ← Score ⊑∘FinalBoards
PartTwo ← Score ⊑∘⌽∘FinalBoards

input ← •FLines ⊑ •args
numbers ← DrawOrder input
b ← Boards input
•Out b PartOne numbers
•Out b PartTwo numbers
