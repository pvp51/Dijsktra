package assignment3;

public class Edge {

    private Vertex one, two;
    private int weight;
    
    public Edge(Vertex one, Vertex two, int weight){
        this.one = (one.getLabel().compareTo(two.getLabel()) <= 0) ? one : two;
        this.two = (this.one == one) ? two : one;
        this.weight = weight;
    }
    
    public Vertex getNeighbor(Vertex current){
        if(!(current.equals(one) || current.equals(two))){
            return null;
        }
        
        return (current.equals(one)) ? two : one;
    }
    
    public Vertex getOne(){
        return this.one;
    }
    
    public Vertex getTwo(){
        return this.two;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public String toString(){
        return "({" + one + ", " + two + "}, " + weight + ")";
    }
      
}
