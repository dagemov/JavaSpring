package DagemovBackend.DaemovBackendEcommer.domain.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String FirstName;
    private String LastName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private UserType userType;


    public User(Long id, String firstName, String lastName, String email, String password, String address,String phoneNumber, LocalDateTime createdDate, LocalDateTime updatedDate, UserType userType) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
