public class practice {
    static int f = 9; // Initialize f with a value other than 0

    public static void main(String[] args) {
        int cost = 1000;

        // Determine the value of f based on a condition
        if (cost % 1000 >= 0) {
            f = 9;
        } else {
            f = 0;
        }

        // Use a switch-case statement to handle different cases
        switch (f) {
            case 9:
                cost = cost - cost / 10;
                break;
            default:
                // Handle default case if necessary
                break;
        }

        // Print the updated value of cost
        System.out.println(cost + "dkdkd");
    }
}
