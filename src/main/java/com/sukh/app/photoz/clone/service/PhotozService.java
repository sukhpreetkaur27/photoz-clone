package com.sukh.app.photoz.clone.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sukh.app.photoz.clone.model.Photo;
import com.sukh.app.photoz.clone.repository.PhotozRepository;

@Service
public class PhotozService {

  private final PhotozRepository repository;

  public PhotozService(@Autowired PhotozRepository repository) {
    this.repository = repository;
  }

  public Iterable<Photo> get() {
    return repository.findAll();
  }

  public Photo get(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public void remove(Integer id) {
    repository.deleteById(id);
  }

  public Photo save(String fileName, String contentType, byte[] data) {
    Photo photo = new Photo();
//    photo.setId(UUID.randomUUID().toString());
    photo.setContentType(contentType);
    photo.setFileName(fileName);
    photo.setData(data);
    return repository.save(photo);
  }

}
