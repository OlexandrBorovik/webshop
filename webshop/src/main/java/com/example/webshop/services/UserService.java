package com.example.webshop.services;

import com.example.webshop.models.Product;
import com.example.webshop.models.User;
import com.example.webshop.models.enams.Role;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
private final ProductService productService;
    //registration new user
    public boolean createUser(User user, String role) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActiv(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if(role.equals("seller")){
                user.getRoles().add(Role.ROLE_USER);
                log.info("Saving new User");
            }else{
                user.getRoles().add(Role.ROLE_BUYER);
                log.info("Saving new Buyer");
            }

            log.info("Saving new User with email: {}", email);
            userRepository.save(user);
            return true;
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal== null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
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

    public void addToBag(Principal principal, Long id) {
        Product product = productService.getProductById(id);
        Product productBag = new Product();
        productBag.setUser(getUserByPrincipal(principal));
        productBag.setTitle(product.getTitle());
        productBag.setDescription(product.getDescription());
        productBag.setPrice(product.getPrice());
        productRepository.save(productBag);

        log.info("Add product");


    }
}
