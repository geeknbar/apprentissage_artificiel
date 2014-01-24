package apprentissage_artificiel;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/mika.nominal.arff";
		Instances instances = new Instances();
		instances.loadFile(filePath);
		instances.displayInstances();
	}

}
