import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class CountUseless {

	public static float MAX = 999999;
	static int V, E;
	static Set<Edge>[][] usedE = null ; 
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int TotalCount = 0;

		V = input.nextInt();				 // store total number of vertices
		E = input.nextInt(); 				 // store total number of edges

		ArrayList<GNode> adj_list = new ArrayList<>(V);

		for (int i = 0; i < V; i++) {
			adj_list.add(i, null);
		}

		for (int i = 0; i < E; i++) { 		// loop for storing the given graph
			int vertex1 = input.nextInt();
			int vertex2 = input.nextInt();
			float cost = input.nextFloat();

			if (adj_list.get(vertex1) == null) {
				GNode node = new GNode(vertex2, cost, null);  // create a node and add that in the adjacency list
				adj_list.add(vertex1, node);
			} else {
				GNode last = getLast(vertex1, adj_list);
				GNode node = new GNode(vertex2, cost, null);  // create a node and add that in the adjacency list
				last.next = node;
			}

		}

		float[][][] S = new float[V][V][V+1];				  // 3D array 
		usedE = new HashSet[V][V];							  // 2D array to store used edges

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
                
				if(i==j){									  // base case
					S[i][j][0] = 0;
				}else {
					float c = getCost(i, adj_list, j);        
					if (c != MAX) {							  // check if an edge exist
						S[i][j][0] = c;						  // store cost of the edge
						Edge e = new Edge(i, j);
						Set<Edge> tempSet = new HashSet<>();
						tempSet.add(e);
						usedE[i][j] = tempSet;
					} else {
						S[i][j][0] = MAX;					  // if no edge , store ad infinite distance
					}
				}
			}
		}
		
		// apply floyd warshall
		for(int k =1; k<= V ;k++){
			for(int i = 0; i < V; i++){
				for(int j = 0; j < V; j++){
					float a = S[i][j][k-1];
					float b = S[i][k-1][k-1] + S[k-1][j][k-1];
					float x = Math.min(a, b);
					if(a > b){									// update 3D array if shorter path exist
						S[i][j][k] = b;
						Set<Edge> tempSet = new HashSet<>();
						tempSet.addAll(usedE[i][k-1]);			// store the new used edges
						tempSet.addAll(usedE[k-1][j]);			// store the new used edges
						usedE[i][j] = tempSet; 
					}
						S[i][j][k] = x;					
				}
			}
		}
		
		TotalCount = countUsed(usedE,adj_list);					// check for all the used edges and find a count
		int answer = E - TotalCount;							// final answer
		input.close();
		System.out.println(answer);
	}

/**
	 * 
	 * This method checks for all the used edges and finds a count
	 * 
	 * @param usedE
	 * @param adj_list
	 * @return count
*/

	private static int countUsed(Set<Edge>[][] usedE, ArrayList<GNode> adj_list) {
		for(int i =0; i<V ; i++){
			for(int j =0; j<V ; j++){
				Set<Edge> temp = usedE[i][j];
				if(temp != null){
					for(Edge edge : temp){
						int ver1 = edge.vertex1;
						int ver2 = edge.vertex2;
						GNode tempGnode = adj_list.get(ver1);
						while(tempGnode != null){
							if(tempGnode.vertex2 == ver2){
								tempGnode.used = true;        // mark edges in the adjecency list as true if used
								break;
							}
							tempGnode = tempGnode.next;
						}
					}
				}
			}
		}
		
		int cnt = 0;
		for(int i =0 ;i <V ; i++){							// count all the edges which are marked as true
			GNode temp3 = adj_list.get(i);
			while(temp3 != null){
				if(temp3.used == true){
					cnt++;
				}
				temp3 = temp3.next;
			}
			
		}
		return cnt;
	}

/**
 * 
 * This method returnd the cost of the edge
 * 
 * @param i
 * @param adj_list
 * @param j
 * @return cost
 */

	private static float getCost(int i, ArrayList<GNode> adj_list, int j) {

		GNode temp = adj_list.get(i);
		
		while ( temp != null ) {
			if(temp.vertex2 == j){
				return temp.cost;
			}
			temp = temp.next;
		}
			return MAX;
		
	}

/**
	 * This method returns the last node in the adjacency list for vertex1
	 * 
	 * @param vertex1
	 * @param adj_list
	 * @return
 */
	private static GNode getLast(int vertex1, ArrayList<GNode> adj_list) {

		GNode temp = adj_list.get(vertex1);
		while (temp.next != null) {
			temp = temp.next;
		}
		return temp;
	}
}

/**
 * 
 * 
 *  This class creates a node for the adjacency list
 *
 */
class GNode {

	int vertex2;
	float cost;
	GNode next;
	boolean used = false;

	public GNode(int vertex2, float cost, GNode next) {

		this.vertex2 = vertex2;
		this.cost = cost;
		this.next = next;
		this.used = false;

	}
}
/**
 * 
 * This class maintains edges
 * 
 */
class Edge{
	
	int vertex1;
	int vertex2;
	public Edge(int vertex1,int vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		
	}
}
