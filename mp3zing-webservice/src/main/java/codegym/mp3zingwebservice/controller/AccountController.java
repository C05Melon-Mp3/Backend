package codegym.mp3zingwebservice.controller;

import codegym.mp3zingcommon.exception.ResourceNotFoundException;
import codegym.mp3zingconfigure.service.UserPrinciple;
import codegym.mp3zingdao.dto.AccountDTO;
import codegym.mp3zingdao.entity.Account;
import codegym.mp3zingdao.entity.UpdatePassword;
import codegym.mp3zingservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("")
public class AccountController {
    @Autowired
    private AccountService accountService;
//    @Autowired
//    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> createAccounts(@Valid @RequestBody AccountDTO accountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<List>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        accountService.save(accountDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/accounts/update-password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable(value = "id") Long accountId,
                                            @RequestBody UpdatePassword updatePassword) throws ResourceNotFoundException {

        AccountDTO accountDTO = accountService.findById(accountId);
        if (accountDTO != null) {
            if (BCrypt.checkpw(updatePassword.getCurrentPassword(), accountDTO.getPassword())) {
                accountDTO.setPassword(updatePassword.getNewPassword());
                accountService.updatePassword(accountDTO);
                return new ResponseEntity<>("{\"text\":\"Successful\"}",HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"text\":\"NotCompare\"}",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("{\"text\":\"NotFound\"}", HttpStatus.OK);
    }
    @PutMapping("/accounts/update-user/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable(value = "id") Integer id ,@RequestBody AccountDTO accountDTO){
        accountService.updateUser(accountDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/accounts/show/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable(value = "id") Long id) {
        AccountDTO accountDTO = accountService.findById(id);
        return ResponseEntity.ok().body(accountDTO);
    }
    @GetMapping("/accounts")
    public List<Account> getAllAccount() {
        List<Account> accounts;

        accounts = accountService.findAllByDeletedIsFalse();

        return accounts;
    }

    @GetMapping(value="/userInfo")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<UserPrinciple> findUser(){
//        User user = this.userRepository.findById(id).orElse(null);
        UserPrinciple user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<UserPrinciple>( user , HttpStatus.OK);

    }
}

