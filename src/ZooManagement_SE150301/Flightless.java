package ZooManagement_SE150301;

public class Flightless extends Animal {
    private int type_id = 2;
    private boolean cute;

    public boolean getCute() {
        return cute;
    }

    public void setCute(boolean cute) {
        this.cute = cute;
    }
    
    public int getTypeID() {
        return this.type_id;
    }

    public Flightless(String id, int numberOfLegs, String name, String food, double weight, int numberOfSwings, boolean cute) {
        super(id, numberOfLegs, name, food, weight, numberOfSwings);
        this.cute = cute;
    }
    
    @Override
    public void output() {
        super.output();
        System.out.println(">> Animal is Cute: " + getCute());
        System.out.println(">> Type: Bipedal and flightless animals");
    }
    
    @Override
    public String StringToFile(){
        return getID()
        + " | " + getNumberOfLegs()
        + " | " + getName()
        + " | " + getFood()
        + " | " + getWeight()
        + " | " + getNumberOfSwings()
        + " | " + getCute()
        + " | " + "BFLA";
    }
}