package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class DialogUpload {
    private FileConverterUI fcUI;
    private String fileNameOut = null;

    private final JPanel panel = new JPanel(new GridLayout(0,1));

    public DialogUpload(FileConverterUI fcUI) {
        this.fcUI = fcUI;
        fileNameOut = fcUI.getFileNameOut();

        if (fileNameOut == null) {
            JOptionPane.showConfirmDialog(null,
                    "Must convert the poem text first then upload!", "File Upload Error",
                    JOptionPane.DEFAULT_OPTION);
        } else {

            int selection = JOptionPane.showConfirmDialog(null, panel, "Upload File",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (selection == JOptionPane.OK_OPTION) {
                uploadFile();
            }
        }
    }

    public void uploadFile() {
        if (fileNameOut == null) {
            JOptionPane.showConfirmDialog(null,
                    "Must convert the poem text first then upload!", "File Upload Error",
                    JOptionPane.DEFAULT_OPTION);
            return;
        }
        Runtime rt = Runtime.getRuntime();
        Path path = FileSystems.getDefault().getPath(fileNameOut).toAbsolutePath();
        try {
            Process pr = rt.exec("scp -i /Users/ypguo/Documents/bmc.pem " + path + fileNameOut + " ubuntu@ec2-18-191-248-245.us-east-2.compute.amazonaws.com:/var/www/html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
