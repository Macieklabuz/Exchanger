package Currency;
import java.math.BigDecimal;


public class Currency {
    private String code;
    private String name;
    private Integer factor;
    private BigDecimal rate;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Integer getFactor()
    {
        return factor;
    }

    public void setFactor(Integer factor)
    {
        this.factor = factor;
    }

    public BigDecimal getRate()
    {
        return rate;
    }

    public void setRate(BigDecimal rate)
    {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Currency currency = (Currency) obj;
        return this.code.equals(currency.getCode());
    }

}