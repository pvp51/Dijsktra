package assignment3;

public class Edge {

    private Vertex v1, v2;
    private int weight;
    
    public Edge(Vertex v1, Vertex v2, int weight){
        this.v1 = (v1.getName().compareTo(v2.getName()) <= 0) ? v1 : v2;
        this.v2 = (this.v1 == v1) ? v2 : v1;
        this.weight = weight;
    }
    
    public Vertex getNeighbor(Vertex current){
        if(!(current.equals(v1) || current.equals(v2))){
            return null;
        }
        
        return (current.equals(v1)) ? v2 : v1;
    }
    
    public Vertex getV1(){
        return this.v1;
    }
    
    public Vertex getV2(){
        return this.v2;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public String toString(){
        return "{(" + v1 + ", " + v2 + "), " + weight + "}";
    }
      
}
