package demo.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RailServiceImpl implements RailService {

	private NodeManager nodeManager;

	public RailServiceImpl() {
		this.nodeManager = new NodeManager();
	}

	public void addPath(String... paths) {
		// check empty & well formed
		Node from, to;
		int distance;

		for (String path : paths) {
			from = nodeManager.getFrom(path);
			to = nodeManager.getTo(path);
			distance = Integer.parseInt(String.valueOf(path.charAt(2)));

			from.addWayOut(to, distance);

		}

	}

	public String getAllPaths() {
		List<Node> nodes = nodeManager.getNodes();
		StringBuilder sb = new StringBuilder();
		String wayOuts;
		for (Node node : nodes) {
			wayOuts = node.toString();
			if (wayOuts.length() > 1)
				sb.append(wayOuts).append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public Integer calculateDistance(String itinerary) {
		char[] nodes = itinerary.toCharArray();
		Node node = nodeManager.getNode(String.valueOf(nodes[0]));
		Integer distance, total = 0;
		for (int i = 1; i < nodes.length && node !=null; i++) {
			distance = node.getDistance(nodeManager.getNode(String.valueOf(nodes[i])));
			if (distance == null) {
				return null;
			}
			total += distance;
			node=nodeManager.getNode(String.valueOf(nodes[i]));

		}
		return total;
	}
	
	public Way searchShortest(final String from, final String to) {
		List<Way> ways = search(from, to, new Condition<Way>() {

			public boolean needed(Way way) {
				return way.getLoops()<2;
			}

			public boolean apply(Way way) {
				return way.getPath().endsWith(to);
			}
		});

		return Collections.min(ways);
	}
	
	public List<Way> search(String from, String to, Condition<Way> condition){
		
		List<Way> ways = new ArrayList<Way>();
		lookupForWays(from, null, ways, condition);
		recursiveLookup(ways, condition);
		
		List<Way> results = new ArrayList<Way>();
		for (Way way : ways) {
			if(condition.apply(way))
				results.add(way);
		}
		
		return results;
	}

	private void lookupForWays(String from, Way way, List<Way> ways, Condition<Way> condition) {
		Node node = nodeManager.getNode(from);
		for (Node next : node.getNodes()) {
			Way w = (way == null) ? new Way(node, next, node.getDistance(next))
					: way.clone().addPath(next, node.getDistance(next));
			if (condition.needed(w)) {
				ways.add(w);
			}
		}
	}
	
	private void recursiveLookup(List<Way> ways, Condition<Way> condition){
		List<Way> copy = new ArrayList<Way>(ways);
		for (Way way : copy) {
			if(!way.isCloned())
				lookupForWays(way.getLast(), way, ways, condition);
		}
		if(ways.size()>copy.size()){
			recursiveLookup(ways, condition);
		}
	}

}
