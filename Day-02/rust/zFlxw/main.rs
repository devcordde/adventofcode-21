use std::fs;
use std::ops::Add;

fn main() {
    // File input
    let content_string: String = fs::read_to_string("./input.txt").unwrap();
    let contents: Vec<&str> = content_string.lines().collect();

    part_01(&contents);
    part_02(&contents);
}

fn part_01(contents: &Vec<&str>) {
    let mut horizontal_position: u32 = 0;
    let mut depth: u32 = 0;

    for content in contents {
        let arguments: Vec<&str> = content.split(" ").collect();
        let operation: &str = arguments.first().unwrap();
        let num: u32 = arguments.get(1).unwrap().to_string().parse().unwrap();
        if operation == "forward" {
            horizontal_position += num;
        } else if operation == "down" {
            depth += num;
        } else if operation == "up" {
            depth -= num;
        }
    }

    println!("Result: {}", horizontal_position * depth);
}

fn part_02( contents: &Vec<&str>) {
    let mut horizontal_position: u32 = 0;
    let mut depth: u32 = 0;
    let mut aim: u32 = 0;

    for content in contents {
        let arguments: Vec<&str> = content.split(" ").collect();
        let operation: &str = arguments.first().unwrap();
        let num: u32 = arguments.get(1).unwrap().to_string().parse().unwrap();
        if operation == "forward" {
            horizontal_position += num;
            depth += aim * num;
        } else if operation == "down" {
            aim += num;
        } else if operation == "up" {
            aim -= num;
        }
    }

    println!("Result: {}", horizontal_position * depth);
}
