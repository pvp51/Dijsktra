package assignment3;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private List<Edge> neighborhood;
    private String label;
    
    /**
     * 
     * @param label The unique label associated with this Vertex
     */
    public Vertex(String label){
        this.label = label;
        this.neighborhood = new ArrayList<>();
    }
    
    
    /**
     * This method adds an Edge to the incidence neighborhood of this graph iff
     * the edge is not already present. 
     * 
     * @param edge The edge to add
     */
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
    
    public String getLabel(){
        return this.label;
    }
    
    public List<Edge> getNeighbors(){
        return new ArrayList<>(this.neighborhood);
    }
    
    public String toString(){
        return "Vertex " + label;
    }
    
}
