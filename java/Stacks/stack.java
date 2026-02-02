
import java.util.*;
class stack {
    /**
     * Operations:
     * 1. push O(1)
     * 2. pop O(1)
     * 3. peek O(1)
     * 
     * Implementaion:
     * 1.Arrays-> fixed length
     * 2.ArrayList-> variable length
     * 3.Linked List-> variable length
     * @param args
     */
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
        static class stack0{
            //Stack using ArrayList
            class SusingAL{
            static ArrayList<Integer> list=new ArrayList<>();
            public static boolean isEmpty(){
                return list.size()==0;
            }
            //push
            public static void push(int data){
                list.add(data);}
            
            //pop
            public static int pop(){
                int top=list.get(list.size()-1);
                list.remove(list.size()-1);
                return top;
            }
            //peek
            public static int peek(){
                return list.get(list.size()-1);
            }
        }
            class SusingLL{
            //Stack using linkedlist
                static Node head = null;
                public static boolean isEmpty(){
                    return head==null;
                }
                //push
                public static void push(int data){
                    Node newNode=new Node(data);
                    if(isEmpty()){
                        head=newNode;
                        return;
                    }
                    newNode.next = head;
                    head=newNode;
                }
                //pop
                public static int pop(){
                    if(isEmpty()){
                        return-1;
                    }
                    int top=head.data;
                    head=head.next;
                    return top;
                }
                //peek
                public static int peek(){
                    if(isEmpty()){
                        return -1;
                    }
                    return head.data;
                }
         
        }
            }
        public static void PushAtBottom(Stack<Integer> s, int data ){
            if(s.empty()){
                s.push(data);
                return;
            }
            int top = s.pop();
            PushAtBottom(s,data);
            s.push(top);
        }
    
        // reverse a string using stack 
        public static String  reverseString(String str ){
            Stack<Character> s= new Stack<>();
            int idx=0;
            while(idx<str.length()){
                s.push(str.charAt(idx));
                idx++;
            }
            StringBuilder result =new StringBuilder(" ");
            while(!s.empty()){
                char curr =s.pop();
                result.append(curr);
            }
            return str=result.toString();
        }

        // reverse a stack TC-> O(n) SC->O(1) by using recursion
        public static void reverseStack(Stack<Integer>s){
            if(s.empty()){
                return;
            }
            int top=s.pop();
            reverseStack(s);
            PushAtBottom(s,top);
        }
        public static void printStack(Stack<Integer>s){
            while(!s.empty()){
                System.out.print(s.pop()+" ");
            }
        }
    public static void main(String[] args) {
        Stack<Integer> s= new Stack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(6);
        PushAtBottom(s,7);
        printStack(s);
        reverseStack(s);
        printStack(s);
        System.out.println();
        String str = "VINOD AKSHAT";
        System.out.println(reverseString(str));

    
    }
}
