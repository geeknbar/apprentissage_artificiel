package apprentissage_artificiel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Reader {

	private String filePath;
	private Instance instance;
	
	private String trainningSetName;
	private HashMap<String, ArrayList<String>> attributes;
	private ArrayList<ArrayList<String>> data;

	public Reader(String filePath) {
		this.filePath = filePath;
		this.trainningSetName = "";
		this.attributes = new HashMap<String, ArrayList<String>>();
		this.data = new ArrayList<ArrayList<String>>();
		this.instance = new Instance();
	}

	public Instance read() {
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
		this.setInstance();
		return this.instance;
	}
	
	private void setInstance() {
		this.instance.setTrainningSetName(this.trainningSetName);
		this.instance.setAttributes(this.attributes);
		this.instance.setData(this.data);
	}

}
