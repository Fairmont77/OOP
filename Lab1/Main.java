import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Vegetable> salad = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("1. Add vegetable to salad");
            System.out.println("2. Sort salad");
            System.out.println("3. Find vegetables in salad that match a given calorie range");
            System.out.println("4. Exit");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    addVegetableToSalad(salad, in);
                    displaySaladContents(salad);
                    break;
                case 2:
                    sortSalad(salad, in);
                    displaySalad(salad);
                    break;
                case 3:
                    findVegetablesByCalories(salad, in);
                    break;
            }
        }

        System.out.println("Total Calories: " + calculateTotalCalories(salad));
    }

    private static void addVegetableToSalad(ArrayList<Vegetable> salad, Scanner in) {
        System.out.println("1. Add tomato");
        System.out.println("2. Add cucumber");
        System.out.println("3. Add carrot");
        System.out.println("4. Add onion");
        System.out.println("5. Add pepper");

        int choice = in.nextInt();
        switch (choice) {
            case 1:
                salad.add(new Tomato());
                break;
            case 2:
                salad.add(new Cucumber());
                break;
            case 3:
                salad.add(new Carrot());
                break;
            case 4:
                salad.add(new Onion());
                break;
            case 5:
                salad.add(new Pepper());
                break;
        }
    }

    private static void sortSalad(ArrayList<Vegetable> salad, Scanner in) {
        System.out.println("1. Sort by calories");
        System.out.println("2. Sort by weight");

        int choice = in.nextInt();
        switch (choice) {
            case 1:
                Collections.sort(salad, Comparator.comparingInt(Vegetable::getCalories));
                break;
            case 2:
                Collections.sort(salad, Comparator.comparingInt(Vegetable::getWeight));
                break;
        }
    }

    private static void findVegetablesByCalories(ArrayList<Vegetable> salad, Scanner in) {
        System.out.println("Enter min calories");
        int minCalories = in.nextInt();
        System.out.println("Enter max calories");
        int maxCalories = in.nextInt();
        boolean found = false;

        for (Vegetable vegetable : salad) {
            if (vegetable.getCalories() >= minCalories && vegetable.getCalories() <= maxCalories) {
                System.out.println(vegetable.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("The salad does not contain products with the following calorie range: " + minCalories + " to " + maxCalories);
        }
    }

    private static void displaySaladContents(ArrayList<Vegetable> salad) {
        System.out.println("The salad contains:");
        for (Vegetable vegetable : salad) {
            System.out.println("- " + vegetable.getName());
        }
    }

    private static void displaySalad(ArrayList<Vegetable> salad) {
        for (Vegetable vegetable : salad) {
            System.out.println(vegetable.getName() + " - Calories: " + vegetable.getCalories() + ", Weight: " + vegetable.getWeight());
        }
    }

    private static int calculateTotalCalories(ArrayList<Vegetable> salad) {
        int totalCalories = 0;
        for (Vegetable vegetable : salad) {
            totalCalories += vegetable.getCalories();
        }
        return totalCalories;
    }
}

class Vegetable {
    private String name;
    private int calories;
    private int weight;

    public Vegetable(String name, int calories, int weight) {
        this.name = name;
        this.calories = calories;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getWeight() {
        return weight;
    }
}

class Tomato extends Vegetable {
    public Tomato() {
        super("Tomato", 18, 100);
    }
}

class Cucumber extends Vegetable {
    public Cucumber() {
        super("Cucumber", 15, 150);
    }
}

class Carrot extends Vegetable {
    public Carrot() {
        super("Carrot", 41, 200);
    }
}

class Onion extends Vegetable {
    public Onion() {
        super("Onion", 40, 50);
    }
}

class Pepper extends Vegetable {
    public Pepper() {
        super("Pepper", 31, 70);
    }
}
