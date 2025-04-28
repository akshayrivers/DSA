use std::io;
use std::mem::swap;
fn bubble_sort(arr: &mut [i32]) {
    // fizz bottle
    let size = arr.len();
    for i in 0..size - 1 {
        for j in 0..size - i - 1 {
            if arr[j] > arr[j + 1] {
                arr.swap(j, j + 1);
            }
        }
    }
}
fn insertion_sort(arr: &mut [i32]) {
    // place me at my right place
    let size = arr.len();
    for i in 1..size {
        let key = arr[i];
        let mut j = i;
        while j > 0 && arr[j - 1] > key {
            arr[j] = arr[j - 1];
            j -= 1;
        }
        arr[j] = key;
    }
}

fn partition(arr: &mut [i32], low: usize, high: usize) -> usize {
    let mut pivot = arr[high];
    let mut i = low as isize - 1;
    for j in low..high {
        if arr[j] < pivot {
            i += 1;
            arr.swap(i as usize, j);
        }
    }
    arr.swap((i + 1) as usize, high);
    return (i + 1) as usize;
}

// okay so in rust the norm is to use slicing over our arithmatic pointers
// here is the code which uses that approach
// fn quick_sort(arr: &mut [i32]) {
//     if arr.len() > 1 {
//         let pivot_index = partition(arr);
//         let (left, right) = arr.split_at_mut(pivot_index);
//         quick_sort(left);
//         quick_sort(&mut right[1..]);
//     }
// }
// fn partition(arr: &mut [i32]) -> usize {
//     let len = arr.len();
//     let pivot = arr[len - 1];
//     let mut i = 0;
//     for j in 0..len - 1 {
//         if arr[j] < pivot {
//             arr.swap(i, j);
//             i += 1;
//         }
//     }
//     arr.swap(i, len - 1);
//     i
// }
fn quick_sort(arr: &mut [i32], low: usize, high: usize) {
    if low < high {
        let mut pi = partition(arr, low, high);

        if pi > 0 {
            quick_sort(arr, low, pi - 1);
        }
        quick_sort(arr, pi + 1, high);
    }
}
fn print_array(arr: &[i32]) {
    for val in arr {
        print! {"{} ",val};
    }
    println!();
}

fn merge(left: &[i32], right: &[i32], merged: &mut [i32]) {
    let (mut i, mut j, mut k) = (0, 0, 0);
    while i < left.len() && j < right.len() {
        if (left[i] < right[j]) {
            merged[k] = left[i];
            i += 1;
        } else {
            merged[k] = right[j];
            j += 1;
        }
        k += 1;
    }
    while i < left.len() {
        merged[k] = left[i];
        i += 1;
        k += 1;
    }
    while j < right.len() {
        merged[k] = right[j];
        j += 1;
        k += 1;
    }
}
// okay so let us try to implement merge sort using slicing
fn merge_sort(arr: &mut [i32]) {
    let len = arr.len();
    if len < 2 {
        return;
    }
    let mid = len / 2;
    merge_sort(&mut arr[..mid]);
    merge_sort(&mut arr[mid..]);
    let mut merged = arr.to_vec();
    merge(&arr[..mid], &arr[mid..], &mut merged[..]);
    arr.copy_from_slice(&merged);
}

fn main() {
    let mut arr = [5, 4, 3, 2, 1];
    // bubble_sort(&mut arr);
    // insertion_sort(&mut arr);
    // quick_sort(&mut arr, 0, 4);
    merge_sort(&mut arr);
    println!("{:?}", arr);
    print_array(&arr);
}
