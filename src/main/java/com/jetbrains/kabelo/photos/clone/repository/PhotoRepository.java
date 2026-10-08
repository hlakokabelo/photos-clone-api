package com.jetbrains.kabelo.photos.clone.repository;

import org.springframework.data.repository.CrudRepository;

import com.jetbrains.kabelo.photos.clone.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}