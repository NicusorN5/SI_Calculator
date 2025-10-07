package ro.usv.g3143a.tn.calculator;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {
	
	private String expression;
	private ArrayList<Pair<String, TokenType>> tokens;
	
	void addOperator(TokenType t, String currentToken, char c) throws InvalidTokenException
	{
		//Wasted half an hour trying to use a lambda expression, only to find out captured variables cannot be modified...
		if(currentToken.isEmpty() == false)
		{
			try
			{
				//Check if currentToken is a number
				Float.valueOf(currentToken);
				tokens.add(new Pair<String, TokenType>(currentToken, TokenType.Number));	
			}
			catch(NumberFormatException ex)
			{
				//Eeeh...
				throw new InvalidTokenException(currentToken);
			}
		}
		tokens.add(new Pair<String, TokenType>(c+"", t));
	};
	
	private void lexer() throws InvalidTokenException
	{
		//Single Layer FSM.
		tokens = new ArrayList<Pair<String, TokenType>>();
		
		for(int i = 0; i < expression.length(); i++)
		{
			String currentToken = "";
			
			char c = expression.charAt(i);
			
			switch(c)
			{
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
				currentToken += c;
				break;
			case '+':
				addOperator(TokenType.Plus, currentToken, c);
				currentToken = "";
				break;
			case '-':
				addOperator(TokenType.Minus, currentToken, c);
				currentToken = "";
				break;
			case '*':
				addOperator(TokenType.Mul, currentToken, c);
				currentToken = "";
			case '/':
				addOperator(TokenType.Div, currentToken, c);
				currentToken = "";
				break;
			default:
				throw new InvalidTokenException(currentToken);
			}
		}
	}
	
	private float applyOperator(float a, char op, float b) throws ParserException {
		switch(op) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		case '/':
			if(b == 0) {
				if(a == 0) {
					throw new ArithmeticException("0/0 is inderterminated");
				}
				else {
					throw new ArithmeticException("Division by 0.");
				}
			}
			return a/b;
		default:
			throw new ParserException("Invalid operator");
		}
	}
	
	private float shuntingYard() throws InvalidTokenException
	{
		//Simplified(?), function calls aren't used, neither are parenthesis.
		
		Stack<Float> operands = new Stack<Float>();
		Stack<TokenType> operators = new Stack<TokenType>();
		
		float r = 0;
		
		//Fill stacks
		for(int i =0; i < tokens.size(); ++i)
		{
			TokenType op = tokens.get(i).Second; 
			switch(op)
			{
			case Number:
				String v = tokens.get(i).First; 
				try {
				operands.add(Float.valueOf(v));
				}
				catch(NumberFormatException ex)
				{
					throw new InvalidTokenException(v);
				}
				break;
			case Plus:
				operators.add(op);
				break;
			case Minus:
				operators.add(op);
				break;
			case Mul:
				operators.add(op);
				break;
			case Div:
				operators.add(op);
				break;
			default:
				break;
			}
		}
		
		//Pop stacks
		while(operators.empty() != true)
		{
			TokenType t = operators.pop();

			char op;
			switch(t)
			{
			case Plus:
				op = '+';
				break;
			case Minus:
				op = '-';
				break;
			case Mul:
				op = '*';
				break;
			case Div:
				op = '/';
				break;
			default:
				throw new InvalidTokenException(t+"");
			}
			
			float a = operands.pop();
			float b = operands.pop();
			try
			{
				r = applyOperator(a, op, b);
			}
			catch(ParserException ex)
			{
			}
		}
		return r;
	}
	
	public Parser(String exp)
	{
		expression = exp;
	}
	
	public float Evaluate() throws InvalidTokenException
	{
		lexer();
		return shuntingYard();
	}
}
