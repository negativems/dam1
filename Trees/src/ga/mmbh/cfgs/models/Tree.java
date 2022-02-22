package ga.mmbh.cfgs.models;

public class Tree {

	private Node root;

	public Tree(Node root) {
		this.root = root;
	}
	
	public Node buscar(String value) {
		return root.searchNode(root, value);
	}
	
	public void preOrder() {
		System.out.println(root.getValue());
		this.root.preOrder(root);
	}
	
	public void postOrder() {
		this.root.postOrder(root);
		System.out.println(root.getValue());
	}
	
	public void insertNode(Node parent, String value) {
		parent.insertNode(parent, value);
	}
}
