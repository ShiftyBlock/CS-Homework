//Riona:



public abstract class Animal implements Comparable<Animal>  {

	private int id;
	private String name;
	private BirthType birthType;

	public static final String ANIMAL_DESCRIPTION = "Animal";

	public Animal(int id, String name, BirthType birthType) {
		this.id = id;
		this.name = name;
		this.birthType = birthType;
	}
	
	
	// information about birth type
	public final boolean laysEggs() {
		return birthType==BirthType.LAYS_EGGS;
	}
	public final boolean hasLiveBirth() {
		return birthType==BirthType.LIVE_BIRTH;
	}
	public final BirthType getBirthType() {
		return birthType;
	}

	// getters (no setters provided)
	public final int getId() {
		return id;
	}

	public final String getName() {
		return name;
	}

	// toString method will create text with name, id, birth type, warm/cold blodded, and the description
	@Override
	public final String toString() {
		return name + " (id=" + id + "):\t" + birthType.toString() + "\t" +
				(isWarmBlooded() ? "Warm Blooded" : "Cold Blooded") + "\t" +
				getDescription();
	}

	// abstract methods to implement in child classes
	public abstract boolean isWarmBlooded();
	public abstract String getDescription();
	
	
	//(10 points) Add an equals method. Two animals are considered logically equivalent 
	//if they have the same id and the same name, ignoring capitalization.
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Animal)  {
			Animal otherAnimal = (Animal) obj;
			return this.id == otherAnimal.id && 
					this.name.equalsIgnoreCase(otherAnimal.name);
		}
		else {
			return false;
		}
	}
	
	//(10 points) Modify the Animal class to implement the Comparable interface. Update the class header. 
	//Write the compareTo method. Order animals by name (in alphabetic order).
	@Override
	public int compareTo(Animal otherAnimal) {
		return name.compareToIgnoreCase(otherAnimal.name);	
	}
	
}