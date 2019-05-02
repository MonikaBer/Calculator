package application;


/**
* Class Model which implements arithmetic operations.
* 
*/
public class Model implements CalculatorInterface {
	
	/**
	* Method 'calculateBinaryOp' to doing binaryOperations.
	* . . .
	* @param a First argument of the operation (type double).
	* @param b Second argument of the operation (type double).
	* @param operator Arithmetic operator (type String).  
	* @return Result of the operation (type double).
	* *
	*/
	public double calculateBinaryOp(double a, double b, String operator) {
			switch(operator) {
				case "+":
					CalculatorInterface add = (x,y) -> x+y;	
					return add.operation(a,b);
				case "-":
					CalculatorInterface subtract = (x,y) -> x-y;	
					return subtract.operation(a,b);
				case "x":
					CalculatorInterface multiply = (x,y) -> x*y;	
					return multiply.operation(a,b);
				case "÷":
					if ( b == 0 )  { 
						Alarm notDivideByZero = new Alarm("warning", "Warning!", 
								"You try to divide by zero!", "You mustn't divide by zero!");
						notDivideByZero.launchAlarm();
						return 0;
					}
					
					CalculatorInterface divide = (x,y) -> x/y;	
					return divide.operation(a,b);
				case "^":
					CalculatorInterface exponent = (x,y) -> Math.pow(x,y);	
					return exponent.operation(a,b);
			}
			return 0;
	}
	
	/**
	* Method 'calculateUnaryOp' to doing unary operations.
	* . . .
	* @param a Argument of the operation (type double).
	* @param operator Arithmetic operator (type String).  
	* @return Result of the operation (type double).
	* *
	*/
	public double calculateUnaryOp(double a, String operator) {
		switch(operator) {
			case "!":
				CalculatorInterface factorial = (x,y) -> countFactorial(x,y);
				return factorial.operation(a,0);	
			case "√":
				CalculatorInterface extract = (x,y) -> Math.sqrt(x);
				return extract.operation(a,0);	
		}
		return 0;
	}

	/**
	* Overwrite method 'operation' from 'CalculatorInterface'.
	* 
	*/
	@Override
	public double operation(double a, double b) {
		return 0;
	}

	/**
	* Method countFactorial to count factorial.
	* . . .
	* @param a Argument of the operation (type double).
	* @param b Unused argument (only to maintain compatibility with the abstract method 'operation' from 'CalculatorInterface') (type double).  
	* @return Result of the operation (type double).
	* *
	*/
	public static double countFactorial(double a, double b) {
		if (a > 0)
			return a * countFactorial(a - 1, 0);
		else
			return 1;
	}
	
}

