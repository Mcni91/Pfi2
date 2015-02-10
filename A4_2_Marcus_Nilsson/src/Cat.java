
public class Cat extends Mamal{
	
	private int numberOfLives;
	
	public Cat(String latinName, int gestationTime, int numberOfLifes) {
		super(latinName, gestationTime);
		this.numberOfLives = numberOfLifes;
	}
	
	public int getNumberOfLives() {
		return numberOfLives;
	}
	public void setNumberOfLives(int numberOfLives) {
		this.numberOfLives = numberOfLives;
	}
	
	@Override
	public String getInfo() {
		return "The cat " + 
				this.getFriendlyName() + 
				" (lat: " + this.getLatinName() + 
				") nurses for " + 
				this.getGestationTime() +
				" days and has " +
				this.getNumberOfLives() + 
				" lifes.";
	}

}
