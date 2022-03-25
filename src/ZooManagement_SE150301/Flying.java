package ZooManagement_SE150301;

public class Flying extends Animal {
    private int type_id = 3;
    private boolean CanSing;

    public boolean getCanSing() {
        return CanSing;
    }

    public void setCanSing(boolean CanSing) {
        this.CanSing = CanSing;
    }
    
    public int getTypeID() {
        return this.type_id;
    }

    public Flying(String id, int numberOfLegs, String name,String food, double weight, int numberOfSwings, boolean canSing) {
        super(id, numberOfLegs, name, food, weight, numberOfSwings);
        this.CanSing = CanSing;
    }

    @Override
    public void output() {
        super.output();
        System.out.println(">> Animal Can Sing: " + getCanSing());
        System.out.println(">> Type: Bipedal and flying animals");
    }
    
    @Override
    public String StringToFile(){
        return getID()
        + " | " + getNumberOfLegs()
        + " | " + getName()
        + " | " + getFood()
        + " | " + getWeight()
        + " | " + getNumberOfSwings()
        + " | " + getCanSing()
        + " | " + "BFA";
    }
}