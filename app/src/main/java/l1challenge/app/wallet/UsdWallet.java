package l1challenge.app.wallet;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class UsdWallet extends Wallet{
    public UsdWallet(){}

    public UsdWallet(String ownerId){
        super(ownerId);

    }

    @Override
    protected int getCurrencyDecimals() {
        return 2;
    }

    @Override
    public String getCurrency() {
        return "USD";
    }

}
