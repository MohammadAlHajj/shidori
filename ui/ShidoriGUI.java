
package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTree;

import objects.Episode;
import objects.MediaObject;
import objects.Season;
import objects.Show;
import mainFolder.AddComponent;
import mainFolder.Shidori;
import mainFolder.TypeHolderEnum;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ShidoriGUI extends JFrame
{
    private JPanel contentPane;
    private JTree tree;
    private Shidori shidori;
    private JList<Episode> episodeList;
    private DefaultListModel<Episode> listModel;





    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
	EventQueue.invokeLater(new Runnable() {
	    public void run()
	    {
		try
		{
		    ShidoriGUI frame = new ShidoriGUI();
		    frame.setVisible(true);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	});
    }





    /**
     * Create the frame.
     */
    public ShidoriGUI()
    {
	// set the application to the default look and feel of the system
	try
	{
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
	catch (ClassNotFoundException | InstantiationException
		| IllegalAccessException | UnsupportedLookAndFeelException e2)
	{
	    // TODO Auto-generated catch block
	    e2.printStackTrace();
	}

	// Setting up JTree : happens in Shidori class
	try
	{
	    shidori = new Shidori();
	}
	catch (FileNotFoundException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	tree = shidori.getTree();

	// Set up some contentPane characteristics
	{
	    this.setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 1230, 738);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	}

	// -----JList and list model-----
	// listModel will be the container of all the episodes to be presented
	// in the JList (cause: JList elements can't be removed after being
	// added)
	listModel = new DefaultListModel<Episode>();
	episodeList = new JList<Episode>(listModel);

	// this listener will check if there was a double click;
	// if there was a double click, try to open the file using the JAVA
	// desktop; else do nothing
	episodeList.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e)
	    {
		// if double click
		if (e.getClickCount() == 2)
		{
		    // if the desktop of this system is not supported
		    if (!Desktop.isDesktopSupported())
			return;

		    // get system desktop
		    Desktop desktop = Desktop.getDesktop();

		    // if the "Open" action for this desktop is not supported
		    if (!desktop.isSupported(Desktop.Action.OPEN))
			return;

		    try
		    {
			desktop.open(listModel.get(
				episodeList.getSelectedIndex()).getFile());
		    }
		    catch (IOException e1)
		    {
			// TODO
			e1.printStackTrace();
		    }
		}
	    }
	});

	// listScrallable will be the container for the episodeList JList to
	// enable scrolling when the JList is visually full
	JScrollPane listScrollable = new JScrollPane(episodeList);
	listScrollable.setBounds(442, 31, 407, 651);
	episodeList.ensureIndexIsVisible(episodeList.getSelectedIndex());
	contentPane.add(listScrollable);

	// -----Menu Section-----
	JMenuBar menuBar = new JMenuBar();
	JMenu mnFile = new JMenu("File");
	JMenuItem mntmQuit = new JMenuItem("Quit");
	JMenu mnTools = new JMenu("Tools");
	JMenuItem mntmOptions = new JMenuItem("Options");
	{
	    // Menu location
	    menuBar.setBounds(0, 0, 1224, 21);

	    // Menu hierarchy
	    contentPane.add(menuBar);
	    menuBar.add(mnFile);
	    mnFile.add(mntmQuit);
	    menuBar.add(mnTools);
	    mnTools.add(mntmOptions);

	    // menu action listeners
	    mntmOptions.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{

		}
	    });
	}

	// -----Buttons-----

	JButton btnRefresh = new JButton("Refresh");
	JButton btnAddShow = new JButton("Add Show");
	JButton btnAddSeason = new JButton("Add Season");
	JButton btnAddEpisode = new JButton("Add Episode");
	{
	    // Button location
	    btnAddSeason.setBounds(1094, 65, 115, 23);
	    btnAddEpisode.setBounds(1094, 99, 115, 23);
	    btnAddShow.setBounds(1094, 31, 115, 23);
	    btnRefresh.setBounds(1094, 167, 115, 23);

	    // Button Container
	    contentPane.add(btnAddSeason);
	    contentPane.add(btnAddEpisode);
	    contentPane.add(btnAddShow);
	    contentPane.add(btnRefresh);

	    // Button Action listeners
	    btnAddShow.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0)
		{
		    AddComponent aC =
			    shidori.addComponentMethod(TypeHolderEnum.Show);
		}
	    });
	    btnAddEpisode.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0)
		{
		    AddComponent aC =
			    shidori.addComponentMethod(TypeHolderEnum.Episode);
		}
	    });
	    btnAddSeason.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0)
		{
		    AddComponent aC =
			    shidori.addComponentMethod(TypeHolderEnum.Season);
		}
	    });
	}

	// -----JTree Setup-----

	// Listen for when the selection changes and update the tree
	// accordingly
	// tree.addMouseListener(new MouseAdapter() {

	// TODO fix this bullshit
	// episodeList.addMouseListener(new MouseAdapter() {
	// @Override
	// public void mouseEntered(MouseEvent me)
	// {
	// episodeList.setToolTipText(null);
	// episodeList = (JList<Episode>) me.getSource();
	// int index = episodeList.locationToIndex(me.getPoint());
	// if (index > -1)
	// {
	// String text = listModel.getElementAt(index).toString();
	// episodeList.setToolTipText(text);
	// }
	// }
	// });
	episodeList.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseMoved(MouseEvent me)
	    {
		episodeList.setToolTipText(null);
		episodeList = (JList<Episode>) me.getSource();
		int index = episodeList.locationToIndex(me.getPoint());
		if (index > -1)
		{
		    String text = listModel.getElementAt(index).toString();
		    episodeList.setToolTipText(text);
		}
	    }

	    // TimerTask task = new TimerTask() {
	    //
	    // @Override
	    // public void run() {
	    // SwingUtilities.invokeLater(new Runnable() {
	    //
	    // @Override
	    // public void run() {
	    //
	    // }
	    // });
	    // }
	    // };
	});

	// -----JTree Setup-----
	tree.setBounds(20, 32, 407, 650);
	contentPane.add(tree);

	// create a selection listener for the jTree "tree"
	tree.addTreeSelectionListener(new TreeSelectionListener() {
	    /**
	     * refresh episodeList and fill it with the episodes found in the
	     * current selection in the tree
	     */
	    public void valueChanged(TreeSelectionEvent e)
	    {
		// get the selected node in the jTree "tree"
		DefaultMutableTreeNode node =
			(DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		// if nothing is selected, do nothing and return
		if (node == null)
		    return;

		// retrieve the Object inside the selected node
		Object nodeInfo = node.getUserObject();

		// if nodeInfo is a show
		if (nodeInfo instanceof Show)
		{
		    // clear the list
		    listModel.clear();

		    Show show = (Show) nodeInfo;
		    show.update();

		    // update the tree
		    shidori.populateTreeAfterInitialisation();

		    // add the episodes to the JList episodeList
		    for (Episode episode : show.getIndipentEpisodes()
			    .getEpisodes())
		    {
			listModel.addElement((Episode) episode);
		    }
		}
		else if (nodeInfo instanceof Season)
		{
		    listModel.clear();
		    Season season = (Season) nodeInfo;
		    for (MediaObject episode : season.getEpisodes())
			listModel.addElement((Episode) episode);
		}
	    }
	});

	// -----Scroll pane for the tree-----
	JScrollPane treeScrollable = new JScrollPane(tree);
	treeScrollable.setBounds(20, 32, 407, 650);
	contentPane.add(treeScrollable);
    }
}
