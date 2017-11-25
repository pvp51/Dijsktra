package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private List<Edge> neighbors;
    private String name;
    
    public Vertex(String label){
        this.name = label;
        this.neighbors = new ArrayList<>();
    }
    
    public void addNeighbor(Edge edge){
        if(this.neighbors.contains(edge)){
            return;
        }
        
        this.neighbors.add(edge);
    }
    
    public boolean containsNeighbor(Edge other){
        return this.neighbors.contains(other);
    }
    
    public String getName(){
        return this.name;
    }
    
    public Edge getNeighbor(int index){
        return this.neighbors.get(index);
    }
    
    public List<Edge> getNeighbors(){
        return new ArrayList<>(this.neighbors);
    }
    
    public String toString(){
        return "Vertex " + name;
    }
    
}
