import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;


public class Show extends EpisodeSequence
{
	private ArrayList<Season> seasons;		// list of episodes
	private Season indepentEpisodes;		// below
	//abstract (only a container) season that holds independent (seasonless) 
	//episodes
	private int currentEpisode;				// the current episode




	//constructors and their helper methods
	public Show(File root)	{
		super(root);
		initialise();
	}
	public Show(String title)	{
		super(title);
		initialise();
	}
	public Show(String title, int size)	{
		super(title, size);
		initialise();
	}	
	private void initialise(){
		seasons = new ArrayList<Season>();
		indepentEpisodes = new Season("-1");
	}




	//getters
	public Season getIndipentEpisodes(){
		return indepentEpisodes;
	}

	public ArrayList<Season> getSeasons(){
		return seasons;
	}

	public int getSeasonNumber(){
		return seasons.size();
	}

	public int getCurrentEpisode() 	{
		return currentEpisode;
	}




	//setters
	public void setCurrentEpisode(int currentEpisode)	{
		this.currentEpisode = currentEpisode;
	}





	//adds the param seasons to the ArrayList seasons if it doesn't already 
	//exist; throws FileAlreadyExists exception otherwise
	public boolean addSeason(Season season) throws FileAlreadyExists	
	{
		if (seasons.contains(season) )
		{
			throw new FileAlreadyExists();
		}
		else
		{
			seasons.add(season);
			return true;
		}
	}





	public boolean addEpisode(Episode episode) throws FileAlreadyExists	
	{
		indepentEpisodes.addEpisode(episode);
		return true;
	}
	public boolean addEpisode()	
	{
		indepentEpisodes.addEpisode();
		return true;
	}




	public boolean contains(Season season)
	{
		for (Season temp : seasons)
		{
			if( temp.equals(season))
			{
				return true;
			}
		}
		return false;
	}



	public void update() throws FileAlreadyExists
	{
		//     -----file part-----

		// file is the Root File stored in the MainObjct class
		File[] files = file.listFiles();	

		// loop over all files in the root directory
		for(int i=0;i<files.length;i++)
		{
			// if the sub files are directories
			if(files[i].isDirectory())	
			{
				Season season = new Season(files[i]);
				//they are not already added: add them as seasons
				if(!this.contains(season))
				{
					addSeason(season);
					season.update();
				}
			}

			// else if they are not directories and 
			// they are video files:
			// 		add them as independent episodes
			else if ( !files[i].isDirectory() && 
					ShidoriHelper.isVideo(files[i]) )
			{
				Episode episode = new Episode(files[i]);
				// and they have not been already added: 
				if(!indepentEpisodes.contains(episode))
				{
					indepentEpisodes.addEpisode(episode);
				}
			}
		}

		//-----file unavailable part-----
	}
}


