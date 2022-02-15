package ga.mmbh.cfgs.exceptions;

public class ColaExceededSizeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = (long) (Math.random() * 10000);
	
	public ColaExceededSizeException(String message) {
		super(message);
	}
	
	public ColaExceededSizeException() {
		super("You can not add more than ten elements in a queue");
	}
}
