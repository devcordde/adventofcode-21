input = ARGF.read
lines = input.split("\n").map { |value| value.split("").map { |string| string.to_i } }

def transpose(array)
  transposed = []
  array[0].length.times do |i|
    transposing = []
    array.length.times do |j|
      transposing << array[j][i]
    end
    transposed << transposing
  end
  transposed
end

def invert(array)
  array.map { |value| if value == 1; 0 else 1 end }
end

def find_common(array)
  array.max_by { |number| array.select { |val| val == number }.length }
end

transposed = transpose(lines)

# Part 1
gamma_array = transposed.map { |array| find_common(array) }
epsilon_array = invert(gamma_array)
gamma = gamma_array.join.to_i(2)
epsilon = epsilon_array.join.to_i(2)

puts "Part 1: #{gamma * epsilon}"

