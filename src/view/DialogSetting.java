package view;

import controller.ComboListener;
import controller.OpenListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DialogSetting extends JDialog {

    private String pemPath = null;
    private String osSelected = null;
    private String serverPath = null;

    private final JPanel panel = new JPanel(new GridLayout(0,1));
    private final JButton btnOpen = new JButton("Open Pem File");
    private String[] os = {"Mac", "Windows"};
    private final JComboBox comboOS = new JComboBox(os);
    private final JTextField fieldServer = new JTextField();

    private OpenListener ol = new OpenListener(this);
    private ComboListener cl = new ComboListener(this);

    public DialogSetting(FileConverterUI fcUI) {

        panel.setPreferredSize(new Dimension(500, 200));
        panel.add(new JLabel("Open the pem file currently using"));
        btnOpen.addActionListener(ol);
        panel.add(btnOpen);
        panel.add(new JLabel("Select OS using on this computer: "));
        comboOS.addItemListener(cl);
        panel.add(comboOS);
        panel.add(new JLabel("Server Address: "));
        panel.add(fieldServer);

        int selection = JOptionPane.showConfirmDialog(null, panel, "Upload Settings",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (selection == JOptionPane.OK_OPTION) {
            if (pemPath != null || osSelected != null || !fieldServer.getText().equals("")) {
                if (pemPath == null) {
                    pemPath = fcUI.getPemPath();
                } else {
                    fcUI.setPemPath(pemPath);
                }
                if (osSelected == null) {
                    osSelected = fcUI.getOSSelected();
                } else {
                    fcUI.setOSSelected(osSelected);
                }
                if (fieldServer.getText().equals("")) {
                    serverPath = fcUI.getServerPath();
                } else {
                    serverPath = fieldServer.getText();
                    fcUI.setServerPath(serverPath);
                }
                saveConfig(pemPath, osSelected, serverPath);
            }
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
                pemPath = selectedFile.getAbsolutePath();
            } else {
                JOptionPane.showConfirmDialog(null,
                        "File opened must be in pem format!", "File Format Error",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public void setOsSelected(String osSelected) {
        this.osSelected = osSelected;
    }

    private void saveConfig(String pemPath, String osSelected, String serverPath) {
        try {
            FileWriter writer = new FileWriter(new File("config.txt"));
            writer.append(pemPath);
            writer.append("\n");
            writer.append(osSelected);
            writer.append("\n");
            writer.append(serverPath);
            writer.append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


