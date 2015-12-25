package mainFolder;
import objects.Episode;
import objects.MediaObject;
import objects.Season;
import objects.Show;
import exceptions.ContractBrokenException;

/**
 * 
 */

/**
 * @author Mohammad
 *
 */
public interface Addable 
{
	public default boolean add(MediaObject mO) throws ContractBrokenException 
	{
		if (mO instanceof Show)
			return add( (Show)mO);
		else if (mO instanceof Season)
			return add( (Season)mO);
		else if (mO instanceof Episode)
			return add( (Episode)mO);
		else return false;
	}
	/**
	 * use add(MainObject mO) Instead
	 * 
	 * @param show
	 * @return
	 * @throws ContractBrokenException
	 */
	public boolean add(Show show) throws ContractBrokenException;
	public boolean add(Season season) throws ContractBrokenException;
	public boolean add(Episode episode); 

}
