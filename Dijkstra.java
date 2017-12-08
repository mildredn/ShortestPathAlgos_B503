
/*Reference - http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm*/

public class Dijkstra {
	
	/*Finds the vertex with minimum distance from the set of vertices
	  not included in the shortest path tree*/
    static final int V=9;
    int leastPath(int distance[], Boolean shorstestPathTreeSet[])
    {
        //Initialize the min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (shorstestPathTreeSet[v] == false && distance[v] <= min)
            {
                min = distance[v];
                min_index = v;
            }
 
        return min_index;
    }
    
    /*Prints the constructed distance array*/
    void printSolution(int distance[], int n)
    {
        System.out.println("Node   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i+" \t\t "+distance[i]);
    }
    
    /*This function implements Dijkstra's single source shortest path
      algorithm for a graph represented using adjacency matrix representation*/
    void dijkstra(int graph[][], int src)
    {
        int distance[] = new int[V];	 //holds shortest distance from source
 
        /* shorstestPathTreeSet[i] will be true if the vertex i is included in shortest
         path tree or shortest distance from src to i is finalized*/
        Boolean shorstestPathTreeSet[] = new Boolean[V];
 
        /*Initialize all distances as INFINITE and shorstestPathTreeSet[] as false*/
        for (int i = 0; i < V; i++)
        {
            distance[i] = Integer.MAX_VALUE;
            shorstestPathTreeSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        distance[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = leastPath(distance, shorstestPathTreeSet);
 
            // Mark the picked vertex as processed
            shorstestPathTreeSet[u] = true;
 
            // Update distance value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
                // Update distance[v] only if is not in shorstestPathTreeSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of distance[v]
                if (!shorstestPathTreeSet[v] && graph[u][v]!=0 &&
                        distance[u] != Integer.MAX_VALUE &&
                        distance[u]+graph[u][v] < distance[v])
                    distance[v] = distance[u] + graph[u][v];
        }
 
        // print the constructed distance array
        printSolution(distance, V);
    }
	
	public static void main(String[] args) {
		
		int graph[][] = new int [][] {{999, 999, 999, 7, 7, 8, 999, 999, 999, 9},
									  {999, 999, 999, 999, 8, 999, 999, 999, 999, 8},
									  {999, 999, 999, 2, 999, 999, 999, 999, 999, 9},
									  {7, 999, 2, 999, 2, 999, 999, 999, 999, 999},
									  {7, 8, 999, 2, 999, 9, 999, 999, 999, 999},
									  {8, 999, 999, 999, 9, 999, 2, 999, 999, 999},
									  {999, 999, 999, 999, 999, 2, 999, 2, 999, 999},
									  {999, 999, 999, 999, 999, 999, 2, 999, 4, 999},
									  {999, 999, 999, 999, 999, 999, 999, 4, 999, 6},
									  {9, 8, 9, 999, 999, 999, 999, 999, 6, 999}};
            				
        Dijkstra t = new Dijkstra();
        long startTime = System.nanoTime();
        t.dijkstra(graph, 0);
        long endTime = System.nanoTime();
        
        long duration = (endTime - startTime);
        System.out.println("\nDuration: "+duration);

	}

}
