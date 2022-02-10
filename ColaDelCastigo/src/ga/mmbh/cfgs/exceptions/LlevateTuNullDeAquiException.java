package ga.mmbh.cfgs.exceptions;

public class LlevateTuNullDeAquiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = (long) (Math.random() * 10000);
	
	public LlevateTuNullDeAquiException(String message) {
		super(message);
	}
	
	public LlevateTuNullDeAquiException() {
		super("You can not add more null values on a ColaDelCastigo queue");
	}
}
