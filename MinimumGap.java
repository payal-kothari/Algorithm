


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MinimumGap {

	static int n;
	static double first;
	static double last;
	static double start;
	static double finish;
	static double value;
	static int[] compatible;
	static double[] S;
	static List<arr> finalarr = new ArrayList<arr>();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();					// save total number of intervals
		first = input.nextDouble();				 
		last = input.nextDouble();
		
		for (int i = 0; i < n; i++) {			// scan all the intervals and add in the ArrayList (List of objects)
			start = input.nextDouble();		
			finish = input.nextDouble();
			value = finish - start;
			finalarr.add(new arr(start, finish, value));
		}

		Collections.sort(finalarr);				// ascending sort according to the finish time
		compatible();							// calculate compatible intervals 
		createS();								// create array S[]
		double ans = last - first - S[n] ;		// calculate answer
		int finalAns = (int) Math.round(ans);	// round off the answer to get the final answer
		System.out.println(finalAns);
	}

	public static void compatible() {
		compatible = new int[n];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < i; k++) {
				if ((finalarr.get(i)).start >= (finalarr.get(k)).finish) {	// check compatible intervals 
					compatible[i]++;										// maintain the count of the compatible intervals
				}
			}
		}
	}

	public static void createS() {
		int len = n;
		S = new double[++len];
		S[0] = 0;

		for (int j = 1; j <= n; j++) {
			S[j] = Math.max(S[j-1],finalarr.get(j-1).value + S[compatible[j-1]]);  // calculate the values of the array S[]
		}
	}
}

class arr implements Comparable {
	double start;
	double finish;
	double value;

	public arr(double start, double finish, double value) {
		this.start = start;
		this.finish = finish;
		this.value = value;
	}

	public int compareTo(Object o) {				// to get the ascending sorted array of the intervals
		arr a = (arr) o;
		if (finish == a.finish) {
			return 0;
		} else if (finish > a.finish) {
			return 1;
		} else {
			return -1;
		}
	}
}