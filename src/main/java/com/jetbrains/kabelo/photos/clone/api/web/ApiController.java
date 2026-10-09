package com.jetbrains.kabelo.photos.clone.api.web;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

        @GetMapping
        public Map<String, Object> documentation() {
                return Map.of(
                                "name", "Photos API",
                                "version", "1.0.0",
                                "description",
                                "A REST API for uploading, searching, retrieving, downloading and deleting photos.",

                                "endpoints", Map.of(
                                                "GET /api/photos", Map.of(
                                                                "description",
                                                                "Get photos with optional filtering and a result limit.",
                                                                "auth", false,
                                                                "queryParams", Map.of(
                                                                                "fileName",
                                                                                "string (optional, default: '') - Filter by filename (case-insensitive, partial match)",
                                                                                "contentType",
                                                                                "string (optional, default: '') - Filter by MIME type (case-insensitive, partial match)",
                                                                                "limit",
                                                                                "integer (optional, default: 1000) - Maximum number of photos to return"),
                                                                "example",
                                                                "/api/photos?fileName=kab&contentType=image&limit=10",
                                                                "response", "Array of photo metadata objects"),

                                                "GET /api/photo/{id}", Map.of(
                                                                "description", "Retrieve photo metadata by ID.",
                                                                "auth", false,
                                                                "pathParams", Map.of(
                                                                                "id", "integer (required) - Photo ID"),
                                                                "example", "/api/photo/1",
                                                                "response", "Photo metadata object",
                                                                "errors", Map.of(
                                                                                "404", "Photo not found")),

                                                "POST /api/photo", Map.of(
                                                                "description", "Upload a new photo.",
                                                                "auth", false,
                                                                "contentType", "multipart/form-data",
                                                                "formData", Map.of(
                                                                                "data",
                                                                                "file (required) - Photo to upload"),
                                                                "response", "Created photo metadata object"),

                                                "DELETE /api/photo/{id}", Map.of(
                                                                "description", "Delete a photo by ID.",
                                                                "auth", false,
                                                                "pathParams", Map.of(
                                                                                "id", "integer (required) - Photo ID"),
                                                                "example", "/api/photo/1",
                                                                "response", "Empty response body",
                                                                "errors", Map.of(
                                                                                "404", "Photo not found")),

                                                "GET /api/download/{id}", Map.of(
                                                                "description",
                                                                "Retrieve the original photo file by ID.",
                                                                "auth", false,
                                                                "pathParams", Map.of(
                                                                                "id", "integer (required) - Photo ID"),
                                                                "example", "/api/download/1",
                                                                "response", "Binary photo data",
                                                                "contentDisposition",
                                                                "inline or attachment (randomly selected)",
                                                                "errors", Map.of(
                                                                                "404", "Photo not found"))));
        }
}