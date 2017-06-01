package rybak.agata.classes;

/**
 * Created by asus on 2017-06-01.
 */
public class StudentUniversity {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private double studentAverage;
    private String email;
    private String name;
    private String city;
    private int setYear;
    private String universityEmail;
    private String address;
    private int studentYear;

    public StudentUniversity() {
        // TODO Auto-generated constructor stub
    }

    public StudentUniversity(int id, String firstName, String lastName, int age, double studentAverage, String email,
                             String name, String city, int setYear, String universityEmail, String address, int studentYear) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studentAverage = studentAverage;
        this.email = email;
        this.name = name;
        this.city = city;
        this.setYear = setYear;
        this.universityEmail = universityEmail;
        this.address = address;
        this.studentYear = studentYear;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getStudentAverage() {
        return studentAverage;
    }
    public void setStudentAverage(double studentAverage) {
        this.studentAverage = studentAverage;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getUniversityEmail() {
        return universityEmail;
    }
    public void setUniversityEmail(String universityEmail) {
        this.universityEmail = universityEmail;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getStudentYear() {
        return studentYear;
    }
    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
    }

    @Override
    public String toString() {
        return "StudentUniversity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
                + ", studentAverage=" + studentAverage + ", email=" + email + ", name=" + name + ", city=" + city
                + ", setYear=" + setYear + ", universityEmail=" + universityEmail + ", address=" + address
                + ", studentYear=" + studentYear + "]";
    }
}
