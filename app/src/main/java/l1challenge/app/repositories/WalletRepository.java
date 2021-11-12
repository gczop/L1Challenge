package l1challenge.app.repositories;

import l1challenge.app.wallet.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface WalletRepository<T extends Wallet> extends CrudRepository<T, Integer> {

    T findWalletByOwnerAlias(String ownerAlias);

}