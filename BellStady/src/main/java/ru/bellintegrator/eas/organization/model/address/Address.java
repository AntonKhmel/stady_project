package ru.bellintegrator.eas.organization.model.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.bellintegrator.eas.organization.model.address.dependent.*;

import javax.persistence.*;

/**
 * @author Хмель А.В.
 * entity Address
 */
@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // the service field of the hibernate for version control
    @Version
    private Integer version;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "street_id")
    private Street street;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "house_id")
    private House house;

    public Address(int id, Country country, Region region, City city, Street street, House house) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    /**
     * constructor for the hibernate
     */
    public Address() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        if (id != this.id) return false;
        if (version != this.version) return false;
        if (country != null ? !country.equals(this.country) : this.country != null) return false;
        if (region != null ? !region.equals(this.region) : this.region != null) return false;
        if (city != null ? !city.equals(this.city) : this.city != null) return false;
        if (street != null ? !street.equals(this.street) : this.street != null) return false;
        if (house != null ? !house.equals(this.house) : this.house != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + version;

        return result;
    }

    /**
     * @return the string representation of the object Address
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("address:");
        builder.append("{");
        if (country != null) {
            builder.append(country.shortToString());
        }
        if (region != null) {
            builder.append(region);
        }
        if (city != null) {
            builder.append(city);
        }
        if (street != null) {
            builder.append(street);
        }
        if (house != null) {
            builder.append(house);
        }
        builder.append("}");

        return builder.toString();
    }
}
