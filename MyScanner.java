import java.io.InputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;


//errors, whitespace,newline

public class MyScanner{
	int charIndex;
	String tokenString = "";
	String fileContents = "";
	Token token = null;
	private int currentState = 0;
	private int nextState[][] = {
		{17,12,18,20,2,3,4,5,11,13,6,6,9,7,0,0,22,115,1,202,26,6,31,6,6,6,33,6,37,38,40,43},
		{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,202,100,100,100,100,100,100,100,100,100,100,100,100},
		{101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,101,202,101,101,101,101,101,101,101,101,101,101,101,101},
		{102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,202,102,102,102,102,102,102,102,102,102,102,102,102},
		{103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,103,202,103,103,103,103,103,103,103,103,103,103,103,103},
		{104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,104,202,104,104,104,104,104,104,104,104,104,104,104,104},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,6,6,6,6,6,6,6,6,6,6},
		{7,7,7,7,7,7,7,7,7,7,7,7,7,8,201,7,7,201,7,202,7,7,7,7,7,7,7,7,7,7,7,7},
		{106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,202,106,106,106,106,106,106,106,106,106,106,106,106},
		{9,9,9,9,9,9,9,9,9,9,9,9,10,9,201,9,9,201,9,202,9,9,9,9,9,9,9,9,9,9,9,9},
		{106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106,202,106,106,106,106,106,106,106,106,106,106,106,106},
		{107,107,107,107,107,107,107,107,42,107,107,107,107,107,107,107,107,107,107,202,107,107,107,107,107,107,107,107,107,107,107,107},
		{108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,108,202,108,108,108,108,108,108,108,108,108,108,108,108},
		{109,109,109,109,109,109,109,14,109,13,109,15,109,109,109,109,109,109,109,202,109,109,109,109,109,109,109,109,109,109,109,109},
		{200,200,200,200,309,309,309,309,200,23,200,200,309,309,309,309,309,309,309,202,200,200,200,200,200,200,200,200,200,200,200,200},
		{16,16,200,200,200,200,200,200,200,25,200,200,200,200,200,200,200,200,200,202,200,200,200,200,200,200,200,200,200,200,200,200},
		{0,0,0,0,0,0,0,0,0,25,0,0,0,0,0,0,0,0,0,202,0,0,0,0,0,0,0,0,0,0,0,0},
		{110,110,110,110,110,110,110,110,110,110,110,110,110,110,110,110,110,110,110,202,110,110,110,110,110,110,110,110,110,110,110,110},
		{111,111,19,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,202,111,111,111,111,111,111,111,111,111,111,111,111},
		{112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,112,202,112,112,112,112,112,112,112,112,112,112,112,112},
		{113,113,113,21,113,113,113,113,113,113,113,113,113,113,113,113,113,113,113,202,113,113,113,113,113,113,113,113,113,113,113,113},
		{21,21,21,21,21,21,21,21,21,21,21,21,21,21,114,21,21,114,21,21,21,21,21,21,21,21,21,21,21,21,21,21},
		{22,22,22,22,22,22,22,22,22,22,22,22,22,22,114,22,22,114,22,22,22,22,22,22,22,22,22,22,22,22,22,22},
		{109,109,109,109,109,109,109,109,109,23,109,24,109,109,109,109,109,109,109,202,109,109,109,109,109,109,109,109,109,109,109,109},
		{0,0,0,0,0,0,0,0,0,25,310,310,0,0,0,0,0,0,0,202,310,310,310,310,310,310,310,310,310,310,310,310},
		{109,109,109,109,109,109,109,109,109,25,109,109,109,109,109,109,109,109,109,202,109,109,109,109,109,109,109,109,109,109,109,109},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,27,6,6,6,6,6,6,105,105,105,105},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,28,6,6,6,6,6,105,105,105,105},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,6,29,6,6,6,6,105,105,105,105},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,6,6,30,6,6,6,105,105,105,105},
		{118,118,118,118,118,118,118,118,118,118,6,6,118,118,118,118,118,118,118,305,6,6,6,6,6,6,6,6,118,118,118,118},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,6,6,6,32,6,6,105,105,105,105},
		{119,119,119,119,119,119,119,119,119,119,6,6,119,119,119,119,119,119,119,305,6,6,6,6,6,6,6,6,119,119,119,119},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,6,6,6,6,6,34,105,105,105,105},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,35,6,6,6,6,6,6,105,105,105,105},
		{105,105,105,105,105,105,105,105,105,105,6,6,105,105,105,105,105,105,105,305,6,6,6,6,36,6,6,6,105,105,105,105},
		{117,117,117,117,117,117,117,117,117,117,6,6,117,117,117,117,117,117,117,305,6,6,6,6,6,6,6,6,117,117,117,117},
		{116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,116,202,116,116,116,116,116,116,116,116,116,116,116,116},
		{120,120,120,120,120,120,120,120,39,120,120,120,120,120,120,120,120,120,120,202,120,120,120,120,120,120,120,120,120,120,120,120},
		{121,121,121,121,121,121,121,121,121,121,121,121,121,121,121,121,121,121,121,202,121,121,121,121,121,121,121,121,121,121,121,121},
		{122,122,122,122,122,122,122,122,41,122,122,122,122,122,122,122,122,122,122,202,122,122,122,122,122,122,122,122,122,122,122,122},
		{123,123,123,123,123,123,123,123,123,123,123,123,123,123,123,123,123,123,123,202,123,123,123,123,123,123,123,123,123,123,123,123},
		{124,124,124,124,124,124,124,124,124,124,124,124,124,124,124,124,124,124,124,202,124,124,124,124,124,124,124,124,124,124,124,124},
		{0,0,0,0,0,0,0,0,44,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,125,202,125,125,125,125,125,125,125,125,125,125,125,125}
	};
	
	public MyScanner(String filePath) throws IOException
	{
		fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
		//System.out.println(getFileContentLength());
		fileContents += '\0';
		//System.out.println(getFileContentLength());*/
		charIndex = 0;
		tokenString = "";
	}

	public void setCharIndex(int i){
		charIndex = i;
	}

	public int getCharIndex(){
		return charIndex;
	}

	private int charClass(char a)
	{
		boolean isDigit = "1234567890".indexOf( a ) != -1; // if buttonpressed is a digit
		boolean isLetter = "aABbCcDdfGgHhiJjKkLlMmnOopqrstUuVvWwXxYyZz".indexOf( a ) != -1;
		boolean isE = "Ee".indexOf( a ) != -1;
		boolean isUppercaseP = "P".indexOf( a ) != -1;
		boolean isUppercaseR = "R".indexOf( a ) != -1;
		boolean isUppercaseI = "I".indexOf( a ) != -1;
		boolean isUppercaseN = "N".indexOf( a ) != -1;
		boolean isUppercaseT = "T".indexOf( a ) != -1;
		boolean isUppercaseF = "F".indexOf( a ) != -1;
		boolean isUppercaseS = "S".indexOf( a ) != -1;
		boolean isUppercaseQ = "Q".indexOf( a ) != -1;
		if ( a == '+' )
		   return 0;
		else if ( a == '-')
		   return 1;
		else if ( a == '*')
		   return 2;
		else if ( a == '/')
		   return 3;
		else if ( a == '(')
		   return 4;
		else if ( a == ')')
		   return 5;
		else if ( a == ',')
		   return 6;
		else if ( a == '.')
		   return 7;
		else if ( a == '=')
		   return 8;
		else if ( isDigit )
		   return 9;
		else if ( isLetter )
		   return 10;
		else if ( isE )
		   return 11;
		else if ( a == '\'' )
		   return 12;
		else if ( a == '"' )
		   return 13;
		else if ( a == '\n' ||  a == '\r')
		   return 14;
		else if ( a == ' ' )
		   return 15;
		else if ( a == '#' )
		   return 16;
		else if ( a == '\0' )
		   return 17;
		else if ( a == '%' )
		   return 18;
		else if ( isUppercaseP )
		   return 20;
		else if ( isUppercaseR )
		   return 21;
		else if ( isUppercaseI )
		   return 22;
		else if ( isUppercaseN )
		   return 23;
		else if ( isUppercaseT )
		   return 24;
		else if ( isUppercaseF )
		   return 25;
		else if ( isUppercaseS )
		   return 26;
		else if ( isUppercaseQ )
		   return 27;
		else if ( a == ';' )
			return 28;
		else if ( a == '>' )
			return 29;
		else if ( a == '<' )
			return 30;
		else if ( a == '!' )
			return 31;

		else
		   return 19;  // illegal character
	}

	public Token getToken(){
		//tokenString = "";
		//Token token = null;

		do{
			tokenString = "";
			//System.out.println(charIndex);
			//System.out.println(fileContents.charAt(charIndex));
			do{
				char currentCharacter = fileContents.charAt(charIndex++);
				int charClass = charClass(currentCharacter);
				int prevState = currentState; //1
				currentState = nextState[currentState][charClass];
				//System.out.println(currentCharacter + " " + charClass);

				switch( currentState )
				{
					case 0:  
						//setCharIndex(getCharIndex()+1);
						break;
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
					case 11:
					case 12:
					case 13:
					case 14:
					case 15:
					case 16:
					case 17:
					case 18:
					case 19:
					case 20:
					case 26:
					case 27:
					case 28:
					case 29:
					case 30:
					case 31:
					case 32:
					case 33:
					case 34:
					case 35:
					case 36:
					case 37:
					case 38:
					case 39:
					case 40:
					case 41:
					case 42:
					case 43:
					case 44:
						tokenString += currentCharacter;
					    //setCharIndex(getCharIndex()+1);
						break;
					// Comments 
					case 21:
					case 22:
					    //setCharIndex(getCharIndex()+1);
						break;
					case 23:
					case 24:
					case 25:
						tokenString += currentCharacter;
					    //setCharIndex(getCharIndex()+1);
						break;
					case 100: // start over state
					case 101:
					case 102:
					case 103:
					case 104:
					case 105:
					case 106:
					case 107:
					case 108:
					case 109:
					case 110:
					case 111:
					case 112:
					case 113:
					case 114:
					case 115:
					case 116:
					case 117:
					case 118:
					case 119:
					case 120:
					case 121:
					case 122:
					case 123:
					case 124:
					case 125:
						charIndex--;
						break;
					case 200:
					case 201:
					case 202:
						tokenString += currentCharacter;
						//setCharIndex(getCharIndex()+1); // pushback once
						//currentState = 0; // restart due to error
						break;
					case 305:
						//tokenString = tokenString.substring(0,tokenString.length()-1);
						break;
					case 309:
					case 310:
						tokenString = tokenString.substring(0,tokenString.length()-1);
						charIndex-=2;
						break;
				}
				//System.out.println("how: " + currentState + " " + (currentState <= 99) + " " +  token);

				/*if(currentState <= 99)
				{
					System.out.println("HOW: "+ currentState );
				}*/

			} while(currentState <= 99 && (getCharIndex() < getFileContentLength()));

			token = new Token(currentState,tokenString);
			currentState = 0;
			String lexeme = token.getLexeme();
		} while(token.getLexeme() == "COMMENT");

		//currentState = 0;


		return token;

	}

	public int getFileContentLength(){
		return fileContents.length();
	}

	public void S(){
		double ans = EXPRESSION();
		if ( token.getLexeme() == "EOF" ){
			System.out.println( "string accepted" );
			System.out.println( ans );
		}

		else
			System.out.printf( "string not accepted (error on or before position %d)\n", charIndex );

	}

	public double EXPRESSION(){
		double ans = 0;
		//double x = P();
		double x = T();
		double y = A();
		getToken(); // ;

		ans = x + y;

		return ans;
	}


	// MULTIPLY
	public double T(){
		//System.out.println("T");
		double ans = 0;
		double x = F();
		double y = B();
		ans = x * y;


		return ans;
	}


	// ADD
	public double A(){
		double ans = 0;
		//System.out.println("A");
		//System.out.println("A: "+token.getLexeme());
		if(token.getLexeme() == "PLUS"){
			getToken(); 
			double x = T();
			double y = A();
			ans = x + y;
		}

		else if(token.getLexeme() == "MINUS"){
			getToken();
			double x = T();
			double y = A();
			ans = y - x;
		}

		return ans;
	}

	public double B(){
		//System.out.println("B");
		double ans = 0;
		if(token.getLexeme() == "MULT"){
			getToken(); 
			double x = F();
			double y = B();
			ans = x * y;
		}
		else if(token.getLexeme() == "DIVIDE"){
			getToken(); 
			double x = F();
			double y = B();
			System.out.println(x + " " + y);
			ans = y / x;
		}
		else
			return 1;

		return ans;
	}

	public double F(){
		double x = I();
		double y = J();

		return Math.pow(x,y);
	}

	public double J(){
		double ans = 1;
		if(token.getLexeme() == "EXP"){
			getToken(); 
			double x = I();
			double y = J();
			ans = Math.pow( x ,y);
		}
		else
			return 1;
		return ans;

	}

	public double I()
	{
		//System.out.println("F");
		if(token.getLexeme() == "LPAREN"){
			getToken();
			double x = EXPRESSION();
			if(token.getLexeme() == "RPAREN")
			{
				getToken();
			}
			else{
				//getToken();
			}

			//getToken(); // ')'
			return x;
		}

		else if(token.getLexeme() == "MINUS"){
			getToken();
			return -(NUMBER());
		}

		else if(token.getLexeme() == "SQRT"){
			getToken();
			return SQRT();
		}

		/*System.out.println("F");
		if ( token == '-' )
		{
			getNextToken();
			return -(X());
		}
		else*/
        return NUMBER();
	}

	public double NUMBER()
	{
		double num =  Double.parseDouble(token.getT());
		//System.out.println(token.getT());
		getToken();
		//System.out.println("currToken: " + token.getT());
        return num;
	}


	public double SQRT(){
		double ans = 0;
		if(token.getLexeme() == "LPAREN"){
				getToken(); // number
				ans = EXPRESSION();
				//getToken();
				
		}

		return Math.sqrt(ans);
	}

}