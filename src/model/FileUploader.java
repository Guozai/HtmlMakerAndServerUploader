package model;

import view.FileConverterUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUploader {
    private String fileNameOut;

    public FileUploader(FileConverterUI fcUI) {
        fileNameOut = fcUI.getFileNameOut();
        uploadFile();
    }

    public void uploadFile() {
        if (fileNameOut == null) {
            JOptionPane.showConfirmDialog(null,
                    "Must convert the poem text first then upload!", "File Upload Error",
                    JOptionPane.DEFAULT_OPTION);
            return;
        }

        //Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
        //System.out.println(path);

        try {
            Process pr = Runtime.getRuntime().exec("scp -i /Users/ypguo/Documents/bmc.pem " + fileNameOut + " ubuntu@ec2-18-191-248-245.us-east-2.compute.amazonaws.com:/var/www/html");

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = pr.waitFor();
            if (exitVal == 0) {
                JOptionPane.showConfirmDialog(null,
                        "File is successfully uploaded!", "Upload Success",
                        JOptionPane.DEFAULT_OPTION);
                System.out.println(output);
            } else {
                JOptionPane.showConfirmDialog(null,
                        "File Upload Fail!", "Upload Fail",
                        JOptionPane.DEFAULT_OPTION);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
