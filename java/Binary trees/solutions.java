
    /*
     * Question 1 :Check if a Binary Tree is univalued or not We have a binary tree,the task is to check 
     * if the binarytree is univalued or not.If found to be true, then print “YES”. Otherwise, print “NO”.
     * Sample Input 1:Sample Output 1: false
     * Question 2 :
     * Invert Binary TreeMirrorofaTree:
     * Mirror of a BinaryTreeT is another BinaryTree M(T) with left and right children of all non-leaf nodes interchanged.
     * Question 3 :
     * Delete leaf nodes with values as x We have a binarytree and a target integer x,
     * delete all the leaf nodes having value as x.Also,delete the newly formed leaves with the target value as x.
     * Question 4 :Find All Duplicate Subtrees We have a binarytree,find all duplicate subtrees.
     * For each duplicate subtree,we only need to return the root node of any one of them.
     * Two trees are duplicates if they have the same structure with the same node values.
     * Input1:1
     *       / \
     *      4   3 
     *     /   / \
     *    3   4   3 
     *       /
     *      3
     * Output1: 4-3 , 3
     * Question 5 :
     * Maximum Path Sum in a Binary Tree We have a binarytree,find the maximum path sum.
     * The path may start and end at any node in the tree.
     * Input1:
     */

import java.util.*;
import java.util.LinkedList;
public class solutions {
        static class Node {
            int data;
            Node left;
            Node right;
    
            Node(int data) {
                this.data = data;
                this.left = null;
                this.right = null;
            }
        }
    
        static class BinaryTree {
            static int idx = -1;
    
            public Node buildTree(int nodes[]) {
                idx++;
                if (idx >= nodes.length || nodes[idx] == -1) {
                    return null;
                }
                Node newNode = new Node(nodes[idx]);
                newNode.left = buildTree(nodes);
                newNode.right = buildTree(nodes);
                return newNode;
            }
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
    
        public static boolean isUnivalued(Node root) {
            if (root == null) {
                return true;
            }
            //check the comdition if the data on the parent node and child node is same 
            if ((root.left != null && root.data != root.left.data) || (root.right != null && root.data != root.right.data)) {
                return false;
            }
            return isUnivalued(root.left) && isUnivalued(root.right);
        }
    
        public static Node invertTree(Node root) {
            if (root == null) {
                return null;
            }
            // Swap left and right children
            Node temp = root.left;
            // then recursively call for the left and right nodes
            root.left = invertTree(root.right);
            root.right = invertTree(temp);
            return root;
        }
    public static Node Deleteleaves(Node root, int x){
        if ( root ==null){
            return null;
        }
        root.left = Deleteleaves(root.left, x);
        root.right = Deleteleaves(root.right, x);
        if (root.data==x&&root.left==null&&root.right==null) {
            return null;
        }
        return root;
    }

        public static void main(String[] args) {
            int nodes[] = {2, 2, -1, -1, 2, -1,-1};
            BinaryTree tree = new BinaryTree();
            Node root = tree.buildTree(nodes);
    
            // Question 1: Check if the binary tree is univalued
            if (isUnivalued(root)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            // tree before inverting 
            LevelOrder(root);
            // Question 2: Invert the binary tree
            System.out.println();
            root = invertTree(root);
            // tree after inversion
            LevelOrder(root);
            System.out.println();
            Deleteleaves(root, 02);
            LevelOrder(root);
        }
    }
    
