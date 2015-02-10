
public abstract class Mamal extends Animal{
	
	private int gestationTime;
	
	public Mamal(String latinName, int gestationTime) {
		super(latinName);
		this.gestationTime = gestationTime;
	}
	
	public int getGestationTime(){
		return gestationTime;
	}
}
