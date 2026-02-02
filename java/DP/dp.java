// package DP;

import java.util.Arrays;
import java.util.HashSet;

class dp{
    /*
     * So Dynamic programming, this is set of techniques to efficiently solve a particlar type of problems that have overalpping 
     * subproblems and optimal substructure property
     * identifying-> 1. it should be an optipal problem 2. some choice is given(recursion)
     * our main set of problems in this topic will revolve around concepts used while solving the following problems:
     * 1. fibonacci
     * 2. 0-1 Knapsack
     * 3. Unbounded Knapsack 
     * 4. LCS(longest common subsequence)
     * 5. Kadane's Algorithm 
     * 6. Catalan number
     * 7. DP on grid(2-D array)
     */
    public static int fib(int n,int[]f){
        if (n==0||n==1) {
            return n;
        }
        if (f[n]!=0) {
            return f[n];
        }
        f[n]= fib(n-1,f)+fib(n-2,f);
        return f[n];
    }
    /*
     * 0-1 knapsack using memoisation- the big idea here is using a 2-D array with rows and colums corresponding to 
     * number of items and weight capacity and buy using recursion we will store the value of the maximum profit in each cell.
     * the 2-d array will be intialised to -1  
     */
    public static int Knapsack(int val[], int wt[],int W, int n, int dp[][] ){
        if (W==0||n==0) {
            return 0;
        }
        // now what this check  is that the box is not visited before if it is visited then return the values 
        if (dp[n][W]!=-1) {
            return dp[n][W];
        }
        if (wt[n-1]<=W) {//checking if the item fits in our knapsack(Valid)
            //including decision 
            int ans1=val[n-1]+Knapsack(val, wt, W-wt[n-1], n-1, dp);
            //excluding decision 
            int ans2=Knapsack(val, wt, W, n-1,dp);
            dp[n][W]= Math.max(ans1,ans2);
            return dp[n][W];
        }else{//not valid
            dp[n][W]= Knapsack(val, wt, W, n-1,dp);
            return dp[n][W]; 
        }
    }
    public static int KanpsackTab(int val[],int wt[], int W){
        int n = val.length;
        int dp[][]= new int[n+1][W+1];
        for (int i = 0; i < dp.length; i++) {
            //0th column
            dp[i][0]=0;
            for (int j = 0; j < dp[0].length; j++) {
                //0th row
                dp[0][j]=0;
            }
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < W+1; j++) {
                int v= val[i-1];
                int w=wt[i-1];
                if (w<=j) {//valid
                    int incprofit=v+dp[i-1][j-w];
                    int excprofit=dp[i-1][j];
                    dp[i][j]=Math.max(incprofit, excprofit);
                }else{//invalid
                    int excprofit=dp[i-1][j];
                    dp[i][j]=excprofit;
                }
            }
        }
        //print(dp);
        return dp[n][W];
    }
    private static void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void print(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static boolean targetSumSubset(int arr[],int sum){
        int n = arr.length;
        boolean dp[][]= new boolean[n+1][sum+1];
        for (int i = 0; i < n+1; i++) {
            dp[i][0]=true;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < sum+1; j++) {
                int v=arr[i-1];
                //include
                if (v<=j&&dp[i-1][j-v]==true) {
                    dp[i][j]=true;
                }
                //exclude 
                else if (dp[i-1][j]==true) {
                    dp[i][j]=true;
                }
            }
        }
        //  print(dp);
        return dp[n][sum];
    }

    public static int UnboundedKanpsack(int val[],int wt[], int W){
        int n = val.length;
        int dp[][]= new int[n+1][W+1];
        for (int i = 0; i < dp.length; i++) {
            //0th column
            dp[i][0]=0;
            for (int j = 0; j < dp[0].length; j++) {
                //0th row
                dp[0][j]=0;
            }
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < W+1; j++) {
                int v= val[i-1];
                int w=wt[i-1];
                if (w<=j) {//valid
                    int incprofit=v+dp[i][j-w];
                    int excprofit=dp[i-1][j];
                    dp[i][j]=Math.max(incprofit, excprofit);
                }else{//invalid
                    int excprofit=dp[i-1][j];
                    dp[i][j]=excprofit;
                }
            }
        }
        //print(dp);
        return dp[n][W];
    }

    public static int CoinChange(int coins[],int sum1){
        int n = coins.length;
        int dp[][]= new int[n+1][sum1+1];

        // initialising sum is -0
        //i-> coins; j-> sum/change
        for (int i = 0; i < n+1; i++) {
            dp[i][0]=1;
        }
        for (int i = 0; i < sum1+1; i++) {
            dp[0][i]=0;
        }
        //O(N*sum)
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < sum1+1; j++) {
                if (coins[i-1]<=j) {//valid
                    dp[i][j]=dp[i][j-coins[i-1]]+dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][sum1];
    }
    public static int rodcutting(int[]length,int price[],int totrod){
        int n = length.length;
        int dp[][]= new int[n+1][totrod+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i==0||j==0) {
                    dp[i][j]=0;
                }
            }
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < totrod+1; j++) {
                //valid
                if (length[i-1]<=j) {
                    dp[i][j]=Math.max(price[i-1]+dp[i][j-length[i-1]],dp[i-1][j]);
                }else{//invalid
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        //print(dp);
        return dp[n][totrod];

    }
    public static int lcs(String str1, String str2, int n ,int m){
        if (n==0||m==0) {
            return 0;
        }

        if(str1.charAt(n-1)==str2.charAt(m-1)){//same
            return lcs(str1, str2, n-1, m-1)+1;
        }else{//diff
            int ans1=lcs(str1, str2, n-1, m);
            int ans2=lcs(str1, str2, n, m-1);
            return Math.max(ans1, ans2);
        }
    }

    public static int lcs2(String str1, String str2, int n ,int m,int dp1[][]){
        if (n==0||m==0) {
            return 0;
        }
        if (dp1[n][m]!=-1) {
            return dp1[n][m];
        }
        if(str1.charAt(n-1)==str2.charAt(m-1)){//same
            return dp1[n][m]=lcs2(str1, str2, n-1, m-1,dp1)+1;
        }else{//diff
            int ans1=lcs2(str1, str2, n-1, m,dp1);
            int ans2=lcs2(str1, str2, n, m-1,dp1);
            return dp1[n][m]=Math.max(ans1, ans2);
        }
    }

    public static int lcsTab(String str1,String str2){
        int n = str1.length();
        int m= str2.length();
        int dp[][]= new int[n+1][m+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
               if(i==0||j==0) dp[i][j]=0;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    int ans1=dp[i-1][j];
                    int ans2= dp[i][j-1];
                    dp[i][j]= Math.max(ans1, ans2);
                }
            }
        }
        return dp[n][m];
    }
    

    public static int longestCommonSubstring(String str1,String str2){
        int n = str1.length();
        int m= str2.length();
        int dp[][]= new int[n+1][m+1];
        int ans=0;
        //intialize
        for (int i = 0; i < dp.length; i++) {
            dp[i][0]=0;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[0][i]=0;
        }
        
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1]+1;
                    ans=Math.max(ans, dp[i][j]);
                }else{
                    dp[i][j]=0;
                }
            }
        }
        return ans;
    }

    public static int lis(int arr[]){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        int arr2[]= new int[set.size()];//sorted unique elements
        int i=0;
        for (int num : set) {
            arr2[i]=num;
            i++;
        }

        Arrays.sort(arr2);
        return lcs0(arr,arr2);
    }
    
    private static int lcs0(int[] arr, int[] arr2) {
        int n =arr.length;
        int m = arr2.length;
        int dp[][]=new int [n+1][m+1];

        //intialise
        for (int i = 0; i < dp.length; i++) {
            dp[i][0]=0;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[0][i]=0;
        }
        
        //bottom up
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (arr[i-1]==arr2[j-1]) {
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    int ans1= dp[i-1][j];
                    int ans2= dp[i][j-1];
                    dp[i][j]=Math.max(ans1, ans2);
                }
            }
        }
        return dp[n][m];
    }

    public static int editDistance(String str1, String str2 ){
        int n = str1.length();
        int m = str2.length();
        int dp[][]= new int[n+1][m+1];

        //initialise
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i==0) {
                    dp[i][j]=j;
                }
                if (j==0) {
                    dp[i][j]=i;
                }
            }
        }
        //bottom up
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    int add= dp[i][j-1]+1;
                    int del= dp[i-1][j]+1;
                    int rep= dp[i-1][j-1]+1;
                    dp[i][j]=Math.min(add, Math.min(del, rep));
                }
            }
        }
        return dp[n][m];
    }

    public static boolean isMatch(String s , String p){
        //TC-> O(n*m)
        int n = s.length();
        int m = p.length();

        boolean dp[][]= new boolean[n+1][m+1];

        //initialise
        dp[0][0]=true;
        //pattern= " "
        for (int i = 1; i < n+1; i++) {
            dp[i][0]=false;
        }
        //s= " "
        for (int i = 1; i < m+1; i++) {
            if (p.charAt(i-1)=='*') {
                dp[0][i]=dp[0][i-1];
            }
        }
        //bottom up
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                //case 1 -> ith cahr=== jth char|| jth char =='?'
                if (s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='?') {
                    dp[i][j]=dp[i-1][j-1];
                }
                else if (p.charAt(j-1)=='*') {
                    dp[i][j]= dp[i-1][j]||dp[i][j-1];
                }
                else{
                    dp[i][j]=false;
                }
            }
        }
        //string ->n pattern ->m
        return dp[n][m];
    }
    public static int catalanRec(int n ){
        if (n==0||n==1) {
            return 1;
        }

        int ans=0;// cn
        for (int i = 0; i < n; i++) {
            ans+=catalanRec(i)*catalanRec(n-i-1);
        }
        return ans;
    }
    public static int catalanMem(int n,int dp[]){
        if (n==0||n==1) {
            return 1;
        }
        if (dp[n]!=-1) {
            return dp[n];
        }
        int ans=0;
        for (int i = 0; i < n; i++) {
            ans+= catalanMem(i, dp)*catalanMem(n-i-1, dp);
        }
        
        return dp[n]=ans;
    }
    public static int catlanTab(int n){
        //TC-O(n^2)
        int dp[]= new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i]+=dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
    public static int countBST(int n ){
        int dp[]= new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for (int i = 2; i <= n; i++) {
            //Ci -> BST (i nodes )-> dp[i]
            for (int j = 0; j < i; j++) {
                //ci = cj* ci-j-1
                int left = dp[j];
                int right = dp[i-j-1];
                dp[i]+=left * right;
            }
        }
        return dp[n];
    }

    public static int mountainRanges(int n ){
        int dp[]= new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for (int i = 2; i <= n; i++) {
            //i pairs -> mountain ranges => Ci
            for (int j = 0; j < i; j++) {
                int inside = dp[j];
                int outside = dp[i-j-1];
                dp[i]+=inside*outside;//ci= cj*ci-j-1
            }
        }
        return dp[n];
    }

    public static int mcmMem(int arr[],int i, int j,int dp[][]){
        if (i==j) {
            return 0;//single matrix case
        }
        if (dp[i][j]!=-1) {
            return dp[i][j];
        }
        int ans= Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int cost1= mcmMem(arr, i, k,dp);
            int cost2= mcmMem(arr, k+1, j,dp);
            int cost3=arr[i-1]*arr[k]*arr[j];
            int finalcost= cost1+cost2+cost3;
            ans= Math.min(ans, finalcost);
        }
        return dp[i][j]=ans;//mincost
    }

    public static void main(String[] args) {
        int n =4;
        int f[]= new int[n+1];
        System.out.println("The output for the fibonachi code: "+fib(n,f));

        int val[]={15,14,10,45,30};
        int wt[]={2,5,1,3,4};
        int W=7;
        int dp[][]= new int[val.length+1][W+1];
        for (int i = 0; i < dp.length; i++) {
            for(int j=0; j<dp[0].length;j++){
                dp[i][j]=-1;
            }
        }
        System.out.println("The output for 0-1 Knapsack code with memoization: "+Knapsack(val, wt, W,val.length,dp));
        System.out.println("The output for 0-1 Knapsack code with memoization: "+KanpsackTab(val, wt, W));


        int arr[]={4,2,7,1,3};
        int sum=10;
        System.out.println("The output for the target sum subset problem is: " + targetSumSubset(arr, sum));

        System.out.println("The output for Unbounded Knapsack problem is: "+UnboundedKanpsack(val, wt, W));

        int coins[]={2,5,3,6};
        int sum1=10;
        System.out.println("The output for the coin change problem is: " +CoinChange(coins, sum1));

        int length[]={1,2,3,4,5,6,7,8};
        int pirce[]={1,5,8,9,10,17,17,20};
        int totrod=8;
        System.out.println("The output for the rod cutting problem is: "+rodcutting(length, pirce, totrod));

        String str1="abcdge";
        String str2="abedg";
        int n1 = str1.length();
        int m = str2.length();
        int dp1[][]= new int[n1+1][m+1];
        //intialization
        for (int i = 0; i < n1+1; i++) {
            for (int j = 0; j < m+1; j++) {
                dp1[i][j]=-1;
            }
        }
        int arr5[]={50,3,10,7,40,80};
        System.out.println("The output for the least common subsequence using normal recursion is: "+lcs(str1, str2, str1.length(), str2.length()));
        System.out.println("The output for the least common subsequence using memeoizaton is: "+lcs2(str1, str2, n1, m,dp1));
        System.out.println("The output for the least common subsequence using tabulation is: "+lcsTab(str1, str2));
        System.out.println("The output for the longest common substring is: "+longestCommonSubstring("ABCDE", "ABGCE"));
        System.out.println("The output for the longest increasing subsequence in an array is: "+ lis(arr5));
        System.out.println("The no. of steps involved to convert one word to another is: "+ editDistance("intention", "execution"));
        System.out.println("Wildcard matching the input here is string s = \"baaabab\" and pattern p=\"*****ba*****ab\" : "+ isMatch("baaabab", "*****ba*****ab"));
        
        int n5=4;
        int dp5[]= new int [n5+1];
        Arrays.fill(dp5, -1);
        System.out.println("The output for catlan number through using memoisation :"+catalanMem(n5, dp5));
        System.out.println("The output for catlan number through using tabulation :"+catlanTab(n5));
        System.out.println("counting BST :"+ countBST(n5));
        System.out.println(mountainRanges(n5));

        int arrt[]={1,2,3,4,3};// //n=5
        int nt = arrt.length;
        int dpt[][]= new int[nt][nt];
        for (int i = 0; i < dpt.length; i++) {
            Arrays.fill(dpt[i], -1);
        }
        System.out.println(mcmMem(arrt, 1, nt-1,dpt));
    }
}