package cz.tul.app;

import cz.tul.data.Country;
import cz.tul.data.CountryDao;
import cz.tul.data.Town;
import cz.tul.data.TownDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public TownDao townDao() {
        return new TownDao();
    }

    @Bean
    public CountryDao countryDao() {
        return new CountryDao();
    }

    public static void main(String[] args) {


        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        CountryDao countryDao = ctx.getBean(CountryDao.class);
        TownDao townDao = ctx.getBean(TownDao.class);

        Country czechRepublic = new Country("Česká Republika");
        Country slovakia = new Country("Slovensko");

        countryDao.saveOrUpdate(czechRepublic);
        countryDao.saveOrUpdate(slovakia);

        Town prague = new Town("Praha",czechRepublic);
        Town brno = new Town("Brno",czechRepublic);
        Town liberec = new Town("Liberec",czechRepublic);

        townDao.saveOrUpdate(prague);
        townDao.saveOrUpdate(brno);
        townDao.saveOrUpdate(liberec);


        Town bratislava = new Town("Bratislava",slovakia);
        Town kosice = new Town("Košice",slovakia);
        Town trencin = new Town("Trenčín",slovakia);

        townDao.saveOrUpdate(bratislava);
        townDao.saveOrUpdate(kosice);
        townDao.saveOrUpdate(trencin);

        List<Country> countries = countryDao.getAllCountries();
        List<Town> towns = countries.get(0).getTowns();
        System.out.println(towns);

        Town town = towns.get(0);

        Country country = town.getCountry();

        System.out.println(country.getCountryName());
        System.out.println(towns);

    }
}
