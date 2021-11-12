package l1challenge.app.repositories;

import l1challenge.app.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    public User findByAlias(String alias);

}