


import java.util.Scanner;

public class StringConvert {
	
	static char X[];
	static char Y[];
	static final int insert = 4;
	static final int delete = 3;
	static final int replace = 5;
	static int S[][];
	static int m,a,b,c,n;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
		String temp = input.nextLine();
		X = temp.toCharArray();					// scan first string in array X[]
		String temp1 = input.nextLine();
		Y = temp1.toCharArray();				// scan first string in array Y[]
		
		S = new int[X.length+1][Y.length+1];	// 2-D matrix S[]
		
		for(int i =0; i<X.length+1 ; i++){		// values for 0th column
			S[i][0] = delete * i;
		}
		
		for(int i =0; i<Y.length+1 ; i++){		// values for 0th row
			S[0][i] = insert * i;
		}
		
		for( m = 1; m < X.length+1;m++){
			for(n =1; n <Y.length+1 ; n++){
				
				a = S[m][n-1] + insert;
				b = S[m-1][n] + delete;
				
				
				if(X[m-1] == Y[n-1]){			// condition for same characters in X and Y
					S[m][n] = S[m-1][n-1];
				}
				else if(m-2 > 0){				// check if there are more than 1 characters in X so replacement is possible
					c = S[m-2][n-1] + replace;
					S[m][n] = min(a,b,c);
				}
				else{							// condition where there are less than two charactes in X so replacement is not possible
					S[m][n] = Math.min(a, b);
				}
			}
		}
		
		int ans = S[X.length][Y.length];		// final answer
		System.out.println(ans);
		
	}
	
	public static int min(int a , int b, int c) { // method to find minimum from a,b,c
		
		return Math.min(a,Math.min(b, c));
		
	}
}
