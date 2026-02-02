import java.util.*;
public class test {
    class Solution {    

    //     static class Node {
    //         Node[] children = new Node[26];
    //         boolean eow = false;
    
    //         Node() {
    //             for (int i = 0; i < 26; i++) {
    //                 children[i] = null;
    //             }
    //         }
        
    
    //     static Node root = new Node();}
    //     static String ans = "";
    
    //     public String longestCommonPrefix(String[] strs) {
    //         // Insert strings into the Trie
    //         for (int i = 0; i < strs.length; i++) {
    //             insert(strs[i]);
    //         }
            
    //         // Find the longest common prefix
    //         //help(root, new StringBuilder(""));
            
    //         return ans;
    //     }
    
    //     public static void insert(String word) {
    //         Node curr = root;
    //         for (int i = 0; i < word.length(); i++) {
    //             int idx = word.charAt(i) - 'a';
    //             if (curr.children[idx] == null) {
    //                 curr.children[idx] = new Node();
    //             }
    //             curr = curr.children[idx];
    //         }
    //         curr.eow = true;
    //     }
    
    //     public static void help(Node root, StringBuilder temp) {
    //         if (root == null) {
    //             return;
    //         }
    //         for (int i = 0; i < 26; i++) {
    //             if (root.children[i] != null && root.children[i].eow) {
    //                 char ch = (char) (i + 'a');
    //                 temp.append(ch);
    //                 if (temp.length() > ans.length()) {
    //                     ans = temp.toString();
    //                 }
    //                 help(root.children[i], temp);
    //                 temp.deleteCharAt(temp.length() - 1);
    //             }
    //         }
    //     }
    // }
    
    // public static void reverse(int numbers[]){
    //     int temp = 0;
    //     for (int i=0; i<numbers.length/2; i++){
    //         temp=numbers[i];
    //         numbers[i]=numbers[numbers.length-1-i];
    //         numbers[numbers.length-1-i]=temp;

    //     }
    // }
    // public static int findMaxLength(int[] nums) {
    //     int track[] = new int [2];
    //     int ans=0;
    //     int sum=0;
    
    //     for(int i=0; i<nums.length;i++){
    //         sum+=nums[i];
        
    //         if(nums[i]==0){
    //             track[0]++;
    //         }
    //          if(nums[i]==1){
    //             track[1]++;
    //         }
    //        if(check(sum, track[1],track[0])){
    //         ans++;
    //        }
    //     }
    //     return ans;
    // }

    // public static boolean  check( int sum, int count1 , int count0 ){
    //         return sum==count1&&sum==count0;
    
    // }
    // public static void backtrack( int arr[], int n, int i){
    //     if(i==arr.length){
    //         return;
    //     }
    //     arr[i]=n;
    //     backtrack(arr, n+1,i+1);
    //     arr[i]=arr[i]-1;
    // }
    public static int longestPalindrome(String s) {
        HashMap<Character,Integer> h= new HashMap<>();
        int ans=0;
        for(int i =0; i< s.length();i++){
            if(!h.containsKey(s.charAt(i))){
                h.put(s.charAt(i),1);
            }
            else{
                int value=h.get(s.charAt(i));
                h.put(s.charAt(i),value++);
            }
        }
        Set<Character> keys=h.keySet();
        for(char key:keys){
            if(h.get(key)%2==0){
                ans=ans+h.get(key);
                h.remove(key);
            }
        }
        if(!h.isEmpty()){
            ans++;
        }
        
        return ans;
    }
    public static void main(String[] args){
        // for( char ch='A'; ch<='Z';ch++){
        //     System.out.print((char)ch+" ");
        // }
        // System.out.println();
        // for( char ch='A'; ch<='Z';ch++){
        //     System.out.print((char)(ch|' ')+" ");
        //}
            // int arr[]={1,2,3,4,5};
            // backtrack(arr, 1,0);
            // for (int i = 0; i < arr.length; i++) {
            //     System.out.println(arr[i]);
            // }
    //     HashMap<Integer,Integer> map=new HashMap<>();
    //     int arr[]={1,3,2,5,1,3,1,5,1,5,5,5,6};
    //     for( int i =0; i< arr.length;i++){
    //         int num= arr[i];
    //         if(map.containsKey(num)){
    //             map.put(num,map.get(num)+1);
    //         }else{
    //             map.put(num, 1);
    //         }
    //     }
        
    //  Set<Integer> keys=map.keySet();
    // for (Integer key : keys) {
    //     if(map.get(key)>arr.length/3)
    //     System.out.println(key);
    //     }
    //     String strs[]= {"flower","flow","flight"};
    //     System.out.println(longestCommonPrefix(strs[]);
    System.out.println(longestPalindrome("abccccdd"));
    }
}}
