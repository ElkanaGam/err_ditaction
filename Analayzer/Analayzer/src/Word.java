import java.util.List;



public class Word {

	String src;
	List <Syllable> SyllList;
	int SyllNum;
	public Word (String src) {
		this.src=src;
		this.SyllList=Assistent.WordToSyllable(src);
		this.SyllNum=this.SyllList.size();
	}
	
}
