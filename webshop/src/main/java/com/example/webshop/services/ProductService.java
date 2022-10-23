package com.example.webshop.services;

import com.example.webshop.models.Picture;
import com.example.webshop.models.Product;
import com.example.webshop.models.User;
import com.example.webshop.models.enams.Role;
import com.example.webshop.repositories.ProductRepository;
import com.example.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.security.Principal;
import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void saveProduct(Principal principal, Product product, MultipartFile fileOne,
                            MultipartFile fileTwo, MultipartFile fileThree) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Picture one;
        Picture two;
        Picture three;



        if (fileOne!=null) {
            one = toPicture(fileOne);
            one.setPreviewPicture(true);
            product.addPicture(one);

        }
        if (fileTwo!=null) {
            two = toPicture(fileTwo);
            product.addPicture(two);
        }
        if (fileThree!=null) {
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
        picture.setOriginalFileName(file.getOriginalFilename());
        picture.setContentType(file.getContentType());
        picture.setSize(file.getSize());
        picture.setBytes(file.getBytes());
        return picture;

    }

    public List<Product> allProducts(String title, User user  ) {
List<Product>list = new ArrayList<>();

        List<Product>listAll = productRepository.findAll();
        if (title != null) {
            return productRepository.findByTitle(title);
        } else {
                for (Product product : listAll)
                    if (product.getUser().getRoles().stream().findFirst() .equals(user.getRoles().stream().findFirst())==false)  {
                        list.add(product);
                    }
                return list;
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

    public List<Product> allProductsBag(User user) {
        return user.getProducts();
    }

    public List<Product> allProductsUser() {
        return productRepository.findAll();
    }
}
