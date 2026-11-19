public abstract class Collectible {

    protected String id; 
    protected YearEstimate yearOfOrigin; 
    protected String owner;
    protected String condition; 
    protected double startingPrice; 

    public Collectible(String id, YearEstimate yearOfOrigin, String owner,String condition, double startingPrice){
        this.id=id;
        this.yearOfOrigin=yearOfOrigin;
        this.owner=owner;
        this.condition=condition;
        this.startingPrice=startingPrice;
    }
    public String getId(){
        return id;
    }
    public YearEstimate getYearEstimate(){
        return yearOfOrigin;
    }
    public int getYearOfOrigin(){
        return yearOfOrigin.getMiddleEstimate();
    }
    public String getOwner(){
        return owner;
    }
    public String getCondition(){
        return condition;
    }
    public double getStartingPrice(){
        return startingPrice;
    }
    public abstract String getinfo();

    public String toString(){
        return "id=" + id+ ", Estimated Year of origin " + yearOfOrigin.getRange() + ", owner=" + owner + ", condition=" + condition + ", price=" + startingPrice;
    }
}