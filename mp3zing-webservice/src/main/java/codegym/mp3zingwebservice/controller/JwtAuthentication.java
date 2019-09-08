package codegym.mp3zingwebservice.controller;

import codegym.mp3zingconfigure.jwt.JwtProvider;
import codegym.mp3zingconfigure.service.UserPrinciple;
import codegym.mp3zingdao.dto.request.JwtRequest;
import codegym.mp3zingdao.dto.request.SignUpRequest;
import codegym.mp3zingdao.dto.response.JwtResponse;
import codegym.mp3zingdao.dto.response.MessageErrorResponse;
import codegym.mp3zingdao.entity.Account;
import codegym.mp3zingdao.entity.Role;
import codegym.mp3zingdao.entity.RoleName;
import codegym.mp3zingdao.entity.User;
import codegym.mp3zingdao.repository.AccountRepository;
import codegym.mp3zingdao.repository.RoleRepository;
import codegym.mp3zingdao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class JwtAuthentication {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody JwtRequest loginRequest) {
//       User user = userRepository.findByUserName(loginRequest.getUsername());
        Account account = accountRepository.findByUsername(loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("HELLO");
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserPrinciple) {
//            String username = ((UserPrinciple) principal).getUsername();
//            Collection<? extends GrantedAuthority> author= ((UserDetails) principal).getAuthorities();
//
//            System.out.println(author);
//            System.out.println(username);
//        } else {
//            String username = principal.toString();
//            System.out.println(username);
//        }

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("hello " + userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        System.out.println(signUpRequest.getName());
        System.out.println(signUpRequest.getEmail());
        System.out.println(signUpRequest.getRole());
        System.out.println(signUpRequest.getUsername());
        System.out.println(signUpRequest.getPassword());
        System.out.println(signUpRequest.getProviderLogin());
        System.out.println(signUpRequest.getAddress());
        System.out.println(signUpRequest.getAge());
        System.out.println(signUpRequest.getNumberPhone());
        System.out.println(signUpRequest.getGender());


        //sign-up by facebook or google when it provider string
        //check email
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            System.out.println("Kiem Tra email");
            if (!signUpRequest.getProviderLogin().isEmpty()) {
                System.out.println("kiem tra provider");
                signUpRequest.setPassword("123456");
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateJwtToken(authentication);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                System.out.println("SO OKE");
                return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
            } else {
                System.out.println("da ton tai email");
                return new ResponseEntity<>(new MessageErrorResponse("Fail -> Email is already in use!"),
                        HttpStatus.BAD_REQUEST);
            }

        }
        //check username
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {

            return new ResponseEntity<>(new MessageErrorResponse("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            Collection<? extends GrantedAuthority> author= ((UserDetails) principal).getAuthorities();
//            System.out.println(author);
//            System.out.println(username);
//        } else {
//            String username = principal.toString();
//            System.out.println(username);
//        }
//        User user = new User(signUpRequest.getName(),signUpRequest.getUsername(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getNumberPhone() ,signUpRequest.getEmail(),signUpRequest.getAddress());
        Account account = new Account();
        account.setAddress(signUpRequest.getAddress());
        account.setAge(signUpRequest.getAge());
        account.setDeleted(Boolean.FALSE);
        account.setEmail(signUpRequest.getEmail());
        account.setFullName(signUpRequest.getName());
        account.setPassword(encoder.encode(signUpRequest.getPassword()));
        account.setGender(signUpRequest.getGender());
        account.setPhone(signUpRequest.getNumberPhone());
        account.setUsername(signUpRequest.getUsername());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);

                    break;
                case "owner":
                    Role ownerRole = roleRepository.findByName(RoleName.ROLE_OWNER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(ownerRole);

                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

//        user.setRoles(roles);
        account.setRoles(roles);
//        userRepository.save(user);
        accountRepository.save(account);
        return new ResponseEntity<>(new MessageErrorResponse("User registered successfully!"), HttpStatus.OK);
    }

}