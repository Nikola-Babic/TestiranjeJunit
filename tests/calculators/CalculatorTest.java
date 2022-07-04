package calculators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import calculators.Calculator;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;

class CalculatorTest {
	
	private Calculator calculator = new Calculator();

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
	void testCalculator() {
		assertNotNull(calculator);
	}

	@Test
	void testSetCurrentValue() {
		calculator.setCurrentValue(Double.valueOf(1.0));
		assertThat(Double.valueOf(1.0), is(calculator.getCurrentValue()));
	}

	@Test
	void testGetCurrentValue() {
		assertThat(Double.valueOf(0.0), is(calculator.getCurrentValue()));
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Simple operations (+, -, *, /)")
	@MethodSource("methodWithParamsForCalculate")
	void testCalculateParameterized(Double currentValue, Double value, char operator, Double result) throws NotSupportedOperationException, DivisionByZeroException {
		calculator.calculate(currentValue, '+');
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(), is(result));
	}
	
	private static Stream<Arguments> methodWithParamsForCalculate(){
		return Stream.of(
				Arguments.of(2.0, 3.0, "+", 5.0),
				Arguments.of(-2.0, 3.0, "+", 1.0),
				Arguments.of(5.0, 7.0, "-", -2.0),
				Arguments.of(5.0, -2.0, "-", 7.0),
				Arguments.of(4.0, 4.0, "*", 16.0),
				Arguments.of(-4.0, 2.0, "*", -8.0),
				Arguments.of(-4.0, -3.0, "*", 12.0),
				Arguments.of(9.0, 2.0, "/", 4.5)
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Division With Zero")
	@MethodSource("methodWithParamsForNullDivision")
	void testCalculateDivisionWithNull(Double currentValue, Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException {
		calculator.calculate(currentValue, '+');
		Exception exception = assertThrows(
				DivisionByZeroException.class,
				()->calculator.calculate(value, operator));
		assertThat(exception, is(instanceOf(DivisionByZeroException.class)));
	}
	
	private static Stream<Arguments> methodWithParamsForNullDivision(){
		return Stream.of(
				Arguments.of(2.0, 0.0, "/"),
				Arguments.of(3.0, 0.0, "/"),
				Arguments.of(4.0, 0.0, "/"),
				Arguments.of(10.0, 0.0, "/")
				);
	}
	
	@ParameterizedTest
	@DisplayName ("Testing: Unsupported Operation")
	@MethodSource("methodWithParamsForUnsupportedOperation")
	void testCalculateWrongOperations(Double currentValue, Double value, char operator) throws NotSupportedOperationException, DivisionByZeroException {
		calculator.calculate(currentValue, '+');
		Exception exception = assertThrows(
				NotSupportedOperationException.class,
				()->calculator.calculate(value, operator));
		assertThat(exception, is(instanceOf(NotSupportedOperationException.class)));
	}
	
	private static Stream<Arguments> methodWithParamsForUnsupportedOperation(){
		return Stream.of(
				Arguments.of(4.0, 2.0, "a"),
				Arguments.of(2.0, 3.0, "2"),
				Arguments.of(3.0, 4.0, "."),
				Arguments.of(5.0, 5.0, "?")
				);
	}

}
