import java.util.*;

public class trial {

    public static void print_array(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        System.out.println("hello there");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("Enter your age: ");
        int age = sc.nextInt();

        System.out.println("Hello, " + name + "! You are " + age + " years old.");

        int a = 100;
        System.out.println("a test variable : " + a);
        sc.close();
        int[] nums = { 1, 3, 4, 5, 6, 7, 8, 9, 10 };
        print_array(nums);
        print_array(new int[] { 2, 3, 4, 4 });
    }
}
