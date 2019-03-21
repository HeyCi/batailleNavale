package naval;

import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {

		HashMap<Letter, String> hashMap = new HashMap();

		hashMap.put(Letter.A, "value1");
		hashMap.put(Letter.B, "value2");
		hashMap.put(Letter.C, "value3");
		hashMap.put(Letter.D, "value4");

		System.out.println(hashMap.containsKey(Letter.A));
		System.out.println(hashMap.containsKey(Letter.J));

		String valeur = hashMap.get(Letter.B);
		System.out.println("La valeur associée à la clé " + Letter.B + " : " + valeur);

		System.out.println(hashMap.containsKey(Letter.F));
	}
}
