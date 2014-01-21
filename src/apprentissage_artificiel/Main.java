package apprentissage_artificiel;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/weather.nominal.arff";
		Reader fr = new Reader(filePath);
		Instance instance = fr.read();
		//instance.displayFile();
		ID3 id3 = new ID3(instance);
		id3.calculateEntropy();
	}

}
