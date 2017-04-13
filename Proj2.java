import java.io.InputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Proj2{
	
	public static void main(String[] args) throws IOException {
		//System.out.println("Hello world!");
		MyScanner scanner = new MyScanner(args[0]);
		int i = 0;

		Token t = scanner.getToken();

		while(t.getLexeme() != "EOF"){
			//t = scanner.getToken();
			if(t.getLexeme().length() <= 8)
				System.out.println(t.getLexeme() + "\t\t" + t.getT());
			else
				System.out.println(t.getLexeme() + "\t" + t.getT());
			t = scanner.getToken();
			//i = scanner.getCharIndex();
		}


		/*System.out.println(scanner.getToken("sample2.txt"));
		scanner.setCharIndex(4);
		System.out.println(scanner.getToken("sample2.txt"));*/
		
    
	}
}