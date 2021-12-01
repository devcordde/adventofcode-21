input = ARGF.read
return if input == nil

lines = input.split("\n").map { |item| item.to_i }

last_number = nil
last_numbers = []
results = [0, 0]

lines.each do |num|
  # Part 1
  if last_number != nil
    results[0] += 1 if num > last_number
  end
  last_number = num

  # Part 2
  if last_numbers.length == 3
    current_numbers = last_numbers.last(2) << num
    results[1] += 1 if current_numbers.sum > last_numbers.sum
    last_numbers = last_numbers.drop(1)
  end
  last_numbers << num
end

puts "Part 1: #{results[0]}"
puts "Part 2: #{results[1]}"
