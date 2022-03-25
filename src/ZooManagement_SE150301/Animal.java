package ZooManagement_SE150301;

import java.util.ArrayList;

public abstract class Animal {
    private String id;
    private String name;
    private String food;
    private double weight;
    private int numberOfSwings;
    private int numberOfLegs;

    public Animal() {
        super();
    }

    public Animal(String id, int numberOfLegs, String name, String food, double weight, int numberOfSwings) {
        this.id = id;
        this.numberOfLegs = numberOfLegs;
        this.name = name;
        this.weight = weight;
        this.numberOfSwings = numberOfSwings;
        this.food = food;
    }

    public abstract int getTypeID();

    public String getID(){
        return this.id;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFood(){
        return this.food;
    }

    public void setFood(String food){
        this.food = food;
    }
    
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNumberOfLegs(){
        return this.numberOfLegs;
    }
    
     public void setNumberOfLegs(int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }
     
    public int getNumberOfSwings() {
        return this.numberOfSwings;
    }

    public void setNumberOfSwings(int numberOfSwings) {
        this.numberOfSwings = numberOfSwings;
    }
    
    public void output() {
        System.out.println(
                ">> ID: " + id
                + "\n>> Number of legs: " + numberOfLegs
                + "\n>> Name: " + name
                + "\n>> Food: " + food 
                + "\n>> Weight: " + weight
                + "\n>> Number of swing: " + numberOfSwings);
    }
    public abstract String StringToFile();
} 
