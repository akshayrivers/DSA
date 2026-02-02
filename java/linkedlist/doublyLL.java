public class doublyLL {
    public class Node{
        int data;
        Node next;
        Node prev;
        public Node(int data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    public doublyLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public  void Addfirst(int data){
        Node newNode= new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        newNode.next=head;
        head.prev=newNode;
        head=newNode;
    }
    public  void Addlast(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    public void Print(){
        if( head==null){
            System.out.println("ll is empty");
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            if(temp.next==null){
                System.out.print("null");
            }
            temp=temp.next;
        }
        System.out.println();
    }
    public int RemoveFirst(){
        if(size==0){
            System.out.println("dll is empty");
            return Integer.MIN_VALUE;
        }
        if(size==1){
            int val=head.data;
            head=tail=null;
            size--;
        }
        int val=head.data;
        head=head.next;
        head.prev=null;
        size--;
        return val;
    }
    public static void Reverse() {
        Node curr = head;
        Node prev = null;
        Node next;
        
        // Travel the list and reverse the pointers
        while (curr != null) {
            next = curr.next;
            curr.next = curr.prev; // Reverse next and prev pointers
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        
        // Update head and tail pointers
        tail = head;
        head = prev;
    }
    
    public static void main(String[] args) {
        doublyLL dll=new doublyLL();
        dll.Addfirst(2);
        dll.Addlast(8);
        dll.Addfirst(9);
        dll.Addlast(5);
        dll.Print();
        dll.Reverse();
        dll.Print();

    }
}
