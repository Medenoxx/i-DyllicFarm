package animals;

//erweitert Klasse Animal
//hat zusätzlich zu den Feldern von Animal auch das Feld milk
public class Cow extends Animal {
    public int milk;


    //Constructor
    public Cow(String name, int hunger, int age, int milk) {
        super(name, hunger, age);
        this.milk = milk;

    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    //Reduziert hungerwert um 20 und erhöht milk Wert um 1
    //Animal class hat bereits feed implementiert, hier wird sie aber erneut definiert (überschrieben)
    //um zusätzlich zur Reduzierung vom Hunger auch die Milkproduktion zu erhöhen = Methode wird erweitert
    @Override
    public void feed() {
        hunger = Math.max(hunger-(hungerValue * 2),0);
        milk += 1; //erhöht sich um 1 nach jedem füttern
        setMilk(milk);
    }


}
