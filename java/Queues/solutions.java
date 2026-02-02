import java.util.*;
import java.util.LinkedList;
public class solutions {
    /*
    Question 1:
    Generate Binary Numbers
    GivenanumberN.
    The task is to generate and print all binary numbers with decimal values from 1 to N.
    Sample Input 1: N =2
    Sample Output 1: 1 10
    Sample input:6
    Sample Output:1 10 11 100 101 110 
    */
    public static void generateBinary(int n){
        Queue<String>q=new LinkedList<String>();
        q.add("1");
        while (n-->0) {
            String s1=q.peek();
            q.remove();
            System.out.println(s1);
            String s2 = s1;
            q.add(s1+0);
            q.add(s2+1);

        }
    }

    public static void main(String[] args) {
        int n=10;
        generateBinary(n);
    }
}
