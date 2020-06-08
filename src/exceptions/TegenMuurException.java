package exceptions;

import util.Taal;

public class TegenMuurException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Taal taal = Taal.getInstance();
	public TegenMuurException() {
		super(taal.vertaal("tegenmuur"));
	}
}
