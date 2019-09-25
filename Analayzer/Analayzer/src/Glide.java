
public class Glide extends Consonants {
	
	public Glide (String letter) {
		super (letter);
		this.voicing=1;
		this.sonorntRank=5;
		switch (letter) {
		case "j":
			this.articulationPlace="palatal";
			break;
		case "l":
			this.articulationPlace="dental";
			break;
		default:
			System.out.println("error letter");
			break;
		}
		
	}

}
