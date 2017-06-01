package rybak.agata.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.sqlite.SQLiteConfig;
import rybak.agata.classes.*;

public class Database implements DatabaseDao {
    //obiekt przechowujacy jedyna instancje klasy Database
    private static Database database = null;

    //dane do polaczeni z baza danych
    private static final String DRIVER = "org.sqlite.JDBC"; //nazwa sterownika ktory umozliwi prawidlowe
    //polaczenie z baza danych sqlite
    private static final String DATABASE = "jdbc:sqlite:Test.db"; //nazwa bazy danych - to tak naprawde tylko
    //Test.db ale przedrostek jest konieczny bo tego wymaga sqlite
    private Connection connection; //zmienna do polaczenia z baza i zarzadzania polaczeniem
    private Statement statement; //zmienna do zarzadzania niektorymi zapytaniami do bazy,
    //np uzywana przy zapytaniu CREATE TABLE - bedzie ponizej

    //prywatny konstruktor - wywolany tylko jeden raz w metodzie getInstance umozliwi nawiazanie polaczenia
    //z baza danych
    private Database()
    {
        try {
            Class.forName(DRIVER); //zaladowanie sterownika do polaczenia z sqlite
            SQLiteConfig configuration = new SQLiteConfig(); //konfiguracja ktora pozwoli uaktywnic
            //mechanizm kluczy obcych
            configuration.enforceForeignKeys(true);
            connection = DriverManager.getConnection(DATABASE, configuration.toProperties()); //nawiazanie polaczenia z baza,
            //jezeli nie istniala zostanie utworzona nowa, a jak istaniala to zostanie otworzone
            //polaczenie z istniejaca - plik bazy danych znajduje sie w katalogu projektu
            statement = connection.createStatement(); //inicjlazacja obiektu satement ktory wykorzystamy przy
            //tworzeniu tabel
            createTable();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Database getInstance()
    {
        if (database == null) //jezeli obiekt byl null, czyli na poczatku, jeden jedyny raz zostanie
        //zainicjlaizowany konstruktorem bezarguentowy, ktory przy okazji polaczy nas z baza danych
        {
            database = new Database();
        }
        return database; //za kadym kolejnym wywlaniem metody getInstance kod w if juz nie zajdzie
        //bo obiekt nie bedzie null i to spowoduje ze zawsze zostanie zwrocona instancja database
        //utworzona na samym poczatku - singleton
    }

    //metoda tworzy pierwsza tabele w bazie danych
    public void createTable()
    {
        try {
            //piszesz zwykly sql
            String sqlCreateTableStudent =
                    "CREATE TABLE IF NOT EXISTS Student "
                            + "("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "firstName VARCHAR(50) NOT NULL, "
                            + "lastName VARCHAR(50) NOT NULL, "
                            + "age INTEGER NOT NULL, "
                            + "studentAverage DOUBLE NOT NULL, "
                            + "email VARCHAR(50) NOT NULL "
                            + ")";
            String sqlCreateTableUniversity =
                    "CREATE TABLE IF NOT EXISTS University "
                            + "("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR(50) NOT NULL, "
                            + "city VARCHAR(50) NOT NULL, "
                            + "setYear INTEGER NOT NULL, "
                            + "email VARCHAR(50) NOT NULL, "
                            + "address VARCHAR(50) NOT NULL"
                            + ")";

            String sqlCreateTableRegister =
                    "CREATE TABLE IF NOT EXISTS Register "
                            + "( "
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "studentId INTEGER NOT NULL, "
                            + "universityId INTEGER NOT NULL, "
                            + "studentYear INTEGER NOT NULL, "
                            + "FOREIGN KEY (studentId) REFERENCES Student(id) ON DELETE CASCADE ON UPDATE CASCADE,"
                            + "FOREIGN KEY (universityId) REFERENCES University(id) ON DELETE CASCADE ON UPDATE CASCADE"
                            + " )";

            String sqlCreateTableUser =
                    "CREATE TABLE IF NOT EXISTS User "
                            + "("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "name VARCHAR(50) NOT NULL, "
                            + "city VARCHAR(50) NOT NULL, "
                            + "age INTEGER NOT NULL, "
                            + "mail VARCHAR(50) NOT NULL, "
                            + "username VARCHAR(50) NOT NULL, "
                            + "password VARCHAR(50) NOT NULL "
                            + ")";

            statement.execute(sqlCreateTableStudent); //statement przyjmuje w metodzie execute string z sql-em
            //i tworzy na jego podsatawie tabelke w rzeczywistej bazie danych
            statement.execute(sqlCreateTableUniversity);
            statement.execute(sqlCreateTableRegister);
            statement.execute(sqlCreateTableUser);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void insertStudent(Student s)
    {
        try {
            String sqlInsertStudent =
                    "INSERT INTO Student (firstName, lastName, age, studentAverage, email) "
                            + "VALUES "
                            + "(?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlInsertStudent);
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setInt(3, s.getAge());
            ps.setDouble(4,  s.getStudentAverage());
            ps.setString(5, s.getEmail());
            ps.execute(); //wykonanie sparametryzowanego zapytania
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertUniversity(University u)
    {
        try {
            String sqlInsertUniversity =
                    "INSERT INTO University (name, city, setYear, email, address) "
                            + "VALUES "
                            + "(?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlInsertUniversity);
            ps.setString(1, u.getName());
            ps.setString(2, u.getCity());
            ps.setInt(3, u.getSetYear());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getAddress());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertRegistry(Register r)
    {
        try {
            String sqlInsertRegister =
                    "INSERT INTO Register (studentId, universityId, studentYear) "
                            + "VALUES "
                            + "(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlInsertRegister);
            ps.setInt(1, r.getStudentId());
            ps.setInt(2, r.getUniversityId());
            ps.setInt(3, r.getStudentYear());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertUser(User u)
    {
        try {
            String sqlInsertUser =
                    "INSERT INTO User (name, city, age, mail, username, password) "
                            + "VALUES "
                            + "(?,?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sqlInsertUser);
            ps.setString(1, u.getName());
            ps.setString(2, u.getCity());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getMail());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateStudent(Student s)
    {
        try {
            String sqlUpdateStudent =
                    "UPDATE Student SET firstName=?, lastName=?, age=?, studentAverage=?, email = ? "
                            + "WHERE id = ? ";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateStudent);
            ps.setString(1, s.getFirstName());
            ps.setString(2, s.getLastName());
            ps.setInt(3, s.getAge());
            ps.setDouble(4,  s.getStudentAverage());
            ps.setString(5, s.getEmail());
            ps.setInt(6, s.getId());
            ps.execute(); //wykonanie sparametryzowanego zapytania
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateUniversity(University u)
    {
        try {
            String sqlUpdateUniversity =
                    "UPDATE University SET name=?, city=?, setYear=?, email=?, address=? WHERE id=? ";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateUniversity);
            ps.setString(1, u.getName());
            ps.setString(2, u.getCity());
            ps.setInt(3, u.getSetYear());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getAddress());
            ps.setInt(6,  u.getId());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateRegister(Register r)
    {
        try {
            String sqlUpdateRegister =
                    "UPDATE Register SET studentId=?, universityId=?, studentYear=? WHERE id=? ";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateRegister);
            ps.setInt(1, r.getStudentId());
            ps.setInt(2, r.getUniversityId());
            ps.setInt(3, r.getStudentYear());
            ps.setInt(4, r.getId());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateUser(User u)
    {
        try {
            String sqlUpdateUser =
                    "UPDATE User SET name=?, city=?, age=?, mail=?, username=?, password=? "
                            + "WHERE id = ? ";
            PreparedStatement ps = connection.prepareStatement(sqlUpdateUser);
            ps.setString(1, u.getName());
            ps.setString(2, u.getCity());
            ps.setInt(3, u.getAge());
            ps.setString(4, u.getMail());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getPassword());
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id)
    {
        try {
            String deleteStudentSql = "DELETE FROM Student WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteStudentSql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteUniversity(int id)
    {
        try {
            String deleteUniversitySql = "DELETE FROM University WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteUniversitySql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteRegister(int id)
    {
        try {
            String deleteRegisterSql = "DELETE FROM Register WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteRegisterSql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteUser(int id)
    {
        try {
            String deleteUserSql = "DELETE FROM User WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteUserSql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Student> selectStudent()
    {
        List<Student> students = null;

        try {
            String selectStudentsSql = "SELECT * FROM Student";
            ResultSet rs = statement.executeQuery(selectStudentsSql);
            int id, age;
            String firstName, lastName, email;
            double average;
            students = new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                age = rs.getInt(4);
                average = rs.getDouble(5);
                email = rs.getString(6);
                students.add(new Student(id, firstName, lastName, age, average, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return students;
    }

    public List<Integer> selectStudentIds()
    {
        List<Integer> idList = null;

        try {
            String selectIntegerSql = "SELECT DISTINCT id FROM Student";
            ResultSet rs = statement.executeQuery(selectIntegerSql);
            int id;
            idList= new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                idList.add(id);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return idList;
    }

    public List<University> selectUniversity()
    {
        List<University> universities = null;

        try {
            String selectUniversitiesSql = "SELECT * FROM University";
            ResultSet rs = statement.executeQuery(selectUniversitiesSql);

            int id, setYear;
            String name, city, email, address;
            universities = new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                name = rs.getString(2);
                city = rs.getString(3);
                setYear = rs.getInt(4);
                email = rs.getString(5);
                address = rs.getString(6);
                universities.add(new University(id, name, city, setYear, email, address));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return universities;
    }

    public List<Integer> selectUniversityIds()
    {
        List<Integer> idList = null;

        try {
            String selectIntegerSql = "SELECT DISTINCT id FROM University";
            ResultSet rs = statement.executeQuery(selectIntegerSql);
            int id;
            idList= new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                idList.add(id);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return idList;
    }

    public List<Register> selectRegister()
    {
        List<Register> register = null;

        try {
            String selectRegisterSql = "SELECT * FROM Register";
            ResultSet rs = statement.executeQuery(selectRegisterSql);

            int id, studentId, universityId, studentYear;
            register = new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                studentId = rs.getInt(2);
                universityId = rs.getInt(3);
                studentYear = rs.getInt(4);
                register.add(new Register(id, studentId, universityId, studentYear));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return register;
    }

    public Student selectStudentById(int idd)
    {
        Student student = null;

        try {
            String selectStudentsSql = "SELECT * FROM Student WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(selectStudentsSql);
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            int id, age;
            String firstName, lastName, email;
            double average;
            if(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                age = rs.getInt(4);
                average = rs.getDouble(5);
                email = rs.getString(6);
                student = new Student(id, firstName, lastName, age, average, email);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return student;
    }

    public University selectUniversityById(int idd)
    {
        University university = null;

        try {
            String selectUniversitySql = "SELECT * FROM University WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(selectUniversitySql);
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            int id, setYear;
            String name, city, email, address;

            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                name = rs.getString(2);
                city = rs.getString(3);
                setYear = rs.getInt(4);
                email = rs.getString(5);
                address = rs.getString(6);
                university = new University(id, name, city, setYear, email, address);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return university;
    }

    public Register selectRegisterById(int idd)
    {
        Register register = null;

        try {
            String selectRegisterSql = "SELECT * FROM Register WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(selectRegisterSql);
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();

            int id, studentId, universityId, studentYear;

            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                studentId = rs.getInt(2);
                universityId = rs.getInt(3);
                studentYear = rs.getInt(4);
                register = new Register(id, studentId, universityId, studentYear);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return register;
    }

    public List<StudentUniversity> selectStudentUniversity()
    {
        List<StudentUniversity> studentUniversity = null;

        try {
            String selectStudentUniversitySql =
                    "SELECT "
                            + "R.id, S.firstName, S.lastName, S.age, S.studentAverage, S.email, U.name, U.city, U.setYear, U.email, U.address, R.studentYear "
                            + "FROM Student S INNER JOIN Register R ON "
                            + "S.id = R.studentId INNER JOIN University U ON R.universityId = U.id;";
            ResultSet rs = statement.executeQuery(selectStudentUniversitySql);

            int id, age,setYear, studentYear;
            String firstName, lastName, email, name, city, universityEmail, address;
            double studentAverage;
            studentUniversity = new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                age = rs.getInt(4);
                studentAverage = rs.getDouble(5);
                email = rs.getString(6);
                name = rs.getString(7);
                city = rs.getString(8);
                setYear = rs.getInt(9);
                universityEmail = rs.getString(10);
                address = rs.getString(11);
                studentYear = rs.getInt(12);
                studentUniversity.add(new StudentUniversity(id, firstName, lastName, age, studentAverage, email, name, city , setYear, universityEmail, address, studentYear));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return studentUniversity;
    }

    public List<StudentUniversity> filterStudentUniversity(
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
    )
    {
        List<StudentUniversity> studentUniversity = null;

        try {
            String selectStudentUniversitySql =
                    "SELECT "
                            + "R.id, S.firstName, S.lastName, S.age, S.studentAverage, S.email, U.name, U.city, U.setYear, U.email, U.address, R.studentYear "
                            + "FROM Student S INNER JOIN Register R ON "
                            + "S.id = R.studentId INNER JOIN University U ON R.universityId = U.id WHERE 1=1 ";

            if (namesActive)
            {
                String nameFilterSql = " AND S.firstName IN ('" + String.join("','", namesList) + "')";
                selectStudentUniversitySql += nameFilterSql;
            }

            if (lastNamesActive)
            {
                String lastNameFilterSql = " AND S.lastName IN ('" + String.join("','", lastNameList) + "')";
                selectStudentUniversitySql += lastNameFilterSql;
            }

            if (citiesActive)
            {
                String citiesFilterSql = " AND U.city IN ('" + String.join("','", citiesList) + "')";
                selectStudentUniversitySql += citiesFilterSql;
            }

            if (universitiesNamesActive)
            {
                String universitiesFilterSql = " AND U.name IN ('" + String.join("','", universitiesNamesList) + "')";
                selectStudentUniversitySql += universitiesFilterSql;
            }

            if (studentAgeActive)
            {
                String ageFilterSql = " AND S.age BETWEEN " + studentAgeFrom + " AND " + studentAgeTo + " ";
                selectStudentUniversitySql += ageFilterSql;
            }

            if (studentYearActive)
            {
                String yearFilterSql = " AND R.studentYear BETWEEN " + studentYearFrom + " AND " + studentYearTo + " ";
                selectStudentUniversitySql += yearFilterSql;
            }

            System.out.println(selectStudentUniversitySql);

            ResultSet rs = statement.executeQuery(selectStudentUniversitySql);

            int id, age,setYear, studentYear;
            String firstName, lastName, email, name, city, universityEmail, address;
            double studentAverage;
            studentUniversity = new ArrayList<>();
            while(rs.next()) //dopoki jest nastepny wiersz do pobrania ze zwroconych danych to go pobieramy
            {
                id = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                age = rs.getInt(4);
                studentAverage = rs.getDouble(5);
                email = rs.getString(6);
                name = rs.getString(7);
                city = rs.getString(8);
                setYear = rs.getInt(9);
                universityEmail = rs.getString(10);
                address = rs.getString(11);
                studentYear = rs.getInt(12);
                studentUniversity.add(new StudentUniversity(id, firstName, lastName, age, studentAverage, email, name, city , setYear, universityEmail, address, studentYear));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return studentUniversity;
    }

    public Optional<User> getUserByUsernameAndPassword(String username, String password)
    {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return Optional.of(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return Optional.ofNullable(null);
    }

}
