package apprentissage_artificiel;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/weather.nominal.arff";
		Reader fr = new Reader(filePath);
		fr.read();
		fr.displayFile();
	}

}
