package l1challenge.app.wallet;

import java.math.BigDecimal;

public class UsdtWallet extends Wallet{
    public UsdtWallet(){}

    public UsdtWallet(String ownerId){
        super(BigDecimal.ZERO, ownerId, 8);
    }
}
