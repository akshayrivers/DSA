public class tries {
 static class Node{
    Node children[]= new Node[26];
    boolean eow=false;
    Node(){
        for (int i = 0; i < 26; i++) {
            children[i]=null;
        }
    }
}
 public static Node root= new Node();// trie ki pehli node kahli hoti hai 
 public static void insert(String word){//O(L) length of the word
    Node curr= root;
    for (int i = 0; i < word.length(); i++) {
        int idx= word.charAt(i)-'a';
        if (curr.children[idx]==null) {
            curr.children[idx]= new Node();
        }
        curr= curr.children[idx];
    }
    curr.eow=true;
 }
 public static boolean search(String word){//O(L) length of the word
    Node curr= root;
    for (int i = 0; i < word.length(); i++) {
        int idx= word.charAt(i)-'a';
        if (curr.children[idx]==null) {
            return false;
        }
        curr= curr.children[idx];
    }
    return curr.eow ==true;
 }
 public static boolean wordBreak(String key){//O(L) key length
    if (key.length()==0) {
        return true;
    }
    for (int i = 1; i <= key.length(); i++) {
        if (search(key.substring(0,i))&& wordBreak(key.substring(i,key.length()))) {
            return true;
        }
    }
    return false;
 }
public static boolean startsWith(String prefix){
    Node curr= root;
    for (int i = 0; i < prefix.length(); i++) {
        int idx= prefix.charAt(i)-'a';
        if (curr.children[idx]==null) {
            return false;
        }
        curr= curr.children[idx];
    }
    return true;
}
public static String ans="";
// public static void longestCommonPrefix(Node root, StringBuilder temp) {
//     if (root == null) {
//         return;
//     }
//     for (int i = 0; i < 26; i++) {
//         if (root.children[i] != null && root.children[i].eow==true) {
//             char ch = (char) (i+'a');
//             temp.append(ch);
//             if (temp.length()>ans.length()) {
//                 ans = temp.toString();
//             }
//             longestCommonPrefix(root.children[i], temp);
//             temp.deleteCharAt(temp.length() - 1);
//         }
//     }
// }
public static void longestCommonPrefix(Node root, StringBuilder temp) {
    if (root == null) {
        return;
    }
    for (int i = 0; i < 26; i++) {
        if (root.children[i] != null && root.children[i].eow == true) {
            char ch = (char) (i + 'a');
            StringBuilder newTemp = new StringBuilder(temp); // Create a new StringBuilder
            newTemp.append(ch);
            if (newTemp.length() > ans.length()) {
                ans = newTemp.toString();
            }
            longestCommonPrefix(root.children[i], newTemp); // Pass the new StringBuilder
        }
    }
}

public static void main(String[] args) {
        String words[]= {"i","like","sam","samsung","mobile","ice"};
        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }
        String key="ilikesamsung";
        System.out.println(wordBreak(key));
        //String words[]={"a","banana","app","apple","apply","ap","appl"};
        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }
        longestCommonPrefix(root, new StringBuilder(""));
        System.out.println(ans);
        // String prefix="wo";
        // System.out.println(startsWith(prefix));

    }        

}
