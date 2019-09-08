package codegym.mp3zingdao.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "userName"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    private long id;
    private String userName;
    private String password;
    private String fullName;
    private String numberPhone;
    private String email;
    private String address;
    private Set<Role> roles = new HashSet<>();
    public User() {
    }
    public User( String fullName,String userName, String password,  String numberPhone, String email, String address) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.numberPhone = numberPhone;
        this.email = email;
        this.address = address;

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "userName", nullable = false)
    @NotBlank
    @Size(min = 4)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 6)
    public String getPassword() {
        return password;
    }

    public void setPassword(String matKhau) {
        this.password = matKhau;
    }

    @Column(name = "fullName", nullable = false)
    @Size(min = 3, max = 25)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String hoTen) {
        this.fullName = hoTen;
    }

    @Column(name = "numberPhone", nullable = false)
    @NotBlank
    @Pattern(regexp = "^0[0-9]{9}")
    public String getNumberPhone() {
        return this.numberPhone;
    }

    public void setNumberPhone(String soDienThoai) {
        this.numberPhone = soDienThoai;
    }

    @Column(name = "email", nullable = false)
    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", nullable = false)
    @NotBlank
//    @Pattern(regexp = "[\\\\w,]{2,}\\\\w")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String diaChi) {
        this.address = diaChi;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
