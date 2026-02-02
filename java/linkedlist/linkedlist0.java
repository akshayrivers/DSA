class linkedlist0{
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
    //method to add first element into the linked list
    public static void addfirst(int data){
        //step 1: create new node
        Node newNode= new Node(data);
        size++;
        if(head==null){
            head = newNode;
            tail= head;//set tail to new since its the first element
        }
        //step 2: newnode next = head
        newNode.next=head;//link
        //step 3: head = newnode
        head=newNode;
    }
    
    //method to add last element into the linked list
    public static void addlast(int data){
        Node newNode=new Node(data);//step 1 create a new node 
        size++;
        tail.next =newNode;
        if(head==null){
            head=tail=newNode;
            return; 
        }
        tail=newNode;
    }

    //method to print the linked list
    public static void print(){
        if(head==null){
            System.out.println("linked list is empty");
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print("->");
            } else {
                System.out.print("->null");
            }
            temp = temp.next;
        }
        System.out.println();
    }
    
    //add the element at a given index 
    public static void add_Nth(int index,int data ){
        Node temp = head;
        Node newNode=new Node(data);
        size++;
        int i=0;
        while (i<index-1) {
            temp=temp.next;
            i++;
        }
        newNode.next=temp.next;
        temp.next=newNode;
    } 
    //method to remove the first element
    public static int removeFirst(){
        if(size==0){
            System.out.print("Linkedlist is empty");
            return Integer.MIN_VALUE;
        }
        else if(size ==1){
            int val= head.data;
            head=tail=null;
            size--;
            return val;
        }
        int val =head.data;
        head=head.next;
        size--;
        return val;
    }

    //method to remove the last element
    public static int removeLast(){
        if(size ==0){
            System.out.print("LinkedList is empty");
            return Integer.MIN_VALUE;
        }
        else if( size ==1){
            int val= head.data;
            head=tail= null;
            return val;
        }
        //prev:i = size-2
        Node prev=head;
        for(int i =0;i<size-2;i++){
            prev=prev.next;
            int val=prev.next.data=tail.data;
            prev.next=null;
            size--;
            return val;
        }
        return -1;
    }

    //method to reverse the linked list iterative approach
    public static void reverse(){
        /** 
         * next =curr.next
         * curr.next=prev
         * prev=curr
         * curr=next
         */
        Node prev=null;
        Node curr=tail=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head =prev;
    }

    //searching for an element with iterative approach
    public static int iter_search(int key){
        Node temp =head;
        int i=0;
        while(temp!=null){
            if(temp.data==key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        //key not found
        return -1;
    }

    //searching an element with recursive approach
    public static int  rec_search(int key){
        return helper(head,key);
    }
    public static int helper(Node head,int key){
        if(head==null){
            return -1;
        }
        if(head.data==key){
            return 0;
        }
        int idx = helper(head.next,key);
        if(idx==-1){
            return -1;
        }
        return idx+1;
    }

    public static Node findMid(Node head){
        Node slow = head;
        Node fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    //checking if ll is palindrome
    public static boolean isPalindrome(){
        //base case
        if(head==null||head.next==null){
            return true;
        }
        //Step 1
        Node midnode=findMid(head);
        //Step 2
        Node prev=null;
        Node curr=midnode;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node right =prev;//right half head
        Node left=head;
        //Step 3 
        while(right!=null){
            if(left.data!=right.data){
                return false;
            }
            left=left.next;
            right=right.next;
        }
        return true;
    } 

    //removing nth node from end of the ll
    public static void removeNth_nodefromend(int n){
        //calculate size
        int sz=0;
        Node temp=head;
        while(temp!=null){
            temp=temp.next;
            sz++;
        }
       if(n==sz){
        reverse();
        head = head.next;
        reverse();
        return;
       }
        //size-n
        int i=1;
        int itofind=sz-n;
        Node prev=head;
        while(i<itofind){
            prev=prev.next;
            i++;
        }
        prev.next=prev.next.next;
        return;
    }
    

    //checking if the ll contains a cycle
    public static boolean isCycle(){
        Node slow=head;
        Node fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;//cycle exists
            }
        }
        return false;
    }

//method to remove cycle
    public static void removeCycle() {
        Node slow = head;
        Node fast = head;

        // Cycle detection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; // cycle detected
            }
        }

        // No cycle found
        if (slow != fast) {
            System.out.println("No cycle found in the list.");
            return;
        }

        // Finding the node before the cycle
        slow = head;
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }

        // Removing the cycle
        fast.next = null;
        System.out.println("Cycle removed from the list.");
    }

    /*Merge sort on a linked list TC->O(nlogn)
      step1: find middle of ll
      step2:sort left half and right half 
      step3:merge
    */
    public static Node mergesort(Node head){
        if(head==null||head.next==null){
            return head;
        }
        Node mid=getmid(head);
        //left & right 
        Node righthead=mid.next;
        mid.next=null;
        Node newleft=mergesort(head);
        Node newright=mergesort(righthead);
        //merge
        return merge(newleft,newright);

    }
    public static Node getmid(Node head){
        Node slow=head;
        Node fast=head.next;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;//mid node
    }
    private static Node merge(Node head1, Node head2) {
        Node mergedll = new Node(-1);
        Node temp = mergedll;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
                temp = temp.next; // Move temp to the next node
            } else {
                temp.next = head2;
                head2 = head2.next;
                temp = temp.next; // Move temp to the next node
            }
           
        }
        while (head1!=null) {
            temp.next=head1;
            head1=head1.next;
            temp=temp.next;
        }
        while (head2!=null) {
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
        }
        return mergedll.next;
    }
    
    public static void main(String args[]){
        linkedlist0 ll =new linkedlist0();
        head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        head.next.next.next=new Node(5);
        addfirst(8);
        addfirst(9);
        addfirst(12);
        addfirst(0);
        print();
        mergesort(head);
        print();
        // System.out.println("Size of linked list = "+ size);
        // System.out.println(isCycle());
        // removeCycle();
        // System.out.println(isCycle());
        // print();
        // System.out.println(size);
        // System.out.println( removeFirst());
        // **System.out.println(removeLast());
        // print();System.out.println(isPalindrome());
        // System.out.println(iter_search(10));
        // print();
        // System.out.print(rec_search(9));
        // reverse();
        // print();
        // removeNth_nodefromend(3);
        // print();
        // System.out.println(size);
    }

}