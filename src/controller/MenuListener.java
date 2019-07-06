package controller;

import view.FileConverterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {
    private FileConverterUI fcUI;
    public MenuListener (FileConverterUI fcUI) {
        this.fcUI = fcUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem target = (JMenuItem)e.getSource();
        String actionCommand = target.getActionCommand();
        if (actionCommand.equals("Load")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fcUI.loadFile();
                }
            });
        }
        if (actionCommand.equals("Convert")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fcUI.convertFile();
                }
            });
        }
        if (actionCommand.equals("Exit")) {
            System.exit(0);
        }
    }
}
