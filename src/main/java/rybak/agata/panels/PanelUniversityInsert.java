package rybak.agata.panels;

import rybak.agata.classes.University;
import rybak.agata.database.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelUniversityInsert extends JPanel {
    private JButton btnInsert = new JButton("INSERT");
    private JButton btnCancel = new JButton("CANCEL");

    private JLabel lName = new JLabel("Name");
    private JLabel lCity = new JLabel("City");
    private JLabel lSetYear = new JLabel("Set Year");
    private JLabel lEmail = new JLabel("Email");
    private JLabel lAddress = new JLabel("Address");

    private JTextField tfName = new JTextField(15);
    private JTextField tfCity = new JTextField(15);
    private JTextField tfSetYear = new JTextField(15);
    private JTextField tfEmail = new JTextField(15);
    private JTextField tfAddress = new JTextField(15);

    private PanelUniversity pu;

    public PanelUniversityInsert(PanelUniversity pu)
    {
        super(new GridBagLayout());
        this.pu = pu;
        GridBagConstraints gbcInsert = new GridBagConstraints();

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL FIELDS------------------------------------
        //-------------------------------------------------------------------------------

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "INSERT UNIVERSITY",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lName, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(tfName, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 1;
        panelFields.add(lCity, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 1;
        panelFields.add(tfCity, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lSetYear, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(tfSetYear, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lEmail, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(tfEmail, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 4;
        panelFields.add(lAddress, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfAddress, gbcPanelFields);

        //-------------------------------------------------------------------------------
        //-------------------------------PANEL OPERATIONS--------------------------------
        //-------------------------------------------------------------------------------

        JPanel panelOperations = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelOperations = new GridBagConstraints();

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnInsert, gbcPanelOperations);

        gbcPanelOperations.gridx = 2;
        gbcPanelOperations.gridy = 0;
        panelOperations.add(btnCancel, gbcPanelOperations);

        panelFields.setBackground(new Color(250, 200, 200));
        panelOperations.setBackground(new Color(250, 200, 200));
        setBackground(new Color(250, 200, 200));

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
                    tfName.getText().isEmpty() ||
                            tfCity.getText().isEmpty() ||
                            tfSetYear.getText().isEmpty() ||
                            tfEmail.getText().isEmpty() ||
                            tfAddress.getText().isEmpty()
                    )
            {
                JOptionPane.showMessageDialog(null, "FILL ALL FIELDS TO INSERT NEW STUDENT!");
                return;
            }
            University u = new University(
                    0,
                    tfName.getText(),
                    tfCity.getText(),
                    Integer.parseInt(tfSetYear.getText()),
                    tfEmail.getText(),
                    tfAddress.getText()
            );
            Database.getInstance().insertUniversity(u);
            pu.fillFieldsAfterInsert();

            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();

        });

        btnCancel.addActionListener(e -> {
            JFrame frame = (JFrame)getRootPane().getParent();
            frame.dispose();
        });
    }

}

