#include <iostream>
using namespace std;

class Node {
public:
    int value;
    Node* next;

    Node(int val) {
        value = val;
        next = nullptr;
    }
};

class Queue {
    Node* head;
    Node* tail;

public:
    Queue() {
        head = nullptr;
        tail = nullptr;
    }

    void enqueue(int val) {
        Node* newNode = new Node(val);
        if (head == nullptr) {
            head = tail = newNode;
        } else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    void dequeue() {
        if (head == nullptr) {
            cout << "Queue is empty!\n";
            return;
        }

        Node* temp = head;
        head = head->next;
        delete temp;

        if (head == nullptr) {
            tail = nullptr;
        }
    }

    int size() {
        int count = 0;
        Node* temp = head;
        while (temp != nullptr) {
            temp = temp->next;
            count++;
        }
        return count;
    }

    void printQueue() {
        Node* temp = head;
        while (temp != nullptr) {
            cout << temp->value << " -> ";
            temp = temp->next;
        }
        cout << "NULL\n";
    }

    ~Queue() {
        while (head != nullptr) {
            Node* temp = head;
            head = head->next;
            delete temp;
        }
    }
};

int main() {
    cout << "Input the size of queue you want to create: ";
    int n = 0;
    cin >> n;
    if (n <= 0) {
        cout << "Please write a valid length\n";
        return 0;
    }

    Queue q;
    while (n--) {
        int k;
        cin >> k;
        q.enqueue(k);
    }

    cout << "Size of the queue as of now = " << q.size() << "\n";
    q.printQueue();

    while (true) {
        cout << "Type 1 to enqueue, 2 to dequeue: ";
        int j;
        cin >> j;
        if (j == 1) {
            cout << "Enter element to enqueue: ";
            int k;
            cin >> k;
            q.enqueue(k);
            cout << "After enqueue: ";
            q.printQueue();
        } else {
            q.dequeue();
            cout << "Size of queue after dequeue = " << q.size() << "\n";
            q.printQueue();
        }
    }

    return 0;
}
