package codegym.mp3zingservice.service.impl;

import codegym.mp3zingdao.entity.Account;
import codegym.mp3zingdao.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user=accountRepository.findByUsernameAndDeletedIsFalse(s);
        if(user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        String name=user.getUsername();
        String password=user.getPassword();
        Set<GrantedAuthority> grantedAuthoritySet=new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(name,password,grantedAuthoritySet);
    }
}

