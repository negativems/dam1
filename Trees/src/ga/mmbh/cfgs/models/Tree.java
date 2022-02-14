package ga.mmbh.cfgs.models;

public class Tree {

	private Node root;

	public Tree(Node root) {
		this.root = root;
	}
	
	public Node buscar(String value) {
		return root.searchInOrder(root, value);
	}
	
	public void preOrden() {
		this.root.preOrder(root);
	}
	
	public void inOrder() {
		this.root.inOrder(root);
	}
	
	public void postOrder() {
		this.root.postOrder(root);
	}
}
