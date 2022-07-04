package exceptions;

/**
 * The class implements the exception that should be thrown by our
 * program if it encounters a number that is not in our predefined range.
 * 
 * @author Nikola Babic
 * @version 1.0
 * @since 2021-02-22
 *
 */
public class NumberNotInAreaException extends Exception {
	
	/**
	 * Default constructor.
	 */
	public NumberNotInAreaException() {
		super("The selected number is not in the predefined area, please use a number between 0 and 10, including those two!");
	}
	
	/**
	 * Another constructor, but this one has a String message.
	 * @param message Message will be shown if the exception is thrown.
	 */
	public NumberNotInAreaException(String message) {
		super(message);
	}

}
