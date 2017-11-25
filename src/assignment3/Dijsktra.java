package assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Dijsktra {

	private static Map<Vertex, Integer> vertexDist;
	private static Set<Vertex> notSeenVertices;
	private static Set<Vertex> cloud;
	private static Map<Vertex, Vertex> path;
	private static Graph g;
	private static String source = "LAX";
	private static String destination = "MIA";

	public static void main(String[] args) throws IOException {
		String fileName="";
		System.out.println("Please enter the input file name: ");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);		
		fileName = scan.nextLine();
		
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		String[] array = null;
		g = new Graph();
		System.out.println("Building graph now");
		while((line = in.readLine()) != null)
		{
			System.out.println(line);
			array = line.split("\t");
			g.addEdge(array[0], array[1], Integer.parseInt(array[2]));
		}
		in.close();
		System.out.println("Graph buidling Complete");
		
		System.out.println("Please enter the source vertex: ");		
		source = scan.nextLine();
		System.out.println("Please enter the destination vertex: ");		
		destination = scan.nextLine();

		System.out.println(g.getEdges());
		System.out.println(g.getVertices());
		System.out.println("Calculating Shortest Distance now");
		System.out.println("Shortest Distance: "+dijsktra(g, g.getVertex(source)));
		System.out.println("Calculating Shortest Path");
		System.out.println("Shortest Path: "+getPath(g.getVertex(destination)));

	}
	
	private static Integer dijsktra(Graph g, Vertex v) {
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
		return vertexDist.get(g.getVertex(destination));

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

	private static int getMinDistance(Vertex dest) { 
		return vertexDist.get(dest) == null ? Integer.MAX_VALUE : vertexDist.get(dest);
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
			if (getMinDistance(u) + getDistance(u, target) < getMinDistance(target)) {
				vertexDist.put(target, getMinDistance(u) + getDistance(u, target));
				path.put(target, u);
				notSeenVertices.add(target);
			}
		}
	}

}



