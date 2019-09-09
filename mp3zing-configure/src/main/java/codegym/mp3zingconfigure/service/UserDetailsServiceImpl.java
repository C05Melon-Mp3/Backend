package codegym.mp3zingconfigure.service;

import codegym.mp3zingdao.entity.Account;
import codegym.mp3zingdao.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(" UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User user = userRepository.findByUserName(username);
        Account account = accountRepository.findByUsername(username);
        return UserPrinciple.build(account);
    }


    // JWTAuthenticationFilter sẽ sử dụng hàm này
    @Transactional
    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new UsernameNotFoundException("User not found with id : " + id)
//        );
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return UserPrinciple.build(account);
    }

}
