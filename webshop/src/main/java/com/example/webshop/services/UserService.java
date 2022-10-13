package com.example.webshop.services;

import com.example.webshop.models.User;
import com.example.webshop.models.enams.Role;
import com.example.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //registration new user
    public boolean createUser(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActiv(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.ROLE_USER);
            log.info("Saving new User with email: {}", email);
            userRepository.save(user);
            return true;
        }
    }

    public List<User> users (){
        return userRepository.findAll();
    }
    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
    public void ban(Long id) {
        User user =userRepository.findById(id).orElse(null);
        if(user!=null){
            if (user.isActiv()){
                user.setActiv(false);
            }else {
                user.setActiv(true);
            }
            user.setActiv(false);
        }
        userRepository.save(user);
    }

}
