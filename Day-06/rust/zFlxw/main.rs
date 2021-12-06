use std::fs;

fn main() {
    // File input
    let content_string: String = fs::read_to_string("./input.txt").unwrap();

    // Part 1 - Works with the second method as well, but this was my first try and I didn't want to just discard it.
    // (But it's still slow as hell)
    calculate_fishes(80, &content_string);

    // Part 2
    // technically works, but takes forever and the length of a vector is just too short.
    // calculate_fishes(256, &content_string);
    // This method is way faster.
    calculate_fishs_but_fast(256, &content_string);
}

fn calculate_fishes(days: u16, content_string: &String) {
    let lantern_fishs: &mut Vec<i8> = &mut vec![];
    // Map and store values in vector
    content_string.split(",").map(|num_str| num_str.parse::<i8>().unwrap()).for_each(|num| {
        lantern_fishs.push(num);
    });

    // days to calculate the fishs for
    for i in 1..=days {
        for fish_index in 0..lantern_fishs.len() {
            let fish_timer = lantern_fishs[fish_index];
            let mut new_timer = fish_timer - 1;
            if new_timer < 0 {
                new_timer = 6;
                lantern_fishs.push(8);
            }
            
            lantern_fishs[fish_index] = new_timer;
        }

        println!("Day {}: ({})", i, lantern_fishs.len());
    }
}

fn calculate_fishs_but_fast(days: u16, content_string: &String) {
    let mut num_state = [0 as u64; 9];
    for n in content_string.split(",").map(|num_str| num_str.parse::<usize>().unwrap()) {
        num_state[n] += 1;
    }

    for day in 0..days {
        let spawn = num_state[0];
        for i in 0..=7 {
            num_state[i] = num_state[i + 1];
        }

        num_state[6] += spawn;
        num_state[8] = spawn;

        println!("Day {}: {}", day, num_state.into_iter().fold(0, |acc, n| acc + n));
    }
}