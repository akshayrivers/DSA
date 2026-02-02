class backtracking{
    /*
     * dekho isme main crux hai ki call stack se vapis aate vakt jb ham kuch function perform kar paye 
     * use ham log backtracking bolte hai 
     * aage jane se pehle hame recursion aur divide and conquer ka ache se idea hona chaiye 
     * Types:
     * Decision     Optimization    Enumeration
     * 
     * sabse zyada imoprtant chiz isme hai call stack ko anyalse krna 
     * jaise 3 main ek loop main bar bar ham us function ko call kr rhe hai
     * toh usme pehle sare calls hongi for i=0 fir hogi calls resolve for i=1 and so on ab isme
     * jb ham funxtion ko call laga rhe hain vo bi is loop ko 0 se hi start krega toh yhn
     * hame milega backtrack use main aata hua ki function ki call toh tab hi khtm hogi jb
     * base case hit karega and then we will move back to address the previous call  
     */
    public static void changearr(int arr[],int i,int val){
        //base case
        if(i==arr.length){
            Printarr(arr);
            return;
        }
        //recursion
        arr[i]=val;
        changearr(arr, i+1, val+1);
        arr[i]=arr[i]-2;
    }
    public static void Printarr(int arr[]){
        for( int i=0;i<arr.length-1;i++){
            System.out.print(arr[i]+" ");
        }System.out.println();
    }
    // finding and printing subsets of an string using backtracking 
    public static void findsubsets(String str, String ans,int i ){
        //base case 
        if (i==str.length()) {
            if (ans.length()==0) {
                System.out.println("null");
            }
            else{
                System.out.println(ans);
            }
            return;
        }
        // if the first char wants to tag - Yes 
        findsubsets(str, ans+str.charAt(i), i+1);
        // if it doesn't want to form a pair - No
        findsubsets(str, ans, i+1);
    }
    // 3 ->Find and Print all permutations possible for a string 
    public static void findPermutations( String str, String ans){
        // base case 
        if (str.length()==0) {
            System.out.println(ans);
            return;
        }
        // recursion
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            String newString= str.substring(0,i)+ str.substring(i+1,str.length());
            findPermutations(newString, ans+curr);
        }
    }
    static int count =0;
    public static int gridways(int i ,int j,int n,int m){
        //base case 
        if(i==n-1&&j==m-1){
            return 1;
        }
        else if(i>=n||j>=m||i<0||j<0){
            return 0;
        }
        int w1= gridways(i+1, j, n, m);
        int w2=gridways(i, j+1, n, m);
        int w3=gridways(i-1, j, n, m);
        int w4 =gridways(i, j-1, n, m);
        return w1+w2+w3+w4;
    }
    public static boolean check(int maze[][],int x,int y,int sol[][]){
        int n = maze.length;
        if(x<0||x>=n||y<0||y>=n){
            return false;
        }
        if(maze[x][y]==0||sol[x][y]==1){
            return false;
        }
        return true;
    }
    public static void solve(int x,int y,int maze[][],int sol[][]){
        int n =maze.length;
        if(x==n-1&&y==n-1){
            for(int i=0;i<n;i++){
                for(int j =0;j<n;j++){
                    System.out.print(sol[i][j]+" ");
                }System.out.println();
            }
            System.out.println();
          
        }
        if(check(maze, x, y-1, sol)){
            sol[x][y-1]=1;
            solve(x, y-1, maze,sol); 
            sol[x][y-1]=0;
        }
         if(check(maze, x, y+1, sol)){
            sol[x][y+1]=1;
            solve(x, y+1, maze,sol);
            sol[x][y+1]=0;
        }
        if(check(maze, x+1, y, sol)){
            sol[x+1][y]=1;
            solve(x+1, y, maze,sol);
            sol[x+1][y]=0;
        }
        if(check(maze, x-1, y, sol)){
            sol[x-1][y]=1;
            solve(x-1, y, maze,sol);
            sol[x-1][y]=0;
        }
   
    }
    public static void main(String[] args) {
        int maze[][]={{1,0,0,0},{1,1,0,1},{0,1,0,0},{1,1,1,1}};
        int n=4;
        int sol[][]= new int[n][n];
        for( int i =0;i<4;i++){
            for(int j=0; j<4;j++){
                sol[i][j]=0;
            }
        }
        sol[0][0]=1;
        solve(0,0,maze,sol);

        String str= "abc";
        String ans="";
        //findsubsets(str, ans, 0);
        findPermutations(str, ans);
    }
}