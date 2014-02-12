package demo.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Node {

	private String name;
	private Map<Node,Integer> wayOut;

	public Node(String name) {
		this.name = name;
		this.wayOut = new HashMap<Node, Integer>();
	}
	
	public Integer getDistance(Node to) {
		return this.wayOut.get(to);
	}

	public void addWayOut(Node node, int distance) {
		this.wayOut.put(node, distance);
	}

	public Set<Node> getNodes() {
		return wayOut.keySet();
	}

	public String getCities() {
		if(wayOut.isEmpty())
			return null;
		StringBuilder sb = new StringBuilder();
		for (Node node : wayOut.keySet()) {
			sb.append(node.getName());
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}
	
//	@Override
//	public boolean equals(Object other) {
//		return this.hashCode()==other.hashCode();
//	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		if(wayOut.isEmpty())
			return name;
		StringBuilder sb = new StringBuilder();
		for (Entry<Node, Integer> entry : wayOut.entrySet()) {
			sb.append(name).append(entry.getKey().getName()).append(entry.getValue()).append(",");
		}
		return sb.substring(0, sb.length()-1);
	}

}
