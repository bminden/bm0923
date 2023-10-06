package toolrentalservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static boolean isAHoliday(Date date){
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

    public static boolean isAWeekday(Date date){
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

    public static Date addDay(Date startDate, int daysToMoveForward){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_MONTH, daysToMoveForward);
        return cal.getTime();
    }

    public static Date createDate(String dateString){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid date string.");
        }
    }
}
