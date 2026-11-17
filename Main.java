import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FurnitureCollection collection=new FurnitureCollection();

        collection.loadFromFile("furniture.csv");
        // collection.loadFromFile("corrupted_missing.csv");
        // collection.loadFromFile("corrupted_numbers.csv");
        // collection.loadFromFile("corrupted_condition.csv");

        Scanner in = new Scanner(System.in);
        boolean active=true;

        while (active){
            System.out.println("1) Get number of items");
            System.out.println("2) Get oldest item");
            System.out.println("3) Get newest item");
            System.out.println("4) Get most expensive item");
            System.out.println("5) Get least expensive item");
            System.out.println("6) Get unique owners");
            System.out.println("7) Get average and standard deviation of prices");
            System.out.println("8) Get condition breakdown");
            System.out.println("9) Save statistics to file");
            System.out.println("10) Exit");
            System.out.print("Choice: ");

            String choiceinput=in.nextLine();
            int choice = 0;

            try {
                choice = Integer.parseInt(choiceinput.trim());
            }
            catch (NumberFormatException e){
                choice = 0;
            }

            if (choice < 1 || choice > 10){
                System.out.println("enter in range 1-10");
            }
            else if (choice == 1){
                System.out.println("Number of items: " + collection.getNumberOfItems());
            }
            else if (choice == 2){
                System.out.println("Oldest item is: " + collection.getOldestItem());
            }
            else if (choice == 3){
                System.out.println("Newest item is: " + collection.getNewestItem());
            }
            else if (choice == 4){
                System.out.println("Most expensive item is: " + collection.getMostExpensiveItem());
            }
            else if (choice == 5){
                System.out.println("Least expensive item is: " + collection.getLeastExpensiveItem());
            }
            else if (choice == 6){
                System.out.println("Unique owners are:");
                List<String> owners=collection.getUniqueOwners();
                for (String owner : owners){
                    System.out.println(" - " + owner);
                }
            }
            else if (choice == 7){
                System.out.println("Average price is: " + String.format("%.2f", collection.getAveragePrice()));
                System.out.println("Standard deviation is: " + String.format("%.2f", collection.getStandardDeviationPrice()));
            }
            else if (choice == 8){
                System.out.println("Condition breakdown:");
                System.out.println(collection.getConditionBreakdown());
            }
            else if (choice == 9){
                collection.saveStatistics("statistics.txt");
                System.out.println("Saved to statistics.txt");
            }
            else if (choice == 10){
                active = false;
            }
        }
    }
}