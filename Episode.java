import java.io.File;

/**
 * Container that holds the information specific to an episode
 * 
 * @author Mohammad
 *
 * 
 */
public class Episode extends MainObject
{


	//Constructor for an episode with only the title
	public Episode(String title)	{
		super(title);
	}
	//Constructor for an episode with a file
	public Episode(File file)	{
		super(file);
	}
	//Constructor for an episode with neither a title or file 
	public Episode(int a)	{
		super( Integer.toString(a) );
	}

	
	
}
