package codegym.mp3zingdao.repository;

import codegym.mp3zingdao.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findAllByDeletedIsFalse();
    Long countByUsernameOrEmail(String username, String email);
    Account findByUsernameAndDeletedIsFalse(String s);
}
