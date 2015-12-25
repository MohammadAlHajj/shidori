
package objects;

import java.io.File;
import java.util.ArrayList;

import exceptions.ContractBrokenException;
import mainFolder.ShidoriHelper;

public class Season extends EpisodeSequence
{
	// this list will hold episodes
	protected ArrayList<Episode> episodes;





	public Season(File file)
	{
		super(file);
		episodes = new ArrayList<Episode>();
	}





	public Season(String title)
	{
		super(title);
		episodes = new ArrayList<Episode>();
	}





	public Season(String title, int size)
	{
		super(title, size);
		episodes = new ArrayList<Episode>();
	}





	public ArrayList<Episode> getEpisodes()
	{
		return episodes;
	}





	public boolean contains(Episode episode)
	{
		for (Episode temp : episodes)
		{
			if (temp.equals(episode)) { return true; }
		}
		return false;
	}





	public void update()
	{
		// /file part
		// file is the Root File stored in the MainObjct class
		File[] files = file.listFiles();

		for (int i = 0; i < files.length; i++)
		{
			if (ShidoriHelper.isVideo(files[i]))
			{
				Episode episode = new Episode(files[i]);
				if (!contains(episode))
					add(episode);
			}
		}

		// theoretical part

	}





	/*
	 * (non-Javadoc)
	 * 
	 * @see Addable#add(Show)
	 */
	@Override
	public boolean add(Show show) throws ContractBrokenException
	{
		throw new ContractBrokenException();
	}





	/*
	 * (non-Javadoc)
	 * 
	 * @see Addable#add(Season)
	 */
	@Override
	public boolean add(Season season) throws ContractBrokenException
	{
		throw new ContractBrokenException();
	}





	/*
	 * (non-Javadoc)
	 * 
	 * @see Addable#add(Episode)
	 */
	@Override
	public boolean add(Episode episode)
	{
		if (episodes.contains(episode))
		{
			return false;
		}
		else
		{
			episodes.add(episode);
			return true;
		}
	}

}
