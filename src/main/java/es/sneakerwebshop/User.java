package es.sneakerwebshop;

import jakarta.persistence.*;

import java.util.Objects;

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
    @Column(name = "telephone_number", nullable = false)
    private int telephoneNumber;
    @Basic
    @Column(name = "adress", nullable = false, length = 45)
    private String adress;
    @Basic
    @Column(name = "role", nullable = false)
    private int role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && telephoneNumber == user.telephoneNumber && role == user.role && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(adress, user.adress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, lastname, email, telephoneNumber, adress, role);
    }
}
