package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class ID3 {	 

	public final static String INIT = "";

	private Attribute attribute;
	private HashMap<String, ID3> sons;
	private HashMap<String, String> leafs;
	
	public ID3() {
		
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
	public void recursive(Instances instances, InstanceClass instanceClass, ArrayList<Integer> attributes) {
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
				// retourner un noeud ayant cette valeur
			} else {
				Attribute selectedAttribute = bestAttribute(instances, attributes);
				ArrayList<Integer> remainingAttributes = new ArrayList<Integer>(attributes);
				//System.out.println(attributes.toString());
				//System.out.println(remainingAttributes.toString());
				remainingAttributes.remove(selectedAttribute.getIndex());
				//System.out.println(attributes.toString());
				//System.out.println(remainingAttributes.toString());
				
				
			}
		}

	}

	public Attribute bestAttribute(Instances instances, ArrayList<Integer> attributes) {	
		
		double topGain = 0;
		Attribute topAttribute = null;
		
		HashMap<String, Integer> examplesPerClass = new HashMap<String, Integer>();
		for (Instance instance : instances.getInstances()) {
			String value = instance.getInstanceClass().getValue();
			if (!examplesPerClass.containsKey(value)) {
				examplesPerClass.put(value, 1);
			} else {
				Integer temp = examplesPerClass.get(value);
				temp++;
				examplesPerClass.put(value, temp);
			}
		}

		// Calcul de l'entropie de l'instances
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Entry<String, Integer> entry : examplesPerClass.entrySet()) {
			values.add(entry.getValue());
		}
		//double entropyS = calculateEntropy(this.instances.getNumberOfDataRows(),values);
		double entropyS = calculateEntropy(instances.getInstances().size(),values);
		 
		for (Integer i : attributes) {
			HashMap<String, HashMap<String, Integer>> examplesPerClassPerAttributs = new HashMap<String, HashMap<String, Integer>>();
			for (Instance instance : instances.getInstances()) {
				if (!examplesPerClassPerAttributs.containsKey(instance.getAttributes().get(i).getValue())) {
					HashMap<String, Integer> newEntry = new HashMap<String, Integer>();
					newEntry.put(instance.getInstanceClass().getValue(), 1);
					examplesPerClassPerAttributs.put(instance.getAttributes().get(i).getValue(), newEntry);
				} else {
					HashMap<String, Integer> test = examplesPerClassPerAttributs.get(instance.getAttributes().get(i).getValue());
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
			System.out.println(examplesPerClassPerAttributs.toString() + "\t*** FIN ***");
			double gain = entropyS;
			for (Entry<String, HashMap<String, Integer>> entry : examplesPerClassPerAttributs.entrySet()) {
				ArrayList<Integer> val = new ArrayList<Integer>();
				int ratio = 0;
				for (Entry<String, Integer> value : entry.getValue().entrySet()) {
					ratio += value.getValue();
					val.add(value.getValue());
				}
				//gain -= ((double) ratio / (double) instances.getNumberOfDataRows()) * calculateEntropy(ratio, val);
				gain -= ((double) ratio / (double) instances.getInstances().size()) * calculateEntropy(ratio, val);
				val.clear();
			}
			// Affichage temporaire de chaque gain
			System.out.println("Gain : " + gain);
			if (gain > topGain) {
				topGain = gain;
				topAttribute = instances.getInstances().get(0).getAttributes().get(i);
			}
		}
		
		//System.out.println(topGain[0] + " " + topGain[1]);
		
		return topAttribute;
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
