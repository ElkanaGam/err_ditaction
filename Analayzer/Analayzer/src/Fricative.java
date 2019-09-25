

public class Fricative extends Consonants {
	
	public Fricative(String letter) {
		super (letter);
		this.sonorntRank=2;
		switch (letter) {
		case "f":
			this.articulationPlace="labial";
			this.voicing=0;
			break;
		case "v":
			this.articulationPlace="labial";
			this.voicing=1;
			break;
		case "s":
			this.articulationPlace="dental";
			this.voicing=0;
			break;
		case "z":
			this.articulationPlace="dental";
			this.voicing=1;
			break;
		case "S": //consider replace by S (capital s)
			this.articulationPlace="postdental";
			this.voicing=0;
			break;
		case "x":
			this.articulationPlace="uvelar";
			this.voicing=0;
			break;
		case "r":
			this.articulationPlace="uvelar";
			this.voicing=1;
			break;
		case "h":
			this.articulationPlace="glottal";
			this.voicing=0;
			break;
		default:
			System.out.println("error letter");			
			break;
		}
	}

}
