#include <iostream>
#include <algorithm>

using namespace std;

// fizz bottle- the array is first sorted through the the right side 
void bubble_sort(int arr[],int n ) {
    for(int i=0;i<n-1;i++){
        for( int j=0;j<n-i-1;j++){
            if(arr[j]>arr[j+1]){
                swap(arr[j],arr[j+1]);
            }
        }
    }
}

// Get me in my correct position first - first sorted from left
void insertsion_sort(int arr[],int n ) {
    for(int i =1;i<n;i++){
        int key= arr[i];
        int j =i-1;
        while(j>=0&& arr[j]>key){
            arr[j+1]=arr[j];
            j--;
        }
        arr[j+1]=key;
    }
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

    int k =0;
    cout<<"Enter 1 for bubble_sort and enter 2 from insertion_sort: ";
    cin>>k;
    if(k==1){
        bubble_sort(arr,n);
    }
    else if(k==2){
        insertsion_sort(arr,n);
    }
    cout<<"Your sorted array is : ";
    for (int i = 0; i < n; i++) {
        cout<< arr[i]<<" ";
    }

    return 0;
}
