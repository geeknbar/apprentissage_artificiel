package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Instance {
	
	// Nom du jeu de donnees
	private String trainningSetName;
	// Stockage des attributs et des valeurs possibles
	private LinkedHashMap<String, ArrayList<String>> attributes;
	// Stockage des lignes d'exemples
	private ArrayList<ArrayList<String>> data;
	// Nombre de lignes d'exemple
	private int numberOfDataRows;
	
	public Instance() {
		this.trainningSetName = "";
		this.attributes = new LinkedHashMap<String, ArrayList<String>>();
		this.data = new ArrayList<ArrayList<String>>();
		this.numberOfDataRows = 0;
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
	
	public void setAttributes(LinkedHashMap<String, ArrayList<String>> attributes) {
		this.attributes = attributes;
	}
	
	public void setData(ArrayList<ArrayList<String>> data) {
		this.data = data;
	}
	
	public void setNumberOfDataRows(int numberOfRows) {
		this.numberOfDataRows = numberOfRows;
	}
	
	public String getTrainningSetName() {
		return this.trainningSetName;
	}
	
	public LinkedHashMap<String, ArrayList<String>> getAttributes() {
		return this.attributes;
	}
	
	public ArrayList<ArrayList<String>> getData() {
		return this.data;
	}
	
	public int getNumberOfDataRows() {
		return this.numberOfDataRows;
	}
}
