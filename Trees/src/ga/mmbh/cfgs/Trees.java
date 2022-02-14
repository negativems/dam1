package ga.mmbh.cfgs;

import ga.mmbh.cfgs.models.Tree;
import ga.mmbh.cfgs.models.Node;

public class Trees {

	public static void main(String[] args) {
		
		// HOJAS
		Node C = new Node("C");
		Node E = new Node("E");
		Node H = new Node("H");
		Node A = new Node("A");

		Node D = new Node("D", C, E);
		Node I = new Node("I", H, null);

		Node B = new Node("B", A, D);
		Node G = new Node("G", null, I);

		Node F = new Node("F", B, G);

		Tree arbol = new Tree(F);
		
		// arbol.postOrder();
		
		System.out.println(arbol.buscar("D"));
	}

}
