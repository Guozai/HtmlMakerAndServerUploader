package view;

import controller.ConvertListener;
import controller.LoadListener;
import controller.MenuListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class FileConverterUI extends JFrame {
    private File fileIn = null;
    private String fileName = null;
    private String fileNameOut = null;

    private final JMenuBar mb = new JMenuBar();
    private final JMenu f = new JMenu("File");
    private final JMenuItem[] fItems = {
            new JMenuItem("Load", KeyEvent.VK_L),
            new JMenuItem("Convert", KeyEvent.VK_C),
            new JMenuItem("Exit", KeyEvent.VK_X)
    };

    private final JLabel fileLabel = new JLabel();
    private final JButton btnLoad = new JButton("Load Text File");
    private final JButton btnConvert = new JButton("Convert to HTML");

    private final JPanel centerPanel = new JPanel();
    private final JPanel converterPanel = new JPanel();
    private final JPanel filePanel = new JPanel();
    private final JPanel loadPanel = new JPanel();
    private final JPanel convertPanel = new JPanel();

    private MenuListener ml = new MenuListener(this);
    private LoadListener ll = new LoadListener(this);
    private ConvertListener cl = new ConvertListener(this);

    public FileConverterUI() {

        // add pull-down menu
        for (int i = 0; i < fItems.length; i++) {
            //fItems[i].addActionListener();
            f.add(fItems[i]);
        }
        fItems[2].setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
        f.setMnemonic(KeyEvent.VK_F);
        mb.add(f);
        setJMenuBar(mb);

        filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));
        loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.X_AXIS));
        convertPanel.setLayout(new BoxLayout(convertPanel, BoxLayout.X_AXIS));
        filePanel.setPreferredSize(new Dimension(200, 50));
        loadPanel.setPreferredSize(new Dimension(200, 50));
        loadPanel.setPreferredSize(new Dimension(200, 50));
        fileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        filePanel.add(fileLabel);
        btnLoad.addActionListener(ll);
        btnLoad.setHorizontalAlignment(SwingConstants.CENTER);
        loadPanel.add(btnLoad);
        btnConvert.addActionListener(cl);
        btnConvert.setHorizontalAlignment(SwingConstants.CENTER);
        convertPanel.add(btnConvert);
        converterPanel.setBorder(BorderFactory.createTitledBorder("File Converter"));
        converterPanel.setLayout(new BoxLayout(converterPanel, BoxLayout.Y_AXIS));
        converterPanel.setPreferredSize(new Dimension(200, 150));
        converterPanel.add(filePanel);
        converterPanel.add(loadPanel);
        converterPanel.add(convertPanel);
        centerPanel.setLayout(new FlowLayout());
        centerPanel.add(converterPanel);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) { run(new FileConverterUI(), 400, 600); }

    public static void run(final JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                f.setTitle("File Converter - from txt to html");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
            }
        });
    }

    public void setFileLabel(String fileNameIn) {
        fileLabel.setText(fileNameIn + " is loaded.");
    }

    public void setFile(File fileIn) {
        this.fileIn = fileIn;
    }

    public void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (selectedFile.getName().toLowerCase().endsWith(".txt")) {
                this.setFileLabel(selectedFile.getName());
                this.setFile(selectedFile);
            } else {
                JOptionPane.showConfirmDialog(null,
                        "File loaded must be txt format!", "File Format Error",
                        JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public void readFile(StringBuilder sb) {
        try (FileReader fr = new FileReader(fileIn)) {
            BufferedReader br = new BufferedReader(fr);

            sb.append("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "  <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n" +
                    "  <meta name=\"robots\" content=\"noindex, nofollow\">\n" +
                    "  <meta name=\"googlebot\" content=\"noindex, nofollow\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "  <link href=\"../css/rabbit-lyrics.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                    "  <script src=\"../js/rabbit-lyrics.js\" type=\"text/javascript\"></script>\n" +
                    "  <title>Berthe Mouchette Competition 2019</title>\n" +
                    "  <meta charset=\"utf-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
                    "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>\n" +
                    "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n" +
                    "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n" +
                    "  <style>\n" +
                    "  .navbar-item {\n" +
                    "    color: white;\n" +
                    "    align: left;\n" +
                    "  }\n" +
                    "  .navbar-item:hover {\n" +
                    "    color: white;\n" +
                    "  }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <nav class=\"navbar navbar-expand-sm navbar-light\" style=\"background-color: #2b5faa;\">\n" +
                    "    <ul class=\"navbar-nav\">\n" +
                    "      <li class=\"nav-item\">\n" +
                    "        <a class=\"navbar-icon-back\" href=\"../index.html\">\n" +
                    "          <img width=\"30px\" height=\"30px\" src=\"../img/navBack.png\">\n" +
                    "        </a>\n" +
                    "        <a class=\"navbar-item\" href=\"../index.html\">" + fileName + "</a>\n" +
                    "      </li>\n" +
                    "    </ul>\n" +
                    "  </nav>\n" +
                    "\n" +
                    "  <div class=\"rabbit-lyrics\" data-media=\"#audio-121\" data-height=\"400\" data-alignment=\"center\">\n");
            sb.append("      ");
            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            sb.append("  </div>\n" +
                    "  <audio id=\"audio-121\" controls>\n" +
                    "      <source src=\"http://ec2-18-191-248-245.us-east-2.compute.amazonaws.com/audios/" + fileName + ".mp3\" type=\"audio/mpeg\">\n" +
                    "  </audio>\n" +
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        //System.out.println(sb);
    }

    private void getFileName() {
        String fileNameIn = fileIn.getName();
        try {
            fileName = fileNameIn.substring(0, fileNameIn.indexOf("."));
        } catch (Exception e) {
            fileName = null;
            e.printStackTrace();
        }
    }

    private void setFileNameOut() {
        fileNameOut = fileName + ".html";
    }

    public void writeFile(StringBuilder sb) {
        try {
            FileWriter writer = new FileWriter(new File(fileNameOut));
            writer.append(sb);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void convertFile() {
        StringBuilder sb = new StringBuilder();
        getFileName();
        readFile(sb);
        setFileNameOut();
        writeFile(sb);
        JOptionPane.showConfirmDialog(null,
                "File is successfully converted to " + fileNameOut + "!", "Convertion Successful",
                JOptionPane.OK_CANCEL_OPTION);
    }
}
