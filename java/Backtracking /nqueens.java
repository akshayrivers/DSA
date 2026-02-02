
import java.util.Scanner;

public class nqueens {
    public static boolean isSafe(char board[][],int row,int col){
        //vertical up
        for(int i =row-1;i>=0;i--){
            if(board[i][col]=='Q'){
                return false;
            }
        }
        //diag left up
        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--){
            if(board[i][j]=='Q'){
                return false;
            }
        }
        for(int i =row-1,j=col+1;i>=0&& j<board.length;i--,j++){
            if(board[i][j]=='Q'){
                return false;
            }
        }  
        return true;
    }
    public static void nQueen(char board[][],int row) {
        //base 
        if(row==board.length){
            printboard(board);
        }
        //column loop
        for(int j=0;j<board.length;j++){
            if(isSafe(board,row,j)){
                board[row][j]='Q';
                nQueen(board, row+1);//function call
                board[row][j]='x';//backtracking call
            }
        }
    }
    public static void printboard(char board[][]){
        System.out.println("-----------chess board ----------");
        for(int i=0;i<board.length;i++){
            for(int j =0;j<board.length;j++){
                System.out.print(board[i][j]+" ");
            }System.out.println();
        }
    }
     static int count =0;
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        int n =5;
        if (n >= 4) {
            char[][] board = new char[n][n];
            // Initialize the chessboard with 'x'
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = 'x';
                }
            }
    
            nQueen(board, 0);  // Start the N-Queens algorithm
        } else {
            System.out.println("No solution exists for N less than 4.");
        }
       //sc.close();
    }
}
    
