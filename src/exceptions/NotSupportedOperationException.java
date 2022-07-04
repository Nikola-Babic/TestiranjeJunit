package exceptions;

/**
 * The class implements the exception that should be thrown by our
 * program if it encounters an unsupported operation.
 * 
 * @author Nikola Babic
 * @version 1.0
 * @since 2021-02-22
 *
 */
public class NotSupportedOperationException extends Exception{
	
	/**
	 * Default constructor.
	 */
	public NotSupportedOperationException() {
		super("The selected operation is not supported!");
	}
	
	/**
	 * Another constructor, but this one has a String message.
	 * @param message Message will be shown if the exception is thrown.
	 */
	public NotSupportedOperationException(String message) {
		super(message);
	}
}
