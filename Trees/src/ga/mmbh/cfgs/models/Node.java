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
		
	}

	public void postOrder(Node node) {

	}

	public Node searchPreOrder(Node node, String value) {
		if (!node.value.equals(value)) {
			if (hasChild(node)) {
				for (Node child : node.nodeList) {
					Node nieto = searchPreOrder(child, value);
					if (nieto != null) return nieto;
				}
			}
			
			return null;
		}

		return node;
	}

//	public Node searchPreOrder(Node node, String search) {
//		if (!node.value.equals(search)) {
//			if (hasLeftChild(node)) {
//				if (node.left != null) {
//					Node leftNode = searchPreOrder(node.left, search);
//					if (leftNode != null)
//						return leftNode;
//				}
//			}
//
//			if (hasCenterChild(node)) {
//				if (node.center != null) {
//					Node centerNode = searchPreOrder(node.center, search);
//					if (centerNode != null)
//						return centerNode;
//				}
//			}
//
//			if (hasRightChild(node)) {
//				if (node.right != null) {
//					Node rightNode = searchPreOrder(node.right, search);
//					if (rightNode != null)
//						return rightNode;
//				}
//			}
//
//			return null;
//		}
//
//		return node;
//	}

	@Override
	public String toString() {
		return value;
	}

}
