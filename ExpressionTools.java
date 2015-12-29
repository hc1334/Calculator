package project4;


/**
 * ExpressionTools is a static class that provides methods for dealing with infix and postfix expressions
 * @author Helen Chang
 * @version Nov 17, 2015 
 *
 */


class ExpressionTools{

	/**
	 * Converts an infix expression to postfix expression
	 * @param String of infix expression with each number, operator or bracket separated by spaces
	 * @return String of postfix expression with each number and operator separated by spaces
	 * @throws PostFixException if expression cannot be converted/ is invalid
	 */
	public static String infixToPostfix(String expression) throws PostFixException{
		expression = expression.trim();
		if (expression.isEmpty()){
			throw new PostFixException("Empty line");
		}
		
		
		String[] tokens = expression.split(" ");
		
		String postfix = "";
		MyStack<String> operator = new MyStack<String>();
		for (int i = 0; i<tokens.length; i++){
//			System.out.println("stack: "+operator.toString());
//			System.out.println("postfix: "+postfix);
			String token = tokens[i];
			if (isOperand(token)){
				postfix += token + " ";
			}
			else if (token.equals("(")){
				operator.push(token);
			}
			else if (isOperator(token)){
				if (!operator.empty()){
					while(compareOperators(operator.peek(),token)>=0 && !token.equals(")") && !token.equals("(")){
						postfix += operator.pop()+ " ";
						if (operator.empty()){
							break;
						}
					}
					
				}
				operator.push(token);
			}
			else if(token.equals(")")){
//				System.out.println("stack is empty: "+operator.empty());
				if (operator.empty()){
					throw new PostFixException("No matching right brace");
				}
				while(!operator.empty()){
					if(!operator.peek().equals("(")){
						postfix += operator.pop()+ " ";
					}
					else if (operator.peek().equals("(")){
						operator.pop();
						break;
					}
					if(operator.empty()){
						throw new PostFixException("No matching brace!!");
					}
				}
			}
			//deals with non operator non operand non bracket words or characters
			else if (!token.equals("")){
//				System.out.println("error token is "+token);
				throw new PostFixException("Not recognizable character or lack spaces");
			}
		}
		while(!operator.empty()){
			postfix += operator.pop()+ " ";
		}
//		System.out.println("Postfix is " + postfix);
		return postfix;
	}
	
	/**
	 * Determines if operators are of higher or lower precedence. 
	 * @param first operator being compared
	 * @param second operator being compared
	 * @return 1 if first operator has higher precedence, -1 if second does, 0 if they are of the same precedence
	 * @throws PostFixException
	 */
	private static int compareOperators(String first, String second)throws PostFixException{
		if (first.equals(second) || (first.equals("+") && second.equals("-")) ||(second.equals("+")&&first.equals("-"))||(second.equals("*")&&first.equals("/"))||(second.equals("/")&&first.equals("*"))){
			return 0;
		}
		else if ((first.equals("*")||first.equals("/")) && (second.equals("+")||second.equals("-"))){
			return 1;
		}
		else if ((second.equals("*")||second.equals("/")) && (first.equals("+")||first.equals("-"))){
			return -1;
		}
		else if (first.equals("(")||first.equals(")")){
			return -1;
		}
		throw new PostFixException("Error with Operator: NOT A COMPARABLE OPERATOR");
	}
	
	/**
	 * Evaluates the value of a postfix expression
	 * @param String of an expression in postfix format
	 * @return integer of the value of that expression
	 * @throws PostFixException if expression cannot be evaluated
	 * @throws java.lang.ArithmeticException if divided by 0
	 */
	public static int evaluatePostfix(String expression)throws PostFixException, java.lang.ArithmeticException{
		String[] tokens = expression.split(" ");
		MyStack<Integer> result = new MyStack<Integer>();
		for (int i = 0; i<tokens.length; i++){
			String token = tokens[i];
			if (isOperand(token)){
				result.push(Integer.parseInt(token));
			}
			else if (isOperator(token)){
				try{
					int operand2 = result.pop();
					int operand1 = result.pop();
					int computation;
					switch(token){
					case "+":
						computation = operand1 + operand2;
						break;
					case "-":
						computation = operand1 - operand2;
						break;
					case "*":
						computation = operand1 * operand2;
						break;
					case "/":
						if (operand2 == 0){
							throw new java.lang.ArithmeticException();
						}
						computation = operand1 / operand2;
						break;
					//this should never happen though
					default:
						throw new PostFixException("Cannot perform computation");
					}
					result.push(computation);	
				}
				catch (java.lang.ArithmeticException e){
					throw e;
				}
				catch (Exception e){
					throw new PostFixException("Missing Operands!!");
				}
			}
		}
		int toReturn = result.pop();
		if (!result.empty()){
			throw new PostFixException("Missing Operators!!");
		}
		return toReturn;
	}

	/**
	 * Determines if a string is an operand
	 * @param String of possible operand
	 * @return true if it is an operand, else false
	 */
	public static boolean isOperand(String s){
		try{
			Integer.parseInt(s);
		}
		catch (Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * Determines if a string is an operator
	 * @param String of possible operator
	 * @return true if it is a valid operator (+, 0, *, /), else false
	 */
	public static boolean isOperator(String s){
		if (s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
			return true;
		}
		return false;
	}
}