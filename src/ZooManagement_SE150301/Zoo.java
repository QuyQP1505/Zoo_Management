package ZooManagement_SE150301;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Zoo {
    Scanner sc = new Scanner(System.in);
    ArrayList<Animal> zoo = new ArrayList<>();

    public int find (String id) {
        for (int i = 0; i < zoo.size(); i++) {
            if (zoo.get(i).getID().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    public void loadFromFile(String fName) {
        // Checking file existence
        File f = new File(fName);
        if (!f.exists())
            System.out.println(">> File " + fName + " does not exist !\n");
        else {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String str = null;
                while ((str = br.readLine()) != null) {
                    if (!str.isEmpty()) {
                        StringTokenizer stk = new StringTokenizer(str, "|");
                        String id = stk.nextToken().toUpperCase().trim();
                        int numberOfLegs = Integer.parseInt(stk.nextToken().trim());
                        String name = stk.nextToken().toUpperCase().trim();
                        String food = stk.nextToken().toUpperCase().trim();
                        double weight = Double.parseDouble(stk.nextToken().trim());
                        int numberOfSwings = Integer.parseInt(stk.nextToken().trim());
                        String type = stk.nextToken().toUpperCase().trim();
                        if (find(id) < 0) {
                            switch (type){
                             case "ZLA":
                                 boolean poisonous = Boolean.parseBoolean(stk.nextToken()); 
                                 zoo.add(new ZeroLegged(id, numberOfLegs, name, food, weight, numberOfSwings, poisonous));
                                 break;
                             case "BFLA":
                                 boolean cute = Boolean.parseBoolean(stk.nextToken()); 
                                 zoo.add(new Flightless(id, numberOfLegs, name, food, weight, numberOfSwings, cute));
                                 break;
                             case "BFA":
                                 boolean sing = Boolean.parseBoolean(stk.nextToken());
                                 zoo.add(new Flying(id, numberOfLegs, name, food, weight, numberOfSwings, sing));
                                 break;
                             case "FLA":
                                 boolean danger = Boolean.parseBoolean(stk.nextToken());
                                 boolean canGrowl = Boolean.parseBoolean(stk.nextToken());
                                 zoo.add(new FourLegged(id, numberOfLegs, name, food, weight, numberOfSwings, danger, canGrowl));
                                 break;
                             default:
                                 break;
                             }
                        }
                    }
                }
                br.close();
                fr.close();
            } catch (IOException | NumberFormatException e) {
            System.out.println(">> ERROR: " + e);
            }
        System.out.println(">> Successfully loaded data from file " + fName + "!\n");
        }
    }

    public void saveToFile(String fName) {
        if (zoo.isEmpty()) {
            System.out.println(">> List is empty. No animals to save!\n");
            return;
        }
        try {
            FileWriter fw = new FileWriter(fName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < zoo.size(); i++) {
                bw.write(zoo.get(i).StringToFile());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(">> Failed to save!");
            System.out.println("ERROR: " + e);
            return;
        }
        System.out.println(">> Successfully saved data to file " + fName + "!\n");
    }

    public void showAnimal() {
        if (zoo.isEmpty())
            System.out.println(">> List is empty. No animals to show!\n");
        else {
            int showChoice = 0, flag;
            System.out.println("\t1- Show by type");
            System.out.println("\t2- Show all");

            do {
                System.out.println();
                flag = 0;
                System.out.print("Select way to show: ");
                try {
                    showChoice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                if (flag == 1 || showChoice < 1 || showChoice > 2)
                    System.out.println(">> Invalid choice!\n");
            } while (flag == 1 || showChoice < 1 || showChoice > 2);

            if (showChoice == 1) {
                int typeID = 0, countPrinted = 0;
                System.out.println("\t-------------------");
                System.out.println("\t4 GROUPS OF ANIMALS");
                System.out.println("\t-------------------");
                System.out.println("\t1- 0-legged animals");
                System.out.println("\t2- Fightless animals");
                System.out.println("\t3- Flying animals");
                System.out.println("\t4- 4-legged animals");
                do {
                    flag = 0;
                    System.out.print("\nEnter group number you want to show: ");
                    try {
                        typeID = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        flag = 1;
                    }
                    if (flag == 1 || typeID > 4 || typeID < 1)
                        System.out.println(">> Invalid group number!\n");
                } while (flag == 1 || typeID > 4 || typeID < 1);

                for (int i = 0; i < zoo.size(); i++) {
                    if (zoo.get(i).getTypeID() == typeID) {
                        System.out.println();
                        zoo.get(i).output();
                        countPrinted++;
                    }
                }
                if (countPrinted == 0)
                    System.out.println(">> No animals to show for this group!\n");
                else
                    System.out.println();
            }
            if (showChoice == 2) {
                System.out.println();
                for (int i = 0; i < zoo.size(); i++) {
                    zoo.get(i).output();
                    System.out.println();
                }
            }
        }
    }

    public void addNewAnimal() {
        String addMore;
        do {
            int typeID = 0, flag, numberOfSwings = 0;
            double weight = 0.0;
            String id, name, food, property, property2;
            System.out.println("\t-------------------");
            System.out.println("\t4 GROUPS OF ANIMALS");
            System.out.println("\t-------------------");
            System.out.println("\t1- 0-legged animals");
            System.out.println("\t2- Fightless animals");
            System.out.println("\t3- Flying animals");
            System.out.println("\t4- 4-legged animals");

            // Lấy group id
            do {
                flag = 0;
                System.out.print("\nEnter group number you want to add: ");
                try {
                    typeID = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                if (flag == 1 || typeID > 4 || typeID < 1)
                    System.out.println(">> Invalid group number!\n");
            } while (flag == 1 || typeID > 4 || typeID < 1);

            // Input id
            System.out.println();
            do {
                System.out.print("Enter ID (Zxxx): ");
                id = sc.nextLine().toUpperCase();
                if (!id.matches("^Z\\d{3}$"))
                    System.out.println(">> Wrong ID format!\n");
                if (find(id) >= 0)
                    System.out.println(">> ID is existed!\n");
            } while (!id.matches("^Z\\d{3}$") || find(id) >= 0);

            // Input name
            do {
                System.out.print("Enter name: ");
                name = sc.nextLine().toUpperCase();
                if (name.isEmpty())
                    System.out.println(">> Name cannot be blank!\n");
            } while (name.isEmpty());
            
            //Input food
            do {
                System.out.print("Enter food: ");
                food = sc.nextLine().toUpperCase();
                if (food.isEmpty())
                    System.out.println(">> Food cannot be blank!\n");
            } while (name.isEmpty());
            
            // Input weight
            do {
                flag = 0;
                try {
                    System.out.print("Enter weight (kg): ");
                    weight = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                if (flag == 1 || weight < 1)
                    System.out.println(">> Invalid input!\n");
            } while (flag == 1 || weight < 1);

            // Input swings num
            do {
                flag = 0;
                try {
                    System.out.print("Enter number of swings: ");
                    numberOfSwings = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                if (flag == 1 || numberOfSwings < 0)
                    System.out.println(">> Invalid input!\n");
                else if (numberOfSwings > 2) 
                    System.out.println(">> Number of wings can not more than two!\n");
            } while (flag == 1 || numberOfSwings < 0 || numberOfSwings > 2);

            // Input yes or no in specific property
            switch(typeID){
                case 1:
                    do {
                        System.out.print("is it poison? (Y/N): ");
                        property = sc.nextLine().toUpperCase();
                        if ((!property.equals("Y")) && (!property.equals("N")))
                            System.out.println(">> Invalid choice!\n");
                        else
                            System.out.println();
                    } while ((!property.equals("Y")) && (!property.equals("N")));
                    zoo.add(new ZeroLegged(id, 0, name, food, weight, numberOfSwings, property.startsWith("Y")));
                    break;
                case 2:
                    do {
                        System.out.print("is it cute? (Y/N): ");
                        property = sc.nextLine().toUpperCase();
                        if ((!property.equals("Y")) && (!property.equals("N")))
                            System.out.println(">> Invalid choice!\n");
                        else
                            System.out.println();
                    } while ((!property.equals("Y")) && (!property.equals("N")));
                    zoo.add(new Flightless(id, 2, name, food, weight, numberOfSwings, property.startsWith("Y")));
                    break;
                case 3:
                    do {
                        System.out.print("is it can sing? (Y/N): ");
                        property = sc.nextLine().toUpperCase();
                        if ((!property.equals("Y")) && (!property.equals("N")))
                            System.out.println(">> Invalid choice!\n");
                        else
                            System.out.println();
                    } while ((!property.equals("Y")) && (!property.equals("N")));
                    zoo.add(new Flying(id, 2, name, food, weight, numberOfSwings, property.startsWith("Y")));
                    break;
                case 4:
                    do {
                        System.out.print("is it danger? (Y/N): ");
                        property = sc.nextLine().toUpperCase();
                        if ((!property.equals("Y")) && (!property.equals("N")))
                            System.out.println(">> Invalid choice!\n");
                    } while ((!property.equals("Y")) && (!property.equals("N")));
                    
                    do {
                        System.out.print("is it can growl? (Y/N): ");
                        property2 = sc.nextLine().toUpperCase();
                        if ((!property.equals("Y")) && (!property.equals("N")))
                            System.out.println(">> Invalid choice!\n");
                        else
                            System.out.println();
                    } while ((!property.equals("Y")) && (!property.equals("N")));
                    
                    zoo.add(new FourLegged(id, 4, name, food, weight, numberOfSwings, property.startsWith("Y"), property2.startsWith("Y")));
                    break;
                default:
                    break;
                    }
            // Add more animal or not
            do {
                System.out.print("Do you want to add more animal? (Y/N): ");
                addMore = sc.nextLine().toUpperCase();
                if ((!addMore.equals("Y")) && (!addMore.equals("N")))
                    System.out.println(">> Invalid choice!\n");
                else
                    System.out.println();
            } while ((!addMore.equals("Y")) && (!addMore.equals("N")));
        } while (addMore.equals("Y"));
    }

    public void updateAnimal(){
        if (zoo.isEmpty())
            System.out.println(">> List is empty. No animals to update!\n");
        else {
            String idToUpdate;
            int typeID = 0, flag;
            System.out.println("\t-------------------");
            System.out.println("\t4 GROUPS OF ANIMALS");
            System.out.println("\t-------------------");
            System.out.println("\t1- 0-legged animals");
            System.out.println("\t2- Fightless animals");
            System.out.println("\t3- Flying animals");
            System.out.println("\t4- 4-legged animals");
            
            // Lấy group id
            do {
                flag = 0;
                System.out.print("\nEnter group number you want to update: ");
                try {
                    typeID = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                if (flag == 1 || typeID > 4 || typeID < 1)
                    System.out.println(">> Invalid group number!\n");
            } while (flag == 1 || typeID > 4 || typeID < 1);
            
            
            // Enter id
            do {
                System.out.print("Enter animal's ID you want to update: ");
                idToUpdate = sc.nextLine().toUpperCase();
                if (idToUpdate.isEmpty())
                    System.out.println(">> ID cannot be blank!\n");
            } while (idToUpdate.isEmpty());

            int pos = find(idToUpdate);
            if (pos < 0) {
                System.out.println(">> Animal does not exist!\n");
            } else {
                Animal animal = zoo.get(pos);
                String newName, newFood;
                double newWeight = 0.0;
                int newNumberOfSwings = 0;
                String newProperty, newProperty2;
                String weight, swings;

                System.out.print("Enter new name: ");
                newName = sc.nextLine().toUpperCase();

                if (!newName.isEmpty()) {
                    zoo.get(pos).setName(newName);
                    System.out.println(">> New name (" + newName + ") was updated!\n");
                } else
                    System.out.println(">> Old name was kept!\n");
                
                System.out.print("Enter new food: ");
                newFood = sc.nextLine().toUpperCase();

                if (!newFood.isEmpty()) {
                    zoo.get(pos).setFood(newFood);
                    System.out.println(">> New food (" + newFood + ") was updated!\n");
                } else
                    System.out.println(">> Old food was kept!\n");

                do {
                    System.out.print("Enter new weight (kg): ");
                    weight = sc.nextLine();
                    if (weight.isEmpty())
                        break;
                    else
                        flag = 0;
                    try {
                        newWeight = Double.parseDouble(weight);
                    } catch (NumberFormatException e) {
                        flag = 1;
                    }
                    if (flag == 1 || newWeight < 0)
                        System.out.println(">> Invalid input!\n");
                } while (flag == 1 || newWeight < 0);
                if (weight.isEmpty())
                    System.out.println(">> Old weight was kept!\n");
                else {
                    zoo.get(find(idToUpdate)).setWeight(newWeight);
                    System.out.println(">> New weight (" + newWeight + " kg) was updated!\n");
                }

                do {
                    System.out.print("Enter new number of swings: ");
                    swings = sc.nextLine();
                    flag = 0;
                    if (swings.isEmpty())
                        break;
                    try {
                        newNumberOfSwings = Integer.parseInt(swings);
                    } catch (NumberFormatException e) {
                        flag = 1;
                    }
                    if (flag == 1 || newNumberOfSwings < 0 || newNumberOfSwings > 2)
                        System.out.println(">> Invalid input!\n");
                } while (flag == 1 || newNumberOfSwings < 0 || newNumberOfSwings > 2);

                if (swings.isEmpty())
                    System.out.println(">> Old number of swings was kept!\n");
                else {
                    zoo.get(pos).setNumberOfSwings(newNumberOfSwings);
                    System.out.println(">> New number of swings (" + newNumberOfSwings + ") was updated!\n");
                }

                // Update Property
                switch(typeID){
                    case 1:
                        do {
                            System.out.print("is it poison? (Y/N): ");
                            newProperty = sc.nextLine().toUpperCase();
                            if ((!newProperty.equals("Y")) && (!newProperty.equals("N")))
                                System.out.println(">> Invalid choice!\n");
                            else if (newProperty.isEmpty()) {
                                System.out.println(">> Old property of Zero - legged animal was kept!\n");
                            }
                            else System.out.println("");  
                        } 
                        while ((!newProperty.equals("Y")) && (!newProperty.equals("N")));
                        ((ZeroLegged)animal).setPoisonous(newProperty.startsWith("Y"));
                        break;
                    case 2:
                        do {
                            System.out.print("is it cute? (Y/N): ");
                            newProperty = sc.nextLine().toUpperCase();
                            if ((!newProperty.equals("Y")) && (!newProperty.equals("N")))
                                System.out.println(">> Invalid choice!\n");
                            else if (newProperty.isEmpty()) {
                                System.out.println(">> Old property of Bipedal and flightless animal was kept!\n");
                            }
                            else System.out.println("");  
                        } 
                        while ((!newProperty.equals("Y")) && (!newProperty.equals("N")));
                        ((Flightless)animal).setCute(newProperty.startsWith("Y"));
                        break;
                    case 3:
                        do {
                            System.out.print("is it can sing? (Y/N): ");
                            newProperty = sc.nextLine().toUpperCase();
                            if ((!newProperty.equals("Y")) && (!newProperty.equals("N")))
                                System.out.println(">> Invalid choice!\n");
                            else if (newProperty.isEmpty()) {
                                System.out.println(">> Old property of Bipedal and flying animal was kept!\n");
                            }
                            else System.out.println("");  
                        } 
                        while ((!newProperty.equals("Y")) && (!newProperty.equals("N")));
                        ((Flying)animal).setCanSing(newProperty.startsWith("Y"));
                        break;
                    case 4:
                        do {
                            System.out.print("is it danger? (Y/N): ");
                            newProperty = sc.nextLine().toUpperCase();
                            if ((!newProperty.equals("Y")) && (!newProperty.equals("N")))
                                System.out.println(">> Invalid choice!\n");
                            else if (newProperty.isEmpty()) {
                                System.out.println(">> Old property of Bipedal and flightless animals was kept!\n");
                            }
                            else System.out.println("");  
                        } 
                        while ((!newProperty.equals("Y")) && (!newProperty.equals("N")));
                        
                        do {
                            System.out.print("is it danger? (Y/N): ");
                            newProperty2 = sc.nextLine().toUpperCase();
                            if ((!newProperty.equals("Y")) && (!newProperty.equals("N")))
                                System.out.println(">> Invalid choice!\n");
                            else {
                                System.out.println();
                            }
                        } 
                        while ((!newProperty.equals("Y")) && (!newProperty.equals("N")));
                        
                        ((FourLegged)animal).setDanger(newProperty.startsWith("Y"));
                        ((FourLegged)animal).setCanGrowl(newProperty2.startsWith("Y"));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void deleteAnimal() {
        if (zoo.isEmpty()) {
            System.out.println(">> List is empty. No animals to delete!\n");
            return;
        } else {
            String idToDelete;
            do {
                System.out.print("Enter animal's ID you want to delete: ");
                idToDelete = sc.nextLine().toUpperCase();
                if (idToDelete.isEmpty())
                    System.out.println(">> ID cannot be blank!\n");
            } while (idToDelete.isEmpty());
            int pos = find(idToDelete);
            if (pos < 0)
                System.out.println(">> ID does not exist!\n");
            else {
                String confirmDelete;
                do {
                    System.out.print("Do you want to delete animal (ID = " + idToDelete + ")? (Y/N): ");
                    confirmDelete = sc.nextLine().toUpperCase();
                    if (!confirmDelete.equals("Y") && !confirmDelete.equals("N"))
                        System.out.println(">> Invalid choice!\n");
                } while (!confirmDelete.equals("Y") && !confirmDelete.equals("N"));

                if (confirmDelete.equals("Y")) {
                    zoo.remove(pos);
                    System.out.println(">> Animal was deleted!\n");
                }
            }
        }
    }

    public void searchAnimal() {
        int searchChoice = 0, flag;
        System.out.println("\t1- Search by Name");
        System.out.println("\t2- Search by Weight\n");
        do {
            flag = 0;
            System.out.print("Select the way you want to search: ");
            try {
                searchChoice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                flag = 1;
            }
            if (flag == 1 || searchChoice > 2 || searchChoice < 1)
                System.out.println(">> Invalid choice!\n");
        } while (flag == 1 || searchChoice > 2 || searchChoice < 1);

        if (searchChoice == 1) {
            System.out.println();
            String nameToSearch;
            int countPrinted = 0;
            do {
                System.out.print("Enter animal's name you want search: ");
                nameToSearch = sc.nextLine().toUpperCase();
                if (nameToSearch.isEmpty())
                    System.out.println(">> Name cannot be blank!\n");
            } while (nameToSearch.isEmpty());

            for (int i = 0; i < zoo.size(); i++) {
                if (zoo.get(i).getName().contains(nameToSearch)) {
                    System.out.println();
                    zoo.get(i).output();
                    countPrinted++;
                }
            }

            if (countPrinted == 0)
                System.out.println(">> No animals with that name were found!\n");
            else
                System.out.println();
        }

        if (searchChoice == 2) {
            double UpperWeight = 0.0;
            double LowerWeight = 0.0;
            int countPrinted = 0;
            do {
                flag = 0;
                System.out.print("Enter loweranimal's weight (kg) you want to search: ");
                try {
                    LowerWeight = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                System.out.print("Enter upper animal's weight (kg) you want to search: ");
                try {
                    UpperWeight = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    flag = 1;
                }
                if (flag == 1 || LowerWeight < 0 || UpperWeight < 0 || LowerWeight >= UpperWeight)
                    System.out.println(">> Invalid input!\n");
            } while (flag == 1 || LowerWeight < 0 || UpperWeight < 0 || LowerWeight >= UpperWeight);

            for (int i =0; i < zoo.size();i++) {
                if (zoo.get(i).getWeight() >= LowerWeight && zoo.get(i).getWeight() <= UpperWeight) {
                    System.out.println();
                    zoo.get(i).output();
                    countPrinted++;
                }
            }

            if (countPrinted == 0)
                System.out.println(">> No animals with that name were found!\n");
            else
                System.out.println();
        }
    }
}