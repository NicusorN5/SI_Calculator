package ro.usv.g3143a.tn.calculator;

public final class InvalidTokenException extends ParserException {
	
	public InvalidTokenException(String token) {
		super("Invalid token: "+token);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1767240185637777627L;
}
