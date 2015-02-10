
public class Human {
	private Dog dog;
	private String name;
	
	public Human(String name) {
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void buyDog(Dog dog){
		this.dog = dog;
	}
	/**Returnerar: "x äger en hund som heter y" eller " x äger inte en hund"*/
	public String getInfo(){
		return this.name + " äger en hund som heter " + dog.getName();
	}
}
