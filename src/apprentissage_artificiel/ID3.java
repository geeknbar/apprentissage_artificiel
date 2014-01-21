package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ID3 {

	// Instance 
	Instance instance;
	// Entropie de l'ensemble S
	double entropyS;

	public ID3(Instance instance) {
		this.instance = instance;
		this.entropyS = 0;
	}

	public void compute() {

		// Calcul du nombre d'exemples pour chaque classe
		HashMap<String, Integer> examplesPerClass = new HashMap<String, Integer>();
		for (ArrayList<String> array : this.instance.getData()) {
			if (!examplesPerClass.containsKey(array.get(array.size() - 1))) {
				examplesPerClass.put(array.get(array.size() - 1), 1);
			} else {
				Integer temp = examplesPerClass.get(array.get(array.size() - 1));
				temp++;
				examplesPerClass.put(array.get(array.size() - 1), temp);
			}
		}

		// Calcul de l'entropie de l'ensemble S
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Entry<String, Integer> entry : examplesPerClass.entrySet()) {
			values.add(entry.getValue());
		}
		this.entropyS = calculateEntropy(this.instance.getNumberOfRows(), values);

		// Affichage temporaire
		System.out.println(examplesPerClass.toString());
		System.out.println("Entropie de S : " + this.entropyS);

		// Calcul du gain pour l'attribut 0
		HashMap<String, HashMap<String, Integer>> examplesPerClassPerAttributs = new HashMap<String, HashMap<String, Integer>>();
		for (ArrayList<String> array : this.instance.getData()) {
			int sizeArray = array.size() - 1;
			if (!examplesPerClassPerAttributs.containsKey(array.get(0))) {
				HashMap<String, Integer> newEntry = new HashMap<String, Integer>();
				newEntry.put(array.get(sizeArray), 1);
				examplesPerClassPerAttributs.put(array.get(0), newEntry);
			} else {
				HashMap<String, Integer> test = examplesPerClassPerAttributs.get(array.get(0));
				if (!test.containsKey(array.get(sizeArray))) {
					test.put(array.get(sizeArray), 1);
				} else {
					int toModify = test.get(array.get(sizeArray));
					toModify++;
					test.put(array.get(sizeArray), toModify);
					examplesPerClassPerAttributs.put(array.get(0), test);
				}
			}
		}
		double gain = this.entropyS;
		for (Entry<String, HashMap<String, Integer>> entry : examplesPerClassPerAttributs.entrySet()) {
			ArrayList<Integer> val = new ArrayList<Integer>();
			int ratio = 0;
			for (Entry<String, Integer> value : entry.getValue().entrySet()) {
				ratio += value.getValue();
				val.add(value.getValue());
			}
			gain -= ((double)ratio / (double)this.instance.getNumberOfRows()) * calculateEntropy(ratio, val);
			val.clear();
		}

		// Affichage temporaire
		/*System.out.println(numberOfOcc.toString());
		double gain = entropyS;
		values.clear();
		for (Entry<String, Integer> entry : numberOfOcc.entrySet()) {
			values.add(entry.getValue());
		}
		gain -= calculateEntropy(nbExamples, values);*/
		System.out.println("Gain : " + gain);
	}

	private double calculateEntropy(int nbExamples, ArrayList<Integer> values) {
		double entropy = 0;
		for (Integer value : values) {
			double tmp = (double) value / (double) nbExamples;
			entropy += -(tmp * log2(tmp));
		}
		return entropy;
	}

	private double log2(double a) {
		return logb(a, 2);
	}

	private double logb(double a, double b) {
		return Math.log(a) / Math.log(b);
	}

}
