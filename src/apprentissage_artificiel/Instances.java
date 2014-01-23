package apprentissage_artificiel;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Instances {

	public final static String INIT = "";
	
	private String relationName;
	private LinkedHashMap<String, ArrayList<String>> attributes;
	ArrayList<Instance> instances;
	
	public Instances() {
		this.relationName = INIT;
		this.attributes = new LinkedHashMap<String, ArrayList<String>>();
		this.instances = new ArrayList<Instance>();
	}
	
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
	public String getRelationName() {
		return relationName;
	}
	
	public void setAttributes(LinkedHashMap<String, ArrayList<String>> attributes) {
		this.attributes = attributes;
	}
	
	public LinkedHashMap<String, ArrayList<String>> getAttributes() {
		return attributes;
	}
	
	public void setInstance(ArrayList<Instance> instances) {
		this.instances = instances;
	}
	
	public ArrayList<Instance> getInstances() {
		return instances;
	}
	
}
