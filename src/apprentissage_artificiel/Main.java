package apprentissage_artificiel;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String filePath = "./bin/doc/weather.nominal.arff";
		Instances instances = new Instances();
		instances.loadFile(filePath);
		//instances.displayInstances();
		ID3 id3 = new ID3();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.add(0);
		tmp.add(1);
		tmp.add(2);
		tmp.add(3);
		ID3 test = id3.recursive(instances, new InstanceClass("yes"), tmp);
		test.display();
	}

}
