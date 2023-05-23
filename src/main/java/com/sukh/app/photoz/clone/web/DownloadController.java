package com.sukh.app.photoz.clone.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sukh.app.photoz.clone.model.Photo;
import com.sukh.app.photoz.clone.service.PhotozService;

@RestController
public class DownloadController {

  private final PhotozService service;

  public DownloadController(@Autowired PhotozService photozService) {
    this.service = photozService;
  }

  @GetMapping("/download/{id}")
  public ResponseEntity<byte[]> download(@PathVariable Integer id) {
    Photo photo = service.get(id);
    if (photo == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    byte[] data = photo.getData();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.valueOf(photo.getContentType()));

    /**
     * "attachment" = download the file
     * 
     * "inline" = display the file in the browser
     * 
     * set the file name
     */
    ContentDisposition build = ContentDisposition.builder("attachment")
        .filename(photo.getFileName()).build();
    headers.setContentDisposition(build);

    return new ResponseEntity<>(data, headers, HttpStatus.OK);
  }

}
