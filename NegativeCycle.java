



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NegativeCycle{
	
	static int MAX = 999999;
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int V,E;
		
		V = input.nextInt();										// store total number of vertices
		E = input.nextInt();										// store total number of edges
		
		int first =0;
		int check = 0;
		Set<Integer> vertices = new HashSet<>();
		Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
		
		for(int i=0 ; i<E ; i++){									// loop for storing the given graph 
			int vertex1 = input.nextInt();
			int vertex2 = input.nextInt();
			int cost = input.nextInt();
			
			if(cost < 0){											// check for the negative edge
				first = vertex2;
				check = vertex1;
			}
			vertices.add(vertex1);									// add all vertices in the set
			vertices.add(vertex2);
			
			if(graph.get(vertex1) == null){							// loop to add the first neighbour
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(vertex2);
				temp.add(cost);
				graph.put(vertex1, temp);
			}
			else if(graph.get(vertex1) != null){					// loop to add more neighbours
				ArrayList<Integer> temp = new ArrayList<>();
				temp = graph.get(vertex1);
				temp.add(vertex2);
				temp.add(cost);
				graph.put(vertex1,temp);
			}
		}
		
		vertices.remove(first);										// remove first vertice from the set
		
		int[] distance = new int[V];
		int[] parent = new int[V];
		for(int i=0; i<V ; i++){									// initialise distances and parents of all the vertices
			distance[i] = MAX;
			parent[i] = -1;
		}
	
		distance[first] =0;											// set distance of the starting vertex as zero
		int smallest;

		smallest = Update(first,graph,distance,parent,vertices);	// update the distance and parent values
		
		int j =1;
		while(j<=V-1){												// loop for the remaining vertices
			j++;
			vertices.remove(smallest);								// remove the finalized vertex from the set
			if(smallest != -1){										// check if the vertex with lowest distance is not found
				smallest = Update(smallest, graph, distance, parent, vertices);	// update the distance and parent values
			}
		}		
		
		ArrayList<Integer> neighours2 = new ArrayList<>(); 
		int negDist = 0;
		neighours2 = graph.get(check);								// neighbours of the starting vertex of the negative weight edge
		for(int i=0; i<neighours2.size(); i++){						// loop to find the negative weight 
			i++;
			int dist = neighours2.get(i);
			if(dist < 0){
				negDist = dist;
			}
		}
		
		if((distance[check] + negDist) < 0){						// check if the negative weight cycle is present or not
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	/**
	 * 
	 * Function to update the distances and parents of the vertices 
	 * 
	 * @param u
	 * @param graph
	 * @param distance
	 * @param parent
	 * @param vertices
	 * @return smallest weight vertex
	 */
	private static int Update(int u, Map<Integer, ArrayList<Integer>> graph, int[] distance, int[] parent,
			Set<Integer> vertices) {
		ArrayList<Integer> neighours = new ArrayList<>(); 
		neighours = (ArrayList<Integer>) graph.get(u);
		int smallest = -1;
		
		if(neighours != null){											// check if the vertex has no neighbours
			for(int i=0; i<neighours.size(); i++){						// loop for the neighbours to update the distances and parents
				int v = neighours.get(i);
				i++;
				int uTov = neighours.get(i);
				
				if((distance[v] >   distance[u] + uTov) && vertices.contains(v)){  // update distance if smaller than the previous
					distance[v] =  distance[u] + uTov; 
					parent[v] = u;													// update the parent 
				}
			}
		}
		
		int nxtMin =MAX;
		for(int i=0; i<distance.length ; i++){							// loop to find the smallest weight vertex
			if(distance[i] < nxtMin && vertices.contains(i)){
				nxtMin = distance[i];
				smallest = i;
			}
		}
		return smallest;												// return the smallest weight vertex
	}
}