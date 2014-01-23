package apprentissage_artificiel;

import java.util.ArrayList;

public class Instance {
	
	private ArrayList<Attribute> attributes;
	private InstanceClass instanceClass;
	
	public Instance() {
		this.setAttributes(new ArrayList<Attribute>());
		this.setInstanceClass(new InstanceClass());
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public InstanceClass getInstanceClass() {
		return instanceClass;
	}

	public void setInstanceClass(InstanceClass instanceClass) {
		this.instanceClass = instanceClass;
	}
	
	
	
}
