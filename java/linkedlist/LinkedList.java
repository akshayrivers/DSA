class LinkedList {
    class Node {
        public int data;
        public Node next;

        Node() {
            this.data = 0;
            this.next = null;
        }

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Node head;
    public Node tail;

    // Method to add a new node with data to the linked list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Method to construct a linked list from an array
    public Node constructLL(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
        return head;
    }

    public void print() {
        if (head == null) {
            System.out.println("LinkedList is empty");
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String args[]) {
        LinkedList linkedList = new LinkedList();
        Node linkedListHead = linkedList.constructLL(new int[]{1, 2, 3, 4});
        linkedList.print();
    }
}
//dont know why is not being pushed
