import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class AnalayzeData {

	List <String> ListOfWords;
	Map <Errors, String> ErrorsMap= new HashMap<Errors, String>();
	
	public AnalayzeData(String fileName) {
		try {
			ListOfWords=FileAssistent.FileToList(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void MakeAnalize () {
		String src;
		String prod;
		String [] toSplit=null;
		for (String couple: this.ListOfWords) {
			toSplit=couple.split("\\s+");
			src=toSplit[0];
			prod=toSplit[1];
			Errors.FindErrors(src, prod, ErrorsMap);
		}
		
	}
	
}
