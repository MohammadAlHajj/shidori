
package objects;

import java.io.File;

public abstract class MediaObject
{
    protected File file; // the video file (null if it doesn't exist)
    protected String title; // title of the episode
    protected boolean exists; // the MediaObject actually exists as a file





    // needed for the subclasses to be able to cleanly init file and/or title
    // instance variables
    protected MediaObject(File file)
    {
	this.file = file;
	this.title = file.getName();
	exists = true;
    }





    protected MediaObject(String title)
    {
	this.title = title;
	exists = false;
    }





    public String getName()
    {
	return title;
    }





    public void setName(String name)
    {
	this.title = name;
    }





    public File getFile()
    {
	return file;
    }





    public void setFile(File file)
    {
	this.file = file;
    }





    public boolean exists()
    {
	return exists;
    }





    public boolean equals(MediaObject mO)
    {
	boolean equals = true;
	if (!this.getName().equals(mO.getName()))
	{
	    equals = false;
	}
	if (!this.getClass().equals(mO.getClass()))
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
