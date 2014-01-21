package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ID3 {

	Instance instance;

	public ID3(Instance instance) {
		this.instance = instance;
	}

	public void choice() {
		int nbExamples = 0;
		double entropyS = 0;

		// Calcul du nombre de classes
		HashMap<String, Integer> numberOfClasses = new HashMap<String, Integer>();
		for (ArrayList<String> array : this.instance.getData()) {
			nbExamples++;
			if (!numberOfClasses.containsKey(array.get(array.size() - 1))) {
				numberOfClasses.put(array.get(array.size() - 1), 1);
			} else {
				Integer temp = numberOfClasses.get(array.get(array.size() - 1));
				temp++;
				numberOfClasses.put(array.get(array.size() - 1), temp);
			}
		}

		// Calcul de l'entropie de l'ensemble S (entropyS)
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Entry<String, Integer> entry : numberOfClasses.entrySet()) {
			values.add(entry.getValue());
		}
		entropyS = calculateEntropy(nbExamples, values);

		// Affichage temporaire
		System.out.println(numberOfClasses.toString());
		System.out.println("Entropie de S : " + entropyS);

		HashMap<String, HashMap<String, Integer>> numberOfOcc = new HashMap<String, HashMap<String, Integer>>();
		for (ArrayList<String> array : this.instance.getData()) {
			if (!numberOfOcc.containsKey(array.get(0))) {
				HashMap<String, Integer> newEntry = new HashMap<String, Integer>();
				newEntry.put(array.get(array.size() - 1), 1);
				numberOfOcc.put(array.get(0), newEntry);
			} else {
				HashMap<String, Integer> test = numberOfOcc.get(array.get(0));
				if (!test.containsKey(array.get(array.size() - 1))) {
					test.put(array.get(array.size() - 1), 1);
				} else {
					int toModify = test.get(array.get(array.size() - 1));
					toModify++;
					test.put(array.get(array.size() - 1), toModify);
					numberOfOcc.put(array.get(0), test);
				}
			}
		}
		
		double gain = entropyS;
		for (Entry<String, HashMap<String, Integer>> entry : numberOfOcc.entrySet()) {
			ArrayList<Integer> val = new ArrayList<Integer>();
			int ratio = 0;
			for (Entry<String, Integer> value : entry.getValue().entrySet()) {
				ratio += value.getValue();
				val.add(value.getValue());
			}
			gain -= ((double)ratio / (double)nbExamples) * calculateEntropy(ratio, val);
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
