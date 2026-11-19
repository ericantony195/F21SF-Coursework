public class Car extends Collectible {

    private String make;
    private String model;
    private boolean serviced;

    public Car(String id, YearEstimate yearOfOrigin, String owner, String condition, double startingPrice, String make, String model, boolean serviced){
        super(id, yearOfOrigin, owner, condition, startingPrice);
        this.make=make;
        this.model=model;
        this.serviced=serviced;
    }
    public String getMake(){
        return make;
    }
    public String getModel(){
        return model;
    }
    public boolean isServiced(){
        return serviced;
    }
    public String getinfo(){
        return id + ". " + make + " " + model + " (Serviced: " + serviced + ")";
    }
    public String toString(){
        return "Car[" + super.toString() + ", make=" + make + ", model=" + model + ", serviced=" + serviced + "]";
    }
}