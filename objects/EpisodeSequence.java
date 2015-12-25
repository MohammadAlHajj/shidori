package objects;
import java.io.File;

import objects.Episode;
import mainFolder.Addable;


public abstract class EpisodeSequence extends MediaObject implements Addable
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
	
	
	
	public Episode createEpisode()
	{
		return new Episode(size);
	}
}
