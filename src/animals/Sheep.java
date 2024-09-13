package animals;

public class Sheep extends Animal {
    private int wool;

    public Sheep(String name, int hunger, int age, int wool) {
        super(name, hunger, age);
        this.wool = wool;
    }


    public int getWool() {
        return wool;
    }

    public void setWool(int wool) {
        this.wool = wool;
    }

    @Override
    public void feed() {
        hunger = Math.max(hunger-(hungerValue * 2),0);
        wool += 1;
        setWool(wool);
    }


}