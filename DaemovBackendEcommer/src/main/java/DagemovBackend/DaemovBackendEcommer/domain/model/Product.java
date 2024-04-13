package DagemovBackend.DaemovBackendEcommer.domain.model;


import java.time.LocalDateTime;

public class Product {

  private Long id;
  private String name;
  private String description;
  private int stock;
  private float price;
  private Long userId;
  private Long categoryId;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private String urlImage;

  public Product(Long id, String name, String description, int stock, float price, Long userId, Long categoryId, LocalDateTime createdDate, LocalDateTime updatedDate, String urlImage) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.price = price;
    this.userId = userId;
    this.categoryId = categoryId;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.urlImage = urlImage;
  }

  public String getUrlImage() {
    return urlImage;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getStock() {
    return stock;
  }

  public float getPrice() {
    return price;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public void setUrlImage(String urlImage) {
    this.urlImage = urlImage;
  }
}