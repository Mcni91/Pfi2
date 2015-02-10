
public class Snake extends Animal {
	private boolean poisonous;
	public Snake(String latinName, boolean poisonous) {
		super(latinName);
		this.poisonous = poisonous;
	}

	public boolean isPoisonous(){
		return this.poisonous;
	}

	@Override
	public String getInfo() {
		String temp;
		String tempName;
		if(this.isPoisonous()){
			temp = "poisonous";
		} else {
			temp = "not poisonous";
		}
		if (this.getFriendlyName() == null) {
			tempName = "<noname>";
		} else {
			tempName = this.getLatinName();
		}
		return "The snake " + 
				tempName + 
				" (lat: " + this.getLatinName() + 
				") is " + 
				temp +
				".";
	}
}
