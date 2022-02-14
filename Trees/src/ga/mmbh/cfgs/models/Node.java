package ga.mmbh.cfgs.models;

public class Node {

	private String value;
	private Node root;
	private Node right;
	private Node left;
	
	public Node(String value) {
		this.value = value;
	}
	
	public Node(String value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}
	
	public boolean hasLeftChild(Node node) {
		return node.left != null;
	}
	
	public boolean hasRightChild(Node node) {
		return node.right != null;
	}
	
	public void preOrder(Node node) {
		System.out.println(node);
		
		if (hasLeftChild(node)) {
			preOrder(node.left);
		}
		
		if (hasRightChild(node)) {
			preOrder(node.right);
		}
		
	}
	
	public void inOrder(Node node) {
		if (hasLeftChild(node)) {
			inOrder(node.left);
		}
		
		System.out.println(node);
		
		if (hasRightChild(node)) {
			inOrder(node.right);
		}
	}
	
	public void postOrder(Node node) {
		if (hasLeftChild(node)) {
			postOrder(node.left);
		}
		
		if (hasRightChild(node)) {
			postOrder(node.right);
		}
		
		System.out.println(node);
	}
	
	public Node searchInOrder(Node node, String search) {
		if (!node.value.equals(search)) {
			if (hasLeftChild(node)) {			
				return searchInOrder(node.left, search);
			}
			
			if (hasRightChild(node)) {
				return searchInOrder(node.right, search);
			}
			
			return null;
		}
		
		return node;
	}
	
	
	@Override
	public String toString() {
		return value;
	}
	
}
