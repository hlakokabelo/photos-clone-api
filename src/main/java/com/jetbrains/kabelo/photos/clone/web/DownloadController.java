package com.jetbrains.kabelo.photos.clone.web;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jetbrains.kabelo.photos.clone.model.Photo;
import com.jetbrains.kabelo.photos.clone.service.PhotosService;

@RestController
public class DownloadController {
    @Autowired // Injects the Spring-managed PhotosService bean into this controller
    private PhotosService photosService;

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) {

        Photo photo = photosService.get(id);

        if (photo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // Set the content type
        MediaType contentType = MediaType.parseMediaType(photo.getContentType());

        // Randomly choose attachment or inline
        boolean isAttachment = new Random().nextBoolean();

        ContentDisposition contentDisposition = isAttachment
                ? ContentDisposition.attachment().filename(photo.getFileName()).build()
                : ContentDisposition.inline().filename(photo.getFileName()).build();

        // Get the photo data
        byte[] data = photo.getData();

        // Build and return the HTTP response
        return ResponseEntity.ok()
                .contentType(contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(data);
    }

}
