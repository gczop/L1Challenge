package l1challenge.app.wallet;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class Wallet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected int id;
    protected String value;
    protected String ownerId;
    protected String decimals;

    public Wallet(){}
    public Wallet(BigDecimal value, String ownerId, int decimals){
        this.value = value.setScale(decimals).toString();
        this.ownerId = ownerId;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setValue(String value){
        this.value = value;
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

}
