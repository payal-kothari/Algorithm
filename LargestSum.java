import java.util.Scanner;



public class LargestSum {
	static int n;
    static int A[];
    static int S[];
    static int max = 0;
    
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();			
		A = new int[n+1];
		S = new int[n+1];
		for(int i = 1 ; i<=n ; i++){		  // store inputs in an array A[] and S[]
			A[i] = input.nextInt();
			S[i] = A[i];
		}
		
		
		for(int k=1; k<=n; k++){			
			for(int j=1; j<k; j++){
				
				if(A[j] < A[k]){			  // check for a "increasing" sub-sequence till kth element
					if(S[k] < S[j] + A[k]){   // check for a maximum sum for increasing sub-sequence till kth element
						S[k] = S[j] + A[k];
					}
				}
			}
			if(max < S[k])					  // update the maximum sum
				max = S[k];
		}
		
		System.out.println(max);
	}
}
