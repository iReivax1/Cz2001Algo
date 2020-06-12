package graphBFS;
import java.util.*; 

public class graph {

	private int V; //number of vertices in the graph	
	private LinkedList<Node> adj[]; //Adjacency Lists

	public graph(int V) { //constructor for graph
		this.V = V;
		adj = new LinkedList[V];  //create V number of linked list objects 
		for (int i = 0; i < V; ++i) {
			adj[i] = new LinkedList<Node>(); // init each linked list
		}
	}

	//Add an edge into the graph
	public void addEdge(int v, int w) {
		adj[v].add(new Node(w));
		adj[w].add(new Node(v));
	}

	public void addEdge(int v, String from, int w, String to) {
		adj[v].add(new Node(w,to));
		adj[w].add(new Node(v,from));
	}

	//BFS traversal starting from source
	public void BFS(Node source, Node destination) {

		//Mark all the vertices as not visited (false)
		boolean visited[] = new boolean[V];

		//Create a queue for BFS
		LinkedList<Node> queue = new LinkedList<Node>();

		//Mark the current node as visited and enqueue it
		visited[source.key] = true;
		queue.add(source);
		
		//while queue is not empty
		while (queue.size() != 0) {

			// Dequeue a vertex from queue
			source = queue.poll();

			// Get all adjacent vertices of the dequeued vertex source
			Iterator<Node> iterator = adj[source.key].listIterator();

			// If an adjacent vertex has not been visited, then mark it visited and enqueue it
			while (iterator.hasNext()) {
				Node n = iterator.next();

				if (!visited[n.key]) {
					visited[n.key] = true;
					queue.add(n);
					n.parent=source;
					
					if (n.key == destination.key) {
						printShortestPath(n);
						return;
					}
				}
			}
		}
	}	
	private void printShortestPath(Node destination) { // print the nodes need to traverse
		ArrayList<String> locationList = new ArrayList<String>();
		Node n = destination;
			
		while (n!=null) {
			locationList.add(n.location);
			n = n.parent;
		}
			
		for (int i = locationList.size(); i > 0 ; i--) {
			if (locationList.get(i-1) != "")  // Only print Nodes with a known location
			System.out.println(locationList.get(i-1));
		}
	}
}