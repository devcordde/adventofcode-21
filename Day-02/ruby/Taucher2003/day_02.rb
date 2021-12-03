input = ARGF.read
lines = input.split("\n").map { |line| line.split(" ") }

results = [[0, 0], [0, 0, 0]]

lines.each do |line|
  command = line[0]
  value = line[1].to_i
	
  # Part 1
  results[0][0] += value if command == "forward"
  results[0][1] += value if command == "down"
  results[0][1] -= value if command == "up"

  # Part 2
  if command == "forward"
    results[1][0] += value
    results[1][1] += results[1][2] * value 
  end
  results[1][2] += value if command == "down"
  results[1][2] -= value if command == "up"
end

puts "Part 1: #{results[0][0] * results[0][1]}"
puts "Part 2: #{results[1][0] * results[1][1]}"
