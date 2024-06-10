package es.sneakerwebshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "telephone_number", nullable = false)
    private int telephoneNumber;
    @Basic
    @Column(name = "address", nullable = false, length = 45)
    private String address;
    @Basic
    @Column(name = "role", nullable = false)
    private int role;
    @Basic
    @Column(name = "registration_date", nullable = false, length = 45)
    private Date registrationDate;

    public User(String name, String lastname, String email, String password, int telephoneNumber, String address, int role, Date registrationDate) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.role = role;
        this.registrationDate = registrationDate;
    }

}
