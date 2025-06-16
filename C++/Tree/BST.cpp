// Implementation of Binary Search Tree operations.
// • Insert
// • DeleteElement
// • Search
#include <iostream>

using namespace std;

class Node {
public:
    int data;
    Node* left;
    Node* right;

    Node(int data) {
        this->data = data;
        left = right = nullptr;
    }
};

Node* Insert(Node* root, int val) {
    if (root == nullptr) {
        return new Node(val);
    }
    if (val < root->data) {
        root->left = Insert(root->left, val);
    } else {
        root->right = Insert(root->right, val);
    }
    return root;
}
bool Search(Node* root, int key) {
    if (root == nullptr) return false;
    if (root->data == key) return true;
    if (key < root->data){
        return Search(root->left, key);
    }
    else{
        return Search(root->right, key);
    }
}

void inorder(Node* root) {
    if (root == nullptr) return;
    inorder(root->left);
    cout << root->data << " ";
    inorder(root->right);
}
Node* FindInorderSuccessor(Node* root) {// left most node in the right subtree
    while (root && root->left != nullptr) {
        root = root->left;
    }
    return root;
}

Node* DeleteNode(Node* root, int val) {
    if (root == nullptr) return nullptr;

    if (val < root->data) {
        root->left = DeleteNode(root->left, val);
    } else if (val > root->data) {
        root->right = DeleteNode(root->right, val);
    } else {
        // Found node to delete
        // Case 1: No child
        if (root->left == nullptr && root->right == nullptr) {
            delete root;
            return nullptr;
        }

        // Case 2: One child
        if (root->left == nullptr) {
            Node* temp = root->right;
            delete root;
            return temp;
        } else if (root->right == nullptr) {
            Node* temp = root->left;
            delete root;
            return temp;
        }

        // Case 3: Two children
        Node* successor = FindInorderSuccessor(root->right);
        root->data = successor->data;
        root->right = DeleteNode(root->right, successor->data);
    }

    return root;
}
int main() {
    Node* root = nullptr;
    root = Insert(root, 5);
    Insert(root, 3);
    Insert(root, 7);
    Insert(root, 2);
    Insert(root, 4);
    Insert(root, 6);
    Insert(root, 8);

    cout << "Inorder: ";
    inorder(root);
    cout << "\n";

    cout << "Search 4: " << (Search(root, 4) ? "Found" : "Not Found") << "\n";
    cout << "Search 11: " << (Search(root, 11) ? "Found" : "Not Found") << "\n";

    root = DeleteNode(root, 3);
    cout << "Inorder after deleting 3: ";
    inorder(root);
    cout << "\n";

    return 0;
}
