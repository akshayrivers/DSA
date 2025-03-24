#include <iostream>
#include <algorithm>

using namespace std;

int binary_search(int arr[], int k, int n) {
    int l = 0;
    int r = n - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (arr[mid] == k) {
            return mid;
        } else if (arr[mid] > k) {
            r = mid - 1;  
        } else {
            l = mid + 1; 
        }
    }
    return -1;
}

int main() {
    int n;
    cout << "Enter size of array: ";
    cin >> n;

    int arr[n];
    cout << "Enter elements of array: ";
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    sort(arr, arr + n); 

    int k;
    cout << "Input the number you want to search in the given array: ";
    cin >> k;

    int idx = binary_search(arr, k, n);
    if (idx == -1) {
        cout << "No number available in array" << endl;
    } else {
        cout << "The number is at index: " << idx << endl;
    }

    return 0;
}
