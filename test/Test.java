package test;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;

import static java.nio.file.StandardWatchEventKinds.*;


public class Test 
{
	public static void main (String [] args) throws IOException
	{
		WatchService watcher = FileSystems.getDefault().newWatchService();

		ArrayList<Path> directories = new ArrayList<Path>();
		directories.add(Paths.get("C:\\Desktop\\Mohammad\\Videos\\Anime "
				+ "- Cartoon\\Anime"));
		directories.add(Paths.get("H:/Anime - Cartoon - Series/Anime"));

		WatchKey key;
		try {
			for(Path dir : directories)
			{
				System.out.println("Root: " + dir);
				key = dir.register(watcher,
						ENTRY_CREATE,
						ENTRY_DELETE,
						ENTRY_MODIFY);
			}
		} catch (IOException x) {
			System.err.println(x);
		}



		while (true)
		{
			// wait for key to be signaled
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			for (WatchEvent<?> event: key.pollEvents()) 
			{
				WatchEvent.Kind<?> kind = event.kind();

				// This key is registered only for ENTRY_CREATE events,
				// but an OVERFLOW event can occur regardless if events
				// are lost or discarded.
				if (kind == OVERFLOW) 
				{
					continue;
				}

				// The filename is the context of the event.
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
				Path filename = ev.context();
				System.out.println(filename.toAbsolutePath());

				if(kind == ENTRY_CREATE| kind == ENTRY_DELETE|
						kind ==ENTRY_MODIFY)
				{
					System.out.println(filename.toAbsolutePath() + " " + kind 
							+ " " + filename.toAbsolutePath().getNameCount());
				}


				// Reset the key -- this step is critical if you want to
				// receive further watch events.  If the key is no longer valid,
				// the directory is inaccessible so exit the loop.
				boolean valid = key.reset();
				if (!valid) 
				{
					break;
				}
			}
		}
	}
}
