public class recursionbasics {
    public static void decreasingorder(int n){
        if(n==1){
        System.out.print(n);
        return;}
        System.out.print(n+" ");
        decreasingorder(n-1);
    }
    public static void incorder(int n){
        if( n==1){
            System.out.println(n);
            return;
        }
        incorder(n-1);
        System.out.println(n+" ");
    }
    public static int factorial(int n ){
        if(n==0){
            return 1 ;
        }
        return( n*factorial(n-1));
    }   
    public static int sumnatural(int n) {
       if (n ==1){
        return 1;
       }
       return( n+sumnatural(n-1));
    }
    public static int fib(int n ){
        if(n ==0||n==1){
           return n ;
        }
        int fnm1= fib(n-1);
        int fnm2= fib(n-2);
        int fn = fnm1+fnm2;
        return fn ;
    }
public static boolean sorted(int arr[], int i){
    if( i == arr.length-1){
        return true;
    }
    if ( arr[i]>arr[i+1]){
        return false;
    }
    return sorted(arr,i+1);
}
public static int power(int x, int n ){
    if (n==0){
        return 1;
    }
    return x * power(x,n-1);// more optimised code is as following O(n) iska hai 
}
public static int optimisedpower(int x, int n){
    if (n==0){
        return 1;
    }
    int halfpower=optimisedpower(x,n/2);
    int halfpowersq= halfpower*halfpower;
    //n is odd
    if(n%2!=0){
        halfpowersq=x * halfpowersq;
    }
    return halfpowersq;
}
public static int tilingproblem(int n ){
    if(n==0||n==1){
        return 1;
    }
    int fnm1 = tilingproblem(n-1);
    int fnm2 = tilingproblem(n-2);
    int totways=fnm1+fnm2;
    return totways ;
}
public static void removeduplicates(String str, int idx, StringBuilder newStr, boolean[] map) {
    if (idx == str.length()) {
        System.out.print(newStr);
        return;
    }
    char currChar = str.charAt(idx);
    // Adjust to handle both uppercase and lowercase letters
    if (Character.isAlphabetic(currChar) && !map[Character.toLowerCase(currChar) - 'a']) {
        map[Character.toLowerCase(currChar) - 'a'] = true;
        newStr.append(currChar);
    }
    removeduplicates(str, idx + 1, newStr, map);
}
public static int friendspairing(int n){
    if (n==1||n==2){
        return n;
    }
    return friendspairing(n-1)+(n-1)*friendspairing(n-2);
}
public static void occuredat(int arr[],int key,int i ){
    int len= arr.length;
    if (i==len){
        System.out.print(" ");
        return;
    }
    if(arr[i]==key){
        System.out.print(i+" ");
    }
  occuredat(arr, key,i+1);
}
static String digits[] ={"zero","one","two","three","four","five","six","seven","eight","nine"};

public static void numtoeng(int n){
    if (n==0){
        return;
    }
     int lastdigit= n%10;    
     numtoeng(n/10);
     System.out.print(digits[lastdigit]+" ");
    
}
public static int lengthofastring(String str){
    if(str.length()==0){
        return 0;
    
    }
   return lengthofastring(str.substring(1)) +1;
}
public static int countSubstrs(String str,int i,int j,int n) {
    if(n==1){
        return 1;
    }
    if(n<=0) {
        return 0;
    }
    int res=countSubstrs(str,i+1,j,n-1)+countSubstrs(str,i,j-1,n-1)-countSubstrs(str,i+1,j-1,n-2);
    if(str.charAt(i) ==str.charAt(j)) {
        res++ ;
    }
    return res;
}
public static void towerofHanoi(int n, String src, String helper, String dest) {
if (n == 1) {
     System.out.println("transfer disk "+ n + " from "+src+" to "+dest); 
     return;}
     towerofHanoi(n-1, src, dest, helper);
     System.out.println("transfer disk "+ n + " from "+src+" to "+dest);
     towerofHanoi(n-1, helper, src, dest);
}
    public static void main (String[] args){
     int n =5;
     towerofHanoi(n,"S","H","D");
    }
}
