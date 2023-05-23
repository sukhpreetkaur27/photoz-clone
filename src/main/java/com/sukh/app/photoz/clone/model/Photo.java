package com.sukh.app.photoz.clone.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

/**
 * Domain class
 * 
 * @author sukh
 *
 */
@Table("PHOTOZ")
public class Photo {

  @Id
  private Integer id;
  @NotEmpty
  private String fileName;
  private String contentType;
  @JsonIgnore
  private byte[] data;

  /**
   * always have a no-arguments constructor to avoid issues while marshaling
   * objects.
   */
  public Photo() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  // raw data

}
