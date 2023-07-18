package org.project.chess_online.controller;

import org.project.chess_online.entity.Image;
import org.project.chess_online.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/chess/{imageId}")
    public ResponseEntity<Image> getImageForUser(@PathVariable("imageId") Long imageId) {
        Image image = this.imageService.findById(imageId);
        return ResponseEntity.ok(image);
    }
}
