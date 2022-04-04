package ga.mmbh.cfgs;

import ga.mmbh.cfgs.models.Tree;
import ga.mmbh.cfgs.models.Node;

public class Trees {

	public static void main(String[] args) {
		
		// HOJAS		
		Node seis = new Node("seis");
		Node cinco = new Node("cinco", seis);
		Node dos = new Node("dos", cinco);
		Node uno = new Node("uno");
		
		Node cuatro = new Node("cuatro");
		
		Node tres = new Node("tres");
		
		Node ternera = new Node("ternera", cuatro);
		Node cerdo = new Node("cerdo", tres);
		Node pollo = new Node("pollo", uno, dos);
		
		Node raiz = new Node("raiz", pollo, cerdo, ternera);
		Tree arbol = new Tree(raiz);
		
		System.out.println("Buscando nodo: " + arbol.search("cuatro"));
		System.out.println("Insertando nodo: " + arbol.insertNode(pollo, "hola"));
		System.out.println("Obteniendo ruta: " + arbol.getPath("cuatro"));
	}

}
