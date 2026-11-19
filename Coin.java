public class Coin extends Collectible {

    private String material;
    private String placeOfOrigin;
    private String originalValue;

    public Coin(String id, YearEstimate yearOfOrigin, String owner, String condition, double startingPrice, String material, String placeOfOrigin, String originalValue){
        super(id, yearOfOrigin, owner, condition, startingPrice);
        this.material=material;
        this.placeOfOrigin=placeOfOrigin;
        this.originalValue=originalValue;
    }
    public String getMaterial(){
        return material;
    }
    public String getPlaceOfOrigin(){
        return placeOfOrigin;
    }
    public String getOriginalValue(){
        return originalValue;
    }
    public String getinfo(){
        return id + ". " + material + " Coin from " + placeOfOrigin + " (" + originalValue + ")";
    }
    public String toString(){
        return "Coin[" + super.toString() + ", material=" + material + ", placeOfOrigin=" + placeOfOrigin + ", originalValue=" + originalValue + "]";
    }
}