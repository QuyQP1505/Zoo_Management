package ZooManagement_SE150301;


public class FourLegged extends Animal {
    private int type_id = 4;
    private boolean danger;
    private boolean  canGrowl;

    public boolean getCanGrowl() {
        return canGrowl;
    }

    public void setCanGrowl(boolean canGrowl) {
        this.canGrowl = canGrowl;
    }
    public boolean getDanger() {
        return danger;
    }

    public void setDanger(boolean danger) {
        this.danger = danger;
    }
    
    public int getTypeID() {
        return this.type_id;
    }

    public FourLegged (String id, int numberOfLegs, String name, String food, double weight, int numberOfSwings, boolean danger, boolean canGrowl) {
        super(id, numberOfLegs, name, food, weight, numberOfSwings);
        this.danger = danger;
        this.canGrowl = canGrowl;
    }

    @Override
    public void output() {
        super.output();
        System.out.println(">> Animal is Danger: " + getDanger());
        System.out.println(">> Animal can Growl: " + getCanGrowl());
        System.out.println(">> Type: Four-legged Animals");
    }
    
    @Override
    public String StringToFile(){
        return getID()
        + " | " + getNumberOfLegs()
        + " | " + getName()
        + " | " + getFood()
        + " | " + getWeight()
        + " | " + getNumberOfSwings()
        + " | " + getDanger()
        + " | " + getCanGrowl()
        + " | " + "FLA";
    }
}