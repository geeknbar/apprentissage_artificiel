package apprentissage_artificiel;

public class Attribute {

	public final static String INIT = "";
	
	private String name;
	private String value;
	
	public Attribute() {
		this.name = INIT;
		this.value = INIT;
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
	
}
