package rybak.agata.panels;

import rybak.agata.classes.Student;
import rybak.agata.database.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelStudentUpdate extends JPanel {

    private JButton btnUpdate = new JButton("UPDATE");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lId = new JLabel("ID");
    private JLabel lFirstName = new JLabel("First name");
    private JLabel lLastName = new JLabel("Last name");
    private JLabel lAge = new JLabel("Age");
    private JLabel lStudentAverage = new JLabel("Average");
    private JLabel lEmail = new JLabel("Email");

    private JTextField tfId = new JTextField(14);
    private JTextField tfFirstName = new JTextField(14);
    private JTextField tfLastName = new JTextField(14);
    private JTextField tfAge = new JTextField(14);
    private JTextField tfStudentAverage = new JTextField(14);
    private JTextField tfEmail = new JTextField(14);

    private PanelStudent panelStudent;

    public PanelStudentUpdate(PanelStudent panelStudent)
    {
        super(new GridBagLayout());
        this.panelStudent = panelStudent;
        GridBagConstraints gbcUpdate = new GridBagConstraints();

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL FIELDS------------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "UPDATE STUDENT",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));



        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lId, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfId, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lFirstName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfFirstName, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lLastName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfLastName, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lAge, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfAge, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 4;
        panelFields.add(lEmail, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfEmail, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 5;
        panelFields.add(lStudentAverage, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 5;
        panelFields.add(tfStudentAverage, gbcPanelFields);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL OPERATIONS--------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnUpdate, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelFields.setBackground(new Color(200, 200, 250));
        panelOperations.setBackground(new Color(200, 200, 250));
        setBackground(new Color(200, 200, 250));

        //-------------------------------------------------------------------------------
        //--------------------------------PANEL UPDATE-----------------------------------
        //-------------------------------------------------------------------------------

        gbcUpdate.gridx = 0;
        gbcUpdate.gridy = 0;
        add(panelFields, gbcUpdate);

        gbcUpdate.gridx = 0;
        gbcUpdate.gridy = 1;
        add(panelOperations, gbcUpdate);

        fillFields(panelStudent.getStudentFromFields());

        btnUpdate.addActionListener(e -> {
            if (
                    tfId.getText().isEmpty() ||
                            tfFirstName.getText().isEmpty() ||
                            tfLastName.getText().isEmpty() ||
                            tfAge.getText().isEmpty() ||
                            tfEmail.getText().isEmpty() ||
                            tfStudentAverage.getText().isEmpty()
                    )
            {
                JOptionPane.showMessageDialog(null, "FILL ALL FIELDS TO UPDATE CURRENT STUDENT!");
                return;
            }

            Student ps = new Student(
                    Integer.parseInt(tfId.getText()),
                    tfFirstName.getText(),
                    tfLastName.getText(),
                    Integer.parseInt(tfAge.getText()),
                    Double.parseDouble(tfStudentAverage.getText()),
                    tfEmail.getText()
            );

            Database.getInstance().updateStudent(ps);
            panelStudent.fillFieldsAfterUpdate();

            //po wstawieniu danych zamykamy panel od wstawiania
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });
    }


    public void fillFields(Student s)
    {
        if (s != null)
        {
            tfId.setText(String.valueOf(s.getId()));
            tfFirstName.setText(s.getFirstName());
            tfLastName.setText(s.getLastName());
            tfEmail.setText(s.getEmail());
            tfAge.setText(String.valueOf(s.getAge()));
            tfStudentAverage.setText(String.valueOf(s.getStudentAverage()));
        }
    }

}

