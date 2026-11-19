import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Collection {

    private List<Collectible> items;

    public Collection(){
        this.items=new ArrayList<>();
    }
    public void addCollectible(Collectible item){
        if (item != null){
            items.add(item);
        }
    }
    public Collectible findById(String id){
        for (Collectible f : items){
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
            String itemType=parts[0].trim();

            int lenNeeded = 0;
            if (itemType.equalsIgnoreCase("Furniture")){
                lenNeeded = 13;
            }
            else if (itemType.equalsIgnoreCase("Book")){
                lenNeeded = 11;
            }
            else if (itemType.equalsIgnoreCase("Coin") || itemType.equalsIgnoreCase("Car")){
                lenNeeded = 10;
            }
            else {
                System.out.println("Unknown type in line: " + line);
                return;
            }

            if (parts.length > lenNeeded){
                System.out.println("Too many fields in line: " + line);
                return;
            }

            for (int i=0; i<lenNeeded; i++){
                if (parts[i].trim().isEmpty()){
                    System.out.println("Missing field in line: " + line);
                    return;
                }
            }

            String id=parts[1].trim();
            int lowYr = Integer.parseInt(parts[2].trim());
            int highYr = Integer.parseInt(parts[3].trim());
            String owner=parts[4].trim();
            String cond=parts[5].trim();
            double price = Double.parseDouble(parts[6].trim());

            if (findById(id) != null){
                System.out.println("Duplicate id in line: " + line);
                return;
            }
            if (lowYr <= 0 || highYr > 2026 || price <= 0){
                System.out.println("Unrealistic or negative number in line: " + line);
                return;
            }
            if (lowYr > highYr){
                System.out.println("Low year is bigger than high year in line: " + line);
                return;
            }
            if (!isValidCondition(cond)){
                System.out.println("Unexpected condition value in line: " + line);
                return;
            }

            YearEstimate yr = new YearEstimate(lowYr, highYr);

            if (itemType.equalsIgnoreCase("Furniture")){
                String type=parts[7].trim();
                String style = parts[8].trim();
                String maker=parts[9].trim();
                double len = Double.parseDouble(parts[10].trim());
                double ht=Double.parseDouble(parts[11].trim());
                double dep = Double.parseDouble(parts[12].trim());

                if (len <= 0 || ht <= 0 || dep <= 0){
                    System.out.println("Unrealistic or negative dimensions in line: " + line);
                    return;
                }
                Furniture item = new Furniture(id, yr, owner, cond, price, type, style, maker, len, ht, dep);
                this.addCollectible(item);
            }
            else if (itemType.equalsIgnoreCase("Book")){
                String title=parts[7].trim();
                String author=parts[8].trim();
                String edition=parts[9].trim();
                String genre=parts[10].trim();

                Book item = new Book(id, yr, owner, cond, price, title, author, edition, genre);
                this.addCollectible(item);
            }
            else if (itemType.equalsIgnoreCase("Coin")){
                String material=parts[7].trim();
                String place=parts[8].trim();
                String val=parts[9].trim();

                Coin item = new Coin(id, yr, owner, cond, price, material, place, val);
                this.addCollectible(item);
            }
            else if (itemType.equalsIgnoreCase("Car")){
                String make=parts[7].trim();
                String model=parts[8].trim();
                String serv=parts[9].trim();

                // boolean serviced = Boolean.parseBoolean(serv);
                boolean serviced=false;
                if (serv.equalsIgnoreCase("yes")){
                    serviced=true;
                }
                else if (serv.equalsIgnoreCase("no")){
                    serviced=false;
                }
                else {
                    System.out.println("Invalid serviced value in line: " + line);
                    return;
                }
                Car item = new Car(id, yr, owner, cond, price, make, model, serviced);
                this.addCollectible(item);
            }
        }
        catch (NumberFormatException nfe){
            System.out.println("Number conversion error in line: " + line);
        }
        catch (ArrayIndexOutOfBoundsException air){
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
    public Collectible getOldestByLow(){
        if (items.isEmpty()){
            return null;
        }
        Collectible oldest=items.get(0);
        for (Collectible f : items){
            if (f.getYearEstimate().getLow() < oldest.getYearEstimate().getLow()){
                oldest=f;
            }
        }
        return oldest;
    }
    public Collectible getNewestByLow(){
        if (items.isEmpty()){
            return null;
        }
        Collectible newest=items.get(0);
        for (Collectible f : items){
            if (f.getYearEstimate().getLow() > newest.getYearEstimate().getLow()){
                newest=f;
            }
        }
        return newest;
    }
    public Collectible getOldestByHigh(){
        if (items.isEmpty()){
            return null;
        }
        Collectible oldest=items.get(0);
        for (Collectible f : items){
            if (f.getYearEstimate().getHigh() < oldest.getYearEstimate().getHigh()){
                oldest=f;
            }
        }
        return oldest;
    }
    public Collectible getNewestByHigh(){
        if (items.isEmpty()){
            return null;
        }
        Collectible newest=items.get(0);
        for (Collectible f : items){
            if (f.getYearEstimate().getHigh() > newest.getYearEstimate().getHigh()){
                newest=f;
            }
        }
        return newest;
    }
    public Collectible getOldestItem(){
        if (items.isEmpty()){
            return null;
        }
        Collectible oldest=items.get(0);
        for (Collectible f : items){
            if (f.getYearOfOrigin() < oldest.getYearOfOrigin()){
                oldest=f;
            }
        }
        return oldest;
    }
    public Collectible getNewestItem(){
        if (items.isEmpty()){
            return null;
        }
        Collectible newest=items.get(0);
        for (Collectible f : items){
            if (f.getYearOfOrigin() > newest.getYearOfOrigin()){
                newest=f;
            }
        }
        return newest;
    }
    public List<Collectible> getTop3YearDiff(){
        List<Collectible> top3=new ArrayList<>();
        // Collectible max = items.get(0);
        for (int i=0; i<3 && i<items.size(); i++){
            Collectible maxDiffItem=null;
            for (Collectible f : items){
                if (!top3.contains(f)){
                    if (maxDiffItem == null || f.getYearEstimate().getDiff() > maxDiffItem.getYearEstimate().getDiff()){
                        maxDiffItem=f;
                    }
                }
            }
            if (maxDiffItem != null){
                top3.add(maxDiffItem);
            }
        }
        return top3;
    }
    public Collectible getMostExpensiveItem(){
        if (items.isEmpty()){
            return null;
        }
        Collectible mostExpensive=items.get(0);
        for (Collectible f : items){
            if (f.getStartingPrice() > mostExpensive.getStartingPrice()){
                mostExpensive=f;
            }
        }
        return mostExpensive;
    }
    public Collectible getLeastExpensiveItem(){
        if (items.isEmpty()){
            return null;
        }
        Collectible leastExpensive=items.get(0);
        for (Collectible f : items){
            if (f.getStartingPrice() < leastExpensive.getStartingPrice()){
                leastExpensive=f;
            }
        }
        return leastExpensive;
    }
    public List<String> getUniqueOwners(){
        List<String> uniqueOwners=new ArrayList<>();
        for (Collectible f : items){
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
        for (Collectible f : items){
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
        for (Collectible f : items){
            double diff = f.getStartingPrice() - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / items.size());
    }
    public String getConditionBreakdown(){
        int mintCount=0;
        int restoredCount=0;
        int needsRestoringCount=0;
        for (Collectible f : items){
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
        Collectible oldLow = getOldestByLow();
        Collectible newLow = getNewestByLow();
        report += "2) Oldest and Newest Items (Low Estimate):\n";
        if (oldLow != null && newLow != null){
            report += "Oldest: " + oldLow.getinfo() + " (Year: " + oldLow.getYearEstimate().getLow() + ")\n";
            report += "Newest: " + newLow.getinfo() + " (Year: " + newLow.getYearEstimate().getLow() + ")\n\n";
        }
        Collectible oldHigh = getOldestByHigh();
        Collectible newHigh = getNewestByHigh();
        report += "3) Oldest and Newest Items (High Estimate):\n";
        if (oldHigh != null && newHigh != null){
            report += "Oldest: " + oldHigh.getinfo() + " (Year: " + oldHigh.getYearEstimate().getHigh() + ")\n";
            report += "Newest: " + newHigh.getinfo() + " (Year: " + newHigh.getYearEstimate().getHigh() + ")\n\n";
        }
        Collectible oldest = getOldestItem();
        Collectible newest = getNewestItem();
        report += "4) Oldest and Newest Items (Average Estimate):\n";
        if (oldest != null && newest != null){
            report += "Oldest: " + oldest.getinfo() + " (Estimated year of origin: " + oldest.getYearOfOrigin() + ")\n";
            report += "Newest: " + newest.getinfo() + " (Estimated year of origin: " + newest.getYearOfOrigin() + ")\n\n";
        }
        report += "5) Top 3 Items with Most Difference in Year Estimates:\n";
        List<Collectible> top3 = getTop3YearDiff();
        for (Collectible f : top3){
            report += f.getinfo() + " and difference is " + f.getYearEstimate().getDiff() + " years (" + f.getYearEstimate().getRange() + ")\n";
        }
        report += "\n";
        Collectible mostExp = getMostExpensiveItem();
        Collectible leastExp = getLeastExpensiveItem();
        report += "6) Most and Least Expensive Items:\n";
        if (mostExp != null && leastExp != null){
            report += "Most expensive: " + mostExp.getinfo() + " (Price: " + String.format("%.2f", mostExp.getStartingPrice()) + ")\n";
            report += "Least expensive: " + leastExp.getinfo() + " (Price: " + String.format("%.2f", leastExp.getStartingPrice()) + ")\n\n";
        }
        report += "7. Starting Prices Summary:\n";
        report += "Average starting price: " + String.format("%.2f", getAveragePrice()) + "\n";
        report += "Standard deviation: " + String.format("%.2f", getStandardDeviationPrice()) + "\n\n";
        report += "8. Breakdown by Condition:\n";
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