package l1challenge.app.wallet;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class UsdtWallet extends Wallet{
    public UsdtWallet(){}

    public UsdtWallet(String ownerId){
        super(BigDecimal.ZERO, ownerId, 8);
    }
}
