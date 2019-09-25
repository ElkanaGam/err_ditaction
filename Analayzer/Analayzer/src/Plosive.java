
public class Plosive extends Consonants {

	public Plosive(String letter) {
		super (letter);
		switch (letter) {
			case "?":
				this.articulationPlace="glottal";
				this.voicing=0;
				break;
			case "b":
				this.articulationPlace="labial";
				this.voicing=1;
				break;
			case "p":
				this.articulationPlace="labial";
				this.voicing=0;
				break;
			case "t":
				this.articulationPlace="dental";
				this.voicing=0;
				break;
			case "d":
				this.articulationPlace="dental";
				this.voicing=1;
				break;
			case "k":
				this.articulationPlace="velar";
				this.voicing=0;
				break;
			case "g":
				this.articulationPlace="velar";
				this.voicing=1;
				break;
			default:
				System.out.println("error letter");
				break;
				
		}/*switch (ditSign) {
			case "<":
				//add here
				break;
			default:
				this.distortionType=0;
		}*/
	}
}
