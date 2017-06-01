package rybak.agata.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by asus on 2017-06-01.
 */
public class PanelMain extends JPanel {

    private PanelStudent panelStudent;
    private PanelUniversity panelUniversity;
    private PanelRegister panelRegister;
    private PanelStatistics panelStatistics;
    private PanelLogin panelLogin;

    private final static String PANEL_STUDENT = "PANEL_STUDENT";
    private final static String PANEL_UNIVERSITY = "PANEL_UNIVERSITY";
    private final static String PANEL_REGISTER = "PANEL_REGISTER";
    private final static String PANEL_STATISTICS = "PANEL_STATISTICS";
    private final static String PANEL_LOGIN = "PANEL_LOGIN";

    public PanelMain()
    {
        super(new CardLayout());

        panelRegister = new PanelRegister();
        panelStudent = new PanelStudent(panelRegister);
        panelUniversity = new PanelUniversity(panelRegister);
        panelStatistics = new PanelStatistics();
        panelLogin = new PanelLogin();

        add(panelStudent, PANEL_STUDENT);
        add(panelUniversity, PANEL_UNIVERSITY);
        add(panelRegister, PANEL_REGISTER);
        add(panelStatistics, PANEL_STATISTICS);
        add(panelLogin, PANEL_LOGIN);

    }

    public JMenuBar createMenu()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuPanels = new JMenu("PANELS");

        JMenuItem studentItem = new JMenuItem("STUDENT");
        studentItem.addActionListener(e -> {
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this, PANEL_STUDENT);
        });

        JMenuItem universityItem = new JMenuItem("UNIVERSITY");
        universityItem.addActionListener(e -> {
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this, PANEL_UNIVERSITY);
        });

        JMenuItem registerItem = new JMenuItem("REGISTER");
        registerItem.addActionListener(e -> {
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this, PANEL_REGISTER);
        });

        JMenuItem statisticsItem = new JMenuItem("STATISTICS");
        statisticsItem.addActionListener(e -> {
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this, PANEL_STATISTICS);
        });

        JMenuItem loginItem = new JMenuItem("LOGIN");
        loginItem.addActionListener(e -> {
            CardLayout cl = (CardLayout)getLayout();
            cl.show(this, PANEL_LOGIN);
        });

        menuPanels.add(studentItem);
        menuPanels.add(universityItem);
        menuPanels.add(registerItem);
        menuPanels.add(statisticsItem);
        menuPanels.add(loginItem);

        menuBar.add(menuPanels);

        return menuBar;
    }
}
