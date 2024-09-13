package animals;

//erweitert Klasse Animal
//hat zusätzlich zu den Feldern von Animal auch das Feld honey

public class Bees extends Animal {
    public int honey;


    //Constructor
    public Bees(String name, int hunger, int age, int honey) {
        super(name, hunger, age);
        this.honey = honey;

    }

    public int getHoney() {
        return honey;
    }

    public void setHoney(int honey) {
        this.honey = honey;
    }

    @Override
    public void feed() {
        hunger = Math.max(hunger-(hungerValue * 2),0);
        honey += 1;
        setHoney(honey);
    }


}
