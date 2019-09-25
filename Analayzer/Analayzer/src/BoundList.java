import java.awt.List;
import java.util.ArrayList;

public class BoundList<T> extends ArrayList <T> {
	
	int bound=0;
	
	public boolean add( T t) {
		if (this.bound<2){
			super.add(t);
			this.bound++;
			return true;
		}return false;
	}
	

}
