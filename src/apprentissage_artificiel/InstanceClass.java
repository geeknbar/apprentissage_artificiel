package apprentissage_artificiel;

public class InstanceClass {

	public final static String INIT = "";
	
	private String value;
	
	public InstanceClass(String value) {
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
