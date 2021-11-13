package l1challenge.app.repositories;

import l1challenge.app.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends CrudRepository<Operation, Integer> {

    @Query("select o from Operation o where o.wallet in (?1)")
    public Iterable<Operation> findByWalletIn(List<Integer> walletIds);

    /*@Query("select o from Operation o where o.wallet in :ids and "
            + "(:type is null or o.type = :type) and "
            + "(:currency is null or o.currency = :currency) and "
            + "(:limit is null or limit :limit) and "
            + "(:offset is null or offset :offset)")*/
    @Query(value = "select * from operation o where o.wallet in (:ids) and "
            + "(:type is null or o.type = :type) and "
            + "(:currency is null or o.currency = :currency) "
            + "limit :limit offset :offset", nativeQuery = true)
    public List<Operation> findOperationsWithFilters(
            @Param("ids") List<Integer> ids,
            @Param("type") String type,
            @Param("currency") String currency,
            @Param("limit") int limit,
            @Param("offset") int offset
    );


}
