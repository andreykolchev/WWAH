package com.restfully.webapp.DAO;

/**
 *
 * @author 2015 Andrey Kolchev mailto: andreykolchev@gmail.com
 */
import com.restfully.webapp.model.Card;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class CardDAO {

     
    public CardDAO() {
        HibernateUtil.beginTransaction();
    }

    
    public List<Card> getAll() {
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM card"; 
            Query query = session.createQuery(hql);
            List<Card> results = query.list();
            return results;
        } catch (HibernateException ex) {
          //
        }
        return null;
    }
     
    
    public List<Card> getByAccId(int account_id) {
        Session session = HibernateUtil.getSession();
        try {
            String hql = "FROM card WHERE account_id = :account_id";
            Query query = session.createQuery(hql);
            query.setInteger("account_id", account_id);
            List<Card> results = query.list();
            return results;
        } catch (HibernateException ex) {
          //
        }
        return null;
    }

}
