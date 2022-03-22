import java.util.*;

public class AnimalKingdomDriver {

	public static void main(String[] args) {
		ArrayList<Animal> animalList = new ArrayList<Animal>();
		animalList.add(new Parakeet(1, "Tweety Parakeety"));
		animalList.add(new Cow(2, "Bessie the Cow"));
		animalList.add(new Duck(3, "Head in the Sand Hallie"));
		animalList.add(new BelugaWhale(4, "BabyBeluga O\'BabyBeluga"));
		animalList.add(new BlueWhale(5, "Spouty the Blue Whale"));

		for (Animal x: animalList){
			System.out.println(x);
		}

		ArrayList<Animal> sortedList = new ArrayList<>(animalList);
		Collections.sort(sortedList);

		System.out.println();
		for (Animal x: sortedList){
			System.out.println(x.getName());
		}

		Animal cow = animalList.get(1);
		((Cow) cow).goMoo();


		Animal duck = animalList.get(2);
		System.out.println(((Duck) duck).quack());







	}
}
