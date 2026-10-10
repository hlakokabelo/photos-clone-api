package com.jetbrains.kabelo.photos.clone.api.service;

import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jetbrains.kabelo.photos.clone.api.model.Photo;
import com.jetbrains.kabelo.photos.clone.api.repository.PhotoRepository;

@Service
public class PhotosService {

    private final PhotoRepository photoRepository;

    public PhotosService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> get() {
        return photoRepository.findAll();
    }

    public Iterable<Photo> search(String fileName, String contentType, Integer limit) {
        return photoRepository.findByContentTypeContainingIgnoreCaseAndFileNameContainingIgnoreCase(
                contentType, fileName,
                Limit.of(Math.max(1, limit)));
    }

    public Photo get(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    public Photo save(String fileName, String contentType, byte[] data, Long userId) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        photo.setUserId(userId);
        photoRepository.save(photo);
        return photo;
    }

    public void deletePhoto(Long photoId, Long userId) {

        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Photo not found"));

        // Only the owner can delete the photo
        if (!photo.getUserId().equals(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You cannot delete another user's photo");
        }

        photoRepository.delete(photo);
    }
}
