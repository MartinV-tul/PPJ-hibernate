package cz.tul.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "country_name")
    private String name;

    @OneToMany(mappedBy = "country",fetch = FetchType.EAGER)
    private List<Town> towns = new ArrayList<>();

    public Country(){}

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public Country(String name){
        this.name = name;
    }

    public String getCountryName() {
        return name;
    }

    public void setCountryName(String name) {
        this.name = name;
    }

    public void addTown(Town town){
        this.towns.add(town);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Country otherCountry = (Country) obj;
        return name == otherCountry.name;
    }

    @Override
    public String toString() {
        return "Country [name=" + name+"]";
    }

}
