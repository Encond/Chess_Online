package org.project.chess_online.service;

import org.project.chess_online.entity.Image;
import org.project.chess_online.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image findById(Long id) {
        return this.imageRepository.findById(id).get(); // orElseGet(Image::new);
    }
}
