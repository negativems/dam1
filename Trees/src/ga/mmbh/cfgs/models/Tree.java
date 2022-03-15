package ga.mmbh.cfgs.models;

public class Tree {

	private Node root;

	public Tree(Node root) {
		this.root = root;
	}
	
	public Node search(String value) {
		return root.searchNode(root, value);
	}
	
	public void preOrder() {
		System.out.println(root.getValue());
		this.root.preOrder(root);
	}
	
	public void postOrder() {
		this.root.postOrder(root);
		System.out.print(root.getValue());
	}
	
	public void insertNode(Node parent, String value) {
		parent.insertNode(parent, value);
	}
	
	public Node path(String value) {
		search(value).path(value);
	}
}
