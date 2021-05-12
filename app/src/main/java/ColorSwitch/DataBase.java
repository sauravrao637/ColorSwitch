package ColorSwitch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;

public class DataBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int total;
	private String LeaderBaord = "SAURAV -1";
	public Map<String,String> Players  ;
	public ArrayList<savedStates> saved = new ArrayList<>();
	public DataBase() {
		Players = new HashMap<String,String>();
		//Players = new ArrayList<>();
	}
	public void  setTotal(int t) {
		total=t;
	}
	public int getTotal() {
		return this.total;
	}
	public String getLeaderBaord() {
		return LeaderBaord;
	}
	public void setLeaderBaord(String leaderBaord) {
		LeaderBaord = leaderBaord;
	}
	
}
