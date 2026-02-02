import java.util.*;
public class solutions {
    /*
     * assignment questions and their solutions 
     */
    static class Node{
        public int data;
        public Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
        Node(int data,Node next){
            this.data=data;
            this.next=next;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    public static void deleteNnodesafterM(int M, int N) {
        Node temp = head;
        Node prev = null;
        
        while (temp != null) {
            // Move temp M steps forward
            for (int i = 0; i < M ; i++) {
                prev = temp;
                temp = temp.next;
            }
            
            // Delete N nodes after M steps
            for (int i = 0; i < N ; i++) {
                temp = temp.next;
            }
            
            // Connect the previous node to the current temp node
            prev.next = temp;
        }
    }
    
    
    public static void print(){
        Node temp =head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }
        System.out.println("null");
    }
    public static void swapNodes(int x,int y){
        //edge case
        if (x == y) {
            return; // No need to swap if x and y are the same
        }
        // STEP 1: finding positons of prev and curr nodes
        Node prevX=null;
        Node currX=head;
        Node prevY=null;
        Node currY=head;
        while(currX!=null&&currX.data!=x){
            prevX=currX;
            currX=currX.next;
        }
        while(currY!=null&& currY.data!=y){
            prevY=currY;
            currY=currY.next;
        }
        // if the numbers doesn't exist
        if (currX==null||currY==null){
            return;
        }
        //STEP 2: setting the curr after swap to right place
        if(prevX!=null){
            prevX.next=currY;
        }else{
            head=currY;
        }
        if(prevY!=null){
            prevY.next=currX;
        }else{
            head=currX;
        }
        // STEP 3: setting the position of their nexts after swapping 
        Node temp=currX.next;
        currX.next=currY.next;
        currY.next=temp;
    }

    public static void segregateEvenOdd() {
        if (head == null || head.next == null) {
            return; // Nothing to segregate if the list is empty or has only one node
        }
    
        Node end = head;
        Node prev = null;
        Node curr = head;
    
        // Finding the end node
        while (end.next != null) {
            end = end.next;
        }
    
        Node new_end = end;
    
        // Move odd nodes to the end
        while (curr.data % 2 != 0 && curr != end) {
            new_end.next = curr;//assigning the value of cuur to the last
            curr = curr.next; //traversing the curr part
            //moving the new_end to the end again
            new_end.next.next = null;
            new_end = new_end.next;
        }
    
        // If the first node is even
        if (curr.data % 2 == 0) {
            head = curr;
            while (curr != end) {
                if (curr.data % 2 == 0) {
                    prev = curr;
                    curr = curr.next;
                } else {
                    prev.next = curr.next;
                    curr.next = null;
                    new_end.next = curr;
                    new_end = new_end.next;
                    curr = prev.next;
                }
            }
        } else {
            prev = curr;
        }
    
        // If the last node is odd
        if (new_end != end && end.data % 2 != 0) {
            prev.next = end.next;
            end.next = null;
            new_end.next = end;
        }
    }
    
    public static void main(String[] args) {
        LinkedList ll= new LinkedList();
        head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        head.next.next.next=new Node(4);
        head.next.next.next.next=new Node (5);
        head.next.next.next.next.next=new Node (6);
        head.next.next.next.next.next.next=new Node (7);
        head.next.next.next.next.next.next.next=new Node (8);
        print();
        segregateEvenOdd();
        print();
    }
}
