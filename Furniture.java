public class Furniture {

    // Attributes
    private String id; 
    private int yearOfOrigin; 
    private String owner;
    private String condition; 
    private double startingPrice; 

    // Furniture-specific attributes
    private String type;
    private String style; 
    private String maker;
    private double length; 
    private double height;
    private double depth;

    // Constructor
    public Furniture(String id, int yearOfOrigin, String owner,String condition, double startingPrice, String type, String style, String maker, double length, double height, double depth) {

        this.id=id;
        this.yearOfOrigin=yearOfOrigin;
        this.owner=owner;
        this.condition=condition;
        this.startingPrice=startingPrice;
        this.type=type;
        this.style=style;
        this.maker=maker;
        this.length=length;
        this.height=height;
        this.depth=depth;
    }

    // Queries
    public String getId(){
        return id;
    }

    public int getYearOfOrigin(){
        return yearOfOrigin;
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
    @Override
    public String toString(){
        return "Furniture[id=" + id+ ", type=" + type + ", style=" + style + ", maker=" + maker + ", year=" + yearOfOrigin + ", owner=" + owner + ", condition=" + condition + ", price=" + startingPrice + ", dimensions=" + length + "x" + height + "x" + depth + "]";
    }
}