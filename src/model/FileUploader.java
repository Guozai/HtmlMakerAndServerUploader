package model;

import view.FileConverterUI;

import javax.swing.*;
import java.io.*;

public class FileUploader {
    private String htmlOut;
    private String mp3Out;
    private String pemPath;
    private String serverPath;

    public FileUploader(FileConverterUI fcUI) {
        if (fcUI.getHtmlName() != null) {
            htmlOut = fcUI.getHtmlName();
        } else {
            htmlOut = fcUI.getFileNameOut();
        }
        mp3Out = fcUI.getMp3Name();
        pemPath = fcUI.getPemPath();
        serverPath = fcUI.getServerPath();
        uploadFile();
    }

    public void uploadFile() {
        if (htmlOut == null && mp3Out == null) {
            JOptionPane.showConfirmDialog(null,
                    "Must at least open html or audio file to upload!", "File Upload Error",
                    JOptionPane.DEFAULT_OPTION);
            return;
        }

        if (htmlOut != null) {
            try {
                Process pr = Runtime.getRuntime().exec("scp -i " + pemPath + " poems/" + htmlOut
                        + " ubuntu@" + serverPath + ":/var/www/bmc2019.com/html/poems");

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                int exitVal = pr.waitFor();
                if (exitVal == 0) {
                    JOptionPane.showConfirmDialog(null,
                            "Html is successfully uploaded!", "Upload Success",
                            JOptionPane.DEFAULT_OPTION);
                    System.out.println(output);
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Html Upload Fail!", "Upload Fail",
                            JOptionPane.DEFAULT_OPTION);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (mp3Out != null) {
            try {
                Process pr = Runtime.getRuntime().exec("scp -i " + pemPath + " audios/" + mp3Out
                        + " ubuntu@" + serverPath + ":/var/www/bmc2019.com/html/audios");

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                int exitVal = pr.waitFor();
                if (exitVal == 0) {
                    JOptionPane.showConfirmDialog(null,
                            "Audio is successfully uploaded!", "Upload Success",
                            JOptionPane.DEFAULT_OPTION);
                    System.out.println(output);
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Audio Upload Fail!", "Upload Fail",
                            JOptionPane.DEFAULT_OPTION);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
