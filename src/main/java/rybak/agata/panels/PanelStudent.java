package rybak.agata.panels;

import rybak.agata.classes.Student;
import rybak.agata.database.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelStudent extends JPanel {
    private JButton btnLeft = new JButton("<<<");
    private JButton btnRight = new JButton(">>>");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

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

    private JTextField tfId = new JTextField(5);

    //do przechowywania danych z bazy uzywamy liste
    private List<Student> students;
    private int idx;

    private PanelRegister panelRegister;

    public PanelStudent(PanelRegister panelRegister) {
        super(new GridBagLayout());
        this.panelRegister = panelRegister;
        //trzeba utworzyc pomocniczy obiekt GridBagConstraints
        GridBagConstraints gbcMain = new GridBagConstraints();

        //-------------------------------------------------------------------------------
        //----------------------------PANEL NAVIGATION-----------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelNavigation = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelNavigation = new GridBagConstraints();

        gbcPanelNavigation.gridx = 0; //kolumna
        gbcPanelNavigation.gridy = 0; //wiersz
        btnLeft.addActionListener(e -> {
            //tutaj piszemy co chcemy zrobic kiedy wcisniemy przycisk
            --idx;
            if (idx < 0)
            {
                idx = students.size() - 1;
            }
            fillFields();
        });
        panelNavigation.add(btnLeft, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 1;
        gbcPanelNavigation.gridy = 0;
        panelNavigation.add(tfId, gbcPanelNavigation);

        gbcPanelNavigation.gridx = 2;
        gbcPanelNavigation.gridy = 0;
        btnRight.addActionListener(e -> {
            //tutaj piszemy co chcemy zrobic kiedy wcisniemy przycisk
            ++idx;
            if (idx >= students.size())
            {
                idx = 0;
            }
            fillFields();
        });
        panelNavigation.add(btnRight, gbcPanelNavigation);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL FIELDS------------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "STUDENT FORM",
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
        //-------------------------------PANEL OPERATIONS--------------------------------
        //-------------------------------------------------------------------------------
        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 0;
		/*btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		})*/
        btnInsert.addActionListener(e -> {generateInsertWindow();});
        panelOperations.add(btnInsert, gbcPanelOperations);

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        btnDelete.addActionListener(e -> {
            if (!tfId.getText().isEmpty())
            {
                Database.getInstance().deleteStudent(Integer.parseInt(tfId.getText()));
                students = Database.getInstance().selectStudent();
                if(idx > 0)
                {
                    --idx;
                }
                fillFields();
                panelRegister.updateComboBoxes();
            }

        });
        panelOperations.add(btnDelete, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        btnUpdate.addActionListener(e -> {generateUpdateWindow();});
        panelOperations.add(btnUpdate, gbcPanelOperations);

        panelNavigation.setBackground(new Color(200, 200, 250));
        panelFields.setBackground(new Color(200, 200, 250));
        panelOperations.setBackground(new Color(200, 200, 250));
        setBackground(new Color(200, 200, 250));

        //-------------------------------------------------------------------------------
        //--------------------------------PANEL MAIN-------------------------------------
        //-------------------------------------------------------------------------------
        //trzy panele musze dodac do panelu glownego
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        add(panelNavigation, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        add(panelFields, gbcMain);

        gbcMain.gridx = 0;
        gbcMain.gridy = 2;
        add(panelOperations, gbcMain);

		/*btnLeft.setBackground(new Color(120,34,210));
		//btnLeft.setBackground(Color.RED);
		btnLeft.setFont(new Font("ComicSans", Font.BOLD, 16));
		add(btnLeft);

		lFirstName.setFont(new Font("ComicSans", Font.BOLD, 16));
		add(lFirstName);

		tfFirstName.setFont(new Font("ComicSans", Font.BOLD, 16));
		add(tfFirstName);*/

        students = Database.getInstance().selectStudent();
        idx = 0;
        fillFields();

    }

    public PanelRegister getPanelRegister() {
        return panelRegister;
    }

    public void fillFields()
    {
        if (students.isEmpty() || students == null)
        {
            tfId.setText("");
            tfFirstName.setText("");
            tfLastName.setText("");
            tfEmail.setText("");
            tfAge.setText("");
            tfStudentAverage.setText("");
        }
        else
        {
            Student s = students.get(idx);
            tfId.setText(String.valueOf(s.getId()));
            tfFirstName.setText(s.getFirstName());
            tfLastName.setText(s.getLastName());
            tfEmail.setText(s.getEmail());
            tfAge.setText(String.valueOf(s.getAge()));
            tfStudentAverage.setText(String.valueOf(s.getStudentAverage()));
        }
    }

    public void fillFieldsAfterInsert()
    {
        students = Database.getInstance().selectStudent();
        if (!students.isEmpty() && students != null)
        {
            idx = students.size() - 1;
            fillFields();
        }
    }

    public void fillFieldsAfterUpdate()
    {
        students = Database.getInstance().selectStudent();
        if (!students.isEmpty() && students != null)
        {
            fillFields();
        }
    }

    public Student getStudentFromFields()
    {
        if (
                tfFirstName.getText().isEmpty() ||
                        tfLastName.getText().isEmpty() ||
                        tfAge.getText().isEmpty() ||
                        tfEmail.getText().isEmpty() ||
                        tfStudentAverage.getText().isEmpty()
                )
        {
            JOptionPane.showMessageDialog(null, "STUDENT CAN'T BE UPDATE!");
            return null;
        }
        return new Student(
                Integer.parseInt(tfId.getText()),
                tfFirstName.getText(),
                tfLastName.getText(),
                Integer.parseInt(tfAge.getText()),
                Double.parseDouble(tfStudentAverage.getText()),
                tfEmail.getText()
        );
    }

    private void generateInsertWindow()
    {
        JFrame frame = new JFrame("UNIVERSITY APP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelStudentInsert panel = new PanelStudentInsert(this);
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocation(590, 270);
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

        frame.pack(); //dopasowuje rozmiar okienka do jego zawartosci

    }

    private void generateUpdateWindow()
    {
        JFrame frame = new JFrame("UNIVERSITY APP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelStudentUpdate panel = new PanelStudentUpdate(this);
        panel.setVisible(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocation(590, 270);
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

        frame.pack(); //dopasowuje rozmiar okienka do jego zawartosci

    }

}

