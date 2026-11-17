import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FurnitureCollection {

    private List<Furniture> items;
    public FurnitureCollection(){
        this.items=new ArrayList<>();
    }
    public void addFurniture(Furniture item){
        if (item != null){
            items.add(item);
        }
    }

    public Furniture findById(String id){
        for (Furniture f : items){
            if (f.getId().equalsIgnoreCase(id)){
                return f;
            }
        }
        return null;
    }




    public void loadFromFile(String filename){
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()){
                String inputLine = scanner.nextLine();
                if (!inputLine.trim().isEmpty()){
                    processLine(inputLine);
                }
            }
            scanner.close();

            if (items.isEmpty()){
                System.out.println("File is empty or has no valid items: " + filename);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(filename + " not found ");
            System.exit(0);
        }
    }



    private void processLine(String line){
        try {
            String parts[] = line.split(",");

            if (parts.length > 11){
                System.out.println("Too many fields in line: " + line);
                return;
            }

            for (int i=0; i<11; i++){
                if (parts[i].trim().isEmpty()){
                    System.out.println("Missing field in line: " + line);
                    return;
                }
            }

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

            if (findById(id) != null){
                System.out.println("Duplicate id " + id + " in line: " + line);
                return;
            }



            if (yr <= 0 || yr > 2026 || price <= 0 || len <= 0 || ht <= 0 || dep <= 0){
                System.out.println("Unrealistic value in line: " + line);
                return;
            }


            if (!isValidCondition(cond)){
                System.out.println("Unexpected condition value in line: " + line + ". Should be (mint, restored, needs restoring)");
                return;
            }

            Furniture item = new Furniture(id, yr, owner, cond, price, type, style, maker, len, ht, dep);
            this.addFurniture(item);
        }
        catch (NumberFormatException nfe){
            System.out.println("Number conversion error in line: " + line);
        }
        catch (ArrayIndexOutOfBoundsException aio){
            System.out.println("Not enough items in line: " + line);
        }
    }
    private boolean isValidCondition(String cond){
        if (cond.equalsIgnoreCase("mint") || cond.equalsIgnoreCase("restored") || cond.equalsIgnoreCase("needs restoring")){
            return true;
        }
        return false;
    }


    public int getNumberOfItems(){
        return items.size();
    }
    public Furniture getOldestItem(){
        if (items.isEmpty()){
            return null;
        }
        Furniture oldest=items.get(0);
        for (Furniture f : items){
            if (f.getYearOfOrigin() < oldest.getYearOfOrigin()){
                oldest=f;
            }
        }
        return oldest;
    }
    public Furniture getNewestItem(){
        if (items.isEmpty()){
            return null;
        }
        Furniture newest=items.get(0);
        for (Furniture f : items){
            if (f.getYearOfOrigin() > newest.getYearOfOrigin()){
                newest=f;
            }
        }
        return newest;
    }
    public Furniture getMostExpensiveItem(){
        if (items.isEmpty()){
            return null;
        }
        Furniture mostExpensive=items.get(0);
        for (Furniture f : items){
            if (f.getStartingPrice() > mostExpensive.getStartingPrice()){
                mostExpensive=f;
            }
        }
        return mostExpensive;
    }


    public Furniture getLeastExpensiveItem(){
        if (items.isEmpty()){
            return null;
        }
        Furniture leastExpensive=items.get(0);
        for (Furniture f : items){
            if (f.getStartingPrice() < leastExpensive.getStartingPrice()){
                leastExpensive=f;
            }
        }
        return leastExpensive;
    }
    public List<String> getUniqueOwners(){
        List<String> uniqueOwners=new ArrayList<>();
        for (Furniture f : items){
            String owner=f.getOwner();
            if (!uniqueOwners.contains(owner)){
                uniqueOwners.add(owner);
            }
        }
        return uniqueOwners;
    }
    public double getAveragePrice(){
        if (items.isEmpty()){
            return 0.0;
        }
        double total = 0;
        for (Furniture f : items){
            total += f.getStartingPrice();
        }
        return total / items.size();
    }
    public double getStandardDeviationPrice(){
        if (items.isEmpty()){
            return 0.0;
        }
        double mean = getAveragePrice();
        double sumSq = 0;
        for (Furniture f : items){
            double diff = f.getStartingPrice() - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / items.size());
    }
    public String getConditionBreakdown(){
        int mintCount=0;
        int restoredCount=0;
        int needsRestoringCount=0;
        for (Furniture f : items){
            String c = f.getCondition();
            if (c.equalsIgnoreCase("mint")){
                mintCount++;
            }
            else if (c.equalsIgnoreCase("restored")){
                restoredCount++;
            }
            else if (c.equalsIgnoreCase("needs restoring")){
                needsRestoringCount++;
            }
        }
        String breakdown = "Mint: " + mintCount + "\n";
        breakdown += "Restored: " + restoredCount + "\n";
        breakdown += "Needs restoring: " + needsRestoringCount;
        return breakdown;
    }
    public void saveStatistics(String filename){
        String report = "Stastic Summary\n";
        report += "1) Total number of items: " + getNumberOfItems() + "\n\n";
        Furniture oldest = getOldestItem();
        Furniture newest = getNewestItem();
        report += "2) Oldest and Newest Items:\n";
        if (oldest != null && newest != null){
            report += "Oldest: " + oldest.getinfo() + " (Year: " + oldest.getYearOfOrigin() + ")\n";
            report += "Newest: " + newest.getinfo() + " (Year: " + newest.getYearOfOrigin() + ")\n\n";
        }
        Furniture mostExp = getMostExpensiveItem();
        Furniture leastExp = getLeastExpensiveItem();
        report += "3) Most and Least Expensive Items:\n";
        if (mostExp != null && leastExp != null){
            report += "Most expensive: " + mostExp.getinfo() + " (Price: " + String.format("%.2f", mostExp.getStartingPrice()) + ")\n";
            report += "Least expensive: " + leastExp.getinfo() + " (Price: " + String.format("%.2f", leastExp.getStartingPrice()) + ")\n\n";
        }
        report += "4) Starting Prices Summary:\n";
        report += "Average starting price: " + String.format("%.2f", getAveragePrice()) + "\n";
        report += "Standard deviation: " + String.format("%.2f", getStandardDeviationPrice()) + "\n\n";
        report += "5) Breakdown by Condition:\n";
        report += getConditionBreakdown() + "\n";
        FileWriter fw;
        try {
            fw = new FileWriter(filename);
            fw.write(report);
            fw.close();
        }
        catch (FileNotFoundException fnf){
            System.out.println(filename + " not found ");
            System.exit(0);
        }
        catch (IOException ioe){
            System.out.println("Error writing to file " + filename);
            System.exit(1);
        }
    }
}

