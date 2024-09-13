import animals.Bees;
import animals.Chicken;
import animals.Cow;
import animals.Sheep;
import farm.Farm;
import java.util.Scanner;


public class Main {
    static Farm farm = new Farm(); //neues Farm-Objekt
    static Scanner scanner = new Scanner(System.in); //Scanner um User-Input auszulesen
    static String userInput; //User-Input wird als String in userInput gespeichert
    static int round = 1; //Variable für Rundenzähler, die erhöht werden soll. Als Runde gilt hier, wenn entweder add/feed oder harvest 3x ausgeführt wurde.
    //die booleans werden verwendet, um bei den Abfragen zu kontrollieren, welches Tier aktuell ausgewählt ist

    public static void main(String[] args) {
        System.out.println("""
                Welcome to my i-Dyllic farm, where life is simple and the animals are plentiful!\s
                Here, you can take care of sheep, cows, and chickens, feed them, and collect their valuable resources\s
                like wool, milk, and eggs. But be careful, as the hunger of our animals grows steadily,\s
                and occasionally a hungry wolf sneaks in, disrupting our peaceful balance""");

        System.out.println("Are you ready to master life on the i-Dyllic farm? (yes / no)");
        userInput = scanner.nextLine();
        System.out.println();

        if (userInput.equalsIgnoreCase("yes")) {

            farm.initializeFarm();

            while (true) {
                checkRound();

                farm.printAllAnimals();

                System.out.println("What do you want to do? Pick one option: Feed / Add / Harvest");
                userInput = scanner.nextLine();
                System.out.println();

                if (userInput.equalsIgnoreCase("feed")) {
                    chooseFeeding();
                    round++;

                } else if (userInput.equalsIgnoreCase("add")) {
                    chooseAdding();
                    round++;

                } else if (userInput.equalsIgnoreCase("harvest")) {
                    chooseHarvesting();
                    round++;

                } else {
                    System.out.println("Pick a valid option.\n");
                }

                farm.increaseHunger();

                boolean isDeadAnimal = farm.checkAnimalsAlive();
                if (isDeadAnimal) {
                    System.out.println("One or more animals are starved to death. You are not a good farmer my friend.");
                    break;
                }
            }
        } else System.out.println("Bye.");
    }

    public static void checkRound(){
        if (round % 3 == 0) {
            farm.increaseAge(); //Alter erhöht bei jeder dritten Runde
            farm.handleWolfAttack(scanner); //Wolfattacke bei jeder dritten Runde
        }
    }

    public static void chooseFeeding(){
        boolean isDoneFeeding = false;
        while (!isDoneFeeding) {
            System.out.println("Do you want to feed: All / Cows / Sheeps / Chickens / BeeHive");
            userInput = scanner.nextLine();
            System.out.println();

            if (userInput.equalsIgnoreCase("all")) {
                farm.feedAllAnimals("cow");
                farm.feedAllAnimals("sheep");
                farm.feedAllAnimals("chicken");
                farm.feedAllAnimals("bees");
                isDoneFeeding = true;
            } else if (userInput.equalsIgnoreCase("cows")) {
                System.out.println("Do you want to feed all cows or only one? If only one, enter it's name");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("all")) {
                    farm.feedAllAnimals("cow");
                } else {
                    boolean animalExists = farm.checkAnimalsExist("cow", userInput);
                    while (!animalExists) {
                        System.out.println("Please enter a name of an existing cow.");
                        userInput = scanner.nextLine();
                        animalExists = farm.checkAnimalsExist("cow", userInput);
                    }
                    farm.feedSingleAnimal("cow", userInput);
                }
            } else if (userInput.equalsIgnoreCase("sheeps")) {
                System.out.println("Do you want to feed all sheeps or only one? If only one, enter it's name");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("all")) {
                    farm.feedAllAnimals("sheep");
                } else {
                    boolean animalExists = farm.checkAnimalsExist("sheep", userInput);
                    while (!animalExists) {
                        System.out.println("Please enter a name of an existing sheep.");
                        userInput = scanner.nextLine();
                        animalExists = farm.checkAnimalsExist("sheep", userInput);
                    }
                    farm.feedSingleAnimal("sheep", userInput);
                }

            } else if (userInput.equalsIgnoreCase("chickens")) {
                System.out.println("Do you want to feed all chicken or only one? If only one, enter it's name.");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("all")) {
                    farm.feedAllAnimals("chicken");
                } else {
                    boolean animalExists = farm.checkAnimalsExist("chicken", userInput);
                    while (!animalExists) {
                        System.out.println("Please enter a name of an existing chicken.");
                        userInput = scanner.nextLine();
                        animalExists = farm.checkAnimalsExist("chicken", userInput);
                    }
                    farm.feedSingleAnimal("chicken", userInput);
                }

            } else if (userInput.equalsIgnoreCase("BeeHive")) {
                System.out.println("Do you want to feed all hives or only one? If only one, enter hive name.");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("all")) {
                    farm.feedAllAnimals("bees");
                } else {
                    boolean animalExists = farm.checkAnimalsExist("bees", userInput);
                    while (!animalExists) {
                        System.out.println("Please enter a name of an existing Hive.");
                        userInput = scanner.nextLine();
                        animalExists = farm.checkAnimalsExist("bees", userInput);
                    }
                    farm.feedSingleAnimal("bees", userInput);
                }

            } else {
                System.out.println("Pick a valid option. \n");
            }

            farm.printAllAnimals(); //zur besseren Übersicht

            System.out.println("Do you want to feed another animal?");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("no")) {
                isDoneFeeding = true;
            }
        }
    }

    public static void chooseAdding(){
        boolean isDoneAdding = false;
        while (!isDoneAdding) {
            System.out.println("Which species do you want to add to? Cow / Sheep / Chicken / BeeHive");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("Cow")) {

                System.out.println("Please enter a cow name.");
                String cowName = scanner.nextLine();
                System.out.println("Please enter how hungry the new cow is.");
                int cowHungry = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter the age of the cow.");
                int cowAge = Integer.parseInt(scanner.nextLine()); // Hier parsen wir auf ein Int weil der Userinput ein String ist und für Alter wir ein int brauchen
                System.out.println("Please enter the milk amount of the cow.");
                int cowMilkAmount = Integer.parseInt(scanner.nextLine());
                Cow newCow = new Cow(cowName, cowHungry, cowAge, cowMilkAmount);
                farm.addAnimal(newCow);

            } else if (userInput.equalsIgnoreCase("sheep")) {
                System.out.println("Please enter a sheep name.");
                String sheepName = scanner.nextLine();
                System.out.println("Please enter how hungry the new sheep is.");
                int sheepHungry = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter the age of the sheep.");
                int sheepAge = Integer.parseInt(scanner.nextLine()); // Hier parsen wir auf ein Int weil der Userinput ein String ist und für Alter wir ein int brauchen
                System.out.println("Please enter the wool amount of the sheep.");
                int sheepWoolAmount = Integer.parseInt(scanner.nextLine());
                Sheep newSheep = new Sheep(sheepName, sheepHungry, sheepAge, sheepWoolAmount);
                farm.addAnimal(newSheep);

            } else if (userInput.equalsIgnoreCase("chicken")) {
                System.out.println("Please enter a chicken name.");
                String chickenName = scanner.nextLine();
                System.out.println("Please enter how hungry the new chicken is.");
                int chickenHungry = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter the age of the chicken.");
                int chickenAge = Integer.parseInt(scanner.nextLine()); // Hier parsen wir auf ein Int weil der Userinput ein String ist und für Alter wir ein int brauchen
                System.out.println("Please enter the egg amount of the chicken.");
                int chickenEggAmount = Integer.parseInt(scanner.nextLine());
                Chicken newChicken = new Chicken(chickenName, chickenHungry, chickenAge, chickenEggAmount);
                farm.addAnimal(newChicken);

            } else if (userInput.equalsIgnoreCase("BeeHive")) {
                System.out.println("Please enter a BeeHive name.");
                String beesName = scanner.nextLine();
                System.out.println("Please enter how hungry the new BeeHive is.");
                int beesHungry = Integer.parseInt(scanner.nextLine());
                System.out.println("Please enter the age of the BeeHive.");
                int beesAge = Integer.parseInt(scanner.nextLine()); // Hier parsen wir auf ein Int weil der Userinput ein String ist und für Alter wir ein int brauchen
                System.out.println("Please enter the honey amount of the BeeHive.");
                int beesHoneyAmount = Integer.parseInt(scanner.nextLine());
                Bees newBees = new Bees(beesName, beesHungry, beesAge, beesHoneyAmount);
                farm.addAnimal(newBees);
            } else {
                System.out.println("Pick a valid option. \n");
            }
            farm.printAllAnimals();
            System.out.println("Do you want to add another Animal? (yes / no)");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("no")) {
                isDoneAdding = true;
            }
        }
    }

    public static void chooseHarvesting(){
        boolean isDoneHarvesting = false;
        while (!isDoneHarvesting) {
            System.out.println("Which species do you want to harvest from? Cow / Sheep / Chicken / BeeHive");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("cow")) {
                farm.harvestMilk();
            } else if (userInput.equalsIgnoreCase("sheep")) {
                farm.harvestWool();
            } else if (userInput.equalsIgnoreCase("chicken")) {
                farm.harvestEggs();
            } else if (userInput.equalsIgnoreCase("beeHive")) {
                farm.harvestHoney();
            } else {
                System.out.println("Pick a valid option. \n");
            }
            farm.printAllAnimals();

            System.out.println("Do you want to harvest another animal?(yes / no)");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("no")) {
                isDoneHarvesting = true;
            }
        }
    }
}

