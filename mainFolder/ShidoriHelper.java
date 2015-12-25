
package mainFolder;

import java.io.File;
import java.util.Arrays;

public final class ShidoriHelper
{
    private static final String[] formats = new String[] { ".3g2", ".3gp",
	    ".asf ", ".avi", ".drc", ".flv", ".flv", ".m4v", ".mkv", ".mng",
	    ".mov", ".mp4", ".m4p", ".m4v", ".mpg", ".mp2", ".mpeg", ".mpe",
	    ".mpv", ".mpg", ".mpeg", ".m2v", ".mxf", ".nsv", ".ogv", ".ogg",
	    ".qt", ".rm", ".rmvb", ".roq", ".svi", ".webm", ".wmv", ".yuv" };





    public static boolean isVideo(File f)
    {
	String s = f.getName();
	String[] temp = s.split("\\.");

	s = "." + temp[temp.length - 1].toLowerCase();

	return Arrays.binarySearch(formats, s) >= 0;
    }
}
