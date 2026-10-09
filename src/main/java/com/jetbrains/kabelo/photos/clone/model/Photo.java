package com.jetbrains.kabelo.photos.clone.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

@Table("PHOTO")
public class Photo {

    @Id
    private Integer id;

    @NotEmpty
    private String fileName;

    private Long userId;

    /*
     * means: that the data field will not be included in the JSON representation of
     * the Photo object when it is serialized. This is useful for preventing
     * sensitive or large data from being exposed in API responses.
     */
    @JsonIgnore
    private byte[] data;

    private String contentType;

    public Photo() {
    }

    public byte[] getData() {
        return data;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

}
