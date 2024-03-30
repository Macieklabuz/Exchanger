
import Currency.Currency;

import java.math.BigDecimal;


public class Exchanger {
    private static Exchanger exchanger;

    public static Exchanger getInstance() {
        if (exchanger == null) {
            exchanger = new Exchanger();
        }
        return exchanger;
    }


    public BigDecimal exchange(Currency sourceCurrency, Currency finalCurrency, double amount) {
        BigDecimal sourceFactor = BigDecimal.valueOf(sourceCurrency.getFactor());
        BigDecimal sourceRate = (sourceCurrency.getRate());
        BigDecimal finalRate = (finalCurrency.getRate());
        BigDecimal finalFactor = BigDecimal.valueOf(finalCurrency.getFactor());
        BigDecimal inputAmount = BigDecimal.valueOf(amount);

        BigDecimal output = inputAmount
                .divide(sourceFactor, 10, BigDecimal.ROUND_HALF_UP)
                .multiply(sourceRate)
                .divide(finalRate, 10, BigDecimal.ROUND_HALF_UP)
                .multiply(finalFactor);

        return output.setScale(2, BigDecimal.ROUND_HALF_UP);

    }
}
