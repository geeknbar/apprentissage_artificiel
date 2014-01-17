package apprentissage_artificiel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Reader {

	private String filePath;
	private String trainningSetName;
	private HashMap<String, ArrayList<String>> attributes;
	private ArrayList<ArrayList<String>> data;

	public Reader(String filePath) {
		this.filePath = filePath;
		this.trainningSetName = "";
		this.attributes = new HashMap<String, ArrayList<String>>();
		this.data = new ArrayList<ArrayList<String>>();
	}

	public void read() {
		try {
			InputStream ips = new FileInputStream(this.filePath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			boolean isData = false;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					if ("@relation".equals(line.split(" ", 3)[0])) {
						this.trainningSetName = line.split(" ", 3)[1];
					}

					if ("@attribute".equals(line.split(" ", 3)[0])) {
						String attribute = line.split(" ", 3)[1];
						String values = line.split(" ", 3)[2];
						
						values = values.replaceAll(" ", "");
						values = values.replaceAll("\\{", "");
						values = values.replaceAll("\\}", "");
						
						ArrayList<String> possibleValues = new ArrayList<String>();
						for (String value : values.split(",")) {
							possibleValues.add(value);
						}
						this.attributes.put(attribute, possibleValues);
					}

					if ("@data".equals(line.split(" ", 3)[0])) {
						isData = true;
					} else if (isData) {
						ArrayList<String> columns = new ArrayList<String>();
						for (String column : line.split(",")) {
							columns.add(column);
						}
						this.data.add(columns);
					}
				}

			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String getTrainningSetName() {
		return this.trainningSetName;
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
		System.out.println("@data");
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

}
