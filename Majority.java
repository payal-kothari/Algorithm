







import java.util.Scanner;
public class Majority {
      public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int total = input.nextInt();
		int A[] = new int[total];
		int k,Y1=0,Y2=0;
		int z;
		int B[] = new int[100000];		// to store the count
		
		for(int i = 0 ;i<total ;i++)
		{
			A[i] = input.nextInt();     // store the inputs in an array
		}
		input.close(); 					// close the scanner
		
		for(k = 0;k<total ; k++)
		{
		   	z = A[k];
		   	B[z]++;
		   	if(B[z] > total/2)			// check for n/2 times repetition
		   	{
		   		Y1 = 1;
		   	}
		   	if(B[z] > total/3)			// check for n/3 times repetition
		   	{
		   		Y2 = 1;
		   	}		   	
		}
		
		if(Y1 == 1){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}
		if(Y2 == 1){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}		
		
	}
}
