#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/**
 * Multiple ways to partition the array:
 * 1. naive approach 
 * 2. lumuto paritition
 * 3. Hoarse's partition
 */
// int partition_nav(vector<int>&arr, int low , int high){

// }

int partition_hoarse(vector<int>& arr, int low, int high){
    int pivot = arr[low];
    int i = low - 1;
    int j = high + 1;

    while (true) {
        do { i++; } while (arr[i] < pivot);
        do { j--; } while (arr[j] > pivot);

        if (i >= j) return j;

        swap(arr[i], arr[j]);
    }
}
int partition_lumuto(vector<int>& arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[high]);  
    return i + 1;
}
void quick_sort(vector<int>& arr, int low, int high) {//avg: O(nlogn) worst: O(n^2)
    if (low < high) {
      
        int pi = partition_lumuto(arr, low, high);

        quick_sort(arr, low, pi - 1);
        // quick_sort(arr, low, pi); for hoarse Partition 
        quick_sort(arr, pi + 1, high);
    }

}
int main() {

    vector<int> arr = {5,4,3,6,7,9,0,1,2}; 
    quick_sort(arr, 0, arr.size() - 1);
    cout<<"Sorted Array: ";
    for (int num : arr) cout << num << " ";



    return 0;
}
