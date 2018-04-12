import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author piyushghai on {4/12/18}
 */

public class Utils {

    public static List<String> getFileList(String directoryPath) {
        List<String> results = new ArrayList<String>();


        File[] files = new File(directoryPath).listFiles();

        if (files == null)
            return results;
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }

        return results;
    }
}
