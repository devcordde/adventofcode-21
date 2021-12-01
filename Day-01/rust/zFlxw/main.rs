use std::fs;
use std::ops::Add;

fn main() {
    // File input - may have to change your path.
    let content_string: String = fs::read_to_string("./input.txt").unwrap();
    let contents: Vec<&str> = content_string.lines().collect();

    part_01(&contents);
    part_02(&contents);
}

fn part_01(contents: &Vec<&str>) {
    let mut measurements: u16 = 0;
    let mut last_measurement: u16 = 0;

    for i in 1..contents.len() {
        let content: &str = contents[i];
        let num_content: u16 = content.to_string().parse::<u16>().unwrap();
        if num_content > last_measurement {
            measurements += 1;
        }

        last_measurement = num_content;
    }

    println!("Measurements: {}", measurements);
}

fn part_02(contents: &Vec<&str>) {
    let mut measurements: u16 = 0;
    let mut last_measurement: u16 = 0;
    let mut index: u16 = 0;

    loop {
        if index + 2 < contents.len() as u16 {
            let mut one: u16 = contents.get(index as usize).unwrap().to_string().parse().unwrap();
            let mut two: u16 = contents.get(index as usize + 1).unwrap().to_string().parse().unwrap();
            let mut three: u16 = contents.get(index as usize + 2).unwrap().to_string().parse().unwrap();

            println!("{} {} {}", one, two, three);

            // not first iteration
            if last_measurement != 0 {
                if one + two + three > last_measurement {
                    measurements += 1;
                }
            }

            last_measurement = one + two + three;
            println!("{}", last_measurement);
            index += 1;
        } else {
            break;
        }
    }

    println!("Measurements: {}", measurements);
}
