package ga.mmbh.cfgs.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

	private String value;

	private List<Node> nodeList = new ArrayList<Node>();

	public Node(String value) {
		this.value = value;
	}

	public Node(String value, List<Node> nodeList) {
		this.value = value;
		this.nodeList = nodeList;
	}

	public Node(String value, Node... nodes) {
		this.value = value;
		nodeList.addAll(Arrays.asList(nodes));
	}

	public List<Node> getChildren() {
		return nodeList;
	}

	public boolean hasChild(Node node) {
		return !node.getChildren().isEmpty();
	}

	public void preOrder(Node node) {
		if (hasChild(node)) {
			for (Node child : node.nodeList) {
				System.out.println(child.value);
				preOrder(child);
			}
		}
	}

	public void postOrder(Node node) {
		if (hasChild(node)) {
			for (Node child : node.nodeList) {
				postOrder(child);
				System.out.println(child.value);
			}
		}
	}

	public Node searchNode(Node node, String searchValue) {
		if (!node.value.equals(searchValue)) {
			if (hasChild(node)) {
				for (Node child : node.nodeList) {
					Node nieto = searchNode(child, searchValue);
					if (nieto != null) return nieto;
				}
			}
			
			return null;
		}

		return node;
	}
	
	public Node insertNode(Node currentNode, Node node, String value) {
		if (node == null) throw new NullPointerException();
		
		if (!currentNode.equals(node)) {
			if (hasChild(currentNode)) {
				for (Node child : currentNode.nodeList) {
					Node nieto = insertNode(child, node, value);
					if (nieto != null) return nieto;
				}
			}
			
			return null;
		}
		
		node.nodeList.add(new Node(value));
		return node;
	}
	
	public String getPath(Node node, String searchValue) {
		if (!node.value.equals(searchValue)) {
			if (hasChild(node)) {
				for (Node child : node.nodeList) {
					String nieto = getPath(child, searchValue);
					if (nieto != null) return node.value + "/" + nieto;
				}
			}
			
			return null;
		}

		return node.getValue();
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
