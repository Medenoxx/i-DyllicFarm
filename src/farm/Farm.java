package farm;

import animals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Farm implements EventManager {

    //dekl. & init. von Listen für die versch. Tierarten
    //erstelle Listen für die jeweiligen Tierarten
    private List<Sheep> sheepList = new ArrayList<>();
    private List<Cow> cowList = new ArrayList<>();
    private List<Chicken> chickenList = new ArrayList<>();
    private List<Bees> beesList = new ArrayList<>();


    //Getter und Setter für die Listen
    public List<Sheep> getSheepList() {
        return sheepList;
    }

    public void setSheepList(List<Sheep> sheepList) {
        this.sheepList = sheepList;
    }


    public List<Cow> getCowList() {
        return cowList;
    }

    public void setCowList(List<Cow> cowList) {
        this.cowList = cowList;
    }


    public List<Chicken> getChickenList() {
        return chickenList;
    }

    public void setChickenList(List<Chicken> chickenList) {
        this.chickenList = chickenList;
    }

    public List<Bees> getBeesList() {
        return beesList;
    }

    public void setBeesList(List<Bees> beesList) {
        this.beesList = beesList;
    }


    //Jetzt Initialisiere ich die Farm
    public void initializeFarm() {

        cowList.add(new Cow("Rosi", 30, 6, 50));
        cowList.add(new Cow("Lila", 30, 15, 80));
        cowList.add(new Cow("Roki", 30, 4, 55));
        setCowList(cowList); //eingegebene Werte überschreiben CowList

        sheepList.add(new Sheep("Ciko", 40, 2, 50));
        sheepList.add(new Sheep("Miki", 80, 4, 75));
        sheepList.add(new Sheep("Sajo", 60, 10, 90));
        setSheepList(sheepList);

        chickenList.add(new Chicken("Ibro", 50, 1, 30));
        chickenList.add(new Chicken("Hari", 80, 2, 80));
        chickenList.add(new Chicken("Chicki Wingi", 20, 4, 100));
        setChickenList(chickenList);

        beesList.add(new Bees("Hive Nokki", 20, 2, 50));
        beesList.add(new Bees("Hive Eblas", 10, 1, 40));
        beesList.add(new Bees("Hive Adima", 10, 3, 30));
        setBeesList(beesList);

    }

    //Methode, um Hunger zu erhöhen
    //iteriert über gesamte Liste und erhöht Hungerwert so, wie er in der jeweiligen Tierart bei der Override Methode definiert wurde
    public void increaseHunger() {
        getCowList().forEach(Cow::hunger); //:: wird verwendet um beim ersten : die classe von der Methode zu trennen, mit dem zweiten : referenziert man auf die hunger Methode in der Cow class; erhöht hungerwert für jedes Cow-Objekt +10
        getSheepList().forEach(Sheep::hunger);
        getChickenList().forEach(Chicken::hunger);
        getBeesList().forEach(Bees::hunger);
    }

    //Methode (switch), um alle Tiere zu füttern
    //
    public void feedAllAnimals(String animal) {
        switch (animal.toLowerCase()) {
            case "cow": //iteriert durch Liste Cows und füttert nur die hungrigen Tiere
                getCowList().forEach(cow -> {
                    //boolean isHungry, weil wir für jedes Tier wissen wollen, ob es hungrig genug für fütterung ist -> true/false
                    //prüft, ob cow hungrig ist, indem checkHunger Methode aufgerufen wird
                    boolean isHungry = checkHunger(cow.getHunger(), cow.getName());
                    //holen uns hier nur name, um es bei checkHunger Methode im sout ausgeben zu können
                    if (isHungry) {
                        //füttert Kuh indem es feed-Methode aufruft
                        cow.feed();
                        System.out.println(cow.getName() + " is munching!");
                    }
                });
                break;

            case "sheep":
                getSheepList().forEach(sheep -> {
                    boolean isHungry = checkHunger(sheep.getHunger(), sheep.getName());
                    if (isHungry) {
                        sheep.feed();
                        System.out.println(sheep.getName() + " is munching!");
                    }
                });
                break;

            case "chicken":
                getChickenList().forEach(chicken -> {
                    boolean isHungry = checkHunger(chicken.getHunger(), chicken.getName());
                    if (isHungry) {
                        chicken.feed();
                        System.out.println(chicken.getName() + " is munching!");
                    }
                });
                break;

            case "bees":
                getBeesList().forEach(bees -> {
                    boolean isHungry = checkHunger(bees.getHunger(), bees.getName());
                    if (isHungry) {
                        bees.feed();
                        System.out.println(bees.getName() + " is munching!");
                    }
                });
                break;

            default:
                System.out.println("Invalid species.");
        }

    }

    //wenn animal cow dann holt man kuhliste und iteriert diese durch. wenn der Name in der Liste vorhanden ist -> wenn ja: prüfen ob Tier gefüttert werden kann -> wenn ja: Tier füttern
    //Methode prüft, ob der Name matcht und füttert dann nur dieses eine Tier!
    //sucht nach Bedingung
    public void feedSingleAnimal(String animal, String name) {
        if (animal.equalsIgnoreCase("cow")) {
            getCowList().forEach(cow -> { //lambda expression used
                if (name.equalsIgnoreCase(cow.getName())) { //if name in der Liste ist, dann füttern
                    boolean isHungry = checkHunger(cow.getHunger(), cow.getName()); //wenn isHungry = true wird kuh gefüttert
                    if (isHungry) {
                        cow.feed();
                    }
                }
            });
        } else if (animal.equalsIgnoreCase("sheep")) {
            getSheepList().forEach(sheep -> {
                if (name.equalsIgnoreCase(sheep.getName())) {
                    boolean isHungry = checkHunger(sheep.getHunger(), sheep.getName());
                    if (isHungry)
                        sheep.feed();
                }
            });
        } else if (animal.equalsIgnoreCase("chicken")) {
            getChickenList().forEach(chicken -> {
                if (name.equalsIgnoreCase(chicken.getName())) {
                    boolean isHungry = checkHunger(chicken.getHunger(), chicken.getName());
                    if (isHungry)
                        chicken.feed();
                }
            });
        } else if (animal.equalsIgnoreCase("bees")) {
            getBeesList().forEach(bees -> {
                if (name.equalsIgnoreCase(bees.getName())) {
                    boolean isHungry = checkHunger(bees.getHunger(), bees.getName());
                    if (isHungry)
                        bees.feed();
                }
            });
        }
    }

    //Prüft, ob best. Spezies mit best. Namen überhaupt in der Farm existiert, iteriert über Liste und gibt zurück, ob mind. ein Tier matcht
    public boolean checkAnimalsExist(String animal, String name) {
        switch (animal.toLowerCase()) {
            case "cow":
                // Prüfung ob es mind. eine Übereinstimmung in cowList mit dem im Paramtere übergebenem "name" gibt und gibt ein true zurück wenn das stimmt
                return cowList.stream().anyMatch(cow -> name.equalsIgnoreCase(cow.getName()));
            case "sheep":
                return sheepList.stream().anyMatch(sheep -> name.equalsIgnoreCase(sheep.getName()));
            case "chicken":
                return chickenList.stream().anyMatch(chicken -> name.equalsIgnoreCase(chicken.getName()));
            case "bees":
                return beesList.stream().anyMatch(bees -> name.equalsIgnoreCase(bees.getName()));
            default:
                System.out.println("Animal not Found.");
                return false;
        }
    }

    //Polymorphie: Animal definiert Methode getHunger, die von allen Unterklassen impl. und überschr. wird. Liste animalList kann Objekte versch. Unterklassen von Animal enthalten. Methode getHunger wird aufgerufen, ohne dass spezifischer Typ des Tieres bekannt sein muss
    //ermöglicht neue Tierarten hinzuzufügen, indem neue Unterklassen von Animal erstellt werden, ohne bestehenden Code ändern zu müssen
    public int[] getHungerForAllAnimals(String animal) {
        //deklariert Liste von Tieren, die von Klasse animal erben und setzt alle auf 0
        List<? extends Animal> animalList = null;
        //prüft, ob String sheeps ist -> wenn ja: weist sheepList der animalList zu
        if (animal.equalsIgnoreCase("Sheeps")) {
            animalList = getSheepList();
        } else if (animal.equalsIgnoreCase("Cows")) {
            animalList = getCowList();
        } else if (animal.equalsIgnoreCase("Chickens")) {
            animalList = getChickenList();
        } else if (animal.equalsIgnoreCase("Bees")) {
            animalList = getBeesList();
        }

        //wenn keine passende Liste gefunden wird: null
        if (animalList == null) {
            return null;
        }

        //Array hungerwerte wird erstellt; hat gleiche größe wie animalList
        //Schleife durchläuft animalList und ruft Hungerwert jedes Tieres auf und speichert ihn im Array
        int[] hungerwerte = new int[animalList.size()];
        for (int i = 0; i < animalList.size(); i++) {
            hungerwerte[i] = animalList.get(i).getHunger();
        }
        //Array hungerwert wird zurückgegeben; enthält alle Hungerwerte von Tiere
        return hungerwerte;
    }


    public void printAllAnimals() {
        int[] hungerValues; //Array speichert Hungerwerte d Tiere
        System.out.println("Animals in the Farm: ");
        System.out.println();
        System.out.println("Cows: ");

        //ruft Hungerwerte aller Kühe ab und speichert sie im Array hungerValues
        hungerValues = getHungerForAllAnimals("Cows");
        List<Cow> cows = getCowList();
        //Gibt Name, Hungerwert etc. des Tieres aus
        for (int i = 0; i < hungerValues.length; i++) {

            System.out.println(cows.get(i).getName() + " - Hungervalue: " + hungerValues[i] + "%, Age: " + cows.get(i).getAge() + ", Milk Amount: " + cows.get(i).getMilk() + "%");
        }
        System.out.println();
        System.out.println("Sheeps: ");
        hungerValues = getHungerForAllAnimals("Sheeps");
        List<Sheep> sheeps = getSheepList();
        for (int i = 0; i < hungerValues.length; i++) {

            System.out.println(sheeps.get(i).getName() + " - Hungervalue: " + hungerValues[i] + "%, Age: " + sheeps.get(i).getAge() + ", Wool Amount: " + sheeps.get(i).getWool() + "%");
        }
        System.out.println();
        System.out.println("Chickens: ");
        hungerValues = getHungerForAllAnimals("Chickens");
        List<Chicken> chickens = getChickenList();
        for (int i = 0; i < hungerValues.length; i++) {

            System.out.println(chickens.get(i).getName() + " - Hungervalue: " + hungerValues[i] + "%, Age: " + chickens.get(i).getAge() + ", Egg Amount: " + chickens.get(i).getEggs() + "%");
        }
        System.out.println();
        System.out.println("Bees: ");
        hungerValues = getHungerForAllAnimals("Bees");
        List<Bees> bees = getBeesList();
        for (int i = 0; i < hungerValues.length; i++) {
            System.out.println(bees.get(i).getName() + " - Hungervalue: " + hungerValues[i] + "%, Age: " + bees.get(i).getAge() + ", Honey Amount: " + bees.get(i).getHoney() + "%");
        }
        System.out.println();
    }

    public boolean checkHunger(int hungervalue, String name) {
        if (hungervalue >= 75) {
            System.out.println("The animal " + name + " will be fed.");
            return true;
        } //manuelle Prüfung bei mittlerem Wert
        else if (hungervalue >= 50) {
            Scanner userinput = new Scanner(System.in);
            System.out.println("Should the animal " + name + " be fed?: "); //Abfrage User
            String feedyn = userinput.nextLine(); //Übernahme eingegebener Wert

            //abhängig vom Tier wird der jeweilige Hungerwert um 20 verringert, mindestens aber nur auf 0 um
            //Negativwerte zu vermeiden
            if (feedyn.equalsIgnoreCase("yes")) {
                System.out.println("Feeding!");
                return true;
            }
        } else
            System.out.println("The animal " + name + " can not be feed.");
        return false;
    }

    public void increaseAge() {
        cowList.forEach(cow -> cow.setAge(cow.getAge() + 1));
        sheepList.forEach(sheep -> sheep.setAge(sheep.getAge() + 1));
        chickenList.forEach(chicken -> chicken.setAge(chicken.getAge() + 1));
        beesList.forEach(bees -> bees.setAge(bees.getAge() + 1));
    }

    // polymorphie/Overloading wir wollen hier je nachdem welche Spezies übergeben wird das Tier zur Liste hinzufügen
    public void addAnimal(Cow cow) {
        getCowList().add(cow);
    }

    public void addAnimal(Sheep sheep) {
        getSheepList().add(sheep);
    }

    public void addAnimal(Chicken chicken) {
        getChickenList().add(chicken);
    }

    public void addAnimal(Bees bees) {
        getBeesList().add(bees);
    }

    public void harvestMilk() {
        getCowList().forEach(cow -> cow.setMilk(0));
    }

    public void harvestWool() {
        getSheepList().forEach(sheep -> sheep.setWool(0));
    }

    public void harvestEggs() {
        getChickenList().forEach(chicken -> chicken.setEggs(0));
    }

    public void harvestHoney() {
        getBeesList().forEach(bees -> bees.setHoney(0));
    }

    //prüft, ob irgendein Tier in der Liste einen Hungerwert von >100 hat. Wenn mind. eines > 100 dann: ja -> true, sonst false
    public boolean checkAnimalsAlive() {
        return cowList.stream().anyMatch(cow -> cow.getHunger() > 100) ||
                sheepList.stream().anyMatch(sheep -> sheep.getHunger() > 100) ||
                chickenList.stream().anyMatch(chicken -> chicken.getHunger() > 100) ||
                beesList.stream().anyMatch(bees -> bees.getHunger() > 100);
    }


    @Override
    public void handleWolfAttack(Scanner scanner) {
        System.out.println("A wolf pack is approaching your farm! What do you want to do?");
        System.out.println("1) Shoot wolves\n2) Call the police\n3) Hide");
        int choice = Integer.parseInt(scanner.nextLine());

        //erzeugt Zufallszahl zw 0 - 99
        Random random = new Random();
        int chance = random.nextInt(100);
        switch (choice) {
            case 1: // Shoot wolves
                //Wahrscheinlichkeit für Erfolg des Abschreckens 60%
                if (chance < 60) {
                    System.out.println("You successfully scared away the wolves!");
                } else {
                    System.out.println("The wolves attacked your animals!");
                    killRandomAnimals();
                    handlePostWolfAttack(scanner);
                }
                break;
            case 2: // Call the police
                //Wahrscheinlichkeit für Polizei kommt rechtzeitig 50%
                if (chance < 50) {
                    System.out.println("The police arrived in time and scared away the wolves!");
                } else {
                    System.out.println("The police were too late, and the wolves attacked your animals!");
                    killRandomAnimals();
                    handlePostWolfAttack(scanner);
                }
                break;
            case 3: // Hide
                //Wahrscheinlichkeit sich zu verstecken erfolgreich 30%
                if (chance < 30) {
                    System.out.println("You successfully hid from the wolves!");
                } else {
                    System.out.println("The wolves found your animals and attacked!");
                    killRandomAnimals();
                    handlePostWolfAttack(scanner);
                }
                break;
            default:
                System.out.println("Invalid choice! The wolves attacked your animals!");
                killRandomAnimals();
                handlePostWolfAttack(scanner);
                break;
        }
    }

    @Override
    public void handlePostWolfAttack(Scanner scanner) {
        System.out.println("Your animals are suffering from depression after the attack. How do you want to help them?");
        System.out.println("1) Buy more animals\n2) Care more for them\n3) Give them time to process things");
        int choice = Integer.parseInt(scanner.nextLine());
        Random random = new Random();
        int chance = random.nextInt(100);
        switch (choice) {
            case 1: // Buy more animals
                if (chance < 50) {
                    System.out.println("Buying more animals helped lift the spirits of the remaining ones.");
                } else {
                    System.out.println("Buying more animals did not help, and all animals died of depression.");
                    killAllAnimals();
                }
                break;
            case 2: // Care more for them
                if (chance < 70) {
                    System.out.println("Caring more for the animals helped them recover.");
                } else {
                    System.out.println("Your efforts were not enough, and all animals died of depression.");
                    killAllAnimals();
                }
                break;
            case 3: // Give them time
                if (chance < 60) {
                    System.out.println("Giving them time to process things helped them recover.");
                } else {
                    System.out.println("The time was not enough, and all animals died of depression.");
                    killAllAnimals();
                }
                break;
            default:
                System.out.println("Invalid choice! All animals died of depression.");
                killAllAnimals();
                break;
        }
    }

    private void killRandomAnimals() {
        //Objekt zur Erzeugung zufälliger Zahlen
        Random random = new Random();
        //Berechnet zufällige Anzahl an Todesfällen
        int cowDeaths = random.nextInt(cowList.size() + 1); //erzeugt zuf. Ganzzahl von 0 - cowList size + 1 ermöglicht, dass Zahl um eins größer sein kann als Elemente in Liste = alle Elemente können getötet werden, sonst wäre es Listsize - 1
        int sheepDeaths = random.nextInt(sheepList.size() + 1);
        int chickenDeaths = random.nextInt(chickenList.size() + 1);
        int beeDeaths = random.nextInt(beesList.size() + 1);

        //Tötung von Tieren basierend auf Todesfällen
        for (int i = 0; i < cowDeaths && !cowList.isEmpty(); i++) {
            cowList.remove(random.nextInt(cowList.size()));
        }
        for (int i = 0; i < sheepDeaths && !sheepList.isEmpty(); i++) {
            sheepList.remove(random.nextInt(sheepList.size()));
        }
        for (int i = 0; i < chickenDeaths && !chickenList.isEmpty(); i++) {
            chickenList.remove(random.nextInt(chickenList.size()));
        }
        for (int i = 0; i < beeDeaths && !beesList.isEmpty(); i++) {
            beesList.remove(random.nextInt(beesList.size()));
        }

        System.out.println("The wolves killed some of your animals.");
        printAllAnimals();
    }

    //Tötet alle Tiere
    private void killAllAnimals() {
        cowList.clear();
        sheepList.clear();
        chickenList.clear();
        beesList.clear();
    }
}