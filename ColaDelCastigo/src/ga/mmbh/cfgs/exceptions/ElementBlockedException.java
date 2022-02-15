package ga.mmbh.cfgs.exceptions;

public class ElementBlockedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = (long) (Math.random() * 10000);
	
	public ElementBlockedException(String message) {
		super(message);
	}
	
	public ElementBlockedException() {
		super("You can not have less than one element in a queue");
	}
}
