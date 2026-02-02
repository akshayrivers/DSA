import java.util.*;
// heap is not implemented as a class
public class heap {
    static class Student implements Comparable<Student>{
        // we will use overriding to make it into a priority queue
        String Name;
        int rank;
        public Student(String Name , int rank){
            this.Name= Name;
            this.rank= rank;
        }
        @Override
        public int compareTo(Student s2){
            return this.rank-s2.rank;
        }
    }
    // we use heaps to implememnt priority queues 
    static class MinHeap{
        ArrayList <Integer> arr = new ArrayList<>();
        public void add(int data){
            // add at last idx
            arr.add(data);
            int x= arr.size()-1;// x is the child index
            int par = (x-1)/2;//parent index
            while (arr.get(x)<arr.get(par)) {
                //swap
                int temp= arr.get(x);
                arr.set(x,arr.get(par));
                arr.set(par,temp);
                x= par;
                par=(x-1)/2;
            }

        }
        public int peek(){
            return arr.get(0);
        }
        // it is to correct the structure after we have removed the first element 
        private void heapify(int i){
            int left =2*i+1;
            int right= 2*i+2;
            int minIdx=i;
            if (left<arr.size()&&arr.get(minIdx)>arr.get(left)) {
                minIdx= left;
            }
            if (right<arr.size()&&arr.get(minIdx)>arr.get(right)) {
                minIdx= right;
            }
            if (minIdx!=i) {
                //swap 
                int temp= arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);
                heapify(minIdx);
            }

        }
        // removing the first element from arr and then heapifying the heap again 
        public int remove(){
            int data = arr.get(0);
            //step 1 - swap first and last 
            int temp= arr.get(0);
            arr.set(0, arr.get(arr.size()-1));
            arr.set(arr.size()-1, temp);
            //step 2 - delete last 
            arr.remove(arr.size()-1);
            //step 3 - heapify 
            heapify(0);
            return data;
        }
        public boolean isEmpty(){
            return arr.size()==0;
        }

    }
    public static void Heapify(int[] arr, int i, int size){
        int left = 2*i+1;
        int right= 2*i+2;
        int maxIdx= i;
        if (left< size&&arr[left]>arr[maxIdx]) {
            maxIdx= left;
        }
        if (right< size&&arr[right]>arr[maxIdx]) {
            maxIdx= right;
        }
        if (maxIdx!=i) {
            //swap
            int temp= arr[i];
            arr[i]=arr[maxIdx];
            arr[maxIdx]=temp;
            Heapify(arr, maxIdx, size);
        }

    }
    // TC-> O(n logn)
    public static void HeapSort(int arr[]){
        // creating max heap 
        int n= arr.length;
        for (int i = n/2; i >=0; i--) {// confused about why this step well that 
            //is beacuse parent =(x-1)/2 or (x-2)/2 that is why we need this 
            Heapify(arr, i, n);
        }
        // step 2 push at the end
        for (int i = n-1; i > 0 ; i--) {
            // swap ( largest first with last)
            int temp = arr[0];
            arr[0]=arr[i];
            arr[i]=temp;
            Heapify( arr,0,i);
        }
    }
    // helping class to get the distance between two points   
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int distSq;
        int idx;
        public Point(int x , int y , int distSq,int idx){
            this.x=x;
            this.y=y;
            this.distSq=distSq;
            this.idx=idx;
        }
        @Override
        public int compareTo(Point p2){
            return this.distSq-p2.distSq;
        }

    }
    /*
    WEAKEST SOLDIER
     * We are given an mxn binary matrix of 1's (soldiers) and 0's (civilians). 
     * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
    A row i is weaker than a row j if one of the following is true:
    • The number of soldiers in row i is less than the number of soldiers in row j.
    • Both rows have the same number of soldiers and i < j.
    Find the K weakest rows.
    m=4, n=4, k=2
    1000
    1111
    1000
    1000
    ans = row0 & row2
    */
    static class Row implements Comparable<Row>{
        int soldiers;
        int idx ;
        public Row(int soldiers, int idx){
            this.soldiers= soldiers;
            this.idx= idx;
        }
        @Override
        public int compareTo(Row r2){
            if (this.soldiers==r2.soldiers) {
                return this.idx- r2.idx;
            }
            else{
                return this.soldiers-r2.soldiers;
            }
        }
    }

    static class pair implements Comparable<pair>{
        int numb;
        int idx;
        public pair(int numb,int idx){
            this.numb=numb;
            this.idx=idx;
        }
        @Override
        //sorting in descending order 
        public int compareTo(pair p2){
            return p2.numb-this.numb;
        }

    }
    public static void main(String[] args) {
        int army[][]= {{1,0,0,0},{1,1,1,1},{1,0,0,0},{1,0,0,0}};
        int l =2 ;
        PriorityQueue<Row> oo=new PriorityQueue<>();
        for (int i = 0; i < army.length; i++) {
            int count=0;
            for (int j = 0; j < army[0].length; j++) {
                count+= army[i][j]==1?1:0;
            }
            oo.add(new Row(count, i));
        }
        for (int i = 0; i < l; i++) {
            System.out.println("R"+ oo.remove().idx);
        }

        MinHeap pq = new MinHeap();
        pq.add(4);
        pq.add(3);
        pq.add(5);
        pq.add(6);
        System.out.println(pq.isEmpty());
        while (!pq.isEmpty()) {
            System.out.println(pq.peek());
            pq.remove();
        }
        System.out.println(pq.isEmpty());
        int[] array = {3,4,1,2,6,7,3,9};
        HeapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        // distancw between two cars 
        int point[][]={{3,3},{5,-1},{-2,4}};
        int k=2;
        PriorityQueue<Point> pq1 = new PriorityQueue<>();
        for (int i = 0; i < point.length; i++) {
            int distSq= point[i][0]*point[i][0] + point[i][1]*point[i][1];
            pq1.add(new Point(point[i][0],point[i][1],distSq,i));
        }
        //nearest k cars 
        for (int i = 0; i < k; i++) {
            System.out.println("C" + pq1.remove().idx);
        }

        /* 
        Connect N ropes 
        Given are N ropes of different length,the task is to connect these ropes into one rope with minimum cost, such that the cost 
        to connect two ropes is equal to the sum of their lenghts.
        ropes= {4,3,2,6}
        ans = 29 
        the thing in this to see is that. Initially we may think of using greedy approach to sovle this questions. but the result 
        in that is wrong. 
        here we see that after connecting the ropes, some ropes are going to be repeated And we want that repeating rope to be of the min length
        so we solve this question by using priority queues 

        */ 
        int ropes[]= {4,3,2,6};
        PriorityQueue<Integer> pq2= new PriorityQueue<>();// initialising a priority queue 
        for (int i = 0; i < ropes.length; i++) {// adding the vlaues into it 
            pq2.add(ropes[i]);
        }
        int cost=0;
        // running the loop until there is only one rope left that is pq2.size=1;
        while (pq2.size()>1) {
            int min = pq2.remove();
            int min2= pq2.remove();
            cost+=min+min2;
            pq2.add(min+min2);
        }
        System.out.println(cost);

        // Sliding Window Maximum TC-> O(Nlogk)
        int arr[]={1,3,-1,-3,5,3,6,7};
        int k1= 3;
        int res[]= new int[arr.length-k];//n-k+1
        PriorityQueue<pair> win= new PriorityQueue<>();
        // 1st window
        for (int i = 0; i < k1; i++) {
            win.add(new pair(arr[i],i));
        }
        res[0]= win.peek().numb;

        for (int i = k1; i < arr.length; i++) {
            while (win.size()>0 && win.peek().idx<=(i-k1)) {
                win.remove();
            }
            win.add(new pair(arr[i],i));
            res[i-k1+1]= win.peek().numb;
        }
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+"  ");
        }
    }
}
