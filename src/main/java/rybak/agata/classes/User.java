package rybak.agata.classes;

/**
 * Created by asus on 2017-06-01.
 */
public class User {
    private int id;
    private String name;
    private String city;
    private int age;
    private String mail;
    private String username;
    private String password;

    public User(int id, String name, String city, int age, String mail, String username, String password) {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public User(){}

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", city=" + city + ", age=" + age + ", mail=" + mail
                + ", username=" + username + ", password=" + password + "]";
    }


}