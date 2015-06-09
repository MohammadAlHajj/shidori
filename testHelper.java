import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class testHelper 
{
	public static void main (String[]a) throws IOException
	{
		File f = new File("C:\\Users\\Mohammad\\Desktop\\testDir");
		for (int i = 0;i<100; i++)
		{
			File f1 = new File(f.getAbsolutePath() + "/" + i);
			f1.mkdir();
			
			for (int j = 0;j<100; j++)
			{
				File f2 = new File(f1.getAbsolutePath() + "/" + j);
				f2.mkdir();
				
				for (int k = 0;k<100; k++)
				{
					File f3 = new File(f2.getAbsolutePath() + "/" + k + ".mp4");
					if(!f3.exists())
					{
						f3.createNewFile();
					}
				}
			}
			System.out.println(i);
		}
	}
}
