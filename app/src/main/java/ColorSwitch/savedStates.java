package ColorSwitch;

import java.io.Serializable;
import java.util.ArrayList;

public class savedStates implements Serializable {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> objects;
	public savedStates(String[] details) {
		objects = new ArrayList<String>();
		for(int i = 0;i<details.length;i++)objects.add(details[i]);
	}
	
	
	
}
