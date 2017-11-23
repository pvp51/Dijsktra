package assignment3;

public class Label {
	private Vertex u;
	private int distance;
	
	
	public Label(Vertex u, int distance) {
		super();
		this.u = u;
		this.distance = distance;
	}
	public Vertex getVu() {
		return u;
	}
	public void setVu(Vertex u) {
		this.u = u;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}

}
