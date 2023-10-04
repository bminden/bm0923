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

    public Money(BigDecimal amount, Currency currency){
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public Currency getCurrency(){
        return currency;
    }

    public Money add(Money amountToAdd){
        return new Money(this.amount.add(amountToAdd.getAmount()).toString(), this.currency);
    }

    public Money subtract(Money amountToAdd){
        return new Money(this.amount.subtract(amountToAdd.getAmount()).toString(), this.currency);
    }

    public Money multiply(Money multiplier){
        return new Money(this.amount.multiply(multiplier.getAmount()).setScale(2, RoundingMode.HALF_UP), this.currency);
    }

    private BigDecimal setBigDecimalRulesBasedOnCurrency(String amount, Currency currency){
        if(currency.getCurrencyCode().equals("USD")){
            return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        }
        throw new IllegalArgumentException("Currency not supported");
    }
}
