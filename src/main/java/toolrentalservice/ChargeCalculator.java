package toolrentalservice;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class ChargeCalculator {
    public static int calculateChargeDays(Tool tool, Date startDate, int rentalDays){
        int total = 0;
        Date currDate = startDate;
        for(int x = 0; x < rentalDays; x++){
            if(determineCharge(tool, currDate)){
                total++;
            }
            currDate = DateHelper.addDay(currDate, 1);
        }
        return total;
    }

    private static boolean determineCharge(Tool tool, Date date){
        if(DateHelper.isAHoliday(date)){
            return tool.isHolidayChargeable();
        }else{
            if(DateHelper.isAWeekday(date)){
                return tool.isWeekdayChargeable();
            }else{
                return tool.isWeekdayChargeable();
            }
        }
    }

    public static RentalAgreement generateRentalAgreement(Tool tool, int rentalDayCount, int discountPercent, Date checkoutDate){
        Date dueDate = DateHelper.addDay(checkoutDate, rentalDayCount);
        int chargeDays = calculateChargeDays(tool, checkoutDate, rentalDayCount);
        Money prediscountCharge = (new Money(String.valueOf(tool.getDailyRate().getAmount().multiply(new BigDecimal(chargeDays))), Currency.getInstance("USD")));
        Money finalCharge = prediscountCharge.multiply(new Money(new BigDecimal(100-discountPercent).divide(new BigDecimal(100)), Currency.getInstance("USD")));
        Money totalDiscount = finalCharge.subtract(prediscountCharge);

        RentalAgreement rentalAgreement = new RentalAgreement(tool, checkoutDate, dueDate, rentalDayCount, chargeDays, prediscountCharge, discountPercent, totalDiscount, finalCharge);
        return rentalAgreement;
    }
}
