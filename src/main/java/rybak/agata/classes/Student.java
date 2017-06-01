package rybak.agata.classes;

/**
 * Created by asus on 2017-06-01.
 */
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private double studentAverage;
    private String email;

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

    public Student()
    {

    }
    public Student(int id, String firstName, String lastName, int age, double studentAverage, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studentAverage = studentAverage;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
                + ", studentAverage=" + studentAverage + ", email=" + email + "]";
    }
}