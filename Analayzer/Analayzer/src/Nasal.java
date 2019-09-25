
public class Nasal  extends Consonants{
	
	public Nasal(String letter) {
		super (letter);
		this.voicing=1;
		this.sonorntRank=3;
		switch (letter) {
		case "m":
			this.articulationPlace="labial";
			break;
		case "n":
			this.articulationPlace="dental";
			break;
		default:
			System.out.println("error letter");
			break;
		}
	}
}
