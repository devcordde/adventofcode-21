use std::fs;

fn main() {
    // File input
    let content_string: String = fs::read_to_string("./input.txt").unwrap();
    let contents: Vec<&str> = content_string.lines().collect();
    
    let positions: Vec<isize> = content_string.split(",").map(|s| s.parse::<isize>().unwrap()).collect();
    println!("Num: {}", calc_lowest_fuel(true, &positions));
}

fn calc_lowest_fuel(incr: bool, positions: &Vec<isize>) -> isize {
    let mut current_lowest: isize = isize::MAX;
    let highest_num: isize = positions.clone().into_iter().max().unwrap();

    for i in 0..=highest_num {
        let fuel = calc_fuel(incr, i, &positions);
        // I don't know why, but if I remove this line of code, it doesn't finish at all. HOW?
        println!("{}: {}", i, fuel);
        if current_lowest > fuel {
            current_lowest = fuel;
        }
    }

    current_lowest
}

fn calc_fuel(incr: bool, pos: isize, positions: &Vec<isize>) -> isize {
    let mut fuel: isize = 0;
    for i in 0..positions.len() {
        let from_pos = positions[i];
        let diff = isize::abs(from_pos - pos);
        fuel += diff;

        if incr {
            // Part 2
            for j in 0..diff {
                fuel += j;
            }
        }
    }

    fuel
} 
