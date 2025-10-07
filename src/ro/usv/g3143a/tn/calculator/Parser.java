package ro.usv.g3143a.tn.calculator;

import java.util.ArrayList;

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
				float v = Float.valueOf(currentToken);
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
	
	private void createTokens() throws InvalidTokenException
	{
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
	
	private void shuntingYard()
	{
		for(int i =0; i < tokens.size(); ++i)
		{
			
		}
	}
	
	public Parser(String exp)
	{
		expression = exp;
	}
	
	public float Evaluate() throws InvalidTokenException
	{
		createTokens();
		
		return 0.0f;
	}
}
