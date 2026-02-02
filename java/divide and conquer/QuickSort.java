public class QuickSort {
    // Average TC -> O(nlogn) but in the worst case it can get to O(n^2)
    public static void quickSort(int arr[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int PIdx = partition(arr, si, ei);
        quickSort(arr, si, PIdx - 1); // left 
        quickSort(arr, PIdx + 1, ei); // right
    }

    private static int partition(int[] arr, int si, int ei) {
        int pivot = arr[ei];
        int i = si - 1; // to make place for elements smaller than pivot

        for (int j = si; j < ei; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        i++;
        // swap pivot with element at index i
        arr[ei] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int arr[] = {3, 2, 1, 4, 6, 7};
        int n = 6;
        quickSort(arr, 0, n - 1); // quickSort(arr, 0, 5)
        System.out.println("The array now is ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
