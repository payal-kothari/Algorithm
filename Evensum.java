

import java.util.Scanner;

public class Evensum {

	public static void main(String[] args) {
	
		int sum = 0;
		Scanner input = new Scanner(System.in);
		int numberSize = input.nextInt();					// take a input from user
		
		for(int i = 0 ;i < numberSize ; i++ ){
			int n = input.nextInt();						// take series of numbers from user
			if(n%2 == 0){									// check if a number is even or not
				sum = sum + n;								// re-calculate sum if the number is even
			}
		}
		System.out.println(sum);
	}
}
