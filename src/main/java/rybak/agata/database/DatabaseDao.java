package rybak.agata.database;

import rybak.agata.classes.Register;
import rybak.agata.classes.Student;
import rybak.agata.classes.University;
import rybak.agata.classes.User;

/**
 * Created by asus on 2017-06-01.
 */
public interface DatabaseDao {
    void insertStudent(Student s);
    void insertUniversity(University u);
    void insertRegistry(Register r);
    void insertUser(User u);
    void updateStudent(Student s);
    void updateUniversity(University u);
    void updateRegister(Register r);
    void updateUser(User u);
    void deleteStudent(int id);
    void deleteUniversity(int id);
    void deleteRegister(int id);
    void deleteUser(int id);


	/*List<Student> selectStudent();
	List<Integer> selectStudentIds();
	List<University> selectUniversity();
	List<Integer> selectUniversityIds();
	List<Register> selectRegister();
	Student selectStudentById(int idd);
	University selectUniversityById(int idd);
	Register selectRegisterById(int idd);
	List<StudentUniversity> selectStudentUniversity();
	List<StudentUniversity> filterStudentUniversity(
			boolean namesActive,
			boolean lastNamesActive,
			boolean citiesActive,
			boolean universitiesNamesActive,
			boolean studentAgeActive,
			boolean studentYearActive,
			List<String> namesList,
			List<String> lastNameList,
			List<String> citiesList,
			List<String> universitiesNamesList,
			int studentAgeFrom,
			int studentAgeTo,
			int studentYearFrom,
			int studentYearTo
	);*/

}