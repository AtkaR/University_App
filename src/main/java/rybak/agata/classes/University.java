package rybak.agata.classes;

/**
 * Created by asus on 2017-06-01.
 */
public class University {
    private int id;
    private String name;
    private String city;
    private int setYear;
    private String email;
    private String address;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getSetYear() {
        return setYear;
    }
    public void setSetYear(int setYear) {
        this.setYear = setYear;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public University(int id, String name, String city, int setYear, String email, String address) {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.setYear = setYear;
        this.email = email;
        this.address = address;
    }

    public University() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "University [id=" + id + ", name=" + name + ", city=" + city + ", setYear=" + setYear + ", email="
                + email + ", address=" + address + "]";
    }

}