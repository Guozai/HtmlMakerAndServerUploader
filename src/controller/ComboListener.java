package controller;

import view.DialogSetting;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboListener implements ItemListener {
    private DialogSetting dsUI;

    public ComboListener(DialogSetting dsUI) {
        this.dsUI = dsUI;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            dsUI.setOsSelected((String)e.getItem());
        }
    }
}
