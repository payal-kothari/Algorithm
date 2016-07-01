





public class sortPictureOne {

	public static int MergeSort_Recursive(int[] firstHalfage,float[] firstHalfheight) {
		if(firstHalfage.length <2)		// base case
			return 0;
					
		int mid = firstHalfage.length / 2;
		
		// create right and left halves
		int left[] = new int[mid];
		float leftht[] = new float[mid];
		int right[] = new int[firstHalfage.length - mid ];
		float rightht[] = new float[firstHalfage.length - mid ];
		int k = 0;
		for(int i =0 ; i<mid ; i++){
			leftht[i] = firstHalfheight[i];
			left[i] = firstHalfage[i];
		}
		for(int j =mid ; j<firstHalfage.length ; j++){
			rightht[j-mid] = firstHalfheight[j];
			right[j-mid] = firstHalfage[j];
		}
		
		return 	MergeSort_Recursive(left,leftht) + MergeSort_Recursive(right,rightht) + Merge(firstHalfage,left,right,leftht,rightht,firstHalfheight);
		
	}

	public static int Merge(int[] firstHalfage,int[] left,int[] right,float[] leftht,float[] righht,float[] firstHalfheight) {
		
		
		int leftindex = 0;
		int rightindex = 0;
		int k = 0;
		int count =0;
		
		// merging the two halves using different conditions
		while(leftindex < left.length && rightindex <  right.length){
			if(left[leftindex] == right[rightindex]){
				if(leftht[leftindex] <= righht[rightindex] )
				{
					firstHalfheight[k] = leftht[leftindex];
					firstHalfage[k++] = left[leftindex++];
				}
				else{
					firstHalfheight[k] = righht[rightindex];
					firstHalfage[k++] = right[rightindex++];
					count = count +  left.length-leftindex;
				}
			}
			else{
				firstHalfheight[k] = righht[rightindex];
				firstHalfage[k++] = right[rightindex++];
				count = count +  left.length-leftindex;
			}
		}
		
		// merging of the remaining elements in left and/or right halves
		while(leftindex < left.length){
			    firstHalfheight[k] = leftht[leftindex];
				firstHalfage[k++] = left[leftindex++];
		}
		while(rightindex < right.length){
			    firstHalfheight[k] = righht[rightindex];
				firstHalfage[k++] = right[rightindex++];
		}
		
		return count;
	}

}
