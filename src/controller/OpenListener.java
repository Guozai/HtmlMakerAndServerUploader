package controller;

import view.DialogSetting;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenListener implements ActionListener {
    private DialogSetting dsUI;
    public OpenListener (DialogSetting dsUI) {
        this.dsUI = dsUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dsUI.loadFile();
            }
        });
    }
}