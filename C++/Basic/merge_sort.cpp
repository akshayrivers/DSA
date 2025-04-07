#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

void merge(vector<int>&arr, int left, int mid, int right){
    int n1= mid-left+1;
    int n2 = right-mid;

    vector<int> L(n1),R(n2);

    for(int i =0;i<n1;i++){
        L[i]=arr[left+i];
    }
    for(int i =0;i<n2;i++){
        R[i]=arr[mid+1+i];
    }
    int i =0,j=0;
    int k =left;
    // now comes the main step of merge 
    while(i<n1&&j<n2){
        if(L[i]<R[j]){
            arr[k]=L[i];
            i++;
        }
        else{
            arr[k]=R[j];
            j++;
        }
        k++;
    }
    //copying the remaining elements if there are any
    while(i<n1){
        arr[k]=L[i];
        i++;
        k++;
    }
    while(j<n2){
        arr[k]=R[j];
        j++;
        k++;
    }
}

void merge_sort(vector<int>& arr,int low, int high){
    if (low >= high)
        return;

    int mid= low +(high-low)/2;

    merge_sort(arr,low,mid);
    merge_sort(arr, mid+1,high);
    merge(arr,low,mid,high);

}

int main() {

    vector<int> arr = {5,4,3,6,7,9,0,1,2}; 
    merge_sort(arr, 0, arr.size() - 1);
    cout<<"Sorted Array: ";
    for (int num : arr) cout << num << " ";



    return 0;
}
