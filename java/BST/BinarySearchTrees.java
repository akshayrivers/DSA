import java.util.*;
public class BinarySearchTrees {
    /*
     * Binary Search tree 
     * properties:
     * a.leftsubtree<root
     * b.rightsubtree>root
     * c.left and right subtrees are also BST with no duplicates.
     * Special properties->
     * Inorder Traversal[left,Root,right] of BST gives a sorted sequence 
     * 1.BST Search usually -> O(log n) worst case ->O(n)
     * 2.Build a BST
     * 3.Delete a Node
     * 4.Root of leaf paths
     * 5.Validate BST
     * 6.Mirror a BST
     * 7.Sorted array to Balanced BST
     * 8.Size of largest BST in BT
     */
    static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data=data;
            
        }
    }
    //building a BST 
    public static Node insert(Node root,int val){
        if (root==null) {
            root=new Node(val);
            return root;
        }
        if (root.data>val) {
            root.left=insert(root.left, val);
        }else{
            root.right=insert(root.right, val);
        }
        return root;
    }
    // Inorder traversals which in an ideal case will give us a sorted array 
    public static void Inorder(Node root){
        if (root==null) {
            return;
        }
        Inorder(root.left);
        System.out.print(root.data+"  ");
        Inorder(root.right);
    }
    // search an element in a BST
    public static boolean search(Node root, int key){
        if (root==null) {
            return false;
        }
        if (root.data==key) {
            return true;
        }
        if (root.data < key) {
            return search(root.right, key);
        }else{
            return search(root.left, key);
        }
    }
    /*
     * delete a node from a BST
     * there are three cases we will encounter here 
     * 1. No child 
     * 2.One child 
     * 3. two child
     * now the no chid one can be tackled by just the value of null in its place 
     * the one child case can be tackled by using its own child as its substitute in its place 
     * and in the two child one we have to replace it with its inorder succesor 
     * and then delete the node for inorder succesor 
     * 
     * Inorder successor inBST -> Left Most node in the right subtree
     */

    public static Node delNode(Node root, int val){
        if (root.data<val) {
            root.right=delNode(root.right, val);
        }
        else if (root.data>val) {
            root.left=delNode(root.left, val);
        }
        else{// found the node to be deleted this is the equal case
            //Case 1
            if (root.left==null&&root.right==null) {
                return null;
            }
            //Case 2
            if (root.left==null) {
                return root.right;
            }
            else if (root.right==null) {
                return root.left;
            }
            //Case 3 both children found 
            Node Is=Inordersuccessor(root.right);// what are ou confused about, remember 
            //the properties of bst to delete it we need to balance so we can only replace
            // this one by finding the smallest element in the subtree
            root.data= Is.data;
            root.right=delNode(root.right, Is.data);
        }
        return root;
    }
    public static Node Inordersuccessor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
    public static Node Inorderpredecessor(Node root){
        while(root.right!=null){
            root=root.right;
        }
        return root;
    }
    // print in range
    public static void PrintInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }
        
        //  Case 1 -> Traverse left subtree if root's data is greater than k1
        if (root.data > k1) {
            PrintInRange(root.left, k1, k2);
        }
    
        // Case 2 -> If root's data lies in the range, print it
        if (root.data >= k1 && root.data <= k2) {
            System.out.print(root.data + " ");
        }
    
        // Case 3 -> Traverse right subtree if root's data is less than k2
        if (root.data < k2) {
            PrintInRange(root.right, k1, k2);
        }
    }
    // root to leaf paths 
    public static void PrintRoot2leaf(Node root, ArrayList<Integer> path){
        if (root==null) {
            return ;
        }
        path.add(root.data);
        if (root.left==null&&root.right==null) {
            PrintPath(path);
        }
        
        PrintRoot2leaf(root.left, path);
        PrintRoot2leaf(root.right, path);
        path.remove(path.size()-1);
    }
    public static void PrintPath(ArrayList<Integer> path){
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i)+"->");
        }System.out.println("null");
    }
    /*/validate a BST  
    approach 1 is  just compare the left and right for each of them 
    approach 2 suggests to Check if the max value in left subtree is smaller than root and min value in right subtree is greater than the root node 
    */
    public static boolean ValidateBST(Node root,Node min, Node max ){
        if (root==null) {
            return true;
        }
        if (min!=null&&root.data<=min.data) {
            return false;
        }
        else if (max!=null&&root.data>=max.data) {
            return false;
        }
        return ValidateBST(root.left, min, max)&& ValidateBST(root.right, min, max);
        
    }
    public static Node Mirror(Node root){
        //base case
        if (root==null) {
            return null;
        }
        //using recursion
        Node leftS=Mirror(root.left);//storing the value of the left Node in leftS
        Node rightS=Mirror(root.right);//storing the value of the right Node in rightS
        //swapping the values
        root.left=rightS;
        root.right=leftS;
        return root;
    }
    /*
     sorted array to BST 
    the logic here is to make the middle node the root node to make a balanced BST 
     */
    public static Node CreateBST(ArrayList<Integer> arr , int start , int end){
        if (start> end) {
            return null;
        }
        int mid= (start+end)/2;
        Node root1=new Node(arr.get(mid));
        root1.left=CreateBST(arr, start, mid-1);
        root1.right=CreateBST(arr, mid+1, end);
        return root1;
    }
    // convert BST to Balanced BST 
    public static void getInOrder(Node root,ArrayList<Integer> inorder){
        if (root==null) {
            return;
        }
        getInOrder(root.left, inorder);
        inorder.add(root.data);
        getInOrder(root.right, inorder);
    }
    public static Node balanceBST(Node root){
        //inorder seq->O(n)
        ArrayList<Integer>inorder= new ArrayList<>();
        getInOrder(root,inorder);
        root= CreateBST(inorder, 0, inorder.size()-1);
        return root;

    }
    public static void main(String[] args) {
        int values[] = {5, 2, 3, 4, 1, 7};
        Node root = null;
    
        // Insert values into the BST
        for (int value : values) {
            root = insert(root, value);
        }
    
        // Inorder traversal (should print sorted sequence)
        Inorder(root);
        System.out.println();
    
        // Validate if the tree is a BST
        System.out.println("Is the tree a valid BST? " + ValidateBST(root, null, null));
    
        // Example: Deleting a node
        root = delNode(root, 3);
        Inorder(root);
        System.out.println();
    
        // Example: Mirror the BST
        Mirror(root);
        Inorder(root);
        System.out.println();
    
        // Example: Balance the BST
        root = balanceBST(root);
        Inorder(root);
    }
    
}
