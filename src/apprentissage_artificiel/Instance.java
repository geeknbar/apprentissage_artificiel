package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;

public class Instance {
	
	private String trainningSetName;
	private HashMap<String, ArrayList<String>> attributes;
	private ArrayList<ArrayList<String>> data;
	
	public Instance() {
		
	}

	public String getTrainningSetName() {
		return this.trainningSetName;
	}
	
	public HashMap<String, ArrayList<String>> getAttributes() {
		return this.attributes;
	}
	
	public ArrayList<ArrayList<String>> getData() {
		return this.data;
	}
}
