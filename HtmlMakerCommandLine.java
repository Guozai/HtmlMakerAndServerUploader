import java.io.BufferedReader;
import java.io.FileReader;

public class HtmlMakerCommandLine {
    public static void main(String args[]) throws Exception {
        StringBuilder sb = new StringBuilder();

        FileReader fr = new FileReader("lyrics121.txt");
        BufferedReader br = new BufferedReader(fr);

        sb.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n" +
                "  <meta name=\"robots\" content=\"noindex, nofollow\">\n" +
                "  <meta name=\"googlebot\" content=\"noindex, nofollow\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <link href=\"/css/rabbit-lyrics.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                "  <script src=\"js/rabbit-lyrics.js\" type=\"text/javascript\"></script>\n" +
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
                "        <a class=\"navbar-icon-back\" href=\"index.html\">\n" +
                "          <img width=\"30px\" height=\"30px\" src=\"img/navBack.png\">\n" +
                "        </a>\n" +
                "        <a class=\"navbar-item\" href=\"index.html\">Les moments heureux</a>\n" +
                "      </li>\n" +
                "    </ul>\n" +
                "  </nav>\n" +
                "\n" +
                "  <div class=\"rabbit-lyrics\" data-media=\"#audio-121\" data-height=\"400\" data-alignment=\"center\">\n");
        // read line by line
        String line;
        while ((line = br.readLine()) != null) {
            sb.append("      ").append(line).append("\n");
        }
        sb.append("  </div>\n" +
                "  <audio id=\"audio-121\" controls>\n" +
                "      <source src=\"http://ec2-18-191-248-245.us-east-2.compute.amazonaws.com/poems/Poems2019-Yr12-1.mp3\" type=\"audio/mpeg\">\n" +
                "  </audio>\n" +
                "</body>\n" +
                "</html>");

        System.out.println(sb);
    }
}
