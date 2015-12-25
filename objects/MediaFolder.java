/**
 * 
 */

package objects;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Mohammad
 *
 */
public class MediaFolder extends MediaObject
{
	protected ArrayList<MediaObject> contentList;





	protected MediaFolder(File file)
	{
		super(file);
		// TODO Auto-generated constructor stub
	}





	public boolean contains(MediaObject mObject)
	{
		for (MediaObject temp : contentList)
		{
			if (temp.equals(mObject))
				return true;
		}
		return false;
	}





	public boolean add(MediaFolder mFolder)
	{
		if (contentList.contains(mFolder))
		{
			return false;
		}
		else
		{
			contentList.add(mFolder);
			return true;
		}
	}
	public boolean add(Show show)
	{
		if (contentList.contains(show))
		{
			return false;
		}
		else
		{
			contentList.add(show);
			return true;
		}
	}





	public ArrayList<MediaObject> getContentList()
	{
		return contentList;
	}





	public void setContentList(ArrayList<MediaObject> contentList)
	{
		this.contentList = contentList;
	}
}
