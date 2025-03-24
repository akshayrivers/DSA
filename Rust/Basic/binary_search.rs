use std::io;

fn binary_search(arr: &[i32], k: i32) -> i32 {
    let mut l = 0;
    let mut r = arr.len() as i32 - 1;

    while l <= r {
        let mid = l + (r - l) / 2;
        let mid_idx = mid as usize; // because array indices of i32 are of uzise type

        if arr[mid_idx] == k {
            return mid;
        } else if arr[mid_idx] > k {
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    -1
}

fn main() {
    let mut input = String::new();
    println!("Enter size of array: ");
    io::stdin().read_line(&mut input).unwrap();
    let _n: usize = input.trim().parse().unwrap();

    let mut arr: Vec<i32>;
    println!("Enter elements of array: ");
    input.clear();
    io::stdin().read_line(&mut input).unwrap();
    arr = input
        .trim()
        .split_whitespace()
        .map(|x| x.parse::<i32>().unwrap())
        .collect();

    arr.sort(); // Sorting the array

    println!("Input the number you want to search in the given array: ");
    input.clear();
    io::stdin().read_line(&mut input).unwrap();
    let k: i32 = input.trim().parse().unwrap();

    let idx = binary_search(&arr, k);
    if idx == -1 {
        println!("No number available in array");
    } else {
        println!("The number is at index: {}", idx);
    }
}
