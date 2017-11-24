package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijsktra {

	private static Map<Vertex, Integer> vertexDist;
	private static Set<Vertex> notSeenVertices;
	private static Set<Vertex> cloud;
	private static Map<Vertex, Vertex> path;
	private static Graph g;

	public static void main(String[] args) {
		g = new Graph();
		Vertex vLAX = new Vertex("LAX");
		Vertex vDFW = new Vertex("DFW");
		Vertex vSFO = new Vertex("SFO");
		Vertex vMIA = new Vertex("MIA");
		Edge e1 = new Edge(vLAX,vDFW,1235);
		Edge e2 = new Edge(vSFO,vDFW,1464);
		Edge e3 = new Edge(vLAX,vSFO,337);
		Edge e4 = new Edge(vLAX,vMIA,2342);
		Edge e5 = new Edge(vDFW,vMIA,1121);

		g.addVertex(vLAX);
		g.addVertex(vDFW);
		g.addVertex(vSFO);
		g.addVertex(vMIA);
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);

		System.out.println(g.getEdges());
		System.out.println(g.getVertices());

		String source = "LAX";
		String destination = "MIA";

		dijsktra(g, g.getVertex(source));
		System.out.println(getPath(g.getVertex(destination)));

	}
	
	private static void dijsktra(Graph g, Vertex v) {
		vertexDist = new HashMap<>();
		notSeenVertices = new HashSet<>();
		cloud = new HashSet<>();
		path = new HashMap<>();
		vertexDist.put(v, 0);
		notSeenVertices.add(v);

		while(!notSeenVertices.isEmpty()){
			Vertex u = getMinimum(notSeenVertices);
			cloud.add(u);
			notSeenVertices.remove(u);
			calculateShortestDistance(u);
		}

		System.out.println(vertexDist);

	}
	
	public static List<Vertex> getPath(Vertex target) {
        List<Vertex> tempPath = new ArrayList<>();
        Vertex step = target;
        // check if a path exists
        if (path.get(step) == null) {
            return null;
        }
        tempPath.add(step);
        while (path.get(step) != null) {
            step = path.get(step);
            tempPath.add(step);
        }
        Collections.reverse(tempPath);
        return tempPath;
    }

	private static Vertex getMinimum(Set<Vertex> notSeenVertices) {
		Vertex closestVertex = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Vertex v: notSeenVertices) {
			int vertexDistance = vertexDist.get(v);
			if (vertexDistance < lowestDistance) {
				lowestDistance = vertexDistance;
				closestVertex = v;
			}
		}
		return closestVertex;
	}

	private static int getDistance(Vertex src, Vertex dest) {
		for (Edge edge : g.getEdges()) {
			if (edge.getOne().equals(src) && edge.getTwo().equals(dest)) {
				return edge.getWeight();
			}
			else if(edge.getOne().equals(dest) && edge.getTwo().equals(src)) {
				return edge.getWeight();
			}
		}
		return Integer.MAX_VALUE;
	}

	private static int getShortestDistance(Vertex dest) {
		Integer d = vertexDist.get(dest);
		return d == null ? Integer.MAX_VALUE : d;
	}

	private static void calculateShortestDistance(Vertex u) {
		List<Vertex> z = new ArrayList<>();

		for(Edge e: u.getNeighbors()){
			if(e.getOne()!= u && !cloud.contains(e.getOne())){
				z.add(e.getOne());
			}
			else if(!cloud.contains(e.getTwo())){
				z.add(e.getTwo());
			}
		}

		for (Vertex target : z) {
			if (getShortestDistance(u) + getDistance(u, target) < getShortestDistance(target)) {
				vertexDist.put(target, getShortestDistance(u) + getDistance(u, target));
				path.put(target, u);
				notSeenVertices.add(target);
			}
		}
	}

}



