public class queues {
    /* First In First Out
     * Operations:
     * 1.Add O(1)
     * 2.Remove O(1)
     * 3.Peek O(1)
     * 
     * IMPLEMENTATIONS:
     * array->circular queue 
     * linked list 
     */
    //queue using array 
    static class QueueUsingArray{
        static int front;
        static int arr[];
        static int size;
        static int rear;
        QueueUsingArray(int n){
            arr= new int[n];
            size=n;
            rear=-1;;
        }
        public static boolean isEmpty(){
            return rear==-1&&front==-1;
        }

        //Add O(1)
        public static void add(int data){
            if(rear==size-1){
                //if is full
                System.out.println("Queue is full");
                return;
            }
            if(front==-1){
                front =0;//adding int
            }
            rear = (rear+1)%size;
            arr[rear]=data;
        }

        // Remove O(N)
        public static int remove(){
            if(isEmpty()){
                System.out.println("empty Queuw");
                return -1;
            }
            int front=arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i]=arr[i+1];
            }
            rear= rear-1;
            return front;
        }
        //Peek
        public static int peek(){
            if(isEmpty()){
                System.out.println("empty queue");
                return -1;
            }
            return arr[0];
        }
        /*circular queue using array 
        *rear=(rear+1)%size
        *front=(front+1)%size
        */
        public static boolean isFull(){
            return (rear+1)%size==front;
        }
        // Remove O(1)
        public static int Remove(){
            int result= arr[front];
            //last element delete 
            if(rear==front){
                rear=front=-1;
            }
            else{
                front=(front+1)%size;
            }
            return result;
        }
        public static void print() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
            } else {
                int i = front;
                do {
                    System.out.println(arr[i]);
                    i = (i + 1) % size;
                } while (i != (rear + 1) % size);
            }
        }
    
    }

    // implementation of queues using Linked List
    static class QueueUsingLL{
        static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }
        static Node head=null;
        static Node tail=null;
        public static boolean isEmpty(){
            return head==null&&tail==null;
        }
        // Add O(1)
        public static void add(int data){
            Node newNode= new Node(data);
            if (head==null) {
                head=tail=newNode;
                return;
            }
            tail.next=newNode;
            tail=newNode;
        }

         // Remove operation O(1)
         public static int remove() {
            if (isEmpty()) {
                System.out.println("Empty Queue");
                return -1;
            }
            int front = head.data;
            head = head.next;
            if (head == null) {
                tail = null; // Queue becomes empty
            }
            return front;
        }

        // Peek operation O(1)
        public static int peek() {
            if (isEmpty()) {
                System.out.println("Empty Queue");
                return -1;
            }
            return head.data;
        }

    }
       
    public static void main(String[] args) {
        System.out.println("implementation of queue using arrays");
        QueueUsingArray s=new QueueUsingArray(4);
        s.add(1);
        s.add(3);
        s.add(5);
        s.add(7);
        s.print();
        System.out.println("removed element = "+s.Remove());
        s.print();
        System.out.println();
        System.out.println("implementation of queue using linked list");
        QueueUsingLL q = new QueueUsingLL();
        q.add(1);
        q.add(3);
        q.add(5);
        q.add(7);
        System.out.println("Front element: " + q.peek());
        System.out.println("Removed element: " + q.remove());
        System.out.println("Front element after removal: " + q.peek());
    }
}
