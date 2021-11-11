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
    protected String ownerId;

    public Wallet(){}
    public Wallet(String ownerId){
        this.value = BigDecimal.ZERO.setScale(getCurrencyDecimals()).toPlainString();
        this.ownerId = ownerId;
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

    public void setOwnerId(String ownerId){
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return this.ownerId;
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
