package codegym.mp3zingdao.dto;

import codegym.mp3zingcommon.validation.Unique;

import java.io.Serializable;

public class AccountDTO implements Serializable {
    private Long id;
    private String fullName;
    private Integer age;
    private String gender;
    private String phone;
    @Unique(message = "Email is already exit")
    private String email;
    private String address;
    @Unique(message = "username is already exit")
    private String username;
    private String password;
    private Boolean deleted;

//    public Integer getId() {
//        return id;
//    }
   public Long getId() { return id;}
    public void setId(Long id){
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}