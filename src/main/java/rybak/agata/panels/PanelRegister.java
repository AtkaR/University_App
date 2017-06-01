package rybak.agata.panels;

import rybak.agata.classes.Register;
import rybak.agata.classes.Student;
import rybak.agata.classes.StudentUniversity;
import rybak.agata.classes.University;
import rybak.agata.database.Database;
import rybak.agata.database.DatabaseDao;
import rybak.agata.models.CustomComboboxModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelRegister extends JPanel {

    private JButton btnLeft1 = new JButton("<<<");
    private JButton btnRight1 = new JButton(">>>");
    private JButton btnLeft2 = new JButton("<<<");
    private JButton btnRight2 = new JButton(">>>");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnDelete = new JButton("Delete");

    private JLabel lStudentYear = new JLabel("Student Year");
    private JLabel lFirstName = new JLabel("First name");
    private JLabel lLastName = new JLabel("Last name");
    private JLabel lAge = new JLabel("Age");
    private JLabel lStudentAverage = new JLabel("Average");
    private JLabel lEmailS = new JLabel("Email");

    private JTextField tfStudentYear = new JTextField(2);
    private JTextField tfFirstName = new JTextField(14);
    private JTextField tfLastName = new JTextField(14);
    private JTextField tfAge = new JTextField(14);
    private JTextField tfStudentAverage = new JTextField(14);
    private JTextField tfEmailS = new JTextField(14);

    private JLabel lFirstName2 = new JLabel("First name");
    private JLabel lLastName2 = new JLabel("Last name");
    private JLabel lAge2 = new JLabel("Age");
    private JLabel lStudentAverage2 = new JLabel("Average");
    private JLabel lEmailS2 = new JLabel("Email");

    private JTextField tfFirstName2 = new JTextField(14);
    private JTextField tfLastName2 = new JTextField(14);
    private JTextField tfAge2 = new JTextField(14);
    private JTextField tfStudentAverage2 = new JTextField(14);
    private JTextField tfEmailS2 = new JTextField(14);

    private JTextField tfId = new JTextField(5);

    private JLabel lName = new JLabel("Name");
    private JLabel lCity = new JLabel("City");
    private JLabel lSetYear = new JLabel("Set Year");
    private JLabel lEmailU = new JLabel("Email");
    private JLabel lAddress = new JLabel("Address");

    private JTextField tfName = new JTextField(15);
    private JTextField tfCity = new JTextField(15);
    private JTextField tfSetYear = new JTextField(15);
    private JTextField tfEmailU = new JTextField(15);
    private JTextField tfAddress = new JTextField(15);

    private JLabel lName2 = new JLabel("Name");
    private JLabel lCity2 = new JLabel("City");
    private JLabel lSetYear2 = new JLabel("Set Year");
    private JLabel lEmailU2 = new JLabel("Email");
    private JLabel lAddress2 = new JLabel("Address");

    private JTextField tfName2 = new JTextField(15);
    private JTextField tfCity2 = new JTextField(15);
    private JTextField tfSetYear2 = new JTextField(15);
    private JTextField tfEmailU2 = new JTextField(15);
    private JTextField tfAddress2 = new JTextField(15);

    private JLabel lIdS = new JLabel("STUDENT'S ID");
    private JLabel lIdU = new JLabel("UNIVERSITY'S ID");
    private JLabel lSelectYear = new JLabel("SELECT YEAR");
    //tutaj robisz same deklaracje bez new, bo dopiero w konstruktorze bedziesz
    //mogla zainicjalizowac danymi z bazy
    private CustomComboboxModel<Integer> modelCIdS;
    private CustomComboboxModel<Integer> modelCIdU;
    private CustomComboboxModel<Integer> modelCSelectYear;
    private JComboBox<Integer> cIdS;
    private JComboBox<Integer> cIdU;
    private JComboBox<Integer> cSelectYear;

    private List<StudentUniversity> studentUniversities;
    private int idx;

    private DatabaseDao databaseDao = Database.getInstance();

    public PanelRegister() {
        super(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();

        //-------------------------------------------------------------------------------
        //----------------------------PANEL NAVIGATION-----------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelNavigation = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelNavigation = new GridBagConstraints();

        gbcPanelNavigation.gridx = 0;
        gbcPanelNavigation.gridy = 0;

        btnLeft1.addActionListener(e -> {
            --idx;
            if (idx < 0)
            {
                idx = studentUniversities.size() - 1;
            }
            updateFieldsStudentsUniversities();
        });
        panelNavigation.add(btnLeft1, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 1;
        gbcPanelNavigation.gridy = 0;
        panelNavigation.add(tfId, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 2;
        gbcPanelNavigation.gridy = 0;

        btnRight1.addActionListener(e -> {
            ++idx;
            if (idx >= studentUniversities.size())
            {
                idx = 0;
            }
            updateFieldsStudentsUniversities();
        });
        panelNavigation.add(btnRight1, gbcPanelNavigation);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL FIELDS------------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "REGISTERED",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lFirstName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfFirstName, gbcPanelFields);

        gbcPanelFields.gridx = 3;
        gbcPanelFields.gridy = 0;
        panelFields.add(lName, gbcPanelFields);

        gbcPanelFields.gridx = 4;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfName, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lLastName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfLastName, gbcPanelFields);

        gbcPanelFields.gridx = 3;
        gbcPanelFields.gridy = 1;
        panelFields.add(lCity, gbcPanelFields);

        gbcPanelFields.gridx = 4;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfCity, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lAge, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfAge, gbcPanelFields);

        gbcPanelFields.gridx = 3;
        gbcPanelFields.gridy = 2;
        panelFields.add(lSetYear, gbcPanelFields);

        gbcPanelFields.gridx = 4;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfSetYear, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lEmailS, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfEmailS, gbcPanelFields);

        gbcPanelFields.gridx = 3;
        gbcPanelFields.gridy = 3;
        panelFields.add(lEmailU, gbcPanelFields);

        gbcPanelFields.gridx = 4;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfEmailU, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 4;
        panelFields.add(lStudentAverage, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfStudentAverage, gbcPanelFields);

        gbcPanelFields.gridx = 3;
        gbcPanelFields.gridy = 4;
        panelFields.add(lAddress, gbcPanelFields);

        gbcPanelFields.gridx = 4;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfAddress, gbcPanelFields);

        gbcPanelFields.gridx = 2;
        gbcPanelFields.gridy = 5;
        panelFields.add(lStudentYear, gbcPanelFields);

        gbcPanelFields.gridx = 3;
        gbcPanelFields.gridy = 5;
        panelFields.add(tfStudentYear, gbcPanelFields);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL OPERATIONS--------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;

        btnDelete.addActionListener(e -> {
            //usuwamy po id
            if (!tfId.getText().isEmpty())
            {
                Database.getInstance().deleteRegister(Integer.parseInt(tfId.getText()));
                studentUniversities = Database.getInstance().selectStudentUniversity();
                --idx;
                if (idx < 0 )
                {
                    idx = 0;
                }
                updateFieldsStudentsUniversities();
            }
        });
        panelOperations.add(btnDelete, gbcPanelOperations);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL FIELDS 2------------------------------------
        //-------------------------------------------------------------------------------

        JPanel panelFields2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields2 = new GridBagConstraints();

        panelFields2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "ADD NEW",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields2.gridx = 2;
        gbcPanelFields2.gridy = 0;
        panelFields2.add(lSelectYear, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 0;
        List<Integer> yearList = new ArrayList<>();
        Collections.addAll(yearList, 1,2,3,4,5);
        modelCSelectYear = new CustomComboboxModel<>(yearList);
        cSelectYear = new JComboBox<>(modelCSelectYear);
        panelFields2.add(cSelectYear, gbcPanelFields2);

        gbcPanelFields2.gridx = 0;
        gbcPanelFields2.gridy = 1;
        panelFields2.add(lIdS, gbcPanelFields2);

        gbcPanelFields2.gridx = 1;
        gbcPanelFields2.gridy = 1;
        //model - inicjalizacja danymi z bazy
        modelCIdS = new CustomComboboxModel<>(Database.getInstance().selectStudentIds());
        //podpiecie modelu do combo boxa
        cIdS = new JComboBox<>(modelCIdS);
        //combobox ma action listener ktory wypelnia pola pod combobox
        cIdS.addActionListener(e -> {
            fillStudentPanelDown();
        });
        panelFields2.add(cIdS, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 1;
        panelFields2.add(lIdU, gbcPanelFields2);

        gbcPanelFields2.gridx = 4;
        gbcPanelFields2.gridy = 1;
        modelCIdU = new CustomComboboxModel<>(Database.getInstance().selectUniversityIds());
        cIdU = new JComboBox<>(modelCIdU);
        cIdU.addActionListener(e -> {
            fillUniversityPanelDown();
        });
        panelFields2.add(cIdU, gbcPanelFields2);

        gbcPanelFields2.gridx = 0;
        gbcPanelFields2.gridy = 2;
        panelFields2.add(lFirstName2, gbcPanelFields2);

        gbcPanelFields2.gridx = 1;
        gbcPanelFields2.gridy = 2;
        panelFields2.add(tfFirstName2, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 2;
        panelFields2.add(lName2, gbcPanelFields2);

        gbcPanelFields2.gridx = 4;
        gbcPanelFields2.gridy = 2;
        panelFields2.add(tfName2, gbcPanelFields2);

        gbcPanelFields2.gridx = 0;
        gbcPanelFields2.gridy = 3;
        panelFields2.add(lLastName2, gbcPanelFields2);

        gbcPanelFields2.gridx = 1;
        gbcPanelFields2.gridy = 3;
        panelFields2.add(tfLastName2, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 3;
        panelFields2.add(lCity2, gbcPanelFields2);

        gbcPanelFields2.gridx = 4;
        gbcPanelFields2.gridy = 3;
        panelFields2.add(tfCity2, gbcPanelFields2);

        gbcPanelFields2.gridx = 0;
        gbcPanelFields2.gridy = 4;
        panelFields2.add(lAge2, gbcPanelFields2);

        gbcPanelFields2.gridx = 1;
        gbcPanelFields2.gridy = 4;
        panelFields2.add(tfAge2, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 4;
        panelFields2.add(lSetYear2, gbcPanelFields2);

        gbcPanelFields2.gridx = 4;
        gbcPanelFields2.gridy = 4;
        panelFields2.add(tfSetYear2, gbcPanelFields2);

        gbcPanelFields2.gridx = 0;
        gbcPanelFields2.gridy = 5;
        panelFields2.add(lEmailS2, gbcPanelFields2);

        gbcPanelFields2.gridx = 1;
        gbcPanelFields2.gridy = 5;
        panelFields2.add(tfEmailS2, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 5;
        panelFields2.add(lEmailU2, gbcPanelFields2);

        gbcPanelFields2.gridx = 4;
        gbcPanelFields2.gridy = 5;
        panelFields2.add(tfEmailU2, gbcPanelFields2);

        gbcPanelFields2.gridx = 0;
        gbcPanelFields2.gridy = 6;
        panelFields2.add(lStudentAverage2, gbcPanelFields2);

        gbcPanelFields2.gridx = 1;
        gbcPanelFields2.gridy = 6;
        panelFields2.add(tfStudentAverage2, gbcPanelFields2);

        gbcPanelFields2.gridx = 3;
        gbcPanelFields2.gridy = 6;
        panelFields2.add(lAddress2, gbcPanelFields2);

        gbcPanelFields2.gridx = 4;
        gbcPanelFields2.gridy = 6;
        panelFields2.add(tfAddress2, gbcPanelFields2);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL OPERATIONS 2--------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelOperations2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations2 = new GridBagConstraints();

        gbcPanelOperations2.gridx = 2;
        gbcPanelOperations2.gridy = 0;

        btnInsert.addActionListener(e -> {
            Database.getInstance().insertRegistry(
                    new Register(0, (int)cIdS.getSelectedItem(), (int)cIdU.getSelectedItem(), (int)cSelectYear.getSelectedItem()));
            studentUniversities = Database.getInstance().selectStudentUniversity();
            idx = studentUniversities.size() - 1;
            updateFieldsStudentsUniversities();

        });
        panelOperations2.add(btnInsert, gbcPanelOperations2);

        panelNavigation.setBackground(new Color(200, 250, 200));
        panelFields.setBackground(new Color(200, 250, 200));
        panelOperations.setBackground(new Color(200, 250, 200));
        setBackground(new Color(200, 250, 200));
        panelFields2.setBackground(new Color(200, 250, 200));
        panelOperations2.setBackground(new Color(200, 250, 200));
        setBackground(new Color(200, 250, 200));

        //-------------------------------------------------------------------------------
        //--------------------------------PANEL MAIN-------------------------------------
        //-------------------------------------------------------------------------------

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(panelNavigation, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        add(panelFields, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        add(panelOperations, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 3;
        add(panelFields2, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 4;
        add(panelOperations2, gbcMain);

        idx = 0;
        studentUniversities = Database.getInstance().selectStudentUniversity();
        updateFieldsStudentsUniversities();
        fillStudentPanelDown();
        fillUniversityPanelDown();
    }

    public void updateFieldsStudentsUniversities()
    {
        System.out.println("IDX = " + idx);
        System.out.println("-->" + studentUniversities);
        if (studentUniversities != null && !studentUniversities.isEmpty())
        {
            StudentUniversity su = studentUniversities.get(idx);
            tfId.setText(su.getId() + "");
            tfFirstName.setText(su.getFirstName());
            tfLastName.setText(su.getLastName());
            tfAge.setText(String.valueOf(su.getAge()));
            tfEmailS.setText(su.getEmail());
            tfStudentAverage.setText(String.valueOf(su.getStudentAverage()));
            tfName.setText(su.getName());
            tfCity.setText(su.getCity());
            tfSetYear.setText(String.valueOf(su.getSetYear()));
            tfEmailU.setText(su.getEmail());
            tfAddress.setText(su.getAddress());
            tfStudentYear.setText(String.valueOf(su.getStudentYear()));
        }
        else
        {
            tfId.setText("");
            tfFirstName.setText("");
            tfLastName.setText("");
            tfAge.setText("");
            tfEmailS.setText("");
            tfStudentAverage.setText("");
            tfName.setText("");
            tfCity.setText("");
            tfSetYear.setText("");
            tfEmailU.setText("");
            tfAddress.setText("");
            tfStudentYear.setText("");
        }
    }
    public void updateComboBoxes()
    {
        modelCIdS.update(Database.getInstance().selectStudentIds());
        modelCIdU.update(Database.getInstance().selectUniversityIds());
        cIdS.updateUI();
        cIdU.updateUI();
    }

    //metody ktora wypelniaja pola
    public void fillStudentPanelDown()
    {
        int id = (int)cIdS.getSelectedItem();
        Student s = Database.getInstance().selectStudentById(id);
        tfFirstName2.setText(s.getFirstName());
        tfLastName2.setText(s.getLastName());
        tfAge2.setText(String.valueOf(s.getAge()));
        tfEmailS2.setText(s.getEmail());
        tfStudentAverage2.setText(String.valueOf(s.getStudentAverage()));
    }

    public void fillUniversityPanelDown()
    {
        int id = (int)cIdU.getSelectedItem();
        University u = Database.getInstance().selectUniversityById(id);
        tfName2.setText(u.getName());
        tfCity2.setText(u.getCity());
        tfSetYear2.setText(String.valueOf(u.getSetYear()));
        tfEmailU2.setText(u.getEmail());
        tfAddress2.setText(u.getAddress());
    }
}