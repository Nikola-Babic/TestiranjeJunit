package calculators;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * Calculator
 * 
 * This is a simple calculator class,which supports
 * four predefined  operations ( +, -, * or / ).
 * Those operations are: addition, subtraction, multiplication and division.
 * 
 * @author Nikola Babic
 * @version 1.0
 * @since 2021-02-22
 *
 */
public class Calculator {
	
	/**
	 * This variable is used as the first parameter in all supported operations.
	 * It also stores the result of the previous operation.
	 */
	private double currentValue;
	
	/**
	 * The default constructor for the class, it sets the value of currentValue to 0.
	 */
	public Calculator() {
		currentValue=0.0;
	}
	
	/**
	 * This is the getter for this class, gets the current value of currentValue.
	 * 
	 * @return Returns the value of currentValue.
	 */
	public double getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * The setter for this Class, it sets the value of currentValue .
	 * 
	 * @param Value is the value that we want currentValue to become.
	 */
	public void setCurrentValue(double Value) {
		this.currentValue=Value;
	}
	

	/**
	 * This method is used for conducting all supported mathematical operations.
	 * The supported operations are: addition, subtraction, multiplication and division.
	 * 
	 * @param value It is used as the second parameter of the chosen operation.
	 * @param operator Determines which mathematical operation we are conducting ( +, -, * or / ).
	 * @throws NotSupportedOperationException Exception thrown in case of unsupported operation (something that's not +, -, * or /).
	 * @throws DivisionByZeroException Exception thrown in case of division by zero.
	 */
	public void calculate(Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException {
		switch(operator) {
			case '+':
				currentValue += value;
				break;
			case '-':
				currentValue -= value;
				break;
			case '*':
				currentValue *= value;
				break;
			case '/':
				if(value.doubleValue() == 0.0) {
					throw new DivisionByZeroException();
				}else {
					currentValue /= value;
				}
				break;
			default:
				throw new NotSupportedOperationException();
		}
	}

}
