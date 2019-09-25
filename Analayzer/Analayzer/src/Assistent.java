import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Assistent {
	/*get a String word, devied it to its syllable and return List contains all the syllable*/
	static List <Syllable> WordToSyllable (String word){
		List <Syllable> retList= new ArrayList<Syllable>();
		
		return retList;
		
	}
	/*return true iff a char c isnt in the list {a,e,i,o,u} */
	static boolean isNotVowel(char c) {
		if (c=='a'||c=='e'||c=='o'||c=='u'||c=='i') {
			return false;
		}
		return true;
	}
	/*return a list which each element is in type BoundList and contain pair of correspond
	 * syllable in src and prod. if syllable in src has no correspond syllable in prod then omission has occured,  
	 * and it will be match to null. for example:
	 * matchlist("banana", "nana")->[(ba,null),(na,na),(na,na)] */
	static List <BoundList<Syllable>> MatchSyllable (Word src, Word prod){
		 List <BoundList<Syllable>> retList=new ArrayList<BoundList<Syllable>>();
		 int srcl=src.SyllNum;
		 int prodl=prod.SyllNum;
		 if (srcl==prodl) {		//the number of Syllable is equal. there is no omission
			 CreateMatch(src, prod, 0, retList);
			 return retList;
         // prodl < srcl, omission has occurred
		 }else {			 
			 int index = 0;
			 int offset = srcl - prodl + 1;
			 double min_diff = Double.MAX_VALUE;
			 for (int i = 0; i< offset ; i++) {
				 if (FindDiff(src.SyllList.get(i), prod.SyllList.get(0))<min_diff) {
					 index  =  i;
				 }
			 }
			 CreateMatch(src, prod, index, retList);
			 return retList;			 
		 }
	}

	static int FindDiff (Syllable current, Syllable candidate) {
		int onsetDiff=ClusretrDiff(current.getPart("onset"), candidate.getPart("onset"));
		int ryhmeDiff=RyhmeDiff(current.getRhyme(), candidate.getRhyme());
		int codaDiff=ClusretrDiff(current.getPart("coda"), candidate.getPart("Coda"));
		// rhyme differences has double weight for calculation due its importance
		return onsetDiff+(ryhmeDiff*2)+codaDiff;

		
	}
	//for the consonants part of the syllble we have to consider this as more than one consonant, mean, it
	// a cluster
	//
	static int ClusretrDiff(List <Consonants> cur, List <Consonants> can) {
		int diff=0;
		int curL=cur.size();
		int canL=can.size();
		if (curL>canL){				//one or more Cons was omitted
			diff++;
			if (canL>0) {
				/*in this current version i assume that onset length <3, so 
				 *if omission has occured, up to one consonat has to be compared  
				 */
				Double tempDiff = Double.MAX_VALUE;
				for (int i=0; i<canL; i++) {
					Consonants a=cur.get(i);
					Consonants b=can.get(i);
					tempDiff = ConDiff(a, b) < tempDiff ? ConDiff(a, b) : tempDiff;
				}
				return (diff +tempDiff.intValue());
			}
			
		}
		else {						//nothing was omitted		
			for (int i=0; i<curL; i++) {
				diff=diff+(ConDiff(cur.get(i), can.get(i)));
				return diff;
			}
		}
		return diff;	
	}
	
	private static int ConDiff(Consonants a, Consonants b) {
		int diff=0;
		if (!a.articulationPlace.equals(b.articulationPlace)) {
			diff++;
		}
		if (a.voicing!=b.voicing) {
			diff++;
		}
		if (!a.getClass().equals(b.getClass())) {
			diff++;
		}
		return diff;
	}
	private static int RyhmeDiff(Phoneme a, Phoneme b) {
		int diff=0;
		if (!a.Name.equals(b.Name)){ //vowel subtitution
			diff++;			
		}
		return diff;
	}
	//get the undex represnets the ofsset between src start and prod start point and
	//match every syylble to coresponed syllble or null if needed
	static void  CreateMatch(Word src, Word prod, int index, List <BoundList<Syllable>> list ) {
		int j;
		//this is the part that was omitted at the begining of the word
		for (j  = 0; j < index; j++) {
			 BoundList<Syllable> boundList=new BoundList<>();
			 boundList.add(src.SyllList.get(j)); //create the match between every Syll and add it to the List
			 boundList.add(null);
			 list.add(boundList);
		}
		//the remaining syllble of the word
		for (j = index ; j < prod.SyllNum ; j++) {
			 BoundList<Syllable> boundList=new BoundList<>();
			 boundList.add(src.SyllList.get(j)); 
			 boundList.add(prod.SyllList.get(j));
			 list.add(boundList);
		//the  part that was omitted at the end of the word
		}
		for (j = prod.SyllNum; j< src.SyllNum; j++) {
			 BoundList<Syllable> boundList=new BoundList<>();
			 boundList.add(src.SyllList.get(j));
			 boundList.add(null);
			 list.add(boundList);
		}
	}
	
	static boolean isPermotation (String src, String prod) {
		// To Be Complete
		
		return false;
	}
	
	
	
	// Generic function to concatenate multiple lists in Java 
	//taken from https://www.techiedelight.com/concatenate-multiple-lists-java/
	public static<T> List<T> concatenate(List<T>... lists)
	{
		Stream<T> stream = Stream.of();
		for (List<T> l: lists)
			stream = Stream.concat(stream, l.stream());

		return stream.collect(Collectors.toList());
	}
}
