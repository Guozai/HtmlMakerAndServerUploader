package controller;

import view.FileConverterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAudioListener implements ActionListener {
    private FileConverterUI fcUI;
    public OpenAudioListener (FileConverterUI fcUI) {
        this.fcUI = fcUI;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                fcUI.loadFile(".mp3");
            }
        });
    }
}
