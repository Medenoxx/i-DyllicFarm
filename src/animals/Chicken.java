package animals;

public class Chicken extends Animal {


    private int eggs;

    public Chicken(String name, int hunger, int age, int eggs) {
        super(name, hunger, age);
        this.eggs = eggs;
    }


    public int getEggs() {
        return eggs;
    }

    public void setEggs(int eggs) {
        this.eggs = eggs;
    }

    @Override
    public void feed() {
        hunger = Math.max(hunger-(hungerValue * 2),0);
        eggs += 1;
        setEggs(eggs);
    }
}
