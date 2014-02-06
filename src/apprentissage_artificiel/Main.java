package apprentissage_artificiel;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/weather.nominal.arff";
		//String filePath = "./bin/doc/mika.nominal.arff";
		//String filePath = "./bin/doc/tic-tac-toe.arff";
		Instances instances = new Instances();
		instances.loadFile(filePath);
		//instances.displayInstances();
		ID3 id3 = new ID3();
		//ArrayList<Integer> tmp = new ArrayList<Integer>();
		//tmp.add(0);
		//tmp.add(1);
		//tmp.add(2);
		//tmp.add(3);
		//ID3 test = id3.recursive(instances, tmp);
		ID3 test = id3.compute(instances);
		test.display(0);
	}

}
