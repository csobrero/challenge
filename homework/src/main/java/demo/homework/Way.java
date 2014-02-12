package demo.homework;


public class Way implements Comparable<Way>{

	private String path;
	
	private Integer distance;

	private boolean cloned = false;

	private int loops = 0;
	
	public Way addPath(Node to, int distance){
		checkLoops();
		this.path += to.getName();
		this.distance += distance;
		return this;
	}
	
	public int stops(){
		return path.length()-1;
	}
	
	public String getLast(){
		return path.substring(path.length()-1);
	}
	
	public Way(Node from, Node to, int distance) {
		path = from.getName()+to.getName();
		this.distance = distance;
	}
	
	private Way(String path,Integer distance) {
		this.path = path;
		this.distance = distance;
	}

	public boolean isCloned() {
		return cloned;
	}

	public Way trim(String to) {
		return new Way(path.substring(0, getPath().lastIndexOf(to)+1),distance);
	}

	private void checkLoops() {
		int count = path.split(getLast(),-1).length-1; //count repeated char.
		if(count>loops)
			loops=count;
	}

	@Override
	protected Way clone() {
		this.cloned=true;
		return new Way(path,distance);
	}

	public String getPath() {
		return path;
	}

	public Integer getDistance() {
		return distance;
	}

	public int compareTo(Way other) {
		return this.distance-other.getDistance();
	}

	public int getLoops() {
		return loops;
	}
	
}
