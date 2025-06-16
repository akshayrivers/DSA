#include <iostream>

using namespace std;
// Implementation of tree traversals.
// • Pre-order
// • In-order
// • Post-order
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

class BinaryTree {
public:
    int idx = -1;

    Node* buildTree(int nodes[], int size) {
        idx++;
        if (idx >= size || nodes[idx] == -1) {
            return nullptr;
        }

        Node* newNode = new Node(nodes[idx]);
        newNode->left = buildTree(nodes, size);
        newNode->right = buildTree(nodes, size);
        return newNode;
    }

    void preorder(Node* root) {
        if (root == nullptr) return;
        cout << root->data << " ";
        preorder(root->left);
        preorder(root->right);
    }

    void inorder(Node* root) {
        if (root == nullptr) return;
        inorder(root->left);
        cout << root->data << " ";
        inorder(root->right);
    }

    void postorder(Node* root) {
        if (root == nullptr) return;
        postorder(root->left);
        postorder(root->right);
        cout << root->data << " ";
    }
    void levelOrder(Node* root) {
        if (root == nullptr) return;

        queue<Node*> q;
        q.push(root);
        q.push(nullptr);

        while (!q.empty()) {
            Node* curr = q.front();
            q.pop();

            if (curr == nullptr) {
                cout << "\n";  
                if (!q.empty()) {
                    q.push(nullptr); 
                }
            } else {
                cout << curr->data << " ";
                if (curr->left != nullptr) q.push(curr->left);
                if (curr->right != nullptr) q.push(curr->right);
            }
        }
    }
    int height(Node* root){
        if(root==nullptr) return 0;
        int lh = height(root->left);
        int rh = height(root->right);
        return max(lh,rh)+1;
    }
};

int main() {
    int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};  
    BinaryTree tree;
    Node* root = tree.buildTree(nodes, sizeof(nodes) / sizeof(int));

    cout << "Preorder Traversal: ";
    tree.preorder(root);
    cout << "\n";

    cout << "Inorder Traversal: ";
    tree.inorder(root);
    cout << "\n";

    cout << "Postorder Traversal: ";
    tree.postorder(root);
    cout << "\n";
    
    cout << "Height of the tree: "<<tree.height(root)<<"\n";

    cout << "level order Traversal \n";
    tree.levelOrder(root);
    cout<<"\n";

    return 0;
}
