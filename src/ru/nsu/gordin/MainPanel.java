package ru.nsu.gordin;

import ru.nsu.gordin.controller.*;
import ru.nsu.gordin.controller.actions.*;
import ru.nsu.gordin.view.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel{
    protected Action aboutAction, exitAction;

    public MainPanel() {
        super(new BorderLayout());
    }

    public void initActions(DrawPanel panel) {
        aboutAction = new AboutAction("About",
                createToolIcon("info_icon&16"),
                "About lab1",
                new Integer(KeyEvent.VK_F1), panel);
        exitAction = new ExitAction("Exit",
                createToolIcon("on-off_icon&16"),
                "Quit the application",
                new Integer(KeyEvent.VK_E));
    }

    protected static ImageIcon createToolIcon(String imageName) {
        String imgLocation = "/res/icon/"
                + imageName
                + ".png";
        java.net.URL imageURL = MainPanel.class.getResource(imgLocation);
        System.out.println();
        if (imageURL == null) {
//            System.err.println("Resource not found: "
//                    + imgLocation);
            return null;
        } else {
            return new ImageIcon(imageURL);
        }
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createHelpMenu());
        return menuBar;
    }

    public JMenu createFileMenu() {
        JMenuItem menuItem = null;
        JMenu fileMenu = new JMenu("File");

        Action[] actions = {exitAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null); //arbitrarily chose not to use icon
            fileMenu.add(menuItem);
        }

        return fileMenu;
    }

    public JMenu createHelpMenu() {
        JMenuItem menuItem = null;
        JMenu helpMenu = new JMenu("Help");

        Action[] actions = {aboutAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null);
            helpMenu.add(menuItem);
        }

        return helpMenu;
    }

    public void createToolBar() {
        JButton button = null;

        ToolBar toolBar = new ToolBar();
        add(toolBar, BorderLayout.PAGE_START);

        Action[] enabledActions = {aboutAction, exitAction};

        for(int i = 0; i < enabledActions.length; i++){
            button = new JButton(enabledActions[i]);
            if (button.getIcon() != null) {
                button.setText("");
            }
            toolBar.add(button);
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("lab #4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel demo = new MainPanel();
        DrawPanel drawPanel = new DrawPanel(250, 250);
        drawPanel.init();
        frame.addKeyListener(drawPanel);
        demo.initActions(drawPanel);
        frame.setJMenuBar(demo.createMenuBar());
//        demo.createToolBar();
        demo.add(drawPanel);
        demo.setOpaque(true);
        frame.setContentPane(demo);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}