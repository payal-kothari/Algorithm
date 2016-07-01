



import java.util.Scanner;

public class DoubleKnapsack {
    static int n;
    static int W1;
    static int W2;
    static int[][] S;
    static int[] c;
    static int[] w;
    
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n  = input.nextInt();						// take number of items
		W1 = input.nextInt();						// take weight of the first knapsack
		W2 = input.nextInt();						// take weight of the second knapsack
		S = new int[W1+1][W2+1];
		c = new int[n];
		w = new int[n];
		
		for(int i =0 ; i<n ; i++){
			w[i] = input.nextInt();					// take all the weights
			c[i] = input.nextInt();					// take all the costs
		}
		int ans = knapsack(n,c,w,W1,W2);
		
		System.out.println(ans);
	}

	private static int knapsack(int n, int[] c,int[] w,int W1,int W2) {
		
		for(int i =0 ;i <n ; i++){
			for(int w1=W1 ; w1>=0 ; w1--){
				for(int w2=W2 ; w2>=0 ; w2--){
					int a =0;
					int b = 0;
					if(w[i] <= w1)						// for the first bag
					   a = S[w1-w[i]][w2] + c[i];
					if(w[i] <= w2)						// for the second bag
					   b = S[w1][w2-w[i]] + c[i];
					
					S[w1][w2] = max(S[w1][w2],a,b);		// select the maximum value
				}
			}
		}
		
		return S[W1][W2];
	}

	private static int max(int c, int a, int b) {
		return Math.max(c, Math.max(a, b));			// check for the maximum answer
	}
}
