package com.jetbrains.kabelo.photos.clone.api.web;

import java.io.IOException;
import java.net.URI;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.jetbrains.kabelo.photos.clone.api.model.Photo;
import com.jetbrains.kabelo.photos.clone.api.service.PhotosService;

@RestController
@RequestMapping("/api")
public class PhotosController {

    private final PhotosService photosService;

    public PhotosController(@Autowired PhotosService photosService) {
        this.photosService = photosService;
    }

    // GET /api/photos?fileName=kab&contentType=image&limit=10
    @GetMapping("/photos")
    public Iterable<Photo> getByFileNameAndContentType(
            @RequestParam(defaultValue = "") String fileName,
            @RequestParam(defaultValue = "") String contentType,
            @RequestParam(defaultValue = "1000") int limit) {

        if (limit < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Limit must be greater than 0 ");
        }

        return photosService.search(fileName, contentType, limit);
    }

    @GetMapping("/photo/{id}")
    public Photo getPhoto(@PathVariable Integer id) {

        Photo photo = photosService.get(id);
        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Integer id) {

        Photo photo = photosService.remove(id);

        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
    }

    @PostMapping("/photo")
    public ResponseEntity<Photo> create(
            @RequestPart("data") MultipartFile file,
            @AuthenticationPrincipal Jwt jwt) throws IOException {

        // Extract the authenticated user's ID from the JWT
        Long userId = Long.valueOf(jwt.getSubject());

        Photo photo = photosService.save(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes(),
                userId);

        URI location = URI.create("/api/photo/" + photo.getId());

        return ResponseEntity.created(location).body(photo);
    }

    @PostMapping("/photo/manager")
    public ResponseEntity<Photo> createManager(
            @RequestPart("data") MultipartFile file) throws IOException {

        // Extract the authenticated user's ID from the JWT
        Long userId = 1L; // Hardcoded user ID for manager role

        Photo photo = photosService.save(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes(),
                userId);

        URI location = URI.create("/api/photo/" + photo.getId());

        return ResponseEntity.created(location).body(photo);
    }
}
