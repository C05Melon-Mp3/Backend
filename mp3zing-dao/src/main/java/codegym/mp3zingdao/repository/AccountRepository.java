package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {
    List<Account> findAllByDeletedIsFalse();
    Long countByUsernameOrEmail(String username, String email);
    Account findByUsernameAndDeletedIsFalse(String s);
    Account findByUsername(String username);

}
