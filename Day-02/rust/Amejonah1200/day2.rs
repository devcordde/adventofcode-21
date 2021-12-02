use std::fmt::Display;

pub(crate) fn start() {
  let actions: Vec<(&str, u64)> = include_str!("../../inputs/day2.txt").lines().map(|line| {
    let mut split = line.split(' ');
    (split.next().unwrap(), split.next().unwrap().parse::<u64>().unwrap())
  }).collect();
  println!("Part 1: {}", part1(&actions));
  println!("Part 2: {}", part2(&actions));
}

struct Submarine {
  horizontal: u64,
  depth: u64,
  aim: u64,
}

impl Display for Submarine {
  fn fmt(&self, f: &mut std::fmt::Formatter) -> std::fmt::Result {
    write!(f, "{}", self.horizontal * self.depth)
  }
}

fn part1(actions: &[(&str, u64)]) -> Submarine {
  let mut submarine = Submarine { horizontal: 0, depth: 0, aim: 0 };
  for (action, amount) in actions {
    match *action {
      "forward" => { submarine.horizontal += amount; }
      "up" => { submarine.depth -= amount; }
      "down" => { submarine.depth += amount; }
      _ => {}
    }
  }
  submarine
}

fn part2(actions: &[(&str, u64)]) -> Submarine {
  let mut submarine = Submarine { horizontal: 0, depth: 0, aim: 0 };
  for (action, amount) in actions {
    match *action {
      "forward" => {
        submarine.horizontal += amount;
        submarine.depth += submarine.aim * amount;
      }
      "up" => { submarine.aim -= amount; }
      "down" => { submarine.aim += amount; }
      _ => {}
    }
  }
  submarine
}
