// Implementation of Priority queue using Heap.
#include <iostream>
#include <vector>

using namespace std;

class MinHeap {
private:
    vector<int> heap;

    int parent(int i) { return (i - 1) / 2; }
    int leftChild(int i) { return (2 * i + 1); }
    int rightChild(int i) { return (2 * i + 2); }

    void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            swap(heap[parent(i)], heap[i]);
            i = parent(i);
        }
    }

    void heapifyDown(int i) {
        int smallest = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < heap.size() && heap[l] < heap[smallest])
            smallest = l;
        if (r < heap.size() && heap[r] < heap[smallest])
            smallest = r;

        if (smallest != i) {
            swap(heap[i], heap[smallest]);
            heapifyDown(smallest);
        }
    }

public:
    void insert(int key) {
        heap.push_back(key);
        heapifyUp(heap.size() - 1);
    }

    int extractMin() {
        if (heap.empty()) {
            cout << "Heap is empty.\n";
            return -1;
        }

        int root = heap[0];
        heap[0] = heap.back();
        heap.pop_back();
        heapifyDown(0);
        return root;
    }

    int peekMin() {
        if (heap.empty()) {
            cout << "Heap is empty.\n";
            return -1;
        }
        return heap[0];
    }

    void display() {
        cout << "Heap: ";
        for (int val : heap) {
            cout << val << " ";
        }
        cout << "\n";
    }
};
void heapify(vector<int>& arr, int i, int size) {
    int smallest = i;
    int left = 2*i + 1;
    int right = 2*i + 2;

    if (left < size && arr[left] > arr[smallest])
        smallest = left;
    if (right < size && arr[right] > arr[smallest])
        smallest = right;

    if (smallest != i) {
        swap(arr[i], arr[smallest]);
        heapify(arr, smallest, size);
    }
}

void heapSort(vector<int>& arr) {
    int n = arr.size();

    // Build heap
    for (int i = n/2 - 1; i >= 0; i--) {
        heapify(arr, i, n);
    }

    // Heap sort
    for (int i = n - 1; i >= 1; i--) {
        swap(arr[0], arr[i]);
        heapify(arr, 0, i);
    }
}

int main() {
    MinHeap pq;

    pq.insert(10);
    pq.insert(4);
    pq.insert(15);
    pq.insert(3);
    pq.insert(2);
    pq.insert(20);
    pq.insert(0);

    pq.display();

    cout << "Minimum: " << pq.peekMin() << "\n";

    cout << "Extracting Min: " << pq.extractMin() << "\n";
    pq.display();

    cout << "Extracting Min: " << pq.extractMin() << "\n";
    pq.display();

    vector<int> arr = {3, 1, 6, 5, 2, 4};
    heapSort(arr);
    cout << "Sorted array: ";
    for (int num : arr) cout << num << " ";
    cout << endl;

    return 0;
}
