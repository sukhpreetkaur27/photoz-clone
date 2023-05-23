package com.sukh.app.photoz.clone.web;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.sukh.app.photoz.clone.model.Photo;
import com.sukh.app.photoz.clone.service.PhotozService;

/**
 * Controller class
 * 
 * @author sukh
 *
 */
@RestController
public class PhotozController {

  private final PhotozService service;

  public PhotozController(@Autowired PhotozService photozService) {
    this.service = photozService;
  }

//  private Map<String, Photo> db = new HashMap<>() {
//    {
//      put("1", new Photo("1", "hello.jpg"));
//    }
//  };
  // private List<Photo> db = List.of(new Photo("1", "hello.jpg"));

  @GetMapping("/")
  public String hello() {
    return "Hello World";
  }

  @GetMapping("/photoz")
  public Iterable<Photo> get() {
    return service.get();
  }

  @GetMapping("/photoz/{id}")
  public Photo get(@PathVariable(name = "id") Integer id) {
    Photo photo = service.get(id);
    if (photo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return photo;
  }

  @DeleteMapping("/photoz/{id}")
  public void delete(@PathVariable(name = "id") Integer id) {
    service.remove(id);
//    if (photo == null) {
//      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//    }
  }

//  @PostMapping("/photoz")
//  public Photo create(@RequestBody @Valid Photo photo) {
//    photo.setId(UUID.randomUUID().toString());
//    db.put(photo.getId(), photo);
//    return photo;
//  }

  /**
   * Note: the "data" of @RequestPart matches with the
   * src/main/resources/static/upload.html -> L-17. formData.append("data",
   * fileupload.files[0]);
   * 
   * @param file
   * @return
   * @throws IOException
   */
  @PostMapping("/photoz")
  public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
    Photo photo = service.save(file.getOriginalFilename(), file.getContentType(),
        file.getBytes());
    return photo;
  }

}
