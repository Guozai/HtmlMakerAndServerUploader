package controller;

import view.FileConverterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {
    private FileConverterUI fcUI;
    public LoadListener (FileConverterUI fcUI) {
        this.fcUI = fcUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                fcUI.loadFile();
            }
        });
    }
}
