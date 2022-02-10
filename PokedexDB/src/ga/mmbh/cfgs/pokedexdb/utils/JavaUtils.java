package ga.mmbh.cfgs.pokedexdb.utils;

public class JavaUtils {

	public static boolean isFloat(String string) {
	    try{
	        Float.parseFloat(string);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public static boolean isInteger(String string) {
	    try{
	        Integer.parseInt(string);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public static boolean isString(Object object) {
	    try{
	        String.valueOf(object);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
}
