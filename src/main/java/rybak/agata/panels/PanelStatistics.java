package rybak.agata.panels;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelStatistics extends JPanel {

    private JLabel lA = new JLabel("Number of students studying in a given city: ");
    private JLabel lB = new JLabel("The oldest student in a given year:");
    private JLabel lC = new JLabel("Average grades of students at a given university:");
    private JLabel lD = new JLabel("Student with the best average:");
    private JLabel lE = new JLabel("The oldest university:");

    private JComboBox<String> cA = new JComboBox<>();
    private JComboBox<String> cB = new JComboBox<>();
    private JComboBox<String> cC = new JComboBox<>();
    private JTextField tfD = new JTextField(15);
    private JTextField tfE = new JTextField(15);

    public PanelStatistics()
    {
        super(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();

        JPanel panelFields = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanelFields = new GridBagConstraints();

        panelFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2, true),
                "STATISTICS",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", Font.ITALIC, 16),
                Color.BLACK
        ));

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 0;
        panelFields.add(lA, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 0;
        panelFields.add(cA, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 2;
        panelFields.add(lB, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 2;
        panelFields.add(cB, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 3;
        panelFields.add(lC, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 3;
        panelFields.add(cC, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 4;
        panelFields.add(lD, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 4;
        panelFields.add(tfD, gbcPanelFields);

        gbcPanelFields.gridx = 0;
        gbcPanelFields.gridy = 5;
        panelFields.add(lE, gbcPanelFields);

        gbcPanelFields.gridx = 1;
        gbcPanelFields.gridy = 5;
        panelFields.add(tfE, gbcPanelFields);


        panelFields.setBackground(new Color(230, 180, 250));
        setBackground(new Color(230, 180, 250));

        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        add(panelFields, gbcMain);

    }

}

