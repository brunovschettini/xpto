package br.com.xptosystems.security;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_token")
public class UserToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "users", referencedColumnName = "id")
    @ManyToOne
    private Users users;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "access", nullable = true)
    private Date access;
    @Column(name = "access_token", length = 500, nullable = true)
    private String accessToken;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires", nullable = true)
    private Date expires;

    public UserToken() {
        this.id = null;
        this.users = null;
        this.access = new Date();
        this.accessToken = null;
        this.expires = null;
    }

    public UserToken(Long id, Users users, Date access, String accessToken, Date expires) {
        this.id = id;
        this.users = users;
        this.access = access;
        this.accessToken = accessToken;
        this.expires = expires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getAccess() {
        return access;
    }

    public void setAccess(Date access) {
        this.access = access;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}
