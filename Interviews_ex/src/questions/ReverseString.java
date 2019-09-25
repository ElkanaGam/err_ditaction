package questions;

public class ReverseString {
	
	public static Stirng Reverse_String( String s) {
		
		if (s.length() == 1)
		{
			return s.charAt(0);
		}
		else
		{
			swap(s.charAt(0), s.charAt(s.length()-1));
			Reverse_String(	)
		}
			
		
	}
	
	public static void swap (char a, char b) {
		char temp = a;
		a = b;
		b =temp;
	}
}
