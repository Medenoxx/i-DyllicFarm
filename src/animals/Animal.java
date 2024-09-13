package animals;

public class Animal implements HungerManager {

        public String name;
        public int hunger;
        public int age;


        public Animal(String name, int hunger, int age) {
            this.name = name;
            this.hunger = hunger;
            this.age = age;
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public void feed() {
        hunger -= hungerValue * 2;
    }

    @Override
    public void hunger() {
        hunger += hungerValue; //aktueller Hunger + 10
    }
}
