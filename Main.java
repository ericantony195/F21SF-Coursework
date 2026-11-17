import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FurnitureCollection collection=new FurnitureCollection();
        Scanner in = new Scanner(System.in);
        boolean active=true;

        while (active){
            System.out.println("1) Add new furniture item");
            System.out.println("2) Get number of items");
            System.out.println("3) Get oldest item");
            System.out.println("4) Get newest item");
            System.out.println("5) Get most expensive item");
            System.out.println("6) Get least expensive item");
            System.out.println("7) Get unique owners");
            System.out.println("8) Exit");
            System.out.print("Choice: ");

            String choiceinput=in.nextLine();
            int choice = Integer.parseInt(choiceinput.trim());

            if (choice == 1){
                System.out.println("Enter item (id,year,owner,condition,startingprice,type,style,maker,length,height,depth):");
                String inputLine=in.nextLine();
                String parts[] = inputLine.split(",");

                String id=parts[0].trim();
                int yr = Integer.parseInt(parts[1].trim());
                String owner=parts[2].trim();
                String cond=parts[3].trim();
                double price = Double.parseDouble(parts[4].trim());
                String type=parts[5].trim();
                String style = parts[6].trim();
                String maker=parts[7].trim();
                double len = Double.parseDouble(parts[8].trim());
                double ht=Double.parseDouble(parts[9].trim());
                double dep = Double.parseDouble(parts[10].trim());

                Furniture item = new Furniture(id, yr, owner, cond, price, type, style, maker, len, ht, dep);
                collection.addFurniture(item);
                System.out.println("Added.");
            }
            else if (choice == 2){
                System.out.println("Number of items: " + collection.getNumberOfItems());
            }
            else if (choice == 3){
                System.out.println("Oldest item is: " + collection.getOldestItem());
            }
            else if (choice == 4){
                System.out.println("Newest item is: " + collection.getNewestItem());
            }
            else if (choice == 5){
                System.out.println("Most expensive item is: " + collection.getMostExpensiveItem());
            }
            else if (choice == 6){
                System.out.println("Least expensive item is: " + collection.getLeastExpensiveItem());
            }
            else if (choice == 7){
                System.out.println("Unique owners are:");
                List<String> owners=collection.getUniqueOwners();
                for (String owner : owners){
                    System.out.println(" - " + owner);
                }
            }
            else if (choice == 8){
                active = false;
            }
        }
    }
}