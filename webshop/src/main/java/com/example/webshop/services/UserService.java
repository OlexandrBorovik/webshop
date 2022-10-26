package com.example.webshop.services;

import com.example.webshop.models.Product;
import com.example.webshop.models.User;
import com.example.webshop.models.enams.Role;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;

    //------------------------------------------------------------------------------------------
    //registration new user
    public boolean createUser(User user, String role) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return false;
        } else {
            user.setActiv(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (role.equals("seller")) {
                user.getRoles().add(Role.ROLE_USER);
                log.info("Saving new Seller");
            }
            if (role.equals("buyer")) {
                user.getRoles().add(Role.ROLE_BUYER);
                log.info("Saving new Buyer");
            }
            if (user.getEmail().equals("admin@admin.com")) {
                user.getRoles().add(Role.ROLE_ADMIN);
            }
            log.info("Saving new User with email: {}", email);
            userRepository.save(user);
            return true;
        }
    }

    //------------------------------------------------------------------------------------------
    //Find user which doing request
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    //------------------------------------------------------------------------------------------
    //Find all users
    public List<User> users() {
        return userRepository.findAll();
    }

    //------------------------------------------------------------------------------------------
    // Make users not active (for Admin)
    public void ban(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActiv()) {
                user.setActiv(false);
            } else {
                user.setActiv(true);
            }
            userRepository.save(user);
        }
    }

    //------------------------------------------------------------------------------------------
    // Add products to bag (for Buyer)
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
