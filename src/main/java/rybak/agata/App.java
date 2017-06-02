package rybak.agata;

import rybak.agata.classes.*;
import rybak.agata.database.Database;
import rybak.agata.panels.PanelLogin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App
{
    public static void createWindow()
    {
        JFrame frame = new JFrame("UNIVERSITY APP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PanelLogin panel = new PanelLogin();
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocation(400, 100);
        frame.setResizable(false);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        frame.pack();

    }
    public static void main( String[] args )
    {

        Database db = Database.getInstance();
        db.createTable();


        /*db.insertStudent(new Student(0, "Stefan", "Pupka", 32, 4.3, "stefan@pupcia.pl"));
        db.insertStudent(new Student(0, "Halina", "Kapec", 22, 3.3, "halinka@kapciowa.pl"));
        db.insertStudent(new Student(0, "Jurek", "Marchewka", 25, 4.2, "jurcio@marchewka.pl"));
        db.insertStudent(new Student(0, "Agata", "Rybka", 22, 5.0, "agata@rybka.pl"));
        db.insertStudent(new Student(0, "Kamil", "Pomperek", 25, 4.6, "kamil@pomperek.pl"));
        db.insertStudent(new Student(0, "Edward", "Dzik", 20, 4.0, "edward@dziku.pl"));
        db.insertStudent(new Student(0, "Sylwia", "Kuna", 21, 3.5, "sylwia@kuna.pl"));
        db.insertStudent(new Student(0, "Kacper", "Micha", 21, 3.9, "kacper@micha.pl"));
        db.insertStudent(new Student(0, "Wojciech", "Grad", 24, 4.5, "wojciech@grad.pl"));
        db.insertStudent(new Student(0, "Kamil", "Woda", 22, 4.1, "kamil@woda.pl"));
        db.insertStudent(new Student(0, "Andrzej", "Duda", 23, 4.4, "andrzej@duda.pl"));
        db.insertStudent(new Student(0, "Andzelina", "Blond", 21, 3.7, "andzelina@blond.pl"));
        db.insertStudent(new Student(0, "Beata", "Martwa", 24, 4.3, "beata@martwa.pl"));
        db.insertStudent(new Student(0, "Bartosz", "Styk", 22, 5.0, "bartosz@styk.pl"));
        db.insertStudent(new Student(0, "Marek", "Pompa", 21, 4.7, "marek@pompa.pl"));
        db.insertStudent(new Student(0, "Adrian", "Lis", 21, 4.8, "adrian@lis.pl"));
        db.insertStudent(new Student(0, "Wlodzimierz", "Kciuk", 21, 3.0, "wlodzimierz@kciuk.pl"));
        db.insertStudent(new Student(0, "Eleonora", "Pogoda", 24, 3.1, "eleonora@pogoda.pl"));
        db.insertStudent(new Student(0, "Esmeralda", "Niedowidz", 20, 4.6, "esmeralda@niedowidz.pl"));
        db.insertStudent(new Student(0, "Juliusz", "Cezar", 25, 4.0, "juliusz@cezar.pl"));
        db.insertStudent(new Student(0, "Pawel", "Czarek", 22, 4.6, "pawel@czarek.pl"));
        db.insertStudent(new Student(0, "Grazyna", "Luksus", 26, 4.7, "grazyna@luksus.pl"));
        db.insertStudent(new Student(0, "Tomasz", "Podsiad", 28, 3.3, "tomasz@podsiad.pl"));
        db.insertStudent(new Student(0, "Bartosz", "Pyka", 29, 5.0, "bartosz@pyka.pl"));
        db.insertStudent(new Student(0, "Tomasz", "Glowa", 20, 4.9, "tomasz@glowa.pl"));
        db.insertStudent(new Student(0, "Anna", "Rura", 19, 4.1, "anna@rura.pl"));
        db.insertStudent(new Student(0, "Agata", "Oko", 21, 3.9, "agata@oko.pl"));
        db.insertStudent(new Student(0, "Krzysztof", "Wyrozumialy", 27, 3.2, "krzysztof@wyrozumialy.pl"));
        db.insertStudent(new Student(0, "Julia", "Lico", 20, 4.1, "julia@lico.pl"));
        db.insertStudent(new Student(0, "Daria", "Niedopad", 21, 3.0, "daria@niedopad.pl"));


        db.insertUniversity(new University(0, "PK", "Krakow", 2007, "pk@edu.pl", "ul.Warszawska 23, Krakow"));
        db.insertUniversity(new University(0, "AWF", "Krakow", 2010, "awf@edu.pl", "ul.Dluga 72, Krakow"));
        db.insertUniversity(new University(0, "AGH", "Krakow", 2011, "agh@edu.pl", "ul.Czarnowiejska 1, Krakow"));
        db.insertUniversity(new University(0, "UEK", "Krakow", 2012, "uek@edu.pl", "al.Slowackiego 13, Krakow"));
        db.insertUniversity(new University(0, "UJ", "Krakow", 2013, "uj@edu.pl", "ul.Miodowa 76, Krakow"));
        db.insertUniversity(new University(0, "SGH", "Warszawa", 2009, "sgh@edu.pl", "ul.Jesienna 37, Warszawa"));
        db.insertUniversity(new University(0, "PW", "Warszawa", 2003, "pw@edu.pl", "ul.Zimowa 98, Warszawa"));
        db.insertUniversity(new University(0, "WAT", "Warszawa", 2005, "wat@edu.pl", "ul.Grzybowa 15, Warszawa"));
        db.insertUniversity(new University(0, "WSPiA", "Rzeszow", 2010, "wspia@edu.pl", "ul.Dlugosza 19, Rzeszow"));
        db.insertUniversity(new University(0, "KUL", "Lublin", 2003, "kul@edu.pl", "ul.Ksiezy 12, Lublin"));
        db.insertUniversity(new University(0, "UW", "Warszawa", 2002, "uw@edu.pl", "ul.Lisciowa 90, Warszawa"));
        db.insertUniversity(new University(0, "UMCS", "Lublin", 2004, "umcs@edu.pl", "ul.Kolorowa 134, Lublin"));
        db.insertUniversity(new University(0, "PRz", "Rzeszow", 2010, "prz@edu.pl", "ul.Sloneczna 145, Rzeszow"));
        db.insertUniversity(new University(0, "PG", "Gdansk", 2001, "pg@edu.pl", "ul.Szara 74, Gdansk"));
        db.insertUniversity(new University(0, "PW", "Wroclaw", 2003, "pw@edu.pl", "ul.Lotnikow 45, Wroclaw"));
        db.insertUniversity(new University(0, "UP", "Krakow", 2009, "up@edu.pl", "ul.Podchorazych 3, Krakow"));
        db.insertUniversity(new University(0, "UEW", "Wroclaw", 2014, "uew@edu.pl", "ul.Zielona 45, Wroclaw"));
        db.insertUniversity(new University(0, "SGGW", "Warszawa", 2013, "sggw@edu.pl", "ul.Stalowa 111, Warszawa"));
        db.insertUniversity(new University(0, "PWr", "Wroclaw", 2005, "pwr@edu.pl", "ul.Szewska 11, Wroclaw"));
        db.insertUniversity(new University(0, "PL", "Lublin", 2003, "pl@edu.pl", "ul.Dziwna 60, Lublin"));



        db.insertRegistry(new Register(0, 1, 1, 1));
        db.insertRegistry(new Register(0, 2, 2, 2));
        db.insertRegistry(new Register(0, 3, 3, 3));
        db.insertRegistry(new Register(0, 4, 4, 4));
        db.insertRegistry(new Register(0, 5, 5, 5));
        db.insertRegistry(new Register(0, 6, 1, 5));
        db.insertRegistry(new Register(0, 7, 2, 4));
        db.insertRegistry(new Register(0, 8, 3, 3));
        db.insertRegistry(new Register(0, 9, 4, 2));
        db.insertRegistry(new Register(0, 10, 5, 1));
        db.insertRegistry(new Register(0, 11, 1, 2));
        db.insertRegistry(new Register(0, 12, 2, 3));
        db.insertRegistry(new Register(0, 13, 3, 4));
        db.insertRegistry(new Register(0, 14, 4, 5));
        db.insertRegistry(new Register(0, 15, 5, 4));
        */

        //db.updateStudent(new Student(1, "Agata", "Rybka", 18, 4.7, "rybka@stawik.pl"));
        //db.updateUniversity(new University(1, "WSI", "Warszawa", 2016, "wsi@balanga.pl", "ul. Balangowa 7, Warszawa"));
        //db.updateRegister(new Register(1,1,1,1));

        //db.deleteStudent(30);
        //db.deleteUniversity(20);
        //db.deleteRegister(16);

        //db.selectStudent().forEach(System.out::println);
        //db.selectUniversity().forEach(System.out::println);
        //db.selectRegister().forEach(System.out::println);

        //System.out.println(db.selectStudentById(1));
        //System.out.println(db.selectUniversityById(1));
        //System.out.println(db.selectRegisterById(1));
        //System.out.println(db.selectStudentUniversityById());
        //db.selectStudentUniversity().forEach(System.out::println);

        db.insertUser(new User(0, "Agata", "Krakow", 30, "rybka@stawik.pl", "a", "a"));
        db.insertUser(new User(0, "Krzysztof", "Warszawa", 29, "makus@makowo.pl", "k", "k"));

        boolean namesActive = true;
        boolean lastNamesActive = false;
        boolean citiesActive = false;
        boolean universitiesNamesActive = false;
        boolean studentAgeActive = false;
        boolean studentYearActive = false;
        List<String> namesList = new ArrayList<>();
        Collections.addAll(namesList, "A", "B", "C", "D");
        List<String> lastNameList = new ArrayList<>();
        Collections.addAll(lastNameList, "E", "F", "G", "H");
        List<String> citiesList  = new ArrayList<>();
        Collections.addAll(citiesList, "I", "J", "K", "L");
        List<String> universitiesNamesList  = new ArrayList<>();
        Collections.addAll(universitiesNamesList, "M", "N", "O", "P");
        int studentAgeFrom = 5;
        int studentAgeTo = 20;
        int studentYearFrom = 1;
        int studentYearTo = 4;
        List<StudentUniversity> suList = db.filterStudentUniversity(namesActive, lastNamesActive, citiesActive, universitiesNamesActive, studentAgeActive, studentYearActive, namesList, lastNameList, citiesList, universitiesNamesList, studentAgeFrom, studentAgeTo, studentYearFrom, studentYearTo);
        System.out.println("-------------------");
        suList.forEach(c -> {System.out.println(c);});
        javax.swing.SwingUtilities.invokeLater(
                () -> createWindow()
        );
    }
}

