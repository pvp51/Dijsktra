package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private List<Edge> neighborhood;
    private String name;
    
    public Vertex(String label){
        this.name = label;
        this.neighborhood = new ArrayList<>();
    }
    
    public void addNeighbor(Edge edge){
        if(this.neighborhood.contains(edge)){
            return;
        }
        
        this.neighborhood.add(edge);
    }
    
    public boolean containsNeighbor(Edge other){
        return this.neighborhood.contains(other);
    }
  
    public int getNeighborCount(){
        return this.neighborhood.size();
    }
    
    public String getName(){
        return this.name;
    }
    
    public Edge getNeighbor(int index){
        return this.neighborhood.get(index);
    }
    
    public List<Edge> getNeighbors(){
        return new ArrayList<>(this.neighborhood);
    }
    
    public String toString(){
        return "Vertex " + name;
    }
    
}
