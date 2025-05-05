#include <iostream>
#include <algorithm>
using namespace std;

class Node {
public:
    int value;
    Node* next;

    Node(int val) {
        value =val;
        next=nullptr ;
    }
};

class LinkedList {
    Node* head;

public:
    LinkedList() {
        head = nullptr;
    }

    void insert(int val) {
        Node* newNode = new Node(val);
        if (head == nullptr) {
            head = newNode;
        } else {
            Node* temp = head;
            while (temp->next != nullptr){
                temp = temp->next;
            }
                
            temp->next = newNode;
        }
    }

    void insertAtBeg(int val) {
        Node* newNode = new Node(val);
        newNode->next = head;
        head = newNode;
    }

    void deleteAtPos(int pos) {
        if (pos <= 0 || head == nullptr) return;

        Node* temp = head;

        if (pos == 1) {
            head = head->next;
            delete temp;
            return;
        }

        for (int i = 1; temp != nullptr && i < pos - 1; ++i){
            temp = temp->next;
        }
            

        if (temp == nullptr || temp->next == nullptr){
            return;
        }
            
        Node* delNode = temp->next;
        temp->next = delNode->next;
        delete delNode;
    }
    int size(){
        int count =0;
        Node* temp = head;
        while(temp!= nullptr){
            temp=temp->next;
            count++;
        }
        return count;
    }
    void printLL() {
        Node* temp = head;
        while (temp != nullptr) {
            cout << temp->value << " -> ";
            temp = temp->next;
        }
        cout << "NULL\n";
    }

    ~LinkedList() {
        while (head != nullptr) {
            Node* temp = head;
            head = head->next;
            delete temp;
        }
    }
};

int main() {
    cout<<"Input the size of linked list you want to create: ";
    int n =0;
    cin>>n;
    if(n<=0){
        cout<<"please write a valid length";
    }
    LinkedList ll;
    while(n--){
        int k;
        cin>>k;
        ll.insert(k);

    }
    cout<<"Size of the linked list as of Now = "<<ll.size()<<"\n";
    ll.printLL();
    while(true){
        cout<<"type 1 for insetion at the begining 2 for delete at pos ";
        int j ;
        cin>>j;
        if(j==1){
            cout<<"enter element for the insertion at the begining: ";
            int k;
            cin>>k;
            ll.insertAtBeg(k);
            cout<<"after insertion at the begining of the LL: ";
            ll.printLL();
        }
        else{
            cout<<"enter pos for the deletion from the Linked list: ";
            int k1;
            cin>>k1;
            ll.deleteAtPos(k1);
            cout<<"Size of linked list after deletion of one element = "<<ll.size()<<"\n";
            ll.printLL(); 
        }
    }


        

    return 0;
}
