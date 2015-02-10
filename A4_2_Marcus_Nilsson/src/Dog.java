
public class Dog extends Mamal{
	
	private boolean stupid;
	
	public Dog(String latinName, int gestationTime, boolean stupid) {
		super(latinName, gestationTime);
		this.stupid = stupid;
	}
	
	public boolean isStupid(){
		return stupid;
	}
	
	@Override
	public String getInfo() {
		String temp;
		if(this.isStupid()){
			temp = "stupid";
		} else {
			temp = "clever";
		}
		return "The dog " + 
				this.getFriendlyName() + 
				" (lat: " + this.getLatinName() + 
				") nurses for " + 
				this.getGestationTime() +
				" days and is " +
				temp + 
				".";
	}

}
