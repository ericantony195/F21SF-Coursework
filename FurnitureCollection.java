import java.util.ArrayList;
import java.util.List;

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
}