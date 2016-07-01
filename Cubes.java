



import java.util.Scanner;

public class Cubes {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
	    int n = input.nextInt();							// take a input from user
	    
	    for (int i=0 ; i<=n ; i++){

	    	for (int k = 0 ; k<=i+1/2;k++){
	    			if (k*k*k == i){						// check if a number(i) is a perfect cube or not
	    				System.out.println(i);
	    				break;
	    		}
	    	}
	    }
		
	}
}
