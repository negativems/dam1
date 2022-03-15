package ga.mmbh.cfgs.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

	private String value;

	private List<Node> nodeList = new ArrayList<>();

	public Node(String value) {
		this.value = value;
	}

	public Node(String value, List<Node> nodeList) {
		this.value = value;
		this.nodeList = nodeList;
	}

	public Node(String value, Node... nodes) {
		this.value = value;
		this.nodeList = Arrays.asList(nodes);
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

	public Node searchNode(Node node, String value) {
		if (!node.value.equals(value)) {
			if (hasChild(node)) {
				for (Node child : node.nodeList) {
					Node nieto = searchNode(child, value);
					if (nieto != null) return nieto;
				}
			}
			
			return null;
		}

		return node;
	}
	
	public Node insertNode(Node node, String value) {
		if (!node.value.equals(value)) {
			if (hasChild(node)) {
				for (Node child : nodeList) {
					Node nieto = insertNode(child, value);
					if (nieto != null) return nieto;
				}
			}
			return null;
		}
		
		node.nodeList.add(node);
		return node;
	}
	
	public Node path(Node node, String value) {
		if (!node.value.equals(value)) {
			if (hasChild(node)) {
				for (Node child : node.nodeList) {
					Node nieto = searchNode(child, value);
					if (nieto != null) return nieto;
				}
			}
			
			return null;
		}

		return node;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
