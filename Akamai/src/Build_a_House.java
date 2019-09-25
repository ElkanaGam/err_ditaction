
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

public class Build_a_House {
	//print the condition we met
	//position is parameter for the contedo condition
	 static void PrintStatment (String condition, String position) {
		switch (condition) {
		case "akamai":
			System.out.println("The first and the second words are Akamai!");
			break;
		case "contedo":
			System.out.println("The "+position+" word is Contedo!");
			break;
		case "no_dup":
			System.out.println("There is no Duplications!");
			break;
		default:
			System.out.println("We can Build a House");
		
		}
		
	}
	
	//get index and word and update the Map[word.charAt(index] count
	public static void UpdateLettesMap (Map <Character, Integer> map, String word ,int i )
	{
		Integer cnt  = map.get(word.charAt(i));
		if (cnt == null )
		{
			map.put(word.charAt(i),1);
		}
		else {
			map.put(word.charAt(i), cnt+1);
		}
		
	}
	
	// checking if a cuople of words is Akamai
	public static boolean isAkamai(String word1, String word2) {
		
		boolean ret_val = true;
		if (word1 == null || word2 == null)
		{
			return ret_val;
		}
		
		int len1 = word1.length();
		int len2 = word2.length();
		
		Map<Character , Integer> letter_table1 = new HashMap<Character , Integer> ();
		Map<Character , Integer> letter_table2 = new HashMap<Character , Integer> ();
		
		if (len1 != len2)
		{
			ret_val = false;
		}
		else
		{
			for (int i = 0; i < len1; i++)
			{
				UpdateLettesMap(letter_table1, word1, i);
				UpdateLettesMap(letter_table2, word2, i);
				
			}
			// if there is some letters which doesn't appear in the other word so the words are not Akamai
			if (!letter_table1.keySet().equals(letter_table2.keySet()))
			{
				ret_val = false;
			}
			// checking if the number of appearance of every letter in every word is equal
			else
			{
				for (Character c : letter_table1.keySet()) {
					if (letter_table1.get(c) !=  letter_table2.get(c))
					{
						ret_val = false;
					}
				}
				
			}
			
		}
		return ret_val;
	}
	// checking if a given word is reversible
	public static boolean isRevarsible (String word) {
		boolean ret_val = true;
		if (word == null) {
			return ret_val;
		
		}
		int len = word.length();
		int start = 0;
		int end = len - 1;
		while (start < end )
		{
			if (word.charAt(start)!=word.charAt(end))
			{
				ret_val = false;
			}
			start++;
			end--;
			
		}
		return ret_val;
		
	}
	
	//checking if a word is reversible and contains a char from : {'c','o','n','t','d','e','1'}
	public static boolean isCotendo (String word, String position) {
		List<Character> letters = Arrays.asList('c','o','n','t','d','e','1');
		// there is no char at the word so it not contains them 
		if ((word == null) || word.length() == 0)
		{
			return  false;	
		}
		else
		{
			// iterate over the word and find if there any 'c','o','n','t','d','e','1' in it
			for (Character c:letters)
			{
				for(int i = 0; i < word.length() - 1; i++) {
					if (word.charAt(i) == c) {
						if( isRevarsible(word))
						{
							PrintStatment("contedo", position);
							return true;
						}
					}
				}
			}
		}
		//if we reached here so the word doesn't contain any 'contedo1' letter
		return false;	
	}
	
	
	//checking if the Strings array ontains any duplication. return false if does. 
	public static boolean isNotDuplicate (String [] words) {
		Set <String> find_dup = new HashSet<String>();
		if (words == null)
		{
			return true;
		}
		int len = words.length;
		//iterate over the string array and insert to hashset. if word already exist then return false
		for (int i = 0; i < len ; i++)
		{
			if(!find_dup.add(words[i]))
				//duplication is exist
			{
				return false;
			}
		}
		return true;	
	}
	//sum all the conditions and prints if we can build a house
	public static void CanWe (String [] words ) {
		int sum = 0;
		if (words == null)
		{
			PrintStatment("no_dup","dont_care");
			sum++;
			
		}
		else 
		{
			if (words.length > 1)
			{
				if (isAkamai(words[0], words[1]))
				{
					PrintStatment("akamai","dont_care");
					sum++;
				}
				if (isCotendo(words[0], "first") || isCotendo(words[1], "second"))
				{
					sum++;
				}
				if (isNotDuplicate(words))
				{
					PrintStatment("no_dup", "dont_care");
					sum++;
				}
				
			}
			// array size < 2 so for sure there is no duplication but there is no second words so 
			// akamai condition is not met 
			else if (words.length ==1)
			{
				PrintStatment("no_dup", "dont_care");
				sum++;
				if (isCotendo(words[0], "first"))
				{
					sum++;
				}
			}
			//we avoided duplication, but the other conditions are not net
			else {
				PrintStatment("no_dup", "dont_care");
			}
		}
		if (sum >= 2) {
			PrintStatment("build", "dont_care");
		}
		
		
		
	}
	
	
	public static void main(String[] args) {

		String [] arr  = {"abcac", "acbca"};
		CanWe(arr);
		//System.out.println(isNotDuplicate(arr));
		
	}

}
