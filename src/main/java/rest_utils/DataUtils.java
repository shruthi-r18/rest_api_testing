package rest_utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author shrut
 *
 */

public class DataUtils {
	
	/**
	 * @param sFilepath
	 * @return
	 * @throws IOException
	 */
	public static String readJsonFilePathToString(String sFilepath) throws IOException {
		byte[] data =Files.readAllBytes(Paths.get(sFilepath));
		return new String(data,StandardCharsets.UTF_8);
	}
	
	
	

}
