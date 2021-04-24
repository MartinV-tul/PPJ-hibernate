package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CountryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(Country country) {
        session().saveOrUpdate(country);
    }

    public List<Country> getAllCountries() {
        Criteria crit = session().createCriteria(Country.class);
        List<Country> countries = crit.list();
        return countries;
    }

    public void deleteCountry(String countryName){
        session().createQuery("delete from Country where name=:CountryName").setParameter("CountryName",countryName).executeUpdate();
    }

    public void deleteAll() {
        session().createQuery("delete from Country").executeUpdate();
    }
}
