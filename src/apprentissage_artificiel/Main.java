package apprentissage_artificiel;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/weather.nominal.arff";
//		String filePath = "./bin/doc/mika.nominal.arff";
//		String filePath = "./bin/doc/tic-tac-toe.arff";
//		String filePath = "./bin/doc/contact-lenses.arff";
//		String filePath = "./bin/doc/cars.arff";
//		String filePath = "./bin/doc/prime.arff";
//		String filePath = "./bin/doc/agaricus-lepiota.arff";
//		String filePath = "./bin/doc/coup_de_soleil.arff";
		Instances instances = new Instances();
		instances.loadFile(filePath);
		ID3 id3 = new ID3();
		ID3 test = id3.compute(instances);
		test.display(0);
	}

}
