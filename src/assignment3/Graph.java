package assignment3;

import java.util.*;

public class Graph {

	private HashMap<String, Vertex> vertices;
	private HashMap<Integer, Edge> edges;

	public Graph(){
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
	}

	public Graph(ArrayList<Vertex> vertices){
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();

		for(Vertex v: vertices){
			this.vertices.put(v.getName(), v);
		}   
	}

	public boolean addEdge(String oneLabel, String twoLabel, int weight){
		Vertex one = null;
		Vertex first = this.vertices.get(oneLabel);
		if(first==null){
			one = new Vertex(oneLabel);
			addVertex(one);
		}
		else{
			one = first;
		}
		Vertex second = this.vertices.get(twoLabel);
		Vertex two = null;
		if(second==null){
			two = new Vertex(twoLabel);
			addVertex(two);
		}
		else{
			two = second;
		}
		if(addEdge(one,two, weight))
			return true;
		else
			return false;

	}

	public boolean addEdge(Vertex one, Vertex two, int weight){
		if(one.equals(two)){
			return false;   
		}

		//ensures the Edge is not in the Graph
		Edge e = new Edge(one, two, weight);
		if(edges.containsKey(e.hashCode())){
			return false;
		}

		//and that the Edge isn't already incident to one of the vertices
		else if(one.containsNeighbor(e) || two.containsNeighbor(e)){
			return false;
		}

		edges.put(e.hashCode(), e);
		one.addNeighbor(e);
		two.addNeighbor(e);
		return true;
	}


	public boolean addEdge(Edge e){
		if(e.getOne().equals(e.getTwo())){
			return false;   
		}

		//ensures the Edge is not in the Graph
		if(edges.containsKey(e.hashCode())){
			return false;
		}

		//and that the Edge isn't already incident to one of the vertices
		else if(e.getOne().containsNeighbor(e) || e.getTwo().containsNeighbor(e)){
			return false;
		}

		edges.put(e.hashCode(), e);
		e.getOne().addNeighbor(e);
		e.getTwo().addNeighbor(e);
		return true;
	}

	public boolean containsVertex(Vertex vertex){
		return this.vertices.get(vertex.getName()) != null;
	}

	public Vertex getVertex(String label){
		return vertices.get(label);
	}

	public boolean addVertex(Vertex vertex){
		
		vertices.put(vertex.getName(), vertex);
		return true;
	}

	public Set<String> vertexKeys(){
		return this.vertices.keySet();
	}

	public Set<Edge> getEdges(){
		return new HashSet<Edge>(this.edges.values());
	}

	public Set<Vertex> getVertices(){
		return new HashSet<Vertex>(this.vertices.values());
	}  
}

