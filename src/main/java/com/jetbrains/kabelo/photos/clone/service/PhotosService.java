package com.jetbrains.kabelo.photos.clone.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jetbrains.kabelo.photos.clone.model.Photo;

@Service
public class PhotosService {

    private Map<String, Photo> db = new HashMap<>() {
        {
            put("1", new Photo("1", "sunset_beach.jpg"));
            put("2", new Photo("2", "mountain_view.jpg"));
            put("3", new Photo("3", "city_lights.jpg"));
            put("4", new Photo("4", "forest_path.jpg"));
            put("5", new Photo("5", "ocean_waves.jpg"));
            put("6", new Photo("6", "desert_dunes.jpg"));
            put("7", new Photo("7", "snowy_peaks.jpg"));
            put("8", new Photo("8", "autumn_leaves.jpg"));
            put("9", new Photo("9", "night_sky.jpg"));
            put("10", new Photo("10", "flower_garden.jpg"));
            put("11", new Photo("11", "river_canyon.jpg"));
            put("12", new Photo("12", "tropical_island.jpg"));
            put("13", new Photo("13", "old_bridge.jpg"));
            put("14", new Photo("14", "country_road.jpg"));
            put("15", new Photo("15", "waterfall_mist.jpg"));
        }
    };

    public Collection<Photo> get() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setId(db.size() + 1 + "");
        photo.setFileName(fileName);
        photo.setContentType(contentType);
        photo.setData(data);
        db.put(photo.getId(), photo);
        return photo;
    }

    public int size() {
        return db.size();
    }

}
