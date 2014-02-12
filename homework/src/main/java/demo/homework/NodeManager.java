package demo.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodeManager {

	private Node[] nodes = new Node[100];

	public Node getFrom(String path) {
		return retrieveNodeOrCreate(String.valueOf(path.charAt(0)),true);
	}

	public Node getTo(String path) {
		return retrieveNodeOrCreate(String.valueOf(path.charAt(1)),true);
	}
	
	public Node getNode(String name) {
		return retrieveNodeOrCreate(name, false);
	}

	private Node retrieveNodeOrCreate(String name, boolean create) {
		Node node = nodes[name.hashCode()];
		if (create && node == null) {
			node = new Node(name);
			nodes[name.hashCode()] = node;
		}
		return node;
	}

	public List<Node> getNodes() {
		List<Node> list = new ArrayList<Node>(Arrays.asList(nodes));
		list.removeAll(Collections.singleton(null));
		return list;
	}

}
