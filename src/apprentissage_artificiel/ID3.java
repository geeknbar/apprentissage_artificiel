package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class ID3 {	 

	public final static String INIT = "";
	public final static String LEAF = "LEAF";

	private Attribute attribute;
	private HashMap<String, ID3> sons;
	//private HashMap<String, String> leafs;
	
	public ID3() {
		sons = new HashMap<String, ID3>();
		//leafs = new HashMap<String, String>();
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
	public ID3 recursive(Instances instances, InstanceClass instanceClass, ArrayList<Integer> attributes) {
		if (instances.getInstances().size() == 0) { /* Nœud terminal */
			// retourner un nœud Erreur
			//System.out.println("IL EST PASSE PAR LA !!!");
			return null;
		} else if (attributes.size() == 0) { /* Nœud terminal */
			// retourner un nœud ayant la valeur la plus représentée pour attributCible
			//System.out.println("ET ICI !!!");
			HashMap<String, Integer> nbInstanceClass = new HashMap<String, Integer>();
			for (Instance instance : instances.getInstances()) {
				if (!nbInstanceClass.containsKey(instance.getInstanceClass().getValue())) {
					nbInstanceClass.put(instance.getInstanceClass().getValue(), 1);
				} else {
					int toModify = nbInstanceClass.get(instance.getInstanceClass().getValue());
					toModify++;
					nbInstanceClass.put(instance.getInstanceClass().getValue(), toModify);
				}
			}
			String topClass = INIT;
			int nbTopClass = 0;
			for (Entry<String, Integer> entry : nbInstanceClass.entrySet()) {
				if (entry.getValue() > nbTopClass) {
					topClass = entry.getKey();
					nbTopClass = entry.getValue();
				}
			}
			ID3 newId3 = new ID3();
			Attribute attTemp = new Attribute(LEAF, topClass, -1);
			newId3.setAttribute(attTemp);
			return newId3;
		} else {
			HashSet<String> instanceClassValues = new HashSet<String>();
			for (Instance instance : instances.getInstances()) {	
				instanceClassValues.add(instance.getInstanceClass().getValue());
			}
			if (instanceClassValues.size() == 1) {
				// retourner un noeud ayant cette valeur
				//System.out.println("ET POURQUOI PAS LA ???");
				ID3 newId3 = new ID3();
				Attribute attTemp = new Attribute(LEAF, instanceClassValues.toArray()[0].toString(), -1);
				newId3.setAttribute(attTemp);
				return newId3;
			} else {
				//System.out.println("ET POUR TERMINE ICI BIEN SUR !!!");
				// attributSélectionné = attribut maximisant le gain d'information parmi attributsNonCibles
				Attribute attTemp = bestAttribute(instances, attributes);
				// attributsNonCiblesRestants = suppressionListe(attributsNonCibles, attributSélectionné)
				ArrayList<Integer> remainingAttributes = new ArrayList<Integer>(attributes);
				int i = 0;
				while (attributes.get(i) != attTemp.getIndex()) {
					i++;
				}
				remainingAttributes.remove(i);
				// nouveauNœud = nœud étiqueté avec attributSélectionné
				ID3 newId3 = new ID3();
				newId3.setAttribute(attTemp);
				// exemplesFiltrés = filtreExemplesAyantValeurPourAttribut(exemples, attributSélectionné, valeur)
		        // nouveauNœud->fils(valeur) = ID3(exemplesFiltrés, attributCible, attributsNonCiblesRestants)
				for (String s : instances.getAttributes().get(attTemp.getName())) {
					newId3.addSon(s, recursive(filterInstance(instances, attTemp, s), new InstanceClass("yes"), remainingAttributes));
				}
				
				return newId3;
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
			double gain = entropyS;
			for (Entry<String, HashMap<String, Integer>> entry : examplesPerClassPerAttributs.entrySet()) {
				ArrayList<Integer> val = new ArrayList<Integer>();
				int ratio = 0;
				for (Entry<String, Integer> value : entry.getValue().entrySet()) {
					ratio += value.getValue();
					val.add(value.getValue());
				}
				gain -= ((double) ratio / (double) instances.getInstances().size()) * calculateEntropy(ratio, val);
				val.clear();
			}
			if (gain > topGain) {
				topGain = gain;
				topAttribute = instances.getInstances().get(0).getAttributes().get(i);
			}
		}
		return topAttribute;
	}
	
	public Instances filterInstance(Instances instances, Attribute attribute, String value) {
		Instances newInstances = new Instances();
		newInstances.setRelationName(instances.getRelationName());
		newInstances.setAttributes(instances.getAttributes());
		ArrayList<Instance> arrayInstance = new ArrayList<Instance>();
		for (int i = 0; i < instances.getInstances().size(); i++) {
			if (instances.getInstances().get(i).getAttributes().get(attribute.getIndex()).getValue().equals(value)) {
				Instance newInstance = new Instance();
				for (Attribute att : instances.getInstances().get(i).getAttributes()) {
					newInstance.addAttribute(new Attribute(att.getName(), att.getValue(), att.getIndex()));
				}
				InstanceClass newInstanceClass = new InstanceClass(instances.getInstances().get(i).getInstanceClass().getValue());
				newInstance.setInstanceClass(newInstanceClass);
				arrayInstance.add(newInstance);
			}
		}
		newInstances.setInstances(arrayInstance);		
		return newInstances;
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

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public HashMap<String, ID3> getSons() {
		return sons;
	}

	public void setSons(HashMap<String, ID3> sons) {
		this.sons = sons;
	}
/*
	public HashMap<String, String> getLeafs() {
		return leafs;
	}

	public void setLeafs(HashMap<String, String> leafs) {
		this.leafs = leafs;
	}
*/
	public void addSon(String value, ID3 id3) {
		sons.put(value, id3);
	}
	
	public void display(int inc) {
		
		if (!LEAF.equals(attribute.getName())) {
			System.out.println("Attribut" + inc + " = " + attribute.getName());
		} else {
			System.out.println("ValeurDeClasse" + inc + " = " + attribute.getValue());
		}
		
		if (sons.size() > 0) {
			for (Entry<String, ID3> entry : sons.entrySet()) {
				System.out.println("Fils - " + entry.getKey());
				entry.getValue().display(inc + 1);
			}
		}
	}

}
