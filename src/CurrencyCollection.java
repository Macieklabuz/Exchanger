import java.util.ArrayList;
import java.util.List;
import Currency.Currency;

public class CurrencyCollection {
    private List<Currency> list;

    public CurrencyCollection()
    {
        list = new ArrayList<>();
    }


    public String ToString() {
        String temp = "";
        for (Currency x : list) {
            temp += "Code = " + x.getCode() + " | Factor = " + x.getFactor() + " | Rate = " + x.getRate() + "\n";
        }
        return temp;
    }


    public List<Currency> getCurrencyList()
    {
        return this.list;
    }


    public Currency getCurrencyByCode(Currency currency) {
        for (Currency x : list) {
            if (currency.equals(x)) {
                return x;
            }
        }
        return null;
    }
}
