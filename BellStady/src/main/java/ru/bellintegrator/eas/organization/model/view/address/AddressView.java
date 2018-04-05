package ru.bellintegrator.eas.organization.model.view.address;

import ru.bellintegrator.eas.organization.model.view.address.dependent.*;

/**
 * @author Хмель А.В.
 * class AddressView
 */
public class AddressView {
    private int id;
    private CountryView countryView;
    private RegionView regionView;
    private CityView cityView;
    private StreetView streetView;
    private HouseView houseView;

    public AddressView(int id, CountryView countryView, RegionView regionView,
                       CityView cityView, StreetView streetView, HouseView houseView) {
        this.id = id;
        this.countryView = countryView;
        this.regionView = regionView;
        this.cityView = cityView;
        this.streetView = streetView;
        this.houseView = houseView;
    }

    /**
     * constructor for jackson
     */
    public AddressView() {
    }

    // getters and setters methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CountryView getCountryView() {
        return countryView;
    }

    public void setCountryView(CountryView countryView) {
        this.countryView = countryView;
    }

    public RegionView getRegionView() {
        return regionView;
    }

    public void setRegionView(RegionView regionView) {
        this.regionView = regionView;
    }

    public CityView getCityView() {
        return cityView;
    }

    public void setCityView(CityView cityView) {
        this.cityView = cityView;
    }

    public StreetView getStreetView() {
        return streetView;
    }

    public void setStreetView(StreetView streetView) {
        this.streetView = streetView;
    }

    public HouseView getHouseView() {
        return houseView;
    }

    public void setHouseView(HouseView houseView) {
        this.houseView = houseView;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressView addressView = (AddressView) o;
        if (id != this.id) return false;
        if (countryView != null ? !countryView.equals(this.countryView) : this.countryView != null) return false;
        if (regionView != null ? !regionView.equals(this.regionView) : this.regionView != null) return false;
        if (cityView != null ? !cityView.equals(this.cityView) : this.cityView != null) return false;
        if (streetView != null ? !streetView.equals(this.streetView) : this.streetView != null) return false;
        if (houseView != null ? !houseView.equals(this.houseView) : this.houseView != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (countryView != null ? countryView.hashCode() : 0);
        result = 31 * result + (regionView != null ? regionView.hashCode() : 0);
        result = 31 * result + (cityView != null ? cityView.hashCode() : 0);
        result = 31 * result + (streetView != null ? streetView.hashCode() : 0);
        result = 31 * result + (houseView != null ? houseView.hashCode() : 0);

        return result;
    }

    /**
     * @return the string representation of the object AddressView
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AddressView:");
        builder.append("{id: ");
        builder.append(getId());
        builder.append("; ");
        if (countryView != null) {
            builder.append(getCountryView());
        }
        if (regionView != null) {
            builder.append(getRegionView());
        }
        if (cityView != null) {
            builder.append(getCityView());
        }
        if (streetView != null) {
            builder.append(getStreetView());
        }
        if (houseView != null) {
            builder.append(getHouseView());
        }
        builder.append("}");

        return builder.toString();
    }
}
