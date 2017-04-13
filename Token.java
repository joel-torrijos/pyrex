

public class Token{
	int token_id;
	String lexeme;
	String token;

	public Token(int id, String token){
		this.token = token;
		token_id = id;
		lexeme = setLexeme(id);
		//System.out.println(token + " " + id + " " + lexeme);
	}

	public String setLexeme(int id){
		String lexeme = "";
		switch(id){
			case 100:
				lexeme = "MODULO";
				break;
			case 101:
				lexeme = "LPAREN";
				break;
			case 102:
				lexeme = "RPAREN";
				break;
			case 103:
				lexeme = "COMMA";
				break;
			case 104:
				lexeme = "PERIOD";
				break;
			case 105:
			case 305:
				lexeme = "IDENT";
				break;
			case 106:
				lexeme = "STRING";
				break;
			case 107:
				lexeme = "EQUALS";
				break;
			case 108:
				lexeme = "MINUS";
				break;
			case 109:
			case 309:
			case 310:
				lexeme = "NUMBER";
				break;
			case 110:
				lexeme = "PLUS";
				break;
			case 111:
				lexeme = "MULT";
				break;
			case 112:
				lexeme = "EXP";
				break;
			case 113:
				lexeme = "DIVIDE";
				break;
			case 114:
				lexeme = "COMMENT";
				break;
			case 115:
				lexeme = "EOF";
				break;
			case 116:
				lexeme = "SEMICOLON";
				break;
			case 117:
				lexeme = "SQRT";
				break;
			case 118:
				lexeme = "PRINT";
				break;
			case 119:
				lexeme = "IF";
				break;
			case 120:
				lexeme = "GREATER";
				break;
			case 121:
				lexeme = "GREATEREQUALS";
				break;
			case 122:
				lexeme = "LESS";
				break;
			case 123:
				lexeme = "LESSEQUALS";
				break;
			case 124:
				lexeme = "DEQUALS";
				break;
			case 125:
				lexeme = "NOTEQUALS";
				break;
			case 200:
				lexeme = "lexical error: badly formed number";
				break;
			case 201:
				lexeme = "lexical error: unterminated string";
				break;
			case 202:
				lexeme = "lexical error: illegal character";
				break;
		}
		return lexeme;
	}

	public String getLexeme(){
		return lexeme;
	}

	public String getT(){
		return token;
	}

	public void print()
	{
		if(lexeme != "COMMENT")
		{
			System.out.println(lexeme + "\t" + token);
		}
	}
}