package apprentissage_artificiel;

public class Attribute {
	
	private String name;
	private String value;
	private int index;
	
	public Attribute(String name, String value, int index) {
		this.name = name;
		this.value = value;
		this.index = index;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
