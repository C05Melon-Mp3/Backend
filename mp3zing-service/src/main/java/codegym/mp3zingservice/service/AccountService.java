package codegym.mp3zingservice.service;

import codegym.mp3zingdao.dto.AccountDTO;
import codegym.mp3zingdao.entity.Account;

import java.util.List;


public interface AccountService {
    List<Account> findAllByDeletedIsFalse();

    void save(AccountDTO accountDTO);

    AccountDTO updatePassword(AccountDTO accountDTO);

    AccountDTO findById(Long id);
    
    void updateUser(AccountDTO accountDTO);
}

