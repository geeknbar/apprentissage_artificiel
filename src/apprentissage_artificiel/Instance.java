package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Instance {
	
	private String trainningSetName;
	private HashMap<String, ArrayList<String>> attributes;
	private ArrayList<ArrayList<String>> data;
	
	public Instance() {
		this.trainningSetName = "";
		this.attributes = new HashMap<String, ArrayList<String>>();
		this.data = new ArrayList<ArrayList<String>>();
	}
	
	public void displayFile() {
		System.out.println("@relation " + this.trainningSetName + "\n");
		for (Entry<String, ArrayList<String>> entry : this.attributes.entrySet()) {
			String key = entry.getKey();
			ArrayList<String> values = entry.getValue();
			System.out.print("@attribute " + key + " {");
			boolean first = true;
			for (String value : values) {
				if (first) {
					System.out.print(value);
					first = false;
				} else {
					System.out.print("," + value);
				}
			}
			System.out.print("}\n");
		}
		System.out.println("\n@data");
		for (ArrayList<String> array : this.data) {
			int i = 0;
			for (String value : array) {
				if (i == array.size()-1) {
					System.out.print(value);
					i = 0;
				} else {
					System.out.print(value + ",");
					i++;
				}
			}
			System.out.print("\n");
		}
	}

	public void setTrainningSetName(String trainningSetName) {
		this.trainningSetName = trainningSetName;
	}
	
	public void setAttributes(HashMap<String, ArrayList<String>> attributes) {
		this.attributes = attributes;
	}
	
	public void setData(ArrayList<ArrayList<String>> data) {
		this.data = data;
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
