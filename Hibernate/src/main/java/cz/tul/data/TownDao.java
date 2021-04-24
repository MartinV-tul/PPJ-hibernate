package cz.tul.data;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TownDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Town> getTownsOfCountry(String countryName){
        Criteria crit = session().createCriteria(Town.class);
        crit.add((Restrictions.eq("countryName",countryName)));

        List<Town> towns = crit.list();
        return towns;
    }

    public List<Town> getAllTowns(){
        Criteria crit = session().createCriteria(Town.class);
        List<Town> towns = crit.list();
        return towns;
    }

    public Town getTown(String countryName,String townName){
        Criteria crit = session().createCriteria(Town.class);
        crit.add(Restrictions.eq("name", townName));
        crit.add(Restrictions.eq("countryName", countryName));
        return (Town) crit.uniqueResult();
    }

    public void deleteTown(String townName, String countryName){
        Query query = session().createQuery("delete from Town where name=:TownName and countryName=:CountryName");
        query.setString("CountryName",countryName);
        query.setString("TownName",townName);
        query.executeUpdate();
    }

    public void saveOrUpdate(Town town) {
        session().saveOrUpdate(town);
    }

}
