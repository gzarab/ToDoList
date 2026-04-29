
public class List {
    private int day;
    private int month;
    private int quant;
    private String entry;
    private String status;

    List(){
    status = "Done";
    }
    List(String entry, int quant, String status, int day, int month){
        this.entry = entry;
        this.quant = quant;
        this.status = status;
        this.month = month;
        this.day = day;
    }
    public String toString(){
        return String.format("%s : #%d - %s", entry, quant, status);
    }
    public boolean equals(List other){
        return (this.entry == other.entry);
    }
    public void setEntry(String entry){
        this.entry = entry;
    }
    public void setQuant(int quant){
        this.quant = quant;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setDay(int day){
        this.day = day;
    }
    public String getEntry(){
        return this.entry;
    }
    public int getQuant(){
        return this.quant;
    }
    public String getStatus(){
        return this.status;
    }
    public int getMonth(){
        return this.month;
    }
    public int getDay(){
        return this.day;
    }
}
