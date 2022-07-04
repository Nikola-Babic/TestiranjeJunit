package exceptions;

/**
 * The class implements the exception that should be thrown by our
 * program if it encounters division by 0.
 * 
 * @author Nikola Babic
 * @version 1.0
 * @since 2021-02-22
 *
 */
public class DivisionByZeroException extends Exception{

	/**
	 * Default constructor.
	 */
	public DivisionByZeroException() {
		super("You can not divide with 0,please choose another number!");
	}
	
	/**
	 * Another constructor, but this one has a String message.
	 * @param message Message will be shown if the exception is thrown.
	 */
	public DivisionByZeroException(String message) {
		super(message);
	}
}