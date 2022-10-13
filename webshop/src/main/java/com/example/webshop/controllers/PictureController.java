package com.example.webshop.controllers;

import com.example.webshop.models.Picture;
import com.example.webshop.repositories.PictureRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class PictureController {
    private final PictureRepository pictureRepository;

    public PictureController(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    private ResponseEntity<?> getPictureById(@PathVariable Long id) {
        Picture picture = pictureRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", picture.getName())
                .contentType(MediaType.valueOf(picture.getContentType()))
                .contentLength(picture.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(picture.getBytes())));
    }
}
