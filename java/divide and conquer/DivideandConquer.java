import java.util.Arrays;

public class DivideandConquer {
public static void printarr(int arr[]){
    for(int i =0; i <arr.length;i++){
        System.out.print(arr[i]+" ");
    }System.out.println();
}
public static void mergesort(int arr[],int si,int ei){
    if(si>=ei){
        return;
    }
    //kaam
    int mid= si +(ei-si)/2;
    mergesort(arr, si, mid);//left part 
    mergesort(arr, mid+1, ei);//right part
    merge(arr, si, mid, ei);
}
public static void merge(int arr[],int si ,int mid, int ei){
    int temp[]= new int[ei-si+1];
    int i= si;//iterator for left part
    int j= mid+1;//iteratror for right part
    int k =0;//iterator for temp arr

    while(i<=mid&&j<=ei){
        if(arr[i]<arr[j]){
            temp[k]=arr[i];
            i++;
        }
        else{
            temp[k]= arr[j];
            j++;
        }
        k++;
    }
    //left part
    while (i<=mid){
        temp[k++]=arr[i++];
    }
    //right part
    while (j<=ei){
        temp[k++]=arr[j++];
    }
    //copy temp to orignal array
    for (k=0,i=si;k<temp.length;k++,i++){
        arr[i]=temp[k];
    }   
}
private static void mergeSort(String[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;

            // Recursively sort the first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Merge Function for Strings
    private static void merge(String[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        String[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);
        String[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);

        // Merge the temporary arrays

        // Initial indices of the two subarrays
        int i = 0, j = 0;

        // Initial index of the merged subarray
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if any
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if any
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Utility function to print an array of Strings
    private static void printArray(String[] arr) {
        for (String str : arr) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    public static int majorityElement(int[] nums) {
        int count=1;
        int maxcount=1; 
        Arrays.sort(nums);
        int majorelement=nums[0];
       
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                count++;           
            }
            
            else{
                count=1;
            }     
        
          if (count > maxcount) {
                maxcount = count;
                majorelement = nums[i];
            }
        }
      return majorelement;
    }
    private static int countInRange(int[]nums,int num,int lo,int hi)
     {
        int count=0;
        for(int i=lo;i<=hi;i++) {
            if(nums[i] ==num) {
                count++;
            }}
            return count;
        }
        


    public static void main(String[] args) {
        int arr[]={6,3,9,5,2,8,3,23,475,6,77,9344,46636,544,6,75434,4,6,7,4,754,3,3};
        mergesort(arr, 0, arr.length-1);
        printarr(arr);
        String[] arr1 = { "sun", "earth", "mars", "mercury" };
        int result=majorityElement(arr);
System.out.println(result);
        System.out.println("Original Array:");
        printArray(arr1);

        mergeSort(arr1, 0, arr1.length - 1);

        System.out.println("\nSorted Array:");
        printArray(arr1);
    }
}