package rybak.agata.panels;

import rybak.agata.classes.Student;
import rybak.agata.database.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelStudentInsert extends JPanel {
    private JButton btnInsert = new JButton("INSERT");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lFirstName = new JLabel("First name");
    private JLabel lLastName = new JLabel("Last name");
    private JLabel lAge = new JLabel("Age");
    private JLabel lStudentAverage = new JLabel("Average");
    private JLabel lEmail = new JLabel("Email");

    private JTextField tfFirstName = new JTextField(14);
    private JTextField tfLastName = new JTextField(14);
    private JTextField tfAge = new JTextField(14);
    private JTextField tfStudentAverage = new JTextField(14);
    private JTextField tfEmail = new JTextField(14);

    private PanelStudent ps;

    public PanelStudentInsert(PanelStudent ps)
    {
        super(new GridBagLayout());
        this.ps = ps;
        GridBagConstraints gbcInsert = new GridBagConstraints();

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL FIELDS------------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "INSERT STUDENT",
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

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lLastName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfLastName, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lAge, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfAge, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lEmail, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfEmail, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 4;
        panelFields.add(lStudentAverage, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfStudentAverage, gbcPanelFields);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL OPERATIONS------------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnInsert, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelFields.setBackground(new Color(200, 200, 250));
        panelOperations.setBackground(new Color(200, 200, 250));
        setBackground(new Color(200, 200, 250));

        //-------------------------------------------------------------------------------
        //--------------------------------PANEL INSERT-----------------------------------
        //-------------------------------------------------------------------------------

        gbcInsert.gridx = 0;
        gbcInsert.gridy = 0;
        add(panelFields, gbcInsert);

        gbcInsert.gridx = 0;
        gbcInsert.gridy = 1;
        add(panelOperations, gbcInsert);



        btnInsert.addActionListener(e -> {
            if (
                    tfFirstName.getText().isEmpty() ||
                            tfLastName.getText().isEmpty() ||
                            tfAge.getText().isEmpty() ||
                            tfEmail.getText().isEmpty() ||
                            tfStudentAverage.getText().isEmpty()
                    )
            {
                JOptionPane.showMessageDialog(null, "FILL ALL FIELDS TO INSERT NEW STUDENT!");
                return;
            }

            else if(!tfFirstName.getText().matches("[A-Z]+"))
            {
                JOptionPane.showMessageDialog(null, "FIRST NAME MUST CONSIST ONLY OF UPPERCASE LETTERS!");
                return;
            }

            else if(!tfLastName.getText().matches("[A-Z]+"))
            {
                JOptionPane.showMessageDialog(null, "LAST NAME MUST CONSIST ONLY OF UPPERCASE LETTERS!");
                return;
            }

            else if(Integer.parseInt(tfAge.getText()) < 18 || Integer.parseInt(tfAge.getText()) > 65)
            {
                JOptionPane.showMessageDialog(null, "AGE MUST BE BETWEEN 18 - 65!");
                return;
            }

            else if(!tfEmail.getText().matches("[a-z]+@[a-z]+.pl"))
            {
                JOptionPane.showMessageDialog(null, "EMAIL ADDRESS FORMAT IS INCORRECT!");
                return;
            }

            else if(Double.parseDouble(tfStudentAverage.getText()) < 2 || Double.parseDouble(tfStudentAverage.getText()) > 5)
            {
                JOptionPane.showMessageDialog(null, "THE AVERAGE MUST BE BETWEEN 2.0 - 5.0!");
                return;
            }

            Student s = new Student(
                    0,
                    tfFirstName.getText(),
                    tfLastName.getText(),
                    Integer.parseInt(tfAge.getText()),
                    Double.parseDouble(tfStudentAverage.getText()),
                    tfEmail.getText()
            );
            Database.getInstance().insertStudent(s);
            ps.fillFieldsAfterInsert();
            ps.getPanelRegister().updateComboBoxes();

            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();

        });

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });

    }
}
