package toolrentalservice;

public class Tool {
    private String code;
    private String type;
    private String brand;
    private Long weekday;
    private Long weekend;
    private Long holiday;
    
    public Tool(String code){
        this.code = code;
        if(code.toUpperCase().equals("LADW")){
            this.type = "Ladder";
            this.brand = "Werner";
            this.weekday = 199L;
            this.weekend = 199L;
            this.holiday = 0L;
        }else if(code.toUpperCase().equals("CHNS")){
            this.type = "Chainsaw";
            this.brand = "Stihl";
            this.weekday = 149L;
            this.weekend = 0L;
            this.holiday = 149L;
        }else if(code.toUpperCase().equals("JAKD")){
            this.type = "Jackhammer";
            this.brand = "DeWalt";
            this.weekday = 299L;
            this.weekend = 0L;
            this.holiday = 0L;
        }else if(code.toUpperCase().equals("JAKR")){
            this.type = "Jackhammer";
            this.brand = "Ridgid";
            this.weekday = 299L;
            this.weekend = 0L;
            this.holiday = 0L;
        }
    }

    public long getWeekdayCharge(){
        if(!weekday.equals(0L)){
            return weekday;
        }
        return 0L;
    }

    public long getWeekendCharge(){
        if(!weekend.equals(0L)){
            return weekend;
        }
        return 0L;
    }

    public long getHolidayCharge(){
        if(!holiday.equals(0L)){
            return holiday;
        }
        return 0L;
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

}
