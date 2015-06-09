import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class Shidori 
{
	private ArrayList<Show> list;
	private JTree tree;
	private DefaultMutableTreeNode rootNode;
	DefaultTreeModel treeModel;

	
	/**
	 * scans the files in the root director(y/ies) and add them to the show list
	 * creates a Jtree "tree"
	 * takes the created show list and places it in the tree
	 *  
	 * @throws FileAlreadyExists
	 * @throws FileNotFoundException
	 */
	public Shidori() throws FileAlreadyExists, FileNotFoundException
	{
		long startTime = System.currentTimeMillis();
		scanFiles();
		setUpTree();
		populateTree();
		populateTreeAfterInitialisation();
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
	}



	//scan the files in the root director(y/ies) and add them to the show list 
	private void scanFiles() throws FileAlreadyExists, FileNotFoundException
	{
		// list will hold the shows before adding them to the tree
		list = new ArrayList<Show>();

		// File file = new File("C:\\Users\\Mohammad\\Desktop\\testDir");
		File file = new File("E:\\Test folder");
		// check if the file exists
		if(!file.exists())
			throw new FileNotFoundException();

		// get all subfiles
		File[] files = file.listFiles();

		// for all subfiles, if they are directories, add them to the list
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isDirectory())	
			{
				Show s = new Show(files[i]);
				list.add(s);

				/*
				 *  the below line has been commented out for just in time 
				 *refresh of the tree since it was taking 10+ seconds to load 
				 *1 million files and 10 thousand directories
				 */
				//	s.update();
			}
		}
	}



	/**setup a new JTree and returns it's root node
	 * 
	 * @return the root node of a newly generated root node
	 */
	private DefaultMutableTreeNode setUpTree()
	{
		//setting up the tree model (this is useful for updating the tree)
		rootNode = new DefaultMutableTreeNode("Anime");
		treeModel = new DefaultTreeModel(rootNode);
		treeModel.addTreeModelListener(new MyTreeModelListener());

		tree = new JTree(treeModel);
		tree.setShowsRootHandles(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.
				SINGLE_TREE_SELECTION);

		return rootNode;
	}

	/**
	 * gets all shows from the list and adds them to the tree
	 */
	public void populateTree()
	{
		//rootNode.removeAllChildren();			//this removes all nodes
		//treeModel.reload();  //this notifies the listeners and changes the GUI

		for(Show show : list)
		{
			DefaultMutableTreeNode showNode = new DefaultMutableTreeNode(show);
			treeModel.insertNodeInto(showNode, rootNode,
					rootNode.getChildCount());
		}
		for (int i = 0; i < tree.getRowCount(); i++) 
		{
			tree.expandRow(i);
		}




		//		//Listen for when the selection changes.
		//		tree.addTreeSelectionListener(new TreeSelectionListener() 
		//		{
		//			public void valueChanged(TreeSelectionEvent e) {
		//				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		//		tree.getLastSelectedPathComponent();
		//
		//				/* if nothing is selected */ 
		//				if (node == null) return;
		//
		//				/* retrieve the node that was selected */ 
		//				Object nodeInfo = node.getUserObject();
		//
		//				/* React to the node selection. */
		//
		//			}
		//		});




		//		for(int li=0;li < myPathList.size();li++)
		//		{
		//			tree.expandPath((TreePath)myPathList.get(li));
		//		} 
	}
	
	/**
	 * loop over all shows in the tree and add all their shows to the tree
	 */
	public void populateTreeAfterInitialisation()
	{
		//rootNode.removeAllChildren();			//this removes all nodes
		//treeModel.reload();  //this notifies the listeners and changes the GUI

		int childCount = treeModel.getChildCount(rootNode);
		for(int i =0; i< childCount; i++)
		{
			DefaultMutableTreeNode showNode = (DefaultMutableTreeNode)
					(DefaultMutableTreeNode)(treeModel.getChild(rootNode, i));

			Show show = (Show) showNode.getUserObject();

			if (showNode.getChildCount() == 0)
			{
				for(Season season : show.getSeasons())
				{
					DefaultMutableTreeNode seasonNode = 
							new DefaultMutableTreeNode(season);

					treeModel.insertNodeInto(seasonNode, showNode,
							showNode.getChildCount());
				}
			}
		}	
	}




	public JTree getTree(){
		return tree;
	}

	public void addShow(Show s){
		list.add(s);
	}


	/**
	 * Returns the parent show (might be self) or null if no Show was found
	 * 
	 * @return the parent show (might be self) or null if no Show was found
	 */
	public Show getShow()
	{
		//get all ancestors of the current node
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree
				.getLastSelectedPathComponent();
		Object [] o =  node.getUserObjectPath();

		//find a show in the ancestors and store it
		Show s = null;
		for(int i=0;i<o.length;i++)
			if(o[i] instanceof Show)		
				s = (Show)o[i];

		return s;
	}
	/**
	 * Returns the parent season (might be self) or null if no Season was found
	 * 
	 * @return the parent season (might be self) or null if no Season was found
	 */
	public Season getSeason()
	{
		//get all ancestors of the current node (including itself)
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree
				.getLastSelectedPathComponent();
		Object [] o =  node.getUserObjectPath();

		//find a season in the ancestors and store it
		Season s = null;
		for(int i=0;i<o.length;i++)
			if(o[i] instanceof Season)		
				s = (Season)o[i];

		return s;
	}







	/**
	 * creates an AddComponent instance that takes care of adding the new 
	 * element depending on the TypeHonlderEnum passed
	 * 
	 * @param typeToAdd
	 * @return the JFrame child AddComponent that was created
	 * @throws Exception
	 */
	public AddComponent addComponentMethod(TypeHolderEnum typeToAdd) 
	{
		Show show = null;
		Season season = null;

		if(typeToAdd == TypeHolderEnum.Show)
		{
			// do nothing : no changes needed
		}
		else if(typeToAdd == TypeHolderEnum.Season)
		{
			show = getShow();
			season = null;
		}
		else if(typeToAdd == TypeHolderEnum.Episode)
		{
			show = getShow();
			season = getSeason();
		}
		else System.out.println("Unsupported TypeHOlderEnum at "
				+ "Shidoti.AddComponent");

		AddComponent aC = new AddComponent(typeToAdd, show, season, this);
		return aC;
	}
}
