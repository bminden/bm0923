package toolrentalservice;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

public class ChargeCalculator {
    public static Money calculatePrediscountCharges(Tool tool, Date startDate, int rentalDays){
        Money total = new Money("0.00", Currency.getInstance("USD"));
        Date currDate = startDate;
        for(int x = 0; x < rentalDays; x++){
            total.add(determineCharge(tool, currDate));
            currDate = addDay(currDate, 1);
        }
        return total;
    }

    public static Money determineCharge(Tool tool, Date date){
        if(isAHoliday(date)){
            return tool.getHolidayCharge();
        }else{
            if(isAWeekday(date)){
                return tool.getWeekdayCharge();
            }else{
                return tool.getWeekdayCharge();
            }
        }
    }


    private static boolean isAHoliday(Date date){
        return isLaborDay(date) || isJulyFourth(date);
    }

    private static boolean isLaborDay(Date date) {
        if(getDayOfTheWeek(date) == Calendar.MONDAY && getMonth(date) == Calendar.SEPTEMBER && getDayOfWeekInMonth(date) == 1){
            return true;
        }
        return false;
    }

    private static boolean isJulyFourth(Date date){
        if(getMonth(date) == Calendar.JULY){
            int dayOfTheMonth = getDayOfMonth(date);
            int dayOfTheWeek = getDayOfTheWeek(date);
            if(dayOfTheMonth == 4 && dayOfTheWeek != Calendar.SATURDAY && dayOfTheWeek != Calendar.SUNDAY){
                return true;
            }
            if(getDayOfMonth(date) == 3 && getDayOfTheWeek(date) == Calendar.FRIDAY){
                return true;
            }
            if(getDayOfMonth(date) == 5 && getDayOfTheWeek(date) == Calendar.MONDAY){
                return true;
            }
        }

        return false;
    }

    private static boolean isAWeekday(Date date){
        int dayOfWeek = getDayOfTheWeek(date);
        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY){
            return false;
        }
        return true;
    }

    private static int getMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    private static int getDayOfWeekInMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    private static int getDayOfTheWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    private static int getDayOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    private static Date addDay(Date startDate, int daysToMoveForward){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, daysToMoveForward);
        return cal.getTime();
    }
}
