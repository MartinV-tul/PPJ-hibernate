package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CountryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(Country country) {
        Transaction t = session().beginTransaction();
        session().saveOrUpdate(country);
        t.commit();
    }

    public List<Country> getAllCountries() {
        Transaction t = session().beginTransaction();
        Criteria crit = session().createCriteria(Country.class);
        List<Country> countries = crit.list();
        t.commit();
        return countries;
    }

    public void deleteCountry(String countryName){
        Transaction t = session().beginTransaction();
        session().createQuery("delete from Country where name=:CountryName").setParameter("CountryName",countryName).executeUpdate();
        t.commit();
    }

    public void deleteAll() {
        Transaction t = session().beginTransaction();
        session().createQuery("delete from Country").executeUpdate();
        t.commit();
    }
}
