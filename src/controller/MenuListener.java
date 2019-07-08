package controller;

import model.FileUploader;
import view.DialogSetting;
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
                    fcUI.loadFile(".txt");
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
        if (actionCommand.equals("Open Html")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fcUI.loadFile(".html");
                }
            });
        }
        if (actionCommand.equals("Open Audio")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fcUI.loadFile(".mp3");
                }
            });
        }
        if (actionCommand.equals("Upload")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new FileUploader(fcUI);
                }
            });
        }
        if (actionCommand.equals("Upload Settings")) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new DialogSetting(fcUI);
                }
            });
        }
        if (actionCommand.equals("Exit")) {
            System.exit(0);
        }
    }
}
