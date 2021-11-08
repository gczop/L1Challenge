package l1challenge.app.wallet;

import java.math.BigDecimal;

public class UsdWallet extends Wallet{
    public UsdWallet(){}

    public UsdWallet(String ownerId){
        super(BigDecimal.ZERO, ownerId,2);
    }
}
