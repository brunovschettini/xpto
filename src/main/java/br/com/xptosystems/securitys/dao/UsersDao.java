package br.com.xptosystems.securitys.dao;

import br.com.xptosystems.dao.DB;
import br.com.xptosystems.security.Users;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.Query;

public class UsersDao extends DB {

    public Users authUser(Users user) throws NoSuchAlgorithmException {
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.email = :email AND U.password = :password ");
            query.setParameter("email", user.getEmail());
            query.setParameter("password", user.getPassword());
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Users) query.getSingleResult();
            } else {
                query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.nickname = :nickname AND U.password = :password ");
                query.setParameter("nickname", user.getEmail());
                query.setParameter("password", user.getPassword());
                list = query.getResultList();
                if (!list.isEmpty()) {
                    return (Users) query.getSingleResult();
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Users authUser(String login, String password) throws NoSuchAlgorithmException {
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.email = :email AND U.password = :password ");
            query.setParameter("email", login);
            query.setParameter("password", password);
            return (Users) query.getSingleResult();

        } catch (Exception e) {
        }

        return null;
    }

    public boolean existUserEmail(String mail) {
        mail = mail.trim().toLowerCase();
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.email = :email");
            query.setParameter("email", mail);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean existUserNickname(String nickname) {
        nickname = nickname.trim().toLowerCase();
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.nickname = :nickname");
            query.setParameter("nickname", nickname);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public Users findUserByEmail(String email) {
        email = email.trim().toLowerCase();
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.email = :email");
            query.setParameter("email", email);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Users) query.getSingleResult();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public Users findUserByNickname(String nickname) {
        nickname = nickname.trim().toLowerCase();
        try {
            Query query = getEntityManager().createQuery("SELECT U FROM Users AS U WHERE U.nickname = :nickname");
            query.setParameter("nickname", nickname);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Users) query.getSingleResult();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
