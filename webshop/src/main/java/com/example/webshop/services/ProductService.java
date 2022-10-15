package com.example.webshop.services;

import com.example.webshop.models.Picture;
import com.example.webshop.models.Product;
import com.example.webshop.models.User;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private List<Product> productList = new ArrayList<>();

    public void saveProduct(Principal principal, Product product, MultipartFile fileOne,
                            MultipartFile fileTwo, MultipartFile fileThree) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Picture one;
        Picture two;
        Picture three;
        if (fileOne.getSize() != 0) {
            one = toPicture(fileOne);
            one.setPreviewPicture(true);
            product.addPicture(one);
        }
        if (fileTwo.getSize() != 0) {
            two = toPicture(fileTwo);
            product.addPicture(two);
        }
        if (fileThree.getSize() != 0) {
            three = toPicture(fileThree);
            product.addPicture(three);
        }

        log.info("Save new product.Title {};", product.getTitle());
        Product productDB = productRepository.save(product);
        productDB.setPreviewPictureId(productDB.getPictures().get(0).getId());
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal== null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }

    private Picture toPicture(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        picture.setName(file.getName());
        picture.setContentType(file.getContentType());
        picture.setSize(file.getSize());
        picture.setBytes(file.getBytes());
        return picture;

    }

    public List<Product> allProducts(String title) {
        if (title != null) {
            return productRepository.findByTitle(title);
        } else {
            return productRepository.findAll();
        }
    }

    public void deleteProduct(Long id) {

        productRepository.deleteAllById(Collections.singleton(id));
    }


    public Product getProductById(Long id) {
        Product product = productRepository.findAllById(id);
        if (product != null) {
            return product;
        }
        return null;

    }
}
