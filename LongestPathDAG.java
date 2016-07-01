import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class LongestPathDAG {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int V, E;
		V = input.nextInt();
		E = input.nextInt();
		Map<Integer, ArrayList<Integer>> edges = new HashMap<Integer, ArrayList<Integer>>(E);
		int[] from = new int[E];
		int[] to = new int[E];

		int change = 0;
		int j = 0, k = 0;

		for (int i = 0; i < E; i++) {					// store the vertices
			from[j] = input.nextInt();
			j++;
			to[k] = input.nextInt();
			k++;
		}

		for (int i = 0; i < E; i++) {
			int v2 = to[i];
			int v1 = from[i];

			if (edges.get(v2) == null) {				// loop to add the first neighbour
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(v1);
				edges.put(v2, temp);
			} else if (edges.get(v2) != null) {			// loop to add more neighbours
				ArrayList<Integer> temp = new ArrayList<>();
				temp = edges.get(v2);
				temp.add(v1);
				edges.put(v2, temp);
			}
		}

		int[] count = new int[V];
		for (int i = 0; i < to.length; i++) {			// store the count of the incoming edges for all the vertices
			count[to[i]]++;
		}

		int done = 0;
		Queue<Integer> X = new LinkedList<Integer>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] == 0) {						// check of the vertex has zero incoming edges
				X.add(i);
				done++;
			}
		}

		int removeVertex;
		ArrayList<Integer> order = new ArrayList<Integer>();

		while (X.peek() != null) {						// loop for all the vertices with no incoming edges
			removeVertex = X.poll();								// get the vertex with zero incoming edges
			order.add(removeVertex);								// keep the order of the vertices removed
			for (int e : edges.keySet()) {				// loop to update the count of the incoming edges when one vertex "removeVertex" is removed
				ArrayList<Integer> temp = new ArrayList<>();
				temp = edges.get(e);
				if (temp.contains(removeVertex)) {		
					count[e]--;
					if (count[e] == 0) {				// add the vertex to the list if it's incomining edges count becomes zero
						X.add(e);
					}
				}
			}
			X.remove(removeVertex);					
		}

		int[] S = new int[V];
		int max = 0;

		for (int v : order) {							// loop for vertices in the topological order
			if (edges.get(v) == null) {					// vertex with no incoming edges 
				S[v] = 0;
			} else {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp = edges.get(v);
				for (int x : temp) {					// loop for vertices from which edges are incoming to vertex "v"
					if (S[v] < S[x] + 1) {				// check for the longest possible path to "v" from all possible vertices having edge to "v"
						S[v] = S[x] + 1;
						if (max < S[v]) {				// update the maximum value found till now in S[]
							max = S[v];
						}
					}
				}
			}
		}

		System.out.println(max);						// display the answer 
	}
}
