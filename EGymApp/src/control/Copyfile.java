package control;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is used to copy files to specific location.
 * @author Jiaxuan Wang
 */
public class Copyfile {
    /**
     * This method writes the JSONArray into the json file.
     * @param srcPath source path
     * @param destPath destination path
     * @return Nothing.
     * @see IOException
     */
    public static void copyFile(String srcPath, String destPath) throws IOException {


        FileInputStream fis = new FileInputStream(srcPath);

        FileOutputStream fos = new FileOutputStream(destPath);


        int len = 0;

        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }


        fos.close();
        fis.close();

    }
}
