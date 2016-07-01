import java.util.Scanner;

public class oneDup {

	
	public static void main(String[] args) {
		
		int sum = 0;
		int dup=0;
		
		Scanner input = new Scanner(System.in);
		int numberSize = input.nextInt();					// take a input from user
		int[] arr1=new int[numberSize+2];
		
		for(int i = 0 ;i < numberSize+2 ; i++ ){
			int n = input.nextInt();						// take series of numbers from user
			arr1[i] = n;									// store the numbers in an array
		}
		
		for(int i = 0 ;i < numberSize+2 ; i++ ){
			if (i == arr1[i])
			{
				// do nothing
			}
			else
			{
				dup = arr1[i];								// store the duplicate number in "dup"
				break;										// stop searching of the duplicate number
			}
		}
		
		System.out.println(dup);		
	}
}
