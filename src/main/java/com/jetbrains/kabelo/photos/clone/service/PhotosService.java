package com.jetbrains.kabelo.photos.clone.service;

import org.springframework.stereotype.Service;

import com.jetbrains.kabelo.photos.clone.model.Photo;
import com.jetbrains.kabelo.photos.clone.repository.PhotoRepository;

@Service
public class PhotosService {

    private final PhotoRepository photoRepository;

    public PhotosService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> get() {
        return photoRepository.findAll();
    }

    public Photo get(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo remove(Integer id) {
        Photo photo = photoRepository.findById(id).orElse(null);
        if (photo != null) {
            photoRepository.deleteById(id);
        }
        return photo;
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        photoRepository.save(photo);
        return photo;
    }

}
