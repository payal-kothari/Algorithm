





import java.util.Scanner;

public class Picture {
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int totalPeople = input.nextInt();
		int age[] = new int[totalPeople];
		float height[] = new float[totalPeople];
		for(int i = 0 ;i<totalPeople ;i++)
		{
			age[i] = input.nextInt();	
			height[i]=input.nextFloat();
		}
		input.close();					// close the scanner
		int mid = totalPeople/2;    	// mid
		
		// Create the first half 
		int firstHalfage[] = new int[mid];
		float firstHalfheight[] = new float[mid];
		for(int i = 0 ;i<mid ;i++)
		{
			firstHalfage[i] = age[i];
			firstHalfheight[i] = height[i];
		}
		// merge sort on the first half
		int countOfSwapsForFirst = sortPictureOne.MergeSort_Recursive(firstHalfage,firstHalfheight);
		
		
		// Create the second half 
        int SecondHalfage[] = new int[totalPeople - mid ];
		float SecondHalfheight[] = new float[totalPeople - mid];
		int k =0;
		for(int i = mid ;i<totalPeople  ;i++)
		{
			SecondHalfage[k] = age[i];
			SecondHalfheight[k] = height[i];
			k++;
		}
		// merge sort on the second half
		int countOfSwapsForSecond = sortPictureTwo.MergeSort_Recursive(SecondHalfage,SecondHalfheight);
		
		
		// Create the final list 
        int FinalAge[] = new int[totalPeople ];
		float FinalHt[] = new float[totalPeople];
		int w =0;
		for (w =0 ; w< firstHalfage.length ; w++){
			FinalAge[w] = firstHalfage[w];
			FinalHt[w]  =  firstHalfheight[w];
		}
		int y =0;
		for (int z=w ; z< totalPeople ; z++){
			FinalAge[z] = SecondHalfage[y];
			FinalHt[z]  = SecondHalfheight[y];
			y++;
		}
		// merge sort on the final list
        int countOfSwapsForFinal = sortPictureFinal.MergeSort_Recursive(FinalAge,FinalHt);
		
        // calculate the total count
        int finalAns = countOfSwapsForFirst + countOfSwapsForSecond + countOfSwapsForFinal;
        
        System.out.println(finalAns);
		
		
		
		
	}
}
