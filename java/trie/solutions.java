
   /*Question 1 :
    MEDIUMGroup Anagrams 
    Together Given an array of strings strs, group the anagrams together.
    You can return the answer in any order.
    An Anagram is a word or phrase formed by rearranging the 
    letters of a different word or phrase, typically using all the original
    letters exactly once.
    Sample Input 1:   
    strs = ["eat","tea","tan","ate","nat","bat"]
    Sample Output 1: 
    [["bat"], ["nat", "tan"], ["ate","eat", "tea"]] 
    Sample Input 2:   strs = [""]
    Sample Output 2: [[""]]
    Sample Input 3:   strs = ["a"]
    Sample Output 3: [["a"]]*/
    // Group Anagrams method
    import java.util.*;

    public class solutions {
        static class Node {
            Node[] children = new Node[26];
            boolean eow = false;
            List<String> anagrams = new ArrayList<>(); // Initialize anagrams list for each node
    
            Node() {
                for (int i = 0; i < 26; i++) {
                    children[i] = null;
                }
            }
        }
    
        public static Node root = new Node(); // trie ki pehli node kahli hoti hai
    
        public static void insert(String word) {//O(L) length of the word
            Node curr = root;
            int[] count = new int[26];
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                }
                curr = curr.children[idx];
            }
            curr.eow = true;
            curr.anagrams.add(word);
        }
    
        public static List<List<String>> groupAnagrams(String[] strs) {
            for (String str : strs) {
                insert(str);
            }
    
            List<List<String>> result = new ArrayList<>();
            dfs(root, result);
            return result;
        }
    
        public static void dfs(Node node, List<List<String>> result) {
            if (node == null) {
                return;
            }
            if (node.eow) {
                result.add(node.anagrams);
            }
            for (Node child : node.children) {
                dfs(child, result);
            }
        }
    
        public static void main(String[] args) {
            String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
            List<List<String>> result = groupAnagrams(strs);
            System.out.println(result);
        }
    }
        