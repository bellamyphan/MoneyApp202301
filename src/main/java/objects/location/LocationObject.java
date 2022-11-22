package objects.location;

public class LocationObject {
    String country;
    String state;
    String city;

    public LocationObject(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }

    @Override
    public String toString() {
        if (country.compareToIgnoreCase("us") == 0) {
            return city + "-" + state;
        } else {
            return country + "-" + city + "-" + state;
        }
    }
}
