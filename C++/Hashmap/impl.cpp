// Implementation of Hashing Techniques.
// • Chaining
// • Linear Probing#include <iostream>
#include <list>
#include <vector>

using namespace std;

class HashTableChaining {
private:
    vector<list<int>> table;
    int size;

    int hashFunction(int key) {
        return key % size;
    }

public:
    HashTableChaining(int s) {
        size = s;
        table.resize(size);
    }

    void insert(int key) {
        int index = hashFunction(key);
        table[index].push_back(key);
    }

    void remove(int key) {
        int index = hashFunction(key);
        table[index].remove(key);
    }

    void display() {
        for (int i = 0; i < size; i++) {
            cout << i << ": ";
            for (int val : table[i]) {
                cout << val << " -> ";
            }
            cout << "NULL\n";
        }
    }
};


class HashTableLinearProbing {
private:
    vector<int> table;
    int size;
    int EMPTY = -1;

    int hashFunction(int key) {
        return key % size;
    }

public:
    HashTableLinearProbing(int s) {
        size = s;
        table.resize(size, EMPTY);
    }

    void insert(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != EMPTY) {
            index = (index + 1) % size;
            if (index == startIndex) {
                cout << "Hash table is full!\n";
                return;
            }
        }

        table[index] = key;
    }

    void remove(int key) {
        int index = hashFunction(key);
        int startIndex = index;

        while (table[index] != EMPTY) {
            if (table[index] == key) {
                table[index] = EMPTY;
                return;
            }
            index = (index + 1) % size;
            if (index == startIndex) break;
        }

        cout << "Key not found.\n";
    }

    void display() {
        for (int i = 0; i < size; i++) {
            cout << i << ": ";
            if (table[i] != EMPTY)
                cout << table[i];
            else
                cout << "EMPTY";
            cout << "\n";
        }
    }
};
int main() {
    cout << "Hashing with Chaining:\n";
    HashTableChaining chainTable(7);
    chainTable.insert(10);
    chainTable.insert(20);
    chainTable.insert(15);
    chainTable.insert(7);
    chainTable.display();

    cout << "\nHashing with Linear Probing:\n";
    HashTableLinearProbing linearTable(7);
    linearTable.insert(10);
    linearTable.insert(20);
    linearTable.insert(15);
    linearTable.insert(7);
    linearTable.display();

    return 0;
}
