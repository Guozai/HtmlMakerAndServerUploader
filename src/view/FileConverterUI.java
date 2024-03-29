package view;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Paths;

public class FileConverterUI extends JFrame {
    private File fileIn = null;
    private File htmlIn = null;
    private File mp3In = null;
    private String fileName = null;
    private String fileNameOut = null;
    private String pemPath = "/Users/ypguo/Documents/bmc.pem";
    private String serverPath = "ec2-18-191-248-245.us-east-2.compute.amazonaws.com";

    private final JMenuBar mb = new JMenuBar();
    private final JMenu f = new JMenu("File");
    private final JMenuItem[] fItems = {
            new JMenuItem("Load", KeyEvent.VK_L),
            new JMenuItem("Convert", KeyEvent.VK_C),
            new JMenuItem("Open Html", KeyEvent.VK_H),
            new JMenuItem("Open Audio", KeyEvent.VK_A),
            new JMenuItem("Upload", KeyEvent.VK_U),
            new JMenuItem("Upload Settings", KeyEvent.VK_S),
            new JMenuItem("Exit", KeyEvent.VK_X)
    };

    private final JLabel fileLabel = new JLabel("Please load the text file then convert...");
    private final JButton btnLoad = new JButton("Load Text File");
    private final JButton btnConvert = new JButton("Convert to HTML");
    private final JButton btnUpload = new JButton("Upload to Server");
    private final JButton btnSetting = new JButton("Upload Settings");
    private final JLabel openLabel = new JLabel("Please load file to upload...");
    private final JButton btnOpenHtml = new JButton("Open Html File");
    private final JButton btnOpenAudio = new JButton("Open Audio File");

    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JPanel converterPanel = new JPanel();
    private final JPanel filePanel = new JPanel();
    private final JPanel loadPanel = new JPanel();
    private final JPanel convertPanel = new JPanel();
    private final JPanel uploaderPanel = new JPanel();
    private final JPanel uploadPanel = new JPanel();
    private final JPanel openPanel = new JPanel();
    private final JPanel openHtmlPanel = new JPanel();
    private final JPanel openAudioPanel = new JPanel();
    private final JPanel uploadInnerPanel = new JPanel();
    private final JPanel settingPanel = new JPanel();
    private final JPanel settingInnerPanel = new JPanel();

    private MenuListener ml = new MenuListener(this);
    private LoadListener ll = new LoadListener(this);
    private ConvertListener cl = new ConvertListener(this);
    private UploadListener ul = new UploadListener(this);
    private SettingListener sl = new SettingListener(this);
    private OpenHtmlListener ohl = new OpenHtmlListener(this);
    private OpenAudioListener oal = new OpenAudioListener(this);

    public FileConverterUI() {
        loadConfig();

        // add pull-down menu
        for (int i = 0; i < fItems.length; i++) {
            fItems[i].addActionListener(ml);
        }
        f.add(fItems[0]);
        f.add(fItems[1]);
        f.add(new JSeparator());
        f.add(fItems[2]);
        f.add(fItems[3]);
        f.add(fItems[4]);
        f.add(fItems[5]);
        f.add(new JSeparator());
        f.add(fItems[6]);
        fItems[6].setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
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
        converterPanel.setLayout(new BoxLayout(converterPanel, BoxLayout.Y_AXIS));
        converterPanel.setPreferredSize(new Dimension(200, 150));
        converterPanel.add(filePanel);
        converterPanel.add(loadPanel);
        converterPanel.add(convertPanel);
        openPanel.setLayout(new BoxLayout(openPanel, BoxLayout.X_AXIS));
        openPanel.setPreferredSize(new Dimension(200, 50));
        openLabel.setHorizontalAlignment(SwingConstants.CENTER);
        openPanel.add(openLabel);
        openHtmlPanel.setLayout(new BoxLayout(openHtmlPanel, BoxLayout.X_AXIS));
        openHtmlPanel.setPreferredSize(new Dimension(200,50));
        btnOpenHtml.addActionListener(ohl);
        btnOpenHtml.setHorizontalAlignment(SwingConstants.CENTER);
        openHtmlPanel.add(btnOpenHtml);
        openAudioPanel.setLayout(new BoxLayout(openAudioPanel, BoxLayout.X_AXIS));
        openAudioPanel.setPreferredSize(new Dimension(200,50));
        btnOpenAudio.addActionListener(oal);
        btnOpenAudio.setHorizontalAlignment(SwingConstants.CENTER);
        openAudioPanel.add(btnOpenAudio);
        uploadInnerPanel.setLayout(new BoxLayout(uploadInnerPanel, BoxLayout.X_AXIS));
        uploadInnerPanel.setPreferredSize(new Dimension(200,50));
        btnUpload.addActionListener(ul);
        btnUpload.setHorizontalAlignment(SwingConstants.CENTER);
        uploadInnerPanel.add(btnUpload);
        uploadPanel.setBorder(BorderFactory.createTitledBorder("File Uploader"));
        uploadPanel.setLayout(new BoxLayout(uploadPanel, BoxLayout.Y_AXIS));
        uploadPanel.setPreferredSize(new Dimension(200, 70));
        uploadPanel.add(openPanel);
        uploadPanel.add(openHtmlPanel);
        uploadPanel.add(openAudioPanel);
        uploadPanel.add(uploadInnerPanel);
        settingInnerPanel.setLayout(new BoxLayout(settingInnerPanel, BoxLayout.X_AXIS));
        settingInnerPanel.setPreferredSize(new Dimension(200,70));
        btnSetting.addActionListener(sl);
        btnSetting.setHorizontalAlignment(SwingConstants.CENTER);
        settingInnerPanel.add(btnSetting);
        settingPanel.setBorder(BorderFactory.createTitledBorder("Upload Settings"));
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        settingPanel.setPreferredSize(new Dimension(200, 70));
        settingPanel.add(settingInnerPanel);
        uploaderPanel.setLayout(new BoxLayout(uploaderPanel, BoxLayout.Y_AXIS));
        uploaderPanel.setPreferredSize(new Dimension(200, 150));
        uploaderPanel.add(uploadPanel);
        uploaderPanel.add(settingPanel);
        tabbedPane.addTab("Html Converter", converterPanel);
        tabbedPane.addTab("File Uploader", uploaderPanel);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
    public static void main(String[] args) { run(new FileConverterUI(), 300, 400); }

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

    public void setFileLabel(String fileNameIn, String fileExtension) {
        if (fileExtension.equals(".txt")) {
            fileLabel.setText(fileNameIn + " is loaded.");
        } else {
            openLabel.setText(fileNameIn + " is loaded.");
        }
    }

    public void setFile(File fileIn, String fileExtension) {
        if (fileExtension.equals(".txt")) {
            this.fileIn = fileIn;
        } else if (fileExtension.equals(".html")) {
            this.htmlIn = fileIn;
        } else if (fileExtension.equals(".mp3")) {
            this.mp3In = fileIn;
        }
    }

    public String getHtmlName() {
        if (htmlIn != null) {
            return htmlIn.getName();
        } else {
            return null;
        }
    }

    public String getMp3Name() {
        if (mp3In != null) {
            return mp3In.getName();
        } else {
            return null;
        }
    }

    public String getPemPath() { return pemPath; }

    public void setPemPath(String pemPath) { this.pemPath = pemPath; }

    public String getServerPath() { return serverPath; }

    public void setServerPath(String serverPath) { this.serverPath = serverPath; }

    public void loadFile(String fileExtenstion) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            if (selectedFile.getName().toLowerCase().endsWith(fileExtenstion)) {
                this.setFileLabel(selectedFile.getName(), fileExtenstion);
                this.setFile(selectedFile, fileExtenstion);
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
            FileWriter writer = new FileWriter(new File("poems/" + fileNameOut));
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
                "File is successfully converted to " + fileNameOut + "!", "Convertion Success",
                JOptionPane.DEFAULT_OPTION);
    }

    public String getFileNameOut() {
        return fileNameOut;
    }

    private void loadConfig() {
        String file = "config.txt";
        if (Paths.get(file).toFile().isFile()) {
            try (FileReader fr = new FileReader("config.txt")) {
                BufferedReader br = new BufferedReader(fr);

                // read line by line
                setPemPath(br.readLine());
                setServerPath(br.readLine());
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }
}
