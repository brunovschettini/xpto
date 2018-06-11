package br.com.xptosystems.security;

import br.com.xptosystems.utils.Sessions;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "email", length = 150, unique = true)
    private String email;
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "nickname", length = 150, unique = true)
    private String nickname;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    public Users() {
        this.id = null;
        this.email = "";
        this.name = "";
        this.password = "";
        this.active = false;
        this.nickname = "";
        this.registerDate = new Date();
    }

    public Users(Long id, String name, String email, String password, Boolean active,  String nickname, Date registerDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active; 
        this.nickname = nickname;
        this.registerDate = registerDate;
    }

    public static Users get() {
        return ((Users) Sessions.getObject("sessionUsers"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

 
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
