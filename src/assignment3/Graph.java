package assignment3;

import java.util.*;

public class Graph {

	private HashMap<String, Vertex> vertices;
	private HashMap<Integer, Edge> edges;

	public Graph(){
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
	}

	public boolean addEdge(String v1Label, String v2Label, int weight){
		Vertex v1 = null;
		Vertex first = this.vertices.get(v1Label);
		if(first==null){
			v1 = new Vertex(v1Label);
			addVertex(v1);
		}
		else{
			v1 = first;
		}
		Vertex second = this.vertices.get(v2Label);
		Vertex v2 = null;
		if(second==null){
			v2 = new Vertex(v2Label);
			addVertex(v2);
		}
		else{
			v2 = second;
		}
		if(addEdge(v1,v2, weight))
			return true;
		else
			return false;

	}

	public boolean addEdge(Vertex v1, Vertex v2, int weight){
		if(v1.equals(v2)){
			return false;   
		}
		Edge e = new Edge(v1, v2, weight);
		if(edges.containsKey(e.hashCode())){
			return false;
		}
		else if(v1.containsNeighbor(e) || v2.containsNeighbor(e)){
			return false;
		}

		edges.put(e.hashCode(), e);
		v1.addNeighbor(e);
		v2.addNeighbor(e);
		return true;
	}

	public Vertex getVertex(String label){
		return vertices.get(label);
	}

	public boolean addVertex(Vertex vertex){
		vertices.put(vertex.getName(), vertex);
		return true;
	}

	public Set<Edge> getEdges(){
		return new HashSet<Edge>(this.edges.values());
	}

	public Set<Vertex> getVertices(){
		return new HashSet<Vertex>(this.vertices.values());
	}  
}

