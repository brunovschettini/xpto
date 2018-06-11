 
package br.com.xptosystems.securitys.dao;
 
import br.com.xptosystems.dao.DB;
import br.com.xptosystems.security.UserToken;
import br.com.xptosystems.security.Users;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.persistence.Query;

public class UserTokenDao extends DB {

    public Users findByAccessToken(String access_token) {
        if (access_token == null || access_token.isEmpty()) {
            return null;
        }
        try {
            Query query = getEntityManager().createQuery("SELECT UT.users FROM UserToken AS UT WHERE UT.accessToken = :access_token AND UT.expires IS NULL");
            query.setParameter("access_token", access_token);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (Users) query.getSingleResult();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public UserToken findByAccessToken2(String access_token) {
        if (access_token == null || access_token.isEmpty()) {
            return null;
        }
        try {
            Query query = getEntityManager().createQuery("SELECT UT FROM UserToken AS UT WHERE UT.accessToken = :access_token AND UT.expires IS NULL");
            query.setParameter("access_token", access_token);
            List list = query.getResultList();
            if (!list.isEmpty()) {
                return (UserToken) query.getSingleResult();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<UserToken> findByUser(Long user_id) throws NoSuchAlgorithmException {
        try {
            Query query = getEntityManager().createQuery("SELECT UT FROM UserToken AS UT WHERE UT.users.id = :user_id AND UT.expires IS NULL ORDER BY UT.access ASC");
            query.setParameter("user_id", user_id);
            List list = query.getResultList();
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void update(Long user_id) {
        Boolean success = false;
        try {
            Query query = getEntityManager().createNativeQuery("UPDATE user_token SET expires = current_timestamp WHERE users = " + user_id + " AND expires IS NULL ");
            this.getEntityManager().getTransaction().begin();
            if (query.executeUpdate() != 0) {
                this.getEntityManager().getTransaction().rollback();
                success = false;
                return;
            }
            success = true;
            this.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            this.getEntityManager().getTransaction().rollback();
            success = false;
        }
    }
    }