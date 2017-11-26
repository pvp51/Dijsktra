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
			Vertex u = null;
			int lowestDistance = Integer.MAX_VALUE;
			for (Vertex vert: notSeenVertices) {
				int vertexDistance = vertexDist.get(vert);
				if (vertexDistance < lowestDistance) {
					lowestDistance = vertexDistance;
					u = vert;
				}
			}
			
			cloud.add(u);
			notSeenVertices.remove(u);
		
			List<Vertex> z = new ArrayList<>();

			for(Edge e: u.getNeighbors()){
				if(e.getV1()!= u && !cloud.contains(e.getV1())){
					z.add(e.getV1());
				}
				else if(!cloud.contains(e.getV2())){
					z.add(e.getV2());
				}
			}

			for (Vertex dest : z) {
				int du = vertexDist.get(u) == null ? Integer.MAX_VALUE : vertexDist.get(u);
				int wuz = getDistance(u, dest);
				int dz = vertexDist.get(dest) == null ? Integer.MAX_VALUE : vertexDist.get(dest);
				if (du + wuz < dz) {
					dz = du + wuz;
					vertexDist.put(dest, dz);
					path.put(dest, u);
					notSeenVertices.add(dest);
				}
			}
		}
		return vertexDist.get(g.getVertex(destination));

	}
	
	public static List<Vertex> getPath(Vertex dest) {
        List<Vertex> tempPath = new ArrayList<>();
        Vertex via = dest;
        
        if (path.get(via) == null) {
            return null;
        }
        tempPath.add(via);
        while (path.get(via) != null) {
            via = path.get(via);
            tempPath.add(via);
        }
        Collections.reverse(tempPath);
        return tempPath;
    }

	private static int getDistance(Vertex src, Vertex dest) {
		for (Edge edge : g.getEdges()) {
			if (edge.getV1().equals(src) && edge.getV2().equals(dest)) {
				return edge.getWeight();
			}
			else if(edge.getV1().equals(dest) && edge.getV2().equals(src)) {
				return edge.getWeight();
			}
		}
		return Integer.MAX_VALUE;
	}
}



