package toolrentalservice;

import java.util.Currency;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private boolean weekday;
    private boolean weekend;
    private boolean holiday;
    private Money dailyRate;
    
    public Tool(String code){
        this.code = code;
        if(code.toUpperCase().equals("LADW")){
            this.type = "Ladder";
            this.brand = "Werner";
            this.weekday = true;
            this.weekend = true;
            this.holiday = false;
            this.dailyRate = new Money("1.99", Currency.getInstance("USD"));
        }else if(code.toUpperCase().equals("CHNS")){
            this.type = "Chainsaw";
            this.brand = "Stihl";
            this.weekday = true;
            this.weekend = false;
            this.holiday = true;
            this.dailyRate = new Money("1.49", Currency.getInstance("USD"));

        }else if(code.toUpperCase().equals("JAKD")){
            this.type = "Jackhammer";
            this.brand = "DeWalt";
            this.weekday = true;
            this.weekend = false;
            this.holiday = false;
            this.dailyRate = new Money("2.99", Currency.getInstance("USD"));

        }else if(code.toUpperCase().equals("JAKR")){
            this.type = "Jackhammer";
            this.brand = "Ridgid";
            this.weekday = true;
            this.weekend = false;
            this.holiday = false;
            this.dailyRate = new Money("2.99", Currency.getInstance("USD"));
        }
    }

    public boolean isWeekdayChargeable(){
        return weekday;
    }

    public boolean isWeekendChargeable(){
        return weekend;
    }

    public boolean isHolidayChargeable(){
        return holiday;
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

    public Money getDailyRate(){
        return dailyRate;
    }

    public String toString(){
        String toolString = "Tool Code: " + code + "\n";
        toolString += "Tool Type: " + type + "\n";
        toolString += "Tool Brand: " + brand + "\n";
        toolString += "Tool Daily Rate: " + getDailyRate() + "\n";
        return toolString;
    }
}
