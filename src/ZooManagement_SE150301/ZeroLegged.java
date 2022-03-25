package ZooManagement_SE150301;

public class ZeroLegged extends Animal {
    private int type_id = 1;
    private boolean poisonous;

    public boolean getPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }
    public int getTypeID() {
        return this.type_id;
    }

    public ZeroLegged(String id, int numberOfLegs, String name, String food, double weight, int numberOfSwings, boolean poisonous) {
        super(id, numberOfLegs, name, food, weight, numberOfSwings);
        this.poisonous = poisonous;
    }
    
    @Override
    public void output() {
        super.output();
        System.out.println(">> Animal have poison: " + getPoisonous());
        System.out.println(">> Type: Zero-legged Animals");
    }
    
    @Override
    public String StringToFile(){
        return getID()
        + " | " + getNumberOfLegs()
        + " | " + getName()
        + " | " + getFood()
        + " | " + getWeight()
        + " | " + getNumberOfSwings()
        + " | " + getPoisonous()
        + " | " + "ZLA";
    }

}