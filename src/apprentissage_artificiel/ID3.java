package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ID3 {
	/*
	 * // Instance private Instance instance; // Entropie de l'ensemble S
	 * private double entropyS; // Meilleur gain temporaire private String
	 * topGain[];
	 * 
	 * public ID3(Instance instance) { this.instance = instance; this.entropyS =
	 * 0; this.topGain = new String[2]; this.topGain[0] = ""; this.topGain[1] =
	 * "0.0"; }
	 * 
	 * public void compute() { searchRoot(); }
	 * 
	 * private void searchRoot() { // Calcul du nombre d'exemples pour chaque
	 * classe HashMap<String, Integer> examplesPerClass = new HashMap<String,
	 * Integer>(); for (ArrayList<String> array : this.instance.getData()) { if
	 * (!examplesPerClass.containsKey(array.get(array.size() - 1))) {
	 * examplesPerClass.put(array.get(array.size() - 1), 1); } else { Integer
	 * temp = examplesPerClass.get(array.get(array.size() - 1)); temp++;
	 * examplesPerClass.put(array.get(array.size() - 1), temp); } }
	 * 
	 * // Calcul de l'entropie de l'ensemble S ArrayList<Integer> values = new
	 * ArrayList<Integer>(); for (Entry<String, Integer> entry :
	 * examplesPerClass.entrySet()) { values.add(entry.getValue()); }
	 * this.entropyS =
	 * calculateEntropy(this.instance.getNumberOfDataRows(),values);
	 * 
	 * // Affichage temporaire System.out.println(examplesPerClass.toString());
	 * System.out.println("Entropie de S : " + this.entropyS);
	 * System.out.println(this.instance.getAttributes().toString());
	 * 
	 * // Calcul du gain pour l'attribut 0 for (int i = 0; i <
	 * this.instance.getAttributes().size() - 1; i++) { HashMap<String,
	 * HashMap<String, Integer>> examplesPerClassPerAttributs = new
	 * HashMap<String, HashMap<String, Integer>>(); int sizeArray = 0; for
	 * (ArrayList<String> array : this.instance.getData()) { sizeArray =
	 * array.size() - 1; if
	 * (!examplesPerClassPerAttributs.containsKey(array.get(i))) {
	 * HashMap<String, Integer> newEntry = new HashMap<String, Integer>();
	 * newEntry.put(array.get(sizeArray), 1);
	 * examplesPerClassPerAttributs.put(array.get(i), newEntry); } else {
	 * HashMap<String, Integer> test =
	 * examplesPerClassPerAttributs.get(array.get(i)); if
	 * (!test.containsKey(array.get(sizeArray))) {
	 * test.put(array.get(sizeArray), 1); } else { int toModify =
	 * test.get(array.get(sizeArray)); toModify++;
	 * test.put(array.get(sizeArray), toModify);
	 * examplesPerClassPerAttributs.put(array.get(i), test); } } } double gain =
	 * this.entropyS; for (Entry<String, HashMap<String, Integer>> entry :
	 * examplesPerClassPerAttributs.entrySet()) { ArrayList<Integer> val = new
	 * ArrayList<Integer>(); int ratio = 0; for (Entry<String, Integer> value :
	 * entry.getValue().entrySet()) { ratio += value.getValue();
	 * val.add(value.getValue()); } gain -= ((double) ratio / (double)
	 * this.instance.getNumberOfDataRows()) * calculateEntropy(ratio, val);
	 * val.clear(); } // Affichage temporaire de chaque gain
	 * System.out.println("Gain : " + gain); if (gain >
	 * Double.valueOf(topGain[1])) { topGain[0] =
	 * this.instance.getAttributes().keySet().toArray()[i].toString();
	 * topGain[1] = String.valueOf(gain); } } // Affichage temporaire du
	 * meilleur noeud trouve System.out.println("Gain : " + topGain[0] + " : " +
	 * topGain[1]); }
	 */

	private Instances instances;

	public ID3(Instances instances) {
		this.instances = instances;
	}

	/**
	 * 
	 * @param instances
	 *            exemples
	 * @param instanceClass
	 *            attributCible
	 * @param attributes
	 *            attributsNonCibles
	 */
	public void recursive(Instances instances, InstanceClass instanceClass,
			ArrayList<Integer> attributes) {
		if (instances.getInstances().size() == 0) { /* Nœud terminal */
			// retourner un nœud Erreur
		} else if (attributes.size() == 0) { /* Nœud terminal */
			// retourner un nœud ayant la valeur la plus représentée pour
			// attributCible
		} else {
			HashSet<String> instanceClassValues = new HashSet<String>();
			for (Instance instance : instances.getInstances()) {
				instanceClassValues.add(instance.getInstanceClass().getValue());
			}
			if (instanceClassValues.size() == 1) {
				// retourner un nœud ayant cette valeur
			} else {
				Attribute selectedAttribute = bestAttribute(instances,
						attributes);
			}
		}

	}

	public Attribute bestAttribute(Instances instances, ArrayList<Integer> attributes) {
		for (Integer i : attributes) {
			HashMap<String, HashMap<String, Integer>> examplesPerClassPerAttributs = new HashMap<String, HashMap<String, Integer>>();
			for (Instance instance : instances.getInstances()) {
				if (!examplesPerClassPerAttributs.containsKey(instance.getAttributes().get(i))) {
					HashMap<String, Integer> newEntry = new HashMap<String, Integer>();
					newEntry.put(instance.getInstanceClass().getValue(), 1);
					examplesPerClassPerAttributs.put(instance.getAttributes().get(i).getValue(), newEntry);
				} else {
					HashMap<String, Integer> test = examplesPerClassPerAttributs.get(instance.getAttributes().get(i));
					if (!test.containsKey(instance.getInstanceClass().getValue())) {
						test.put(instance.getInstanceClass().getValue(), 1);
					} else {
						int toModify = test.get(instance.getInstanceClass().getValue());
						toModify++;
						test.put(instance.getInstanceClass().getValue(), toModify);
						examplesPerClassPerAttributs.put(instance.getAttributes().get(i).getValue(), test);
					}
				}
			}
			System.out.println(examplesPerClassPerAttributs.toString());
			/*double gain = this.entropyS;
			for (Entry<String, HashMap<String, Integer>> entry : examplesPerClassPerAttributs
					.entrySet()) {
				ArrayList<Integer> val = new ArrayList<Integer>();
				int ratio = 0;
				for (Entry<String, Integer> value : entry.getValue().entrySet()) {
					ratio += value.getValue();
					val.add(value.getValue());
				}
				gain -= ((double) ratio / (double) this.instance.getNumberOfDataRows()) * calculateEntropy(ratio, val);
				val.clear();
			}
			// Affichage temporaire de chaque gain
			System.out.println("Gain : " + gain);
			if (gain > Double.valueOf(topGain[1])) {
				topGain[0] = this.instance.getAttributes().keySet().toArray()[i]
						.toString();
				topGain[1] = String.valueOf(gain);
			}*/
		}

		return null;
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
