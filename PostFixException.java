package project4;
/**
 * Exception objects to be thrown by the ExpressionTools class
 * @author Helen Chang
 * @version Nov 17, 2015 
 *
 */

public class PostFixException extends Exception {
	
	/**
	 * Default constructor calls default constructor of Exception class
	 */
	public PostFixException() { 
		super();
	}
	
	/**
	 * Creates an exception that contains a specific message
	 * @param String of message representing what went wrong
	 */
	public PostFixException(String message) { 
		super(message);
	} 

}