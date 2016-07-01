


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Decodings {
	
	static String inputStr;
	static int[] S;
	static int flag =0;
	
	public static void main(String[] args) {
		
		  // store all the given codes`
		   Set<String> codes = new HashSet<String>();
		   codes.add("0");
		   codes.add("1");
		   codes.add("10");
		   codes.add("111");
		   codes.add("011");
		   codes.add("01");
			Scanner input = new Scanner(System.in);
			inputStr = input.nextLine();				// scan the code word
			S = new int[inputStr.length()];
			
			for(int i = 0 ; i<inputStr.length() ; i++){   // index of array S[]
				     int k = i+1;
					 String temp = inputStr.substring(0, k);
					if(i<2){								//  codeword of length till 2
						 if(codes.contains(temp)){			// check in the given codes
							 if(i == 0) S[i]++;
							 else{
								 S[i] = S[i-1]+1;
							 }
							 flag = 1;
						 }
					  
					  if(flag == 0 )
					     S[i] = S[i-1];
					}
					else{									// codeword of length greater than 2 
						S[i] = S[i-1];
						 String temp2 = temp.substring(temp.length()-2,temp.length());
						  if(codes.contains(temp2)){		// check in the given codes
							  S[i] = S[i] + S[temp.length()-3];
						  }
						 
						 String temp3 = temp.substring(temp.length()-3,temp.length());
							  if(codes.contains(temp3)){	// check in the given codes
								  if(temp.length() == 3){
									  S[i] = S[i] + 1;
								  }
								  else{
								      S[i] = S[i] + S[temp.length()-4];
								  }
							  }
					}
			}
			
			System.out.println(S[inputStr.length()-1]);
			System.exit(0);
	}
}


