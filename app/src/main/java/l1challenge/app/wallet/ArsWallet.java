package l1challenge.app.wallet;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class ArsWallet extends Wallet{

    public ArsWallet(){    }


    public ArsWallet(String ownerId){
        super(ownerId);
    }

    @Override
    protected int getCurrencyDecimals() {
        return 2;
    }

    @Override
    public String getCurrency() {
        return "ARS";
    }

}
