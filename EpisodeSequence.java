import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;


public class EpisodeSequence extends MainObject
{
	private int size;
	
	
	
	protected EpisodeSequence(File file)
	{
		super(file);
	}


	protected EpisodeSequence(String title)
	{
		super(title);
		this.size = 0; 
	}
	protected EpisodeSequence(String title, int size)
	{
		super(title);
		this.size = size;
	}

	public int getSize() 
	{
		return size;
	}
}
