public class Furniture extends Collectible {

    // private String id; 
    // private int yearOfOrigin; 
    // private String owner;
    // private String condition; 
    // private double startingPrice; 

    private String type;
    private String style; 
    private String maker;
    private double length; 
    private double height;
    private double depth;

    public Furniture(String id, YearEstimate yearOfOrigin, String owner,String condition, double startingPrice, String type, String style, String maker, double length, double height, double depth) {
        super(id, yearOfOrigin, owner, condition, startingPrice);
        this.type=type;
        this.style=style;
        this.maker=maker;
        this.length=length;
        this.height=height;
        this.depth=depth;
    }
    public String getType(){
        return type;
    }
    public String getStyle(){
        return style;
    }
    public String getMaker(){
        return maker;
    }
    public double getLength(){
        return length;
    }
    public double getHeight(){
        return height;
    }
    public double getDepth(){
        return depth;
    }
    public String getinfo(){
        return id + ". " + style + " " + type + " by " + maker;
    }
    @Override
    public String toString(){
        // return "Furniture[id=" + id+ ", type=" + type + ", style=" + style + ", maker=" + maker + ", year=" + yearOfOrigin + ", owner=" + owner + ", condition=" + condition + ", price=" + startingPrice + ", dimensions=" + length + "x" + height + "x" + depth + "]";
        return "Furniture[" + super.toString() + ", type=" + type + ", style=" + style + ", maker=" + maker + ", dimensions=" + length + "x" + height + "x" + depth + "]";
    }
}