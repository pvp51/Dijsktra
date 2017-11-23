package assignment3;

import java.util.HashSet;
import java.util.Set;

public class Dijsktra {

	public static void main(String[] args) {
		Graph g = new Graph();
		Vertex vLAX = new Vertex("LAX");
		Vertex vDFW = new Vertex("DFW");
		Vertex vSFO = new Vertex("SFO");
		Edge e1 = new Edge(vLAX,vDFW,1464);
		Edge e2 = new Edge(vSFO,vDFW,1235);
		Edge e3 = new Edge(vLAX,vSFO,337);
		
		
		g.addVertex(vLAX, true);
		g.addVertex(vDFW, true);
		g.addVertex(vSFO, true);
		/*g.addEdge(vLAX,vDFW,1464);
		g.addEdge(vSFO,vDFW,1235);
		g.addEdge(vLAX,vSFO,337);*/
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		
		System.out.println(g.getEdges());
		System.out.println(g.getVertex("DFW"));
		System.out.println(g.getVertex("DFW").getNeighbors());
		System.out.println(g.getVertices());
		
		String source = "LAX";
		String destination = "DFW";
		
		dijsktra(g, vLAX);
		
	}

	private static void dijsktra(Graph g, Vertex vLAX) {
		//Label label = new Label();
		
		Set set = new HashSet<>();
		set.add(new Label(vLAX, 0));
		
		for(Vertex v: g.getVertices()){
			if(vLAX != v){
				System.out.println("1. "+v);
				set.add(new Label(v, 0));
				while(!set.isEmpty()){
					Label l = (Label) set.iterator().next();
					Vertex u = l.getVu();
					System.out.println("2. "+set.size());
					set.remove(l);
					System.out.println("3. "+u);
					System.out.println("4. "+set.size());
					for(Edge e: u.getNeighbors()){
						Vertex z;
						if(e.getOne()!= u)
							z=e.getOne();
						else
							z=e.getTwo();
						int wuz = e.getWeight();
						
						System.out.println("5. "+z);
					}
				}
			}
		}
		
	}

}
