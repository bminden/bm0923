package toolrentalservice;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private boolean weekday;
    private boolean weekend;
    private boolean holiday;
    private int dailyRate;
    
    public Tool(String code){
        this.code = code;
        if(code.toUpperCase().equals("LADW")){
            this.type = "Ladder";
            this.brand = "Werner";
            this.weekday = true;
            this.weekend = true;
            this.holiday = false;
            this.dailyRate = 199;
        }else if(code.toUpperCase().equals("CHNS")){
            this.type = "Chainsaw";
            this.brand = "Stihl";
            this.weekday = true;
            this.weekend = false;
            this.holiday = true;
            this.dailyRate = 149;
        }else if(code.toUpperCase().equals("JAKD")){
            this.type = "Jackhammer";
            this.brand = "DeWalt";
            this.weekday = true;
            this.weekend = false;
            this.holiday = false;
            this.dailyRate = 299;
        }else if(code.toUpperCase().equals("JAKR")){
            this.type = "Jackhammer";
            this.brand = "Ridgid";
            this.weekday = true;
            this.weekend = false;
            this.holiday = false;
            this.dailyRate = 299;
        }
    }

    public int getWeekdayCharge(){
        if(weekday){
            return dailyRate;
        }
        return 0;
    }

    public int getWeekendCharge(){
        if(weekend){
            return dailyRate;
        }
        return 0;
    }

    public int getHolidayCharge(){
        if(holiday){
            return dailyRate;
        }
        return 0;
    }

    public String getCode(){
        return code;
    }

    public String getType(){
        return type;
    }

    public String getBrand(){
        return brand;
    }

    public String toString(){
        String toolString = "Tool Code: " + code + "\n";
        toolString += "Tool Type: " + type + "\n";
        toolString += "Tool Brand: " + brand + "\n";
        toolString += "Tool Daily Rate: " + dailyRate + "\n";
        return toolString;
    }
}
