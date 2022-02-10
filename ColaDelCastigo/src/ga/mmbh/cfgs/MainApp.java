package ga.mmbh.cfgs;

import java.util.Iterator;

import ga.mmbh.cfgs.exceptions.ColaExceededSizeException;
import ga.mmbh.cfgs.exceptions.LlevateTuNullDeAquiException;
import ga.mmbh.cfgs.modules.ColaDelCastigo;

public class MainApp {
	
	public static void main(String[] args) {
		
		ColaDelCastigo<Integer> lista = new ColaDelCastigo<Integer>();
		
		try {
			lista.add(7);
			lista.add(25);
			lista.add(1);
			lista.add(9);
			lista.add(13);
		} catch (ColaExceededSizeException | LlevateTuNullDeAquiException e) {
			e.printStackTrace();
		}
		
		Iterator<Integer> iterator = lista.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
