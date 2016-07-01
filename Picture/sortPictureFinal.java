







public class sortPictureFinal {
	
	
	public static int MergeSort_Recursive(int[] FinalAge,float[] FinalHt) {
		if(FinalAge.length <2)			// base case
			return 0;
					
		int mid = FinalAge.length / 2;
		
		// create right and left halves
		int left[] = new int[mid];
		float leftht[] = new float[mid];
		int right[] = new int[FinalAge.length - mid ];
		float rightht[] = new float[FinalAge.length - mid ];
		int k = 0;
		for(int i =0 ; i<mid ; i++){
			leftht[i] = FinalHt[i];
			left[i] = FinalAge[i];
		}
		for(int j =mid ; j<FinalAge.length ; j++){
			rightht[j-mid] = FinalHt[j];
			right[j-mid] = FinalAge[j];
		}
		
		return 	MergeSort_Recursive(left,leftht) + MergeSort_Recursive(right,rightht) + Merge(FinalAge,left,right,leftht,rightht,FinalHt);
	}

	public static int Merge(int[] FinalAge,int[] left,int[] right,float[] leftht,float[] righht,float[] FinalHt) {
		int leftindex = 0;
		int rightindex = 0;
		int k = 0;
		int count =0;
		
		
		// merging the two halves using different conditions
		while(leftindex < left.length && rightindex <  right.length){
			if(left[leftindex] == right[rightindex] && left[leftindex] == 7 ){
				if(leftht[leftindex] <= righht[rightindex] )
				{
					FinalHt[k] = leftht[leftindex];
					FinalAge[k++] = left[leftindex++];
				}
				else{
					FinalHt[k] = righht[rightindex];
					FinalAge[k++] = right[rightindex++];
					count = count +  left.length-leftindex;
				}
			}
			else if(left[leftindex] == right[rightindex] && left[leftindex] == 8 ){
				if(leftht[leftindex] >= righht[rightindex] )
				{
					FinalHt[k] = leftht[leftindex];
					FinalAge[k++] = left[leftindex++];
				}
				else{
					FinalHt[k] = righht[rightindex];
					FinalAge[k++] = right[rightindex++];
					count = count +  left.length-leftindex;
				}
			}
			else if(right[rightindex] == 7 ){
				FinalHt[k] = righht[rightindex];
				FinalAge[k++] = right[rightindex++];
				count = count +  left.length-leftindex;
			}
			else if (left[leftindex] == 7) {
				FinalHt[k] = leftht[leftindex];
				FinalAge[k++] = left[leftindex++];
			}
			else if(right[rightindex] == 44) {
				FinalHt[k] = righht[rightindex];
				FinalAge[k++] = right[rightindex++];
				count = count +  left.length-leftindex;
			}
			else if (left[leftindex] == 44){
				FinalHt[k] = leftht[leftindex];
				FinalAge[k++] = left[leftindex++];
			}
		}
		
		// merging of the remaining elements in left and/or right halves
		while(leftindex < left.length){
			    FinalHt[k] = leftht[leftindex];
				FinalAge[k++] = left[leftindex++];
		}
		while(rightindex < right.length){
			    FinalHt[k] = righht[rightindex];
				FinalAge[k++] = right[rightindex++];
		}
		
		return count;
	}

}
