import java.util.ArrayList;
import java.util.List;

public class Syllable {
	
	private List <Consonants> onset=new ArrayList<Consonants>();
	private Vowel rhyme=null;
	private List <Consonants>coda=new ArrayList<Consonants>();
	public boolean isCluster;
	public boolean isClosed;
	public int position;
	
	/*
	 * consider another partition, regard 2 vowels (divide every vowel to another syll!! 
	 */
	public Syllable(String src, int position) {
		int i=0;
		int j;
		while (Assistent.isNotVowel(src.charAt(i))) {
			i++;
		}
		j = i;
		while (!Assistent.isNotVowel(src.charAt(j))) {
			j++;
		}
		onset=initiatePhoneme(src.substring(0, i));
		rhyme=new Vowel(src.substring(i,j));
		coda=initiatePhoneme(src.substring(j+1,src.length()));
		isCluster = onset.size() > 1;
		isClosed = coda.size() > 0;
		this.position = position;
	}
	
	/*return a list of the consonants of the Syllable */
	private List <Consonants> initiatePhoneme(String src) {
		int l=src.length();
		List <Consonants> consList= new ArrayList<Consonants>();
		for (int i=0; i<l; i++) {			//iterate over the chars
			char[] ch= {src.charAt(i)};
			String str =new String(ch);
			//Plosive case 
			if (str.equals("b")||str.equals("p") || str.equals("d") ||
					str.equals("k") ||str.equals("g") ||str.equals("?")) {
				consList.add(new Plosive(str));
			// if ch=='t' have to check if the target letter is 'ts'	
			}else if((str.equals("t"))&&(i<l-1)){
				char[] ch1= {src.charAt(i+1)};
				String str2=new String (ch1);
				if (str2.equals("s")) {
					consList.add(new Deafricative("ts"));
				}else {
					consList.add(new Plosive("t"));
				}
			}
			//Fricative case
			else if(str.equals("v")||str.equals("f")||str.equals("s")||str.equals("z") ||
					str.equals("S")||str.equals("x")||str.equals("r")||str.equals("h")) {
				consList.add(new Fricative(str));
					
			}
			//Nasal case
			else if(str.equals("m")||str.equals("n")) {
				consList.add(new Nasal(str));
			}
			//Glide case
			else if(str.equals("l")||str.equals("j")) {
				consList.add(new Glide(str));
			}
		}return consList;
	}

	public List <Consonants> getPart(String part) {
		if (part.equals("coda")) {
			return coda;
		}
		else {
			return onset;
		}
	}
	public List <Consonants> getOnset() {
		return onset;
	}


	public Phoneme getRhyme() {
		return rhyme;
	}


	public List <Consonants> getCoda() {
		return coda;
	}
	
	/*public static void main(String [] arg) {
		Syllable mys= new Syllable ("rav");
		System.out.println(mys.onset.get(0).Name);
	}*/

	


	
	
}
