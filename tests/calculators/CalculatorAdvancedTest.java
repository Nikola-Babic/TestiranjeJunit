package calculators;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import calculators.CalculatorAdvanced;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

class CalculatorAdvancedTest {
	
	private CalculatorAdvanced calculator = new CalculatorAdvanced();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculatorAdvanced() {
		assertNotNull(calculator);
	}

	@ParameterizedTest
	@DisplayName ("Testing: The Power Functionality")
	@MethodSource("methodWithParamsForPower")
	void testCalculateAdvancedPower(Double currentValue, char action, Double result) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, '+');
		calculator.calculateAdvanced(action);
		assertThat(calculator.getCurrentValue(), is(result));
	}
	
	private static Stream<Arguments> methodWithParamsForPower(){
		return Stream.of(
				Arguments.of(2.0, '0', 1.0),
				Arguments.of(2.0, '2', 4.0),
				Arguments.of(-2.0, '2', 4.0),
				Arguments.of(2.0, '3', 8.0),
				Arguments.of(-2.0, '3', -8.0),
				Arguments.of(2.0, '4', 16.0),
				Arguments.of(2.0, '5', 32.0),
				Arguments.of(2.0, '9', 512.0)
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Valid Factorial Examples")
	@MethodSource("methodWithParamsForFactoriel")
	void testCalculateAdvancedFactoriel(Double currentValue, char action, Double result) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, '+');
		calculator.calculateAdvanced(action);
		assertThat(calculator.getCurrentValue(), is(result));
	}
	
	private static Stream<Arguments> methodWithParamsForFactoriel(){
		return Stream.of(
				Arguments.of(0.0, '!', 1.0),
				Arguments.of(2.0, '!', 2.0),
				Arguments.of(3.0, '!', 6.0),
				Arguments.of(4.0, '!', 24.0),
				Arguments.of(10.0, '!', 3628800.0)
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Factorial number not in range")
	@MethodSource("methodWithParamsForFactorielWrong")
	void testCalculateAdvancedFactorielWrong(Double currentValue, char operation, char action) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, operation);
		Exception exception = assertThrows(
				NumberNotInAreaException.class,
				()->calculator.calculateAdvanced(action));
		assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));
	}
	
	private static Stream<Arguments> methodWithParamsForFactorielWrong(){
		return Stream.of(
				Arguments.of(22.0, '+', '!'),
				Arguments.of(7.0, '-', '!'),
				Arguments.of(11.5, '+', '!'),
				Arguments.of(10.0, '-', '!')
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Unsupported operations in AdvancedCalculator")
	@MethodSource("methodWithParamsWrongOperation")
	void testCalculateAdvancedWrongOperation(Double currentValue, char operation, char action) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, operation);
		Exception exception = assertThrows(
				NotSupportedOperationException.class,
				()->calculator.calculateAdvanced(action));
		assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> methodWithParamsWrongOperation(){
		return Stream.of(
				Arguments.of(10.0, '+', 'b'),
				Arguments.of(22.0, '-', 'Z'),
				Arguments.of(17.0, '+', '+'),
				Arguments.of(6.0, '+', '/'),
				Arguments.of(5.0, '+', ';'),
				Arguments.of(3.0, '+', ',')
				);
	}

	@ParameterizedTest
	@DisplayName ("Testing: Valid Examples of Armstrong and Perfect Numbers")
	@MethodSource("methodHasCharacteristicParams")
	void testHasCharacteristic(Double currentValue, char operator, Boolean result) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, '+');
		assertThat(calculator.hasCharacteristic(operator), is(result));
	}
	
	private static Stream<Arguments> methodHasCharacteristicParams(){
		return Stream.of(
				Arguments.of(1.0, 'A', true),
				Arguments.of(153.0, 'A', true),
				Arguments.of(154.0, 'A', false),
				Arguments.of(370.0, 'A', true),
				Arguments.of(369.0, 'A', false),
				Arguments.of(371.0, 'A', true),
				Arguments.of(372.0, 'A', false),
				Arguments.of(407.0, 'A', true),
				Arguments.of(28.0, 'P', true),
				Arguments.of(29.0, 'P', false),
				Arguments.of(496.0, 'P', true),
				Arguments.of(497.0, 'P', false),
				Arguments.of(8128.0, 'P', true),
				Arguments.of(8127.0, 'P', false)
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Armstrong and Perfect, but the number is not in the area")
	@MethodSource("methodHasCharacteristicParamsBadNumber")
	void testHasCharacteristicBadNumber(Double currentValue, char operator, Boolean result) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, '+');
		Exception exception = assertThrows(
				NumberNotInAreaException.class,
				()->calculator.hasCharacteristic(operator));
		assertThat(exception, is(instanceOf(NumberNotInAreaException.class)));
	}
	
	private static Stream<Arguments> methodHasCharacteristicParamsBadNumber(){
		return Stream.of(
				Arguments.of(0.5, 'A', true),
				Arguments.of(-1.0, 'P', false),
				Arguments.of(-2.0, 'A', true)
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Armstrong and Perfect, but the operation is not supported")
	@MethodSource("methodHasCharacteristicParamsNotSupportedOperation")
	void testHasCharacteristicNotSupportedOperation(Double currentValue, char operator, Boolean result) throws NotSupportedOperationException, DivisionByZeroException, NumberNotInAreaException {
		calculator.calculate(currentValue, '+');
		Exception exception = assertThrows(
				NotSupportedOperationException.class,
				()->calculator.hasCharacteristic(operator));
		assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> methodHasCharacteristicParamsNotSupportedOperation(){
		return Stream.of(
				Arguments.of(1.0, 'z', true),
				Arguments.of(154.0, 'Y', false),
				Arguments.of(371.0, '3', true),
				Arguments.of(370.0, ';', true),
				Arguments.of(371.0, ',', false),
				Arguments.of(372.0, '{', false)
				);
	}

}
