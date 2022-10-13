package com.example.webshop.controllers;


import com.example.webshop.models.Product;
import com.example.webshop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        model.addAttribute("products", productService.allProducts(title));
        model.addAttribute("user",productService.getUserByPrincipal(principal));
        return "products";

    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("pictures", product.getPictures());

        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("fileOne") MultipartFile fileOne,
                                @RequestParam("fileTwo") MultipartFile fileTwo,
                                @RequestParam("fileThree") MultipartFile fileThree, Product product , Principal principal) throws IOException {
        productService.saveProduct(principal, product, fileOne, fileTwo, fileThree);
        return "redirect:/";

    }

    @PostMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";

    }
}
