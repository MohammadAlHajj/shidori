import java.io.File;


public class MainObject 
{
	protected File file;			//the video file (null if it doesn't exist)
	protected String title;			//title of the episode
	protected boolean exists;		//the file actually exists as a video
	
	protected MainObject(File file)	{
		this.file = file;
		this.title = file.getName();
		exists = true;
	}
	
	protected MainObject(String title)	{
		this.title = title;
		exists = false;
	}
	
	public String getName() {
		return title;
	}

	
	public void setName(String name) {
		this.title = name;
	}

	
	public File getFile() {
		return file;
	}

	
	public void setFile(File file) {
		this.file = file;
	}

	
	public boolean exists() {
		return exists;
	}
	
	public boolean equals(MainObject mO)
	{
		boolean equals = true;
		if( !this.getName().equals( mO.getName() )  )
		{
			equals = false;
		}
		if( !this.getClass().equals( mO.getClass() ) )
		{
			equals = false;
		}
		return equals;
	}
	
	public String toString()
	{
		return title;
	}
}
