import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class binarytrees {

    /*creating a binary tree form scratch
     * 
    */
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left= null;
            this.right=null;
        }
    }
    static class BinaryTree{
        static int idx=-1;
        public static Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
        
    }/* Tree Traversals 
        A. Preorder
            1)root
            2)Left subtree
            3)Right subtree

        B. Inorder 
            1)Left subtree
            2)Root
            3)Right subtree

        C.Postorder 
            1)Left subtree
            2)Right subtree
            3)Root

        D.Level Order Traversals
            1)DFS
            2)BFS
    */

    //A.Preorder TC-> O(n)
    public static void Preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+"  ");
        Preorder(root.left);
        Preorder(root.right);
    }

    //Inorder TC-> O(n)
    public static void Inorder(Node root){
        if(root==null){
            return;
        }
        Inorder(root.left);
        System.out.print(root.data+" ");
        Inorder(root.right);
    }

    //Postorder TC-> O(n)
    public static void Postorder(Node root){
        if(root==null){
            return;
        }
        Postorder(root.left);
        Postorder(root.right);
        System.out.print(root.data+" ");
    }

    // Level Order Traversals(we use BFS Breadth first search here and queue)
    public static void LevelOrder(Node root){
        if(root==null){
            return;
        }
        Queue<Node>q=new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node currNode=q.remove();
            if (currNode==null) {
                System.out.println();
            
                if (q.isEmpty()) {
                    break;
                }else{
                    q.add(null);
                }
            }else{
                System.out.print(currNode.data+" ");
                if (currNode.left!=null) {
                    q.add(currNode.left);
                }
                if (currNode.right!=null) {
                    q.add(currNode.right);
                }
            }
        }    
    }
    /**
     * 
     * @param args
     * height of a tree  
     * it is the maximum distance between root and a leaf 
     * eg:
     *     1       <-1
     *   2   3     <-2
     * 4   5   6   <-3
     * 
     *  height of this given tree is 3
    */ 
    public static int height(Node root){
        if(root==null){
            return 0;
        }
        int lh=height(root.left);
        int rh=height(root.right);
        return Math.max(lh, rh)+1;
    }
    /*
     * count of nodes => no.of nodes
     */
    public static int Count(Node root ){
        if(root==null){
            return 0;
        }
        int lcount =Count(root.left);
        int rcount =Count(root.right);
        int treecount= lcount+rcount+1 ;
        return treecount;
    }
    // Sum of nodes 
    public static int Sum(Node root){
        if(root==null){
            return 0;
        }
        int leftSum = Sum(root.left);
        int rightSum= Sum(root.right);
        int treesum = leftSum+rightSum+root.data;
        return treesum;
    }

    /*
    
     Diameter of a binary tree
     No.of nodes in the longest path between 2 leaves
     Approach 1 TC->O(n2)
     if diameter passes through root 
     root diam = lh+rh+1;
     if diameter doesn't pass through root
     l diam, r diam 
    
    */
    public static  int diameter(Node root){
        if(root==null){
            return 0;
        }
        int leftDiam = diameter(root.left);
        int leftHt=height(root.left);
        int rightDiam= diameter(root.right);
        int rightHt= height(root.right);
        int self=leftHt+rightHt+1;
        return Math.max(Math.max(rightDiam, rightHt),self);
    }
    /*
     * Approach 2 TC-> O(n)
     * 
     */
    static class Info{
        int diam;
        int ht;
        public  Info(int diam,int ht ){
            this.diam=diam;
            this.ht=ht;
        }
    }
    public static Info Diameter(Node root){
        if(root==null){
            return new Info(0, 0);
        }
        Info leftInfo=Diameter(root.left);
        Info rightInfo=Diameter(root.right);
        int diam = Math.max(Math.max(leftInfo.diam,rightInfo.diam),leftInfo.ht+rightInfo.ht+1);
        int ht= Math.max(leftInfo.ht,rightInfo.ht)+1;
        return new Info(diam ,ht);
    }

    /*checking if the given tree is subtree of another 
        Given the roots of two binary trees and subroot , 
        return true if there is a subtree of root with the same structure and node values of subroot and false otherwise

        1. find subtree
        2. check identical(subtree, node subtree)
        traversal=> root.data == subroot.data
    */ 
    public static boolean isIdentical(Node p,Node q){
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.data != q.data) {
            return false;
        }
        return isIdentical(p.left, q.left) && isIdentical(p.right, q.right);
    }
    public static boolean isSubtree(Node root, Node subroot) {
        if (root == null ) {
            return false;
        }
        if (root.data == subroot.data) {
            if (isIdentical(root, subroot)) {
                return true;
            }
        }
        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot);
    }
    /*
     *Top view of a tree
     * Hashmap in java\
     * import.java.util*;
     * HashMap<String, Integer > map=new HashMap<>();
     * add-> map.put(key,value); O(1)
     * remove->O(1)
     * get-> map.get(key);->O(1)
     * Horizontal distance
     *  
     *  1. we make a info class which includes node and its horizontal diatance
     *  2. initialise a hashmap, queue, int min an max to measure the edges of horizontal distance
     *  3. then we will use level order traversal and keep on adding the new nodes into the hashmap if their hd has not occured before
     *  NOTE: in bottom view of tree we will update the value of hd(key) in the hash map 
     *  4. print the values from hasmap 
     * 
     */
    public static void BottomView(Node root){
        Queue<Infofortopview> q= new LinkedList<>();
        HashMap<Integer, Node> map= new HashMap<>();
        int min=0;
        int max=0;
        q.add(new Infofortopview(root, 0));
        q.add(null);
        while(!q.isEmpty()){
            Infofortopview curr= q.remove();
            if(curr==null){
                if(!q.isEmpty()){
                    q.add(null);
                }else{
                    break;
                }
            }else{
                if(!map.containsKey(curr.hd)){
                    map.put(curr.hd, curr.node);
                }
                else if (map.containsKey(curr.hd)) {
                    map.put(curr.hd, curr.node);
                }
                if(curr.node.left!=null){
                    q.add(new Infofortopview(curr.node.left, curr.hd-1));
                    min= Math.min(min, curr.hd-1);
                }
                if(curr.node.right!=null){
                    q.add(new Infofortopview(curr.node.right, curr.hd+1));
                    max= Math.max(max, curr.hd+1);
                }
            }
        }
        for (int i = min; i <=max; i++) {        
            System.out.print(map.get(i).data+"  ");
        }
        System.out.println();

    }
     // info class made for keeping track of horixontal distance and nodes
    static class Infofortopview{
        Node node;
        int hd;

        public Infofortopview(Node node,int hd){
        this.node=node;
        this.hd=hd;
    }
}
    public static void TopView(Node root){
        // level order
        Queue<Infofortopview>q= new LinkedList<>();
        HashMap<Integer,Node> map=new HashMap<>();
        int min =0;
        int max=0;
        q.add(new Infofortopview(root, 0));
        q.add(null);
        while (!q.isEmpty()) {
            Infofortopview curr= q.remove();
            if (curr==null) {
                if (!q.isEmpty()) {
                    q.add(null);
                }else{
                    break;
                }
            }else{
            if (!map.containsKey(curr.hd)) {
                //first time hd is occuring 
                map.put(curr.hd, curr.node);

            }
            if(curr.node.left!= null){
                q.add(new Infofortopview(curr.node.left, curr.hd-1));
                min =Math.min(min, curr.hd-1);
            }
            if (curr.node.right!=null) {
                q.add(new Infofortopview(curr.node.right, curr.hd+1));
                max= Math.max(max, curr.hd+1);
            }
        }
    }
        for (int i = min; i <=max; i++) {        
            System.out.print(map.get(i).data+"  ");
        }
        System.out.println();
    }
    public static void Kthlevel(Node root, int level,int k){
        if (root==null) {
            return;
        }
        if (level==k) {
            System.out.print(root.data+"  ");
    
        }
        Kthlevel(root.left, level+1, k);
        Kthlevel(root.right, level+1, k);
    }
    /*
    Lowest common ancestor of two nodes
    Approach 1;
        travel from root to node
        with getpath(root,node, AL path)
        loop (i=0 to path1.length && path2.length)-> last commmon ancestor 
        two array list bna kr usme dono ka path store krenge then fir comapre karenge last common elemnt dono AL ko traverse krke 

    Approach 2:
        lca = Ist parent(Ancestor) -> subtree n1&n2
        isme hamne traverrse krke tree pr ek aisa node dundha jikse subtree main dono elements lie krte ho.
    */ 

    //approach 1:
    public static boolean getpath(Node root, int n,  ArrayList<Node> path){
        if (root==null) {
            return false;
        }
        path.add(root);
        if (root.data==n) {
            return true;
        }
        boolean foundLeft=getpath(root.left, n, path);
        boolean foundRight=getpath(root.right, n, path);

        if (foundRight||foundLeft) {
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }
    public static void lca(Node root,int n1 , int n2){
        ArrayList<Node> path1= new ArrayList<>();
        ArrayList<Node> path2= new ArrayList<>();
        getpath(root,n1,path1);
        getpath(root,n2,path2);
        // last common ancestor
        int i=0;
        for(;i<path1.size()&&i<path2.size();i++){
            
            if (path1.get(i)!=path2.get(i)) {
                break;
            }
        }
        //last node->i-1th
        Node lca=path1.get(i-1);
        System.out.println(lca.data);

    }
    //approach 2
    public static Node lca2(Node root, int n1, int n2){
        if (root==null) {
            return null;
        }
        if (root.data==n1||root.data==n2) {
            return root;
        }
        Node leftlca= lca2(root.left, n1, n2);
        Node rightlca=lca2(root.right, n1, n2);
        //leftlca= val rightlca=null
        if(rightlca==null){
            return leftlca;
        }
        if (leftlca==null) {
            return rightlca;
        }
        return root;
    }
    /*
     * Minimum distance b/w nodes
     * approach:
     * 1. dist1 -> lca to n1
     * 2.dist2-> lca to n2
     * dist1 + dist 2= min.dist b/W n1 and n2
     * 
     *      
    */
    public static int minDist(Node root, int n1, int n2){
        Node lca= lca2(root, n1, n2);
        int dist1= lcaDist(lca,n1);
        int dist2= lcaDist(lca,n2);
        return dist1+dist2;
    }
    public static int lcaDist(Node root,int n){
        if (root==null) {
            return -1;
        }
        if (root.data==n) {
            return 0;
        }
        int leftDist=lcaDist(root.left, n);
        int rightDist=lcaDist(root.right, n);

        if (leftDist==-1&&rightDist==-1) {
            return -1;
        }
        else if(leftDist==-1){
            return rightDist+1;
        }
        else{
            return leftDist+1;
        }
    }
    /*
     * Kth Ancestor of node 
     * 1. find my node
     * 2.if(root.data==node) return 0;
     * leftdist and rightdist
     * -1 , -1-> -1
     * valid value->(dist+1)==k
     * print root.data
     */
    public static int KthAncestor(Node root, int n , int k){
        if(root==null){
            return -1;
        }
        if (root.data==n) {
            return 0;
        }
        int leftDist=KthAncestor(root.left, n, k);
        int rightDist=KthAncestor(root.right, n, k);

        if (leftDist==-1&&rightDist==-1) {
            return -1;
        }
        int max= Math.max(leftDist, rightDist);
        if (max+1==k) {
            System.out.println(root.data);
        }
        return max+1;
    }
    /*
     * transforming the binary tree to a sum tree 
     * if root=null return 0;
     * leftchildsum= go left 
     * rightchildsum= go right
     * data = root.data
     * root.data= Lsum+Rsum 
     * return data
     */
    public static int SumTree(Node root){
        if (root==null) {
            return 0;
        }
        int  leftchildsum=SumTree(root.left);
        int rightchildsum=SumTree(root.right);
        int data= root.data;
        root.data= leftchildsum+rightchildsum;
        return data;
    }

    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, 9, -1,-1,8,-1,-1};
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        //System.out.println(root.data);
        //LevelOrder(root);
        // int nodes1[] = {2, 4, -1, -1, 5, -1, -1};
        // BinaryTree subtree = new BinaryTree();
        // Node subroot = subtree.buildTree(nodes1, new int[]{-1});
        // LevelOrder(subroot);
        LevelOrder(root);
        System.out.println("root = "+ root.data);
        TopView(root);
        BottomView(root);
        Kthlevel(root, 0, 3);
        System.out.println();
        System.out.println("lca of n1 and n2 = "+ lca2(root,2,9).data);
        System.out.println("minimum distance between two nodes = " + minDist(root, 2, 9));
        System.out.print("Kth ancestor of a node = ");
        KthAncestor(root, 9 ,1);
        System.out.println();
        LevelOrder(root);
        SumTree(root);
        LevelOrder(root);
    }

    
}
    
    
    

