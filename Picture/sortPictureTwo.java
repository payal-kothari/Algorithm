





public class sortPictureTwo {
	

	public static int MergeSort_Recursive(int[] SecondHalfage,float[] SecondHalfheight) {
		if(SecondHalfage.length <2)				// base case
			return 0;
					
		int mid = SecondHalfage.length / 2;
		
		// create right and left halves
		int left[] = new int[mid];
		float leftht[] = new float[mid];
		int right[] = new int[SecondHalfage.length - mid ];
		float rightht[] = new float[SecondHalfage.length - mid ];
		int k = 0;
		for(int i =0 ; i<mid ; i++){
			leftht[i] = SecondHalfheight[i];
			left[i] = SecondHalfage[i];
		}
		for(int j =mid ; j<SecondHalfage.length ; j++){
			rightht[j-mid] = SecondHalfheight[j];
			right[j-mid] = SecondHalfage[j];
		}
		
		return 	MergeSort_Recursive(left,leftht) + MergeSort_Recursive(right,rightht) + Merge(SecondHalfage,left,right,leftht,rightht,SecondHalfheight);
		
	}

	public static int Merge(int[] SecondHalfage,int[] left,int[] right,float[] leftht,float[] righht,float[] SecondHalfheight) {
		
		
		int leftindex = 0;
		int rightindex = 0;
		int k = 0;
		int count =0;
		
		// merging the two halves using different conditions
		while(leftindex < left.length && rightindex <  right.length){
			if(left[leftindex] == right[rightindex] ){
				if(leftht[leftindex] >= righht[rightindex] )
				{
					SecondHalfheight[k] = leftht[leftindex];
					SecondHalfage[k++] = left[leftindex++];
				}
				else{
					SecondHalfheight[k] = righht[rightindex];
					SecondHalfage[k++] = right[rightindex++];
					count = count +  left.length-leftindex;
				}
			}
			else if(right[rightindex] == 7 ){
				SecondHalfheight[k] = righht[rightindex];
				SecondHalfage[k++] = right[rightindex++];
				count = count +  left.length-leftindex;
			}
			else if (left[leftindex] == 7) {
				SecondHalfheight[k] = leftht[leftindex];
				SecondHalfage[k++] = left[leftindex++];
			}
			else if(right[rightindex] == 44) {
				SecondHalfheight[k] = righht[rightindex];
				SecondHalfage[k++] = right[rightindex++];
				count = count +  left.length-leftindex;
				
			}
			else if (left[leftindex] == 44){
				SecondHalfheight[k] = leftht[leftindex];
				SecondHalfage[k++] = left[leftindex++];
			}
		}
		
		// merging of the remaining elements in left and/or right halves
		while(leftindex < left.length){
			    SecondHalfheight[k] = leftht[leftindex];
				SecondHalfage[k++] = left[leftindex++];
		}
		while(rightindex < right.length){
			    SecondHalfheight[k] = righht[rightindex];
				SecondHalfage[k++] = right[rightindex++];
		}
		
		return count;
	}

}
