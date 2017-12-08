/**Code used from http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 * and http://www.sanfoundry.com/java-program-implement-bellmanford-algorithm/
 * 
 */

	import java.util.Random;
    import java.util.Scanner;

    public class BellmanFord
    {
        private int distances[];
        private int numberofvertices;

        public static final int MAX_VALUE = 999;

        public BellmanFord(int numberofvertices)
        {
            this.numberofvertices = numberofvertices;
            distances = new int[numberofvertices + 1];
        }

        public void BellmanFordEvaluation(int source, int adjacencymatrix[][])
        {
            for (int node = 1; node <= numberofvertices; node++)
            {
                distances[node] = MAX_VALUE;
            }
            
            distances[source] = 0;
            
            for (int node = 1; node <= numberofvertices - 1; node++)
            {
                for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
                {
                    for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
                    {
                        if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE)
                        {
                            if (distances[destinationnode] > distances[sourcenode] 

                                    + adjacencymatrix[sourcenode][destinationnode])

                                	distances[destinationnode] = distances[sourcenode]

                                    + adjacencymatrix[sourcenode][destinationnode];

                        }
                    }
                }
            }

            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
            {
                for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
                {
                	if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE)
                    {
                        if (distances[destinationnode] > distances[sourcenode]

                               + adjacencymatrix[sourcenode][destinationnode])

                            System.out.println("The Graph contains negative egde cycle");
                    }
                }
            }

            for (int vertex = 1; vertex <= numberofvertices; vertex++)
            {
                System.out.println("distance of source  " + source + " to "

                          + vertex + " is " + distances[vertex]);
            }
        }

        public static void main(String... arg)
        {
            int numberofvertices = 0;
        	Random rn = new Random();
        	
            int source;
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the number of vertices");
            numberofvertices = scanner.nextInt();

            int adjacencymatrix[][] = new int[numberofvertices + 1][numberofvertices + 1];

            System.out.println("Enter the adjacency matrix");

            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
            {
                for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
                {
                    adjacencymatrix[sourcenode][destinationnode] = scanner.nextInt();
                    if (sourcenode == destinationnode)
                    {
                        adjacencymatrix[sourcenode][destinationnode] = 0;
                        //continue;
                    }
                    if (adjacencymatrix[sourcenode][destinationnode] == 0)
                    {
                        adjacencymatrix[sourcenode][destinationnode] = MAX_VALUE;
                    }
                }
            }

            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
            {
                for (int destinationnode = sourcenode+1; destinationnode <= numberofvertices; destinationnode++)
                {
                	if(adjacencymatrix[sourcenode][destinationnode]==1)
                	{
                		adjacencymatrix[sourcenode][destinationnode]=rn.nextInt(9 - 1 + 1) + 1;
                		adjacencymatrix[destinationnode][sourcenode]=adjacencymatrix[sourcenode][destinationnode];
                	}
                }
            }
            
            System.out.println("\nPrinting Adjacency matrix");
            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++)
            {
            	//System.out.println("Test");
                for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++)
                {
                	System.out.print(adjacencymatrix[sourcenode][destinationnode]+"  ");
                }
                System.out.println();
            }
            System.out.println("Enter the source vertex");
            source = scanner.nextInt();

            BellmanFord bellmanford = new BellmanFord(numberofvertices);
            long startTime = System.nanoTime();

            bellmanford.BellmanFordEvaluation(source, adjacencymatrix);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
            System.out.println("\nTime taken to execute: "+duration);

            scanner.close();	

        }
    }