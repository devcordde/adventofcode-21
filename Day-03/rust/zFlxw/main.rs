use std::fs;

fn main() {
    // File input
    let content_string: String = fs::read_to_string("./input.txt").unwrap();
    let contents: Vec<&str> = content_string.lines().collect();

    part_01(&contents);
    part_02(&contents);
}

fn part_01(contents: &Vec<&str>) {
    let mut gamma_rate: String = String::from("");
    let mut epsilon_rate: String = String::from("");
    let vector: &mut Vec<Vec<usize>> = &mut get_mapped_vector(contents);
    let _length: usize = contents[0].len();

    let mut temp_zero: usize = 0;
    let mut temp_one: usize = 0;

    for i in 0.._length {
        vector.iter().for_each(|vec| if vec[i] == 0 { temp_zero += 1 } else { temp_one += 1 });
        gamma_rate.push_str(if temp_zero > temp_one { "0" } else { "1" });
        epsilon_rate.push_str(if temp_zero < temp_one { "0" } else { "1" });
        temp_zero = 0;
        temp_one = 0;
    }

    let parsed_gamma = usize::from_str_radix(&gamma_rate, 2).unwrap();
    let parsed_epsilon = usize::from_str_radix(&epsilon_rate, 2).unwrap();

    println!("Consumption: {}", &parsed_gamma * &parsed_epsilon);
}

fn part_02(contents: &Vec<&str>) {
    let vector: &mut Vec<Vec<usize>> = &mut get_mapped_vector(contents);
    let _length: usize = contents[0].len();
    let oxygen: String = get_oxygen_generator_rating(&mut String::from(""), 0, vector[0].len(), &vector);
    let co2: String = get_co2_scrubber_rating(&mut String::from(""), 0, vector[0].len(), &vector);

    let oxygen_radix = usize::from_str_radix(&oxygen, 2).unwrap();
    let co2_radix = usize::from_str_radix(&co2, 2).unwrap();

    println!("Oxygen: {} : {}", oxygen, &oxygen_radix);
    println!("CO2 Scrubber: {} : {}", co2, &co2_radix);
    println!("Life support rating: {}", &co2_radix * &oxygen_radix);
}

fn get_oxygen_generator_rating(rating: &mut String, index: usize, len: usize, vector: &Vec<Vec<usize>>) -> String {
    if index < len {
        let most_common: usize = get_most_common_value(index, &vector);
        let mut vec: Vec<Vec<usize>> = Vec::new();
        vector.into_iter().filter(|v| v[index] == most_common).for_each(|v| vec.push(v.to_vec()));
        rating.push_str(&most_common.to_string());

        return get_oxygen_generator_rating(rating, index + 1, len, &vec);
    }

    rating.to_string()
}

fn get_co2_scrubber_rating(rating: &mut String, index: usize, len: usize, vector: &Vec<Vec<usize>>) -> String {
    if index < len {
        let less_common: usize = get_less_common_value(index, &vector);
        let mut vec: Vec<Vec<usize>> = Vec::new();
        vector.into_iter().filter(|v| v[index] == less_common).for_each(|v| vec.push(v.to_vec()));
        rating.push_str(&less_common.to_string());

        return get_co2_scrubber_rating(rating, index + 1, len, &vec);
    }

    rating.to_string()
}

fn get_most_common_value(index: usize, vector: &Vec<Vec<usize>>) -> usize {
    let mut tmp_zero: usize = 0;
    let mut tmp_one: usize = 0;

    vector.iter().for_each(|vec| if vec[index] == 0 { tmp_zero += 1 } else { tmp_one += 1 });
    if vector.len() == 1 {
        return vector[0][index];
    }

    if tmp_zero == tmp_one {
        return 1;
    }

    if tmp_zero > tmp_one {
        return 0;
    }

    1
}

fn get_less_common_value(index: usize, vector: &Vec<Vec<usize>>) -> usize {
    let mut tmp_zero: usize = 0;
    let mut tmp_one: usize = 0;

    vector.iter().for_each(|vec| if vec[index] == 0 { tmp_zero += 1 } else { tmp_one += 1 });
    if vector.len() == 1 {
        return vector[0][index];
    }

    if tmp_zero == tmp_one {
        return 0;
    }

    if tmp_zero < tmp_one {
        return 0;
    }

    1
}

fn get_mapped_vector(contents: &Vec<&str>) -> Vec<Vec<usize>> {
    let vector: &mut Vec<Vec<usize>> = &mut Vec::new();
    let _length: usize = 0;

    for content in contents {
        vector.push(content.chars().map(|c| c.to_string().parse().unwrap()).collect());
    }

    vector.to_vec()
}
