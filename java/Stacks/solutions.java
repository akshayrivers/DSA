
import java.util.*;

public class solutions {
    // question 1: stock span problem
    public static void StockSpan(int stock[], int[] span) {
        Stack<Integer> s = new Stack<>();
        span[0] = 1;
        s.push(1);
        for (int i = 0; i < stock.length; i++) {
            int currPrice = stock[i];
            while (!s.empty() && currPrice > stock[s.peek()]) {
                s.pop();
            }
            if (s.empty()) {
                span[i] = i + 1;
            } else {
                int prevHigh = s.peek();
                span[i] = i - prevHigh;
            }
            s.push(i);
        }
    }

    // question 2: next greater element
    /*
     * the next greaater element of some element x in an array is the first greater
     * element that is to the right of x is the same array arr={6,8,0,1,3}
     * next greater =[8,-1,1,3,-1]
     */
    public static int[] nextgreaterelement(int arr[]) {
        Stack<Integer> s = new Stack<>();
        int nxtgreater[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            // 1.while
            while (!s.empty() && arr[s.peek()] <= arr[i]) {
                s.pop();
            }
            // 2.if-else
            if (s.empty()) {
                nxtgreater[i] = -1;
            } else {
                nxtgreater[i] = arr[s.peek()];
            }
            // 3.push in s
            s.push(i);
        }
        return nxtgreater;
    }

    // question 3: valid parantheses
    public static boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // opening
            if (ch == '(' || ch == '{' || ch == '[') {
                s.push(ch);
            } else {
                // closing
                if (s.empty()) {
                    return false;
                }
                if (s.peek() == '(' && ch == ')' || s.peek() == '[' && ch == ']' || s.peek() == '{' && ch == '}') {
                    s.pop();
                } else {
                    return false;
                }
            }
        }
        if (s.empty()) {
            return true;
        } else {
            return false;
        }
    }

    // question 4: Duplicate parantheses
    public static boolean isDuplicate(String str1) {
        Stack<Character> s = new Stack<>();

        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            // closing
            if (ch == ')') {
                int count = 0;
                while (s.peek() != '(') {
                    s.pop();
                    count++;
                }
                if (count < 1) {
                    return true;// duplicate
                } else {
                    s.pop();// opening pair
                }
            } else {
                // opening
                s.push(ch);
            }
        }
        return false;
    }

    // question 5: max area in histogram
    public static void maxArea(int arr[]) {
        int maxArea = 0;
        int nsr[] = new int[arr.length];
        int nsl[] = new int[arr.length];
        // next smaller right
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.empty() && arr[s.peek()] >= arr[i]) {
                s.pop();

            }
            if (s.empty()) {
                nsr[i] = arr.length;
            } else {
                nsr[i] = s.peek();
            }
            s.push(i);
        }
        // next smaller left
        Stack<Integer> s1 = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 1.while
            while (!s1.empty() && arr[s1.peek()] >= arr[i]) {
                s1.pop();
            }
            // 2.if-else
            if (s1.empty()) {
                nsl[i] = -1;
            } else {
                nsl[i] = arr[s1.peek()];
            }
            // 3.push in s
            s1.push(i);

            if (s1.empty()) {
                nsl[i] = -1;
            }
        }
        // current area = width=nsr[i]-nsl[i]-1;
        for (int i = 0; i < arr.length; i++) {
            int height = arr[i];
            int width = nsr[i] - nsl[i] - 1;
            int currArea = height * width;
            maxArea = Math.max(maxArea, currArea);
        }
        System.out.println("max area in histogram = " + maxArea);
    }

    public static void main(String[] args) {
        int stocks[] = { 100, 80, 60, 70, 60, 85, 101 };
        int span[] = new int[stocks.length];
        StockSpan(stocks, span);
        for (int i = 0; i < span.length; i++) {
            System.out.print(span[i] + " ");
        }
        System.out.println();
        int arr[] = { 2, 1, 5, 6, 2, 3 };
        int nxtgreater[] = nextgreaterelement(arr);
        for (int i = 0; i < nxtgreater.length; i++) {
            System.out.print(nxtgreater[i] + " ");
        }
        System.out.println();
        String str = "((({}{}{[[()]]})))";
        System.out.println(isValid(str));
        System.out.println();

        String str1 = "((a+b+((c))+c)";
        System.out.println(isDuplicate(str1));
        maxArea(arr);
    }
}
