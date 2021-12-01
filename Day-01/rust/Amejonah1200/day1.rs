pub(crate) fn start() {
  let measurements: Vec<i32> = include_str!("../../inputs/day1.txt").lines().map(|line| line.parse::<i32>().unwrap()).collect();
  println!("Part 1: {}", part1(&measurements));
  println!("Part 2: {}", part2(&measurements));
}

fn part1(measurements: &Vec<i32>) -> usize {
  measurements.windows(2).filter(|pair| pair[0] < pair[1]).count()
}

fn part2(measurements: &Vec<i32>) -> usize {
  part1(&measurements.windows(3).map(|window| window.iter().sum::<i32>()).collect())
}
