package ZooManagement_SE150301;

import java.util.Scanner;
import java.util.Vector;

public class Menu {
    Vector <String> menu = new Vector<>();
    public int choice = 0;

    
    public void add(String Option){
        menu.add(Option);
    }

    public void printMenu() {
        Scanner sc = new Scanner (System.in);

        // Print Out
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + "- " + menu.get(i));
        }

        int flag;
        do {
            flag = 0;
            System.out.print("\nEnter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {
                flag = 1;
            }
            if (flag == 1)
                System.out.println(">> Choice must be a number!");
        } while (flag == 1);

        System.out.println();
    }
}