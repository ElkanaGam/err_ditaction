import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileAssistent {
	/*get a file name and return a list contains every line in the file as a seperate string
	 * pre: file is in legal form 
	 * */
	static List<String> FileToList(String fileName) throws IOException{ 
		File fromFile= new File(fileName);
		BufferedReader buffreader = null;			        /*open the file*/
		try {
			buffreader = new BufferedReader(new FileReader(fromFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pair;
		List <String>  WordList=new ArrayList<String>();
		try {
			while ((pair =buffreader.readLine())!=null) {	/*scan the line in the file and add every lint to the list*/	
				WordList.add(pair);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffreader.close();
		return WordList;
		
	}
		
	
}
