package rybak.agata.classes;

/**
 * Created by asus on 2017-06-01.
 */
public class Register {

    private int id;
    private int studentId;
    private int universityId;
    private int studentYear;

    public Register() {
        // TODO Auto-generated constructor stub
    }

    public Register(int id, int studentId, int universityId, int studentYear) {
        super();
        this.id = id;
        this.studentId = studentId;
        this.universityId = universityId;
        this.studentYear = studentYear;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getUniversityId() {
        return universityId;
    }
    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }
    public int getStudentYear() {
        return studentYear;
    }
    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
    }

    @Override
    public String toString() {
        return "Register [id=" + id + ", studentId=" + studentId + ", universityId=" + universityId + ", studentYear="
                + studentYear + "]";
    }


}

