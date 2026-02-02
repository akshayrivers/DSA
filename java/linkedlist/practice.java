import java.util.Scanner;
public class practice {
    public static double incometaxcalc(double income){
        double tax;
        if (income < 500000){
            tax = 0*income;
        }
        else if (income>=500000 && income < 1000000){
            tax = (0.2*income);
        }
        else if(income>=100000) {
            tax = (0.3*income);
        }
        else {
            tax=0.0;
        }
        return tax;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Income");
        double income = sc.nextDouble();
        System.out.println(incometaxcalc(income));
    }
}