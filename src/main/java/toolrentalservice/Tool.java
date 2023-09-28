package toolrentalservice;

import java.math.BigDecimal;
import java.math.MathContext;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private boolean weekday;
    private boolean weekend;
    private boolean holiday;
    private BigDecimal dailyRate;
    
    public Tool(String code){
        this.code = code;
        if(code.toUpperCase().equals("LADW")){
            this.type = "Ladder";
            this.brand = "Werner";
            this.weekday = true;
            this.weekend = true;
            this.holiday = false;
            this.dailyRate = new BigDecimal(1.99);
        }else if(code.toUpperCase().equals("CHNS")){
            this.type = "Chainsaw";
            this.brand = "Stihl";
            this.weekday = true;
            this.weekend = false;
            this.holiday = true;
            this.dailyRate = new BigDecimal(1.49);
        }else if(code.toUpperCase().equals("JAKD")){
            this.type = "Jackhammer";
            this.brand = "DeWalt";
            this.weekday = true;
            this.weekend = false;
            this.holiday = false;
            this.dailyRate = new BigDecimal(299);
        }else if(code.toUpperCase().equals("JAKR")){
            this.type = "Jackhammer";
            this.brand = "Ridgid";
            this.weekday = true;
            this.weekend = false;
            this.holiday = false;
            this.dailyRate = new BigDecimal(299);
        }
    }

    public BigDecimal getWeekdayCharge(){
        if(weekday){
            return getDailyRate();
        }
        return new BigDecimal(0);
    }

    public BigDecimal getWeekendCharge(){
        if(weekend){
            return getDailyRate();
        }
       return new BigDecimal(0);
    }

    public BigDecimal getHolidayCharge(){
        if(holiday){
            return getDailyRate();
        }
        return new BigDecimal(0);
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

    public BigDecimal getDailyRate(){
        BigDecimal afterDecimal = dailyRate.remainder(BigDecimal.ONE);

        return dailyRate.subtract(afterDecimal).add(afterDecimal.round(new MathContext(3, java.math.RoundingMode.HALF_UP))).stripTrailingZeros();
    }

    public String toString(){
        String toolString = "Tool Code: " + code + "\n";
        toolString += "Tool Type: " + type + "\n";
        toolString += "Tool Brand: " + brand + "\n";
        toolString += "Tool Daily Rate: " + getDailyRate() + "\n";
        return toolString;
    }
}
