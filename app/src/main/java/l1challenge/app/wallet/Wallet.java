package l1challenge.app.wallet;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.math.RoundingMode;

@MappedSuperclass
public abstract class Wallet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected int id;
    protected String value;
    protected String ownerAlias;

    public Wallet(){}
    public Wallet(String ownerAlias){
        this.value = BigDecimal.ZERO.setScale(getCurrencyDecimals()).toPlainString();
        this.ownerAlias = ownerAlias;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setValue(String value){
        this.value = new BigDecimal(value).setScale(getCurrencyDecimals()).toPlainString();
    }

    public String getValue(){
        return this.value;
    }

    public void setOwnerAlias(String ownerAlias){
        this.ownerAlias = ownerAlias;
    }

    public String getOwnerAlias() {
        return this.ownerAlias;
    }

    public void addAmount(String amountString){
        BigDecimal amount = new BigDecimal(amountString).setScale(getCurrencyDecimals(), RoundingMode.HALF_UP);
        value = new BigDecimal(value).add(amount).toPlainString();
    }

    public void extractAmount(String amountString){
        BigDecimal amount = new BigDecimal(amountString).setScale(getCurrencyDecimals(),  RoundingMode.HALF_UP);
        value = new BigDecimal(value).subtract(amount).toString();
    }

    protected abstract int getCurrencyDecimals();
    public abstract String getCurrency();

}
