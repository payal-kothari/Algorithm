import java.awt.GradientPaint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class Spies {
	static int MAX = 999999;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int V,E,TotalUnreliable;
		
		V = input.nextInt();
		E = input.nextInt();
		
		TotalUnreliable = input.nextInt();						// store total number of unreliable vertices(spies)
		
		ArrayList<Integer> Unreliable = new ArrayList<>(); 
		for(int i =0 ; i< TotalUnreliable ; i++){				// store vertices(spies) which are unreliable 
			Unreliable.add(input.nextInt());
		}
		
		Set<Integer> Vertices = new HashSet<>();
		Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
		for(int i=0 ; i<E ; i++){								// loop for storing the given graph 
			int vertex1 = input.nextInt();
			int vertex2 = input.nextInt();
			int cost = input.nextInt();
			
			if(!Unreliable.contains(vertex1)){					// don't store in the set if the vertex is unreliable
				Vertices.add(vertex1);
			}
			if(!Unreliable.contains(vertex2)){					// don't store in the set if the vertex is unreliable
				Vertices.add(vertex2);
			}
			
			if(graph.get(vertex1) == null){						// loop to add the first neighbour
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(vertex2);
				temp.add(cost);
				graph.put(vertex1, temp);
			}
			else if(graph.get(vertex1) != null){				// loop to add more neighbours
				ArrayList<Integer> temp = new ArrayList<>();
				temp = graph.get(vertex1);
				temp.add(vertex2);
				temp.add(cost);
				graph.put(vertex1,temp);
			}
			
			if(graph.get(vertex2) == null){						// loop to add the first neighbour
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(vertex1);
				temp.add(cost);
				graph.put(vertex2, temp);
			}
			else if(graph.get(vertex2) != null){				// loop to add more neighbours
				ArrayList<Integer> temp = new ArrayList<>();
				temp = graph.get(vertex2);
				temp.add(vertex1);
				temp.add(cost);
				graph.put(vertex2,temp);
			}
		}
		
		
		int[] distance = new int[V];
		int[] parent = new int[V];
		
		for(int i=0; i<V ; i++){								// initialise distances and parents of all the vertices
			distance[i] = MAX;
			parent[i] = -1;
		}
	
		int smallest;
		int unreliableNode=0 ;
		int flag = 0;
		int j =1;
		int first =0;
		while(Unreliable.contains(first)){						// find the starting vertex(Spy) which should not be an unreliable spy
			first++;
		}
		Vertices.remove(first);									// remove the starting from the set of vertices
		distance[first] =0;										// set distance of the starting vertex as zero 
		
		smallest = Update(first,graph,distance,parent,Vertices,unreliableNode,Unreliable);  // update the distance and parent values
		if(smallest == -1 && (!Vertices.isEmpty())){			// check if the there is no smallest weight edge found
			flag = 1;
		}
		
		if(flag == 1){											// display the answer and exit
			System.out.println("NONE");
			System.exit(0);
		}
		
		
		while(j<V-1 && !Vertices.isEmpty()){					// loop for the remaining vertices
			j++;
			Vertices.remove(smallest);							// remove the smallest weight vertex found from the set
			if(!Unreliable.contains(smallest)){					// check if the smallest found is an unreliable vertex or not
				smallest = Update(smallest, graph, distance, parent, Vertices,0,Unreliable); // update the distances and parents of the smallest vertex found
				if( smallest == -1 && j<V-1 && (!Vertices.isEmpty())){   // check if no smallest vertex remaining
					flag = 1;
					break;
				}
				else if(Vertices.isEmpty()){
					flag =0;
				}
			}
		}
		
		if(flag == 1){											// display the answer (if NONE)
			System.out.println("NONE");
		}
		else {													
			int k =0,sum=0;
			while(k<distance.length){							// calculate the asnswer
				sum += distance[k];
				k++;
			}
			System.out.println(sum);
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
	 * @param Vertices
	 * @param unreliableNode
	 * @param Unreliable
	 * @return smallest weight vertex
	 */
	private static int Update(int u,Map graph,int[] distance,int[] parent,Set Vertices,int unreliableNode,ArrayList<Integer> Unreliable) {
		ArrayList<Integer> neighours = new ArrayList<>(); 
		neighours = (ArrayList<Integer>) graph.get(u);
		int min=MAX;
		int smallest = -1;
		
		for(int i=0; i<neighours.size(); i++){					// loop for the neighbours to update the distances and parents
			int v = neighours.get(i);
			i++;
			int uTov = neighours.get(i);
			
			if((distance[v] > uTov) && (Vertices.contains(v) || Unreliable.contains(v))){ // update distance if smaller than the previous
				distance[v] = uTov;
				if(min> distance[v]){
					min = distance[v];
				}
				parent[v] = u;									// update the parent 
			}
		}
		
		int nxtMin =MAX;
		
			for(int i=0; i<distance.length ; i++){				// loop to find the smallest weight vertex
				if(distance[i] < nxtMin && Vertices.contains(i)){
					nxtMin = distance[i];
					smallest = i;
				}
			}
			
		return smallest;										// return the smallest weight vertex
	}
}
