package objects.location;

import dao.location.UsCitiesReaderDAO;
import dao.location.UsStatesReaderDAO;

public class LocationObject {
    String country;
    String state;
    String city;

    public LocationObject(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }

    public LocationObject(String cityStateCountry) {
        country = getCountry(cityStateCountry).toUpperCase();
        state = new UsStatesReaderDAO().getFormalStateCode(getState(cityStateCountry));
        city = new UsCitiesReaderDAO(state).getFormalCityName(getCity(cityStateCountry));
    }

    @Override
    public String toString() {
        return city + ", " + state + ", " + country;
    }

    private String getCity(String cityStateCountry) {
        return cityStateCountry.split(", ")[0];
    }

    private String getState(String cityStateCountry) {
        return cityStateCountry.split(", ")[1];
    }

    private String getCountry(String cityStateCountry) {
        return cityStateCountry.split(", ")[2];
    }
}
