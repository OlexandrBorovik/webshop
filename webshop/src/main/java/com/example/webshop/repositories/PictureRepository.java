package com.example.webshop.repositories;

import com.example.webshop.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository <Picture, Long> {
}
