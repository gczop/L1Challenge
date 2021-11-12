package l1challenge.app.repositories;

import l1challenge.app.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Integer> {

    //@Query("select * from operation o where o.wallet in (?1,?2,?3)")
    public Iterable<Operation> findByWalletIn(List<Integer> walletIds);

}
