package ga.mmbh.cfgs.utils;

public class JavaUtils {

	public static boolean isFloat(String string) {
	    try{
	        Float.parseFloat(string);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public static boolean isFloat(String... strings) {
	    for (String s : strings) {
	    	if (!isFloat(s)) return false;
	    }
	    
	    return true;
	}
	
	public static boolean isInteger(String string) {
	    try{
	        Integer.parseInt(string);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public static boolean isInteger(String... strings) {
	    for (String s : strings) {
	    	if (!isInteger(s)) return false;
	    }
	    
	    return true;
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
