package l1challenge.app.wallet;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class UsdtWallet extends Wallet{
    public UsdtWallet(){
    }

    public UsdtWallet(String ownerAlias){
        super(ownerAlias);
    }

    @Override
    protected int getCurrencyDecimals() {
        return 8;
    }

    @Override
    public String getCurrency() {
        return "USDT";
    }

}
