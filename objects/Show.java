
package objects;

import java.io.File;
import java.util.ArrayList;

import mainFolder.ShidoriHelper;
import exceptions.ContractBrokenException;


public class Show extends EpisodeSequence
{
    private ArrayList<Season> seasons; // list of episodes
    private Season indepentEpisodes; // below
    // abstract (only a container) season that holds independent (seasonless)
    // episodes
    private int currentEpisode; // the current episode

    // constructors and their helper methods
    public Show(File root)
    {
	super(root);
	initialise();
    }

    public Show(String title)
    {
	super(title);
	initialise();
    }

    public Show(String title, int size)
    {
	super(title, size);
	initialise();
    }

    private void initialise()
    {
	seasons = new ArrayList<Season>();
	indepentEpisodes = new Season("-1");
    }

    // getters
    public Season getIndipentEpisodes()
    {
	return indepentEpisodes;
    }

    public ArrayList<Season> getSeasons()
    {
	return seasons;
    }

    public int getSeasonNumber()
    {
	return seasons.size();
    }

    public int getCurrentEpisode()
    {
	return currentEpisode;
    }

    // setters
    public void setCurrentEpisode(int currentEpisode)
    {
	this.currentEpisode = currentEpisode;
    }

    public boolean contains(Season season)
    {
	for (Season temp : seasons)
	    if (temp.equals(season))
		return true;

	return false;
    }

    public void update()
    {
	// -----file part-----

	// file is the Root File stored in the MainObjct class
	File[] files = file.listFiles();

	// loop over all files in the root directory
	for (int i = 0; i < files.length; i++)
	{
	    // if the sub files are directories
	    if (files[i].isDirectory())
	    {
		Season season = new Season(files[i]);
		// they are not already added: add them as seasons
		if (!this.contains(season))
		{
		    add(season);
		    season.update();
		}
	    }

	    // else if they are not directories and they are video files:
	    // add them as independent episodes
	    else if (!files[i].isDirectory() && ShidoriHelper.isVideo(files[i]))
	    {
		Episode episode = new Episode(files[i]);
		// and they have not been already added:
		if (!indepentEpisodes.contains(episode))
		{
		    indepentEpisodes.add(episode);
		}
	    }
	}

	// -----file unavailable part-----
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

    /**
     * @see Addable#add(Season)
     * 
     * @param season
     *            to add
     * 
     *            adds the param "season" to the ArrayList seasons if it doesn't
     *            already exist and returns true, otherwise returns false
     */
    @Override
    public boolean add(Season season)
    {
	if (seasons.contains(season))
	    return false;

	else
	{
	    seasons.add(season);
	    return true;
	}
    }

    /**
     * adds the param "episode" to "indipendentEpisodes" if it doesn't already
     * exist and returns true, otherwise returns false
     * 
     * @see Addable#add(Episode)
     * 
     * @param episode
     *            to add
     * 
     * 
     */
    @Override
    public boolean add(Episode episode)
    {
	return indepentEpisodes.add(episode);
    }
}
