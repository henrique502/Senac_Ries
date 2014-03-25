package br.com.hrdev.library;

/*
 * Retirado de: http://www.jguru.com/faq/view.jsp?EID=480122
 */

public class MathParser {
	
	private static final char[] validOperators = {'/','*','+','-'};
	
	private MathParser(){}

	public static int evaluate(String leftSide, char oper, String rightSide) throws IllegalArgumentException {
		int total = 0;
		int leftResult = 0;
		int rightResult = 0;

		int operatorLoc = findOperatorLocation(leftSide);
		if( operatorLoc > 0 && operatorLoc < leftSide.length()-1 ){
			leftResult = evaluate(leftSide.substring(0,operatorLoc),
					leftSide.charAt(operatorLoc),
					leftSide.substring(operatorLoc+1,leftSide.length()));
		} else {
			try {
				leftResult = Integer.parseInt(leftSide);
			} catch(Exception e) {
				throw new IllegalArgumentException("Valor invalido");
			}
		}

		operatorLoc = findOperatorLocation(rightSide);
		if( operatorLoc > 0 && operatorLoc < rightSide.length()-1 ){
			rightResult = evaluate(rightSide.substring(0,operatorLoc),
					rightSide.charAt(operatorLoc),
					rightSide.substring(operatorLoc+1,rightSide.length()));
		} else {
			try {
				rightResult = Integer.parseInt(rightSide);
			} catch(Exception e){
				throw new IllegalArgumentException("Valor invalido");
			}
		}

		switch(oper) {
			case '/': total = leftResult / rightResult; break;
			case '*': total = leftResult * rightResult; break;
			case '+': total = leftResult + rightResult; break;
			case '-': total = leftResult - rightResult; break;
			default: throw new IllegalArgumentException("Operador descohecido");
		}
		return total;
	}

	private static int findOperatorLocation(String string){
		int index = -1;
		for(int i = validOperators.length-1; i >= 0; i--){
			index = string.indexOf(validOperators[i]);
			if(index >= 0)
				return index;
		}
		return index;
	}
}
