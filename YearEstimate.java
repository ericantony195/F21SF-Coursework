public class YearEstimate {

    private int low;
    private int high;

    public YearEstimate(int low, int high){
        this.low=low;
        this.high=high;
    }
    public int getLow(){
        return low;
    }
    public int getHigh(){
        return high;
    }
    public int getMiddleEstimate(){
        // double avg = (low + high) / 2.0;
        return (low + high) / 2;
    }
    public int getDiff(){
        return high - low;
    }
    public String getRange(){
        return low + "-" + high;
    }
    public String toString(){
        return "" + getMiddleEstimate();
    }
}