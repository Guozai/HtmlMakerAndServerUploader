package controller;

import view.DialogSetting;
import view.FileConverterUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingListener implements ActionListener {
    private FileConverterUI fcUI;

    public SettingListener(FileConverterUI fcUI) {
        this.fcUI = fcUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new DialogSetting(fcUI);
    }
}
