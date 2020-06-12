package graphBFS;

public class Node {
	
    int key; //corresponding key for adj list
    Node parent; //parent node
    String location; //location name
    int x,y;
    public Node(int item)
    {
        key = item;
        parent = null;
        location = "";
    }
    public Node (int item, String loc) {
	    	key = item;
	    	parent = null;
	    	location = loc;
    	
    }

}
