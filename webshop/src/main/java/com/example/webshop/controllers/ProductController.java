package com.example.webshop.controllers;


import com.example.webshop.models.Product;
import com.example.webshop.models.User;
import com.example.webshop.models.enams.Role;
import com.example.webshop.services.ProductService;
import com.example.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;


    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("products", productService.allProducts(title, user));
        model.addAttribute("productsUser", productService.allProductsUser());
        model.addAttribute("user", productService.getUserByPrincipal(principal));

        Set<Role> role = user.getRoles();
        model.addAttribute("role", role.stream().findFirst());
        return "products";

    }


    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("pictures", product.getPictures());
        User user = productService.getUserByPrincipal(principal);
        Set<Role> role = user.getRoles();
        model.addAttribute("role", role.stream().findFirst());

        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@Nullable @RequestParam("fileOne") MultipartFile fileOne,
                                @Nullable @RequestParam("fileTwo") MultipartFile fileTwo,
                                @Nullable @RequestParam("fileThree") MultipartFile fileThree,
                                Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, fileOne, fileTwo, fileThree);
        return "redirect:/";

    }

    @GetMapping("/bag")
    public String productInfo(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("products", productService.allProductsBag(user));
        model.addAttribute("user", user);

        return "bag";
    }

    @PostMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";

    }

    @PostMapping("/product/{id}/add")
    public String addProductToBag(@PathVariable Long id, Principal principal) {
        userService.addToBag(principal, id);
        return "redirect:/";
    }
}
