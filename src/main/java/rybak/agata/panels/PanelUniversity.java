package rybak.agata.panels;

import rybak.agata.classes.University;
import rybak.agata.database.Database;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelUniversity extends JPanel {
    private JButton btnLeft = new JButton("<<<");
    private JButton btnRight = new JButton(">>>");
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");

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

    private JTextField tfId = new JTextField(5);

    private List<University> universities;
    private int idx;

    private PanelRegister panelRegister;

    public PanelUniversity(PanelRegister panelRegister) {
        super(new GridBagLayout());
        this.panelRegister = panelRegister;
        GridBagConstraints gbcMain = new GridBagConstraints();

        //-------------------------------------------------------------------------------
        //----------------------------PANEL NAVIGATION-----------------------------------
        //-------------------------------------------------------------------------------

        JPanel panelNavigation = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelNavigation = new GridBagConstraints();

        gbcPanelNavigation.gridx = 0;
        gbcPanelNavigation.gridy = 0;
        btnLeft.addActionListener(e -> {
            --idx;
            if (idx < 0)
            {
                idx = universities.size() - 1;
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
            ++idx;
            if (idx >= universities.size())
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
                "UNIVERSITIES FORM",
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

        gbcPanelOperations.gridx = 0;
        gbcPanelOperations.gridy = 0;

        btnInsert.addActionListener(e -> {generateInsertWindow();});
        panelOperations.add(btnInsert, gbcPanelOperations);

        gbcPanelOperations.gridx = 1;
        gbcPanelOperations.gridy = 0;
        btnDelete.addActionListener(e -> {
            if (!tfId.getText().isEmpty())
            {
                Database.getInstance().deleteUniversity(Integer.parseInt(tfId.getText()));
                universities = Database.getInstance().selectUniversity();
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

        panelNavigation.setBackground(new Color(250, 200, 200));
        panelFields.setBackground(new Color(250, 200, 200));
        panelOperations.setBackground(new Color(250, 200, 200));
        setBackground(new Color(250, 200, 200));

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

        universities = Database.getInstance().selectUniversity();
        idx = 0;
        fillFields();
    }

    public PanelRegister getPanelRegister() {
        return panelRegister;
    }

    public void fillFields()
    {
        if (universities.isEmpty() || universities == null)
        {
            tfId.setText("");
            tfName.setText("");
            tfCity.setText("");
            tfSetYear.setText("");
            tfEmail.setText("");
            tfAddress.setText("");
        }
        else
        {
            University u = universities.get(idx);
            tfId.setText(String.valueOf(u.getId()));
            tfName.setText(u.getName());
            tfCity.setText(u.getCity());
            tfSetYear.setText(String.valueOf(u.getSetYear()));
            tfEmail.setText(u.getEmail());
            tfAddress.setText(u.getAddress());
        }

    }

    public void fillFieldsAfterInsert()
    {
        universities = Database.getInstance().selectUniversity();
        if (!universities.isEmpty() && universities != null)
        {
            idx = universities.size() - 1;
            fillFields();
        }
    }

    public void fillFieldsAfterUpdate()
    {
        universities = Database.getInstance().selectUniversity();
        if (!universities.isEmpty() && universities != null)
        {
            fillFields();
        }
    }

    public University getUniversityFromFields()
    {
        if (
                tfName.getText().isEmpty() ||
                        tfCity.getText().isEmpty() ||
                        tfSetYear.getText().isEmpty() ||
                        tfEmail.getText().isEmpty() ||
                        tfAddress.getText().isEmpty()
                )
        {
            JOptionPane.showMessageDialog(null, "UNIVERSITY CAN'T BE UPDATE!");
            return null;
        }
        return new University(
                Integer.parseInt(tfId.getText()),
                tfName.getText(),
                tfCity.getText(),
                Integer.parseInt(tfSetYear.getText()),
                tfEmail.getText(),
                tfAddress.getText()
        );
    }

    private void generateInsertWindow()
    {
        JFrame frame = new JFrame("UNIVERSITY APP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelUniversityInsert panel = new PanelUniversityInsert(this);
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

        frame.pack();

    }

    private void generateUpdateWindow()
    {
        JFrame frame = new JFrame("UNIVERSITY APP");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelUniversityUpdate panel = new PanelUniversityUpdate(this);
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

        frame.pack();
    }

}

