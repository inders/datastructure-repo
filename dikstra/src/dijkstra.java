import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class dijkstra {

	class Vertex implements Comparable {
		int distance;
		String name;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
		public Vertex getPredecessor() {
			return predecessor;
		}
		public void setPredecessor(Vertex predecessor) {
			this.predecessor = predecessor;
		}
		Vertex predecessor;
		
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Vertex v = (Vertex)o;
			if (this.distance == v.getDistance())
				return 0;
			else if(this.distance > v.getDistance())
				return 1;
			else 
				return -1;
		}
		
		
	}

	class Edge {
		Vertex toVertex;
		int weight;
		public Vertex getToVertex() {
			return toVertex;
		}
		public void setToVertex(Vertex toVertex) {
			this.toVertex = toVertex;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}

	}


	
	public void getSingleSourceShortestPath(Vertex s, Map g) {
		PriorityQueue<Vertex> minHeap = new PriorityQueue<Vertex>();
		//Iterate through the graph vertex nodes and create a min heap based on distance from s
		Iterator<Vertex> it =  g.keySet().iterator();
		while (it.hasNext()) {
			Vertex v = it.next();
			minHeap.add(v);
		}
		// As long as there are elements in the min heap do the following
		while (minHeap.size() > 0) {
			//get the node minimum distance from s
			//first iteration would be s itself
			//This operation will repeat V times and each time the complexity is (logV)
			//so this operation will overall add V (log V) to overall algo complexity
			Vertex v = minHeap.remove();
			//visit all neighbours of v to see if there is a path already and if this path is better
			List<Edge> edgeList = (List<Edge>) g.get(v);
			for (Edge edge:edgeList) {
				Vertex v1 = edge.getToVertex();
				//new distance to reach v1 from s
				int newDistance = edge.getWeight() + v.getDistance();
				if( newDistance < v1.getDistance()) {
					v1.setDistance(newDistance);
					v1.setPredecessor(v);
					//since we have changed the distance of v1 we need to remove and reinsert it to min heap
					// as minheap isn't self adjusting
					//The following opearation will take 2(log V) and needs to done based on degree(V) = E
					//in the worst case if the graph is fully connected so this opeation contributes overall
					// E log(V) to the overall time of this algorithm
					minHeap.remove(v1);
					minHeap.add(v1);
				}
			}
		}
		
	}

	

	public static void main(String[] args) {
		Map<Vertex, List<Edge>> graph = new HashMap<Vertex, List<Edge>>();

		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();
		Vertex d = new Vertex();
		Vertex e = new Vertex();
		Vertex f = new Vertex();
		Vertex g = new Vertex();
		Vertex h = new Vertex();
		Vertex i = new Vertex();
		Vertex j = new Vertex();
		Vertex k = new Vertex();
		Vertex l = new Vertex();

		
		Edge e1 = new Edge();
		a.setDistance(Integer.MAX_VALUE);
		a.setName("a");
		e1.setWeight(1);
		e1.setToVertex(b);
		Edge e2 = new Edge();
		e2.setToVertex(e);
		e2.setWeight(1);
	    List<Edge> l1 = new ArrayList<Edge>();
	    l1.add(e1); l1.add(e2);
	    graph.put(a, l1);
		
		b.setDistance(Integer.MAX_VALUE);
		b.setName("b");
		Edge e3 = new Edge(); Edge e4 = new Edge(); Edge e5 = new Edge();
		e3.setToVertex(a); e3.setWeight(1);
		e4.setToVertex(c); e4.setWeight(1);
		e5.setToVertex(f); e5.setWeight(1);
		List<Edge> l2 = new ArrayList<Edge>();
		l2.add(e3); l2.add(e4); l2.add(e5);
		graph.put(b, l2);
		
		c.setDistance(Integer.MAX_VALUE);
		c.setName("c");
		Edge e6 = new Edge(); Edge e7 = new Edge(); Edge e8 = new Edge();
		e6.setToVertex(b); e6.setWeight(1);
		e7.setToVertex(g); e7.setWeight(1);
		e8.setToVertex(d); e8.setWeight(1);
		List<Edge> l3 = new ArrayList<Edge>();
		l3.add(e6); l3.add(e7); l3.add(e8);
		graph.put(c, l3);
		
		d.setDistance(Integer.MAX_VALUE);
		d.setName("d");
		Edge e9 = new Edge(); Edge e10 = new Edge(); 
		e9.setToVertex(c); e9.setWeight(1);
		e10.setToVertex(h); e10.setWeight(1);
		List<Edge> l4 = new ArrayList<Edge>();
		l4.add(e9); l4.add(e10); 
		graph.put(d, l4);
		
		e.setDistance(Integer.MAX_VALUE);
		e.setName("e");
		Edge e11 = new Edge(); Edge e12 = new Edge(); Edge e40 = new Edge();
		e11.setToVertex(a); e12.setWeight(1);
		e11.setToVertex(i); e12.setWeight(1);
		e40.setToVertex(f); e40.setWeight(1);
		List<Edge> l5 = new ArrayList<Edge>();
		l5.add(e12); l5.add(e11); l5.add(e40);
		graph.put(e, l5);
		
		f.setDistance(0);
		f.setName("f");
		Edge e16 = new Edge(); Edge e17 = new Edge(); Edge e18 = new Edge();Edge e19 = new Edge();
		e16.setToVertex(b); e16.setWeight(1);
		e17.setToVertex(e); e17.setWeight(1);
		e18.setToVertex(j); e18.setWeight(1);
		e19.setToVertex(g); e19.setWeight(1);
		List<Edge> l6 = new ArrayList<Edge>();
		l6.add(e16); l6.add(e17); l6.add(e18); l6.add(e19);
		graph.put(f, l3);
		
		g.setDistance(Integer.MAX_VALUE);
		g.setName("g");
		Edge e20 = new Edge(); Edge e21 = new Edge(); Edge e22 = new Edge();Edge e23 = new Edge();
		e20.setToVertex(c); e20.setWeight(1);
		e21.setToVertex(f); e21.setWeight(1);
		e22.setToVertex(h); e22.setWeight(1);
		e23.setToVertex(k); e23.setWeight(1);
		List<Edge> l7 = new ArrayList<Edge>();
		l7.add(e20); l7.add(e21); l7.add(e22); l7.add(e23);
		graph.put(g, l7);
		
		h.setDistance(Integer.MAX_VALUE);
		h.setName("h");
		Edge e24 = new Edge(); Edge e25 = new Edge(); Edge e26 = new Edge();
		e24.setToVertex(d); e24.setWeight(1);
		e25.setToVertex(g); e25.setWeight(1);
		e26.setToVertex(l); e26.setWeight(1);
		List<Edge> l8 = new ArrayList<Edge>();
		l8.add(e24); l8.add(e25); l8.add(e26); 
		graph.put(h, l8);
		
		i.setDistance(Integer.MAX_VALUE);
		i.setName("i");
		Edge e27 = new Edge(); Edge e28 = new Edge(); 
		e27.setToVertex(e); e27.setWeight(1);
		e28.setToVertex(j); e28.setWeight(1);
		List<Edge> l9 = new ArrayList<Edge>();
		l9.add(e27); l9.add(e28);  
		graph.put(i, l9);
		
		j.setDistance(Integer.MAX_VALUE);
		j.setName("j");
		Edge e29 = new Edge(); Edge e30= new Edge(); Edge e31 = new Edge();
		e29.setToVertex(f); e29.setWeight(1);
		e30.setToVertex(i); e30.setWeight(1);
		e31.setToVertex(k); e31.setWeight(1);
		List<Edge> l10 = new ArrayList<Edge>();
		l10.add(e29); l10.add(e30);  l10.add(e31);
		graph.put(j, l10);
		
		k.setDistance(Integer.MAX_VALUE);
		k.setName("k");
		Edge e32 = new Edge(); Edge e33= new Edge(); Edge e34 = new Edge();
		e32.setToVertex(g); e32.setWeight(1);
		e33.setToVertex(j); e33.setWeight(1);
		e34.setToVertex(l); e34.setWeight(1);
		List<Edge> l11 = new ArrayList<Edge>();
		l11.add(e32); l11.add(e33);  l11.add(e34);
		graph.put(k, l11);
		
		l.setDistance(Integer.MAX_VALUE);
		l.setName("l");
		Edge e35 = new Edge(); Edge e36= new Edge(); 
		e35.setToVertex(h); e35.setWeight(1);
		e36.setToVertex(k); e36.setWeight(1);
		List<Edge> l12 = new ArrayList<Edge>();
		l12.add(e35); l12.add(e36);  
		graph.put(l, l12);
		
		dijkstra dij = new dijkstra();
		dij.getSingleSourceShortestPath(f, graph);
		// Get node h
    	Vertex v = h.getPredecessor();

	    while (v != null) {
	    	System.out.println("Shortest path starts from h..");
	    	System.out.println("Shortest path = " + v.getPredecessor().getName());
	    	v = v.getPredecessor();
	    }
		
	}

}
