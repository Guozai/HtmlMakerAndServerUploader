package controller;

import model.FileUploader;
import view.FileConverterUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadListener implements ActionListener {
    private FileConverterUI fcUI;

    public UploadListener(FileConverterUI fcUI) {
        this.fcUI = fcUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FileUploader(fcUI);
    }
}
