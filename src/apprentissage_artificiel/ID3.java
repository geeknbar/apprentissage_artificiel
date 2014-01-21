package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;

public class ID3 {

	Instance instance;
	
	public ID3(Instance instance) {
		this.instance = instance;
	}
	
	public void calculateEntropy() {
		HashMap<String, Integer> numberOfOcc = new HashMap<String, Integer>();
		HashMap<String, Integer> numberOfClasses = new HashMap<String, Integer>();
		for (ArrayList<String> array : this.instance.getData()) {
			if (!numberOfOcc.containsKey(array.get(0))) {
				numberOfOcc.put(array.get(0), 1);
			} else {
				Integer temp = numberOfOcc.get(array.get(0));
				temp++;
				numberOfOcc.put(array.get(0), temp);
			}
		}
		System.out.println(numberOfOcc.toString());
		
		/*HashMap<String, Integer> values = new HashMap<String, Integer>();
		for (ArrayList<String> array : this.instance.getData()) {
			if (!values.containsKey(array.get(array.size()-1))) {
				values.put(array.get(array.size()-1), 1);
			} else {
				Integer temp = values.get(array.get(array.size()-1));
				temp++;
				values.put(array.get(array.size()-1), temp);
			}
		}*/
	}
	
	private double log2(double a) {
		return logb(a,2);
	}
	
	private double logb(double a, double b) {
		return Math.log(a) / Math.log(b);
	}
	
}
