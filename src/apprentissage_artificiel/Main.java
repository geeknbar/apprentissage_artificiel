package apprentissage_artificiel;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/weather.nominal.arff";
		Instances instances = new Instances();
		instances.loadFile(filePath);
		//instance.displayFile();
		//ID3 id3 = new ID3(instance);
		//id3.compute();
		//instances.displayInstances();
	}

}
