import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author piyushghai on {4/12/18}
 */

public class EnglishCombiner {

    static String folder_path = "/Users/piyushghai/Documents/git-repos/InuktitutToRoman/data/";

    public static void main(String[] args) throws IOException {
        String eng_verse_file = "eng_verses/";

        List<String> filePaths = Utils.getFileList(folder_path + eng_verse_file);
        filePaths.remove(".DS_Store");
        List<String> inukString = new ArrayList<>();
        int count = 0;
        for (String file : filePaths) {
            Reader reader = new InputStreamReader(new FileInputStream(folder_path + eng_verse_file + file), Charset.defaultCharset());
            Reader bufferReader = new BufferedReader(reader);
            String st;
            while ((st = ((BufferedReader) bufferReader).readLine()) != null) {
                st = st.replaceAll("[\\t\\n\\r]+", "");
                inukString.add(st);
                count++;
            }
        }
        System.out.println("count : " + count + " files: " + filePaths.size());
        String outputFileInuk = "eng_original";
        BufferedWriter writer = new BufferedWriter(new FileWriter(folder_path + outputFileInuk, true));

        for (String str : inukString) {
            String mod = str.replaceAll("\\d", "");
            writer.append(mod);
            writer.append('\n');
        }

        writer.close();
    }


}
