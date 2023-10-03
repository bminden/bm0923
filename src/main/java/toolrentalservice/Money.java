package toolrentalservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {
    private BigDecimal amount;
    private Currency currency;

    public Money(String amount, Currency currency){
        this.amount = setBigDecimalRulesBasedOnCurrency(amount, currency);
        this.currency = currency;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public Currency getCurrency(){
        return currency;
    }

    public void add(Money amountToAdd){
        this.amount = this.amount.add(amountToAdd.getAmount());
    }

    public void multiple(Money multiplier){
        this.amount = this.amount.multiply(multiplier.getAmount()).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal setBigDecimalRulesBasedOnCurrency(String amount, Currency currency){
        if(currency.getCurrencyCode().equals("USD")){
            return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        }
        throw new IllegalArgumentException("Currency not supported");
    }
}
