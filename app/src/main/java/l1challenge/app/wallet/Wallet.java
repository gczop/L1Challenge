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
    protected int decimals;

    public Wallet(){}
    public Wallet(String ownerId, int decimals){
        this.value = BigDecimal.ZERO.setScale(decimals).toString();
        this.ownerId = ownerId;
        this.decimals = decimals;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setValue(String value){
        this.value = new BigDecimal(value).setScale(decimals).toString();
    }

    public String getValue(){
        return this.value.toString();
    }

    public void setOwnerId(String ownerId){
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setDecimals(int decimals){
        this.decimals = decimals;
    }

    public int getDecimals(){
        return this.decimals;
    }

    public void addAmount(String amountString){
        BigDecimal amount = new BigDecimal(amountString).setScale(decimals, RoundingMode.HALF_UP);
        value = new BigDecimal(value).add(amount).toString();
    }

    public void extractAmount(String amountString){
        BigDecimal amount = new BigDecimal(amountString).setScale(decimals,  RoundingMode.HALF_UP);
        value = new BigDecimal(value).subtract(amount).toString();
    }

}
