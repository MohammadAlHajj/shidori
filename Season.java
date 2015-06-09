import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;


public class Season extends EpisodeSequence
{
	//this list will hold Seasons and episodes
	protected ArrayList<Episode> episodes;		 

	public Season(File file)	{
		super(file);
		episodes = new ArrayList<Episode>();
	}
	public Season(String title)	{
		super(title);
		episodes = new ArrayList<Episode>();
	}
	public Season(String title, int size)	{
		super(title, size);
		episodes = new ArrayList<Episode>();
	}





	public ArrayList<Episode> getEpisodes(){
		return episodes;
	}






	public boolean addEpisode(Episode episode) throws FileAlreadyExists
	{
		if( episodes.contains(episode) )
		{
			throw new FileAlreadyExists();
		}
		else
		{
			episodes.add(episode);
			return true;
		}
	}
	public boolean addEpisode()
	{
		Episode episode = new Episode(episodes.size()+1);
		episodes.add(episode);
		return true;
	}




	public boolean contains(Episode episode)
	{
		for (Episode temp : episodes)
		{
			if( temp.equals(episode))
			{
				return true;
			}
		}
		return false;
	}




	public void update() 
	{
		///file part
		//file is the Root File stored in the MainObjct class
		File[] files = file.listFiles();			

		for(int i=0;i<files.length;i++)
		{
			if (ShidoriHelper.isVideo(files[i]) )
			{
				Episode episode = new Episode(files[i]); 
				if(!contains(episode))
				{
					try {
						addEpisode(episode);
					}
					catch (FileAlreadyExists e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		//theoretical part

	}

}
