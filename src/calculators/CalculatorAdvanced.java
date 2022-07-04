package calculators;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * CalculatorAdvanced
 * 
 * This class is an advanced calculator, which supports the following:
 * power of a number, factorial of a number, checking whether number is an Armstrong number
 * or a perfect number, it also includes the operations supported in Calculator.
 * 
 * @author Nikola Babic
 * @version 1.0
 * @since 2021-02-22
 *
 */
public class CalculatorAdvanced extends Calculator {

	/**
	 * The default constructor for the class, it sets the value of currentValue to 0.
	 */
	public CalculatorAdvanced() {
		super();
	}

	/**
	 * This method is used for calculating the power or the factorial of a number.
	 * 
	 * @param action This states the operation we want conducted. If the number is in the [0, 9] range, the
	 * method will calculate the power of the number currently stored in currentValue, without the decimal part.
	 * The power calculated to is the number in action.
	 * If the action is '!' the method will calculate the factorial
	 * of the number currently stored in currentValue, without the decimal part.
	 * 
	 * @throws NumberNotInAreaException Thrown in case the chosen action is factorial and currentValue isn't in the [0, 10] range.
	 * @throws NotSupportedOperationException Thrown if action isn't '!' or a number in the [0, 9] range.
	 */
	public void calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException {

		if (48 <= action && action <= 57) {

			if (action == 48) {
				setCurrentValue(Double.valueOf(1));
			}

			else {
				int intValue = (int) getCurrentValue();
				int powerNumber = action - 48;
				int result = 1;
				for (int i = 0; i < powerNumber; i++) {
					result = result * intValue;
				}
				setCurrentValue(Double.valueOf(result));
			}

		} else if (action == 33) {

			if (getCurrentValue() < 0 || getCurrentValue() > 10) {
				throw new NumberNotInAreaException();
			} else {
				int intValue = (int) getCurrentValue();
				if (intValue == 0)
					setCurrentValue(Double.valueOf(1));
				else {
					int result = 1;
					for (int i = 1; i <= intValue; i++) {
						result *= i;
					}
					setCurrentValue(Double.valueOf(result));
				}
			}

		} else {
			throw new NotSupportedOperationException();
		}

	}
	
	/**
	 * The method is used to determine whether or not the number in currentValue without the decimal part
	 * is an Armstrong number, or a Perfect number, or none of them.
	 *  
	 * @param value Tells us what to check for, 'A' for Armstrong, 'P' for perfect number.
	 * @return Boolean Tells us with a boolean value (true/false) if our number passed the check.
	 * @throws NumberNotInAreaException Thrown if the number in currentValue is bellow 1.
	 * @throws NotSupportedOperationException Thrown if the value of parameter value is not 'A' or 'P'.
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		Boolean hasIt = false;
		int intValue = (int) getCurrentValue();
		if(intValue < 1) {
			throw new NumberNotInAreaException();
		}
		if (value == 'A') {
			hasIt = checkIfArmstrong(intValue);
		} else if (value == 'P') {
			hasIt = checkIfPerfect(intValue);
		}

		else {
			throw new NotSupportedOperationException();
		}

		return hasIt;
	}
	
	/**
	 * A helper method which determines if the number is an Armstrong number.
	 * 
	 * @param number The number that we want checked.
	 * @return Boolean Tells us with a boolean value (true/false) if our number passed the check.
	 */
	private Boolean checkIfPerfect(int intValue) {
		int result = 0;
		for (int i = 1; i < intValue; i++) {
			if (intValue % i == 0) {
				result += i;
			}
		}
		if (result == intValue) {
			return true;
		}
		return false;

	}
	
	/**
	 * A helper method which determines if the number is a perfect number.
	 * 
	 * @param number The number that we want checked.
	 * @return Boolean Tells us with a boolean value (true/false) if our number passed the check.
	 */
	private Boolean checkIfArmstrong(int intValue) {
		int result = 0;
		int temp = intValue;
		int digit;
		while (temp > 0) {
			digit = temp % 10;
			temp = temp / 10;
			result = result + (digit * digit * digit);
		}
		if (result == intValue) {
			return true;
		}

		return false;
	}

}
