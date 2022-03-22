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

	@Override
	public final String toString() {
		return name + " (id=" + id + "):\t" + birthType.toString() + "\t" +
				(isWarmBlooded() ? "Warm Blooded" : "Cold Blooded") + "\t" +
				getDescription();
	}

	public abstract boolean isWarmBlooded();
	public abstract String getDescription();
	

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
	
	//sort animals by alphabet
	@Override
	public int compareTo(Animal otherAnimal) {
		return name.compareToIgnoreCase(otherAnimal.name);	
	}
	
}