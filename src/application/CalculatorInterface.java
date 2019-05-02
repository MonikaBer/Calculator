package application;


/**
* Functional interface named 'CalculatorInterface' which has got an abstract method to doing arithmetic operations
* 
*/
@FunctionalInterface
public interface CalculatorInterface {
	/**
	* Method 'operation' to doing binary operations
	* . . .
	* @param a First argument of operation (type double)
	* @param b Second argument of operation (type double) 
	* @return Result of the operation (type double)
	* *
	*/
	double operation(double a, double b);
}
