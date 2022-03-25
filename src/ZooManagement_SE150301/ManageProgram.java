package ZooManagement_SE150301;

public class ManageProgram {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Zoo zoo = new Zoo();
        String fileName = "zoo.txt";
        
        menu.add("Load data from file");
        menu.add("Add new animal");
        menu.add("Update animal");
        menu.add("Delete animal");
        menu.add("Search animal");
        menu.add("Show animal list");
        menu.add("Store data to file");
        menu.add("Quit");
        do {
            
            System.out.println("   | ZOO MANAGER |\n");
            menu.printMenu();
            switch (menu.choice) {
                case 1:
                    zoo.loadFromFile(fileName);
                    break;
                case 2:
                    zoo.addNewAnimal();
                    break;
                case 3:
                    zoo.updateAnimal();
                    break;
                case 4:
                    zoo.deleteAnimal();
                    break;
                case 5:
                    zoo.searchAnimal();
                    break;
                case 6:
                    zoo.showAnimal();
                    break;
                case 7:
                    zoo.saveToFile(fileName);
                    break;
                case 8:
                    System.out.println();
                    break;
                default:
                    System.out.println(">> Input only number from 1 to 8!");
                    break;
            }
            System.out.println("________________________________________\n");
        } while (menu.choice > 0 && menu.choice != menu.menu.size());

    }
}