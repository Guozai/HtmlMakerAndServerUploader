package view;

import controller.OpenListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DialogSetting extends JDialog {
    //private FileConverterUI fcUI;
    private File pemFile = null;

    private final JPanel panel = new JPanel(new GridLayout(0,1));
    private final JButton btnOpen = new JButton("Open Pem File");
    private final JTextField fieldServer = new JTextField();

    private OpenListener ol = new OpenListener(this);

    public DialogSetting(FileConverterUI fcUI) {
        //this.fcUI = fcUI;

        panel.setPreferredSize(new Dimension(500, 100));
        btnOpen.addActionListener(ol);
        panel.add(btnOpen);
        panel.add(new JLabel("Server Address: "));
        panel.add(fieldServer);

        int selection = JOptionPane.showConfirmDialog(null, panel, "Upload Settings",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (selection == JOptionPane.OK_OPTION) {

        }
    }

    public void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(btnOpen.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (selectedFile.getName().toLowerCase().endsWith(".pem")) {
                pemFile = selectedFile;
            } else {
                JOptionPane.showConfirmDialog(null,
                        "File opened must be in pem format!", "File Format Error",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }
}


