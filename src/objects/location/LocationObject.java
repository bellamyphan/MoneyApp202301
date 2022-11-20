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
        return "LocationObject{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
