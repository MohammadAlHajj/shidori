
package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;

import mainFolder.ArrayListComboBoxModel;

/**
 * 
 */

/**
 * @author Mohammad
 *
 */
public class OptionsGUI extends JFrame
{
    private int numberOfGenres = 0;
    private int numberOfRoots = 0;
    private ArrayList<String> genres; // holds the genres
    private ArrayList<ArrayList<GridBagConstraints>> genreRows; // below
    // Holds the GridBagConstraints of the genrePanel (layout is GridBagLayout).
    // each element of this arrayList is an arrayList of its own and consists of
    // 2 elements:
    // the JTextButton's GridBagConstraint, and the JButton's GridBagConstraint
    // these elements are needed for the correct redraw after multiple add and
    // remove events
    private TreeMap<String, String> genreRoots; // holds the genres
    private ArrayList<ArrayList<GridBagConstraints>> rootRows; // below
    // same as genreRows but for the "rootPanel" not "genrePanel"

    private final JFileChooser fc = new JFileChooser();

    private JPanel contentPane;
    private JTextField textField;





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
		    OptionsGUI frame = new OptionsGUI();
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
    public OptionsGUI()
    {
	// setup instance variables
	genres = new ArrayList<String>();
	genreRows = new ArrayList<ArrayList<GridBagConstraints>>();
	genreRoots = new TreeMap<String, String>();
	rootRows = new ArrayList<ArrayList<GridBagConstraints>>();

	// content pane
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 507, 535);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	// -----Genre Creation-----
	JPanel genrePanel = new JPanel();
	genrePanel.setBounds(1, 1, 469, 192);

	JScrollPane scrollPane = new JScrollPane(genrePanel);
	scrollPane.setBounds(12, 16, 458, 194);
	contentPane.add(scrollPane);

	GridBagLayout gbl_panelGenre = new GridBagLayout();
	gbl_panelGenre.columnWidths = new int[] { 133, 0 };
	gbl_panelGenre.rowHeights = new int[] { 20, 0 };
	gbl_panelGenre.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
	gbl_panelGenre.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	genrePanel.setLayout(gbl_panelGenre);

	JLabel lblGenres = new JLabel("Genres");
	gridBagHelper(lblGenres, 0, 0, null, genrePanel);

	JButton buttonAddGenre = new JButton("+");
	gridBagHelper(buttonAddGenre, 1, 0, null, genrePanel);
	buttonAddGenre.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e)
	    {
		numberOfGenres++;

		ArrayList<GridBagConstraints> rowHolder =
			new ArrayList<GridBagConstraints>();

		JTextField genreTextField = new JTextField();
		gridBagHelper(genreTextField, 0, numberOfGenres, rowHolder,
			genrePanel);
		genreTextField.setText(Integer.toString(numberOfGenres));
		genreTextField.setColumns(10);
		genreTextField.addFocusListener(new FocusAdapter() {
		    @Override
		    /*
		     * if the genreTextField is not a whitespace string, or if
		     * it is not already added to genres, add it to genres
		     */
		    public void focusLost(FocusEvent e)
		    {
			if (genres.contains(genreTextField.getText()))
			    ;
			// do nothing
			else if (genreTextField.getText().matches("\\s*"))
			    System.out.println("wtf");
			else
			    genres.add(genreTextField.getText());
		    }
		});

		JButton btnRemoveGenre = new JButton("-");
		gridBagHelper(btnRemoveGenre, 1, numberOfGenres, rowHolder,
			genrePanel);
		btnRemoveGenre.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
			genrePanel.remove(genreTextField);
			genrePanel.remove(btnRemoveGenre);

			genreRows.remove(rowHolder);
			genres.remove(genreTextField.getText());

			for (ArrayList<GridBagConstraints> row : genreRows)
			    for (GridBagConstraints gbc : row)
				if (gbc.gridy > genreRows.indexOf(rowHolder)+ 1)
				    gbc.gridy--;

			// numberOfGenres--;
			revalidate();
			repaint();
		    }
		});

		genreRows.add(rowHolder);

		revalidate();
		repaint();
	    }
	});

	// -----Root Folder Location section-----
	JPanel rootsPanel = new JPanel();
	rootsPanel.setBounds(10, 223, 460, 192);

	JScrollPane rootFoldersSP = new JScrollPane(rootsPanel);
	rootFoldersSP.setBounds(10, 223, 460, 192);
	contentPane.add(rootFoldersSP);

	GridBagLayout gbl_RootsPanel = new GridBagLayout();
	gbl_RootsPanel.columnWidths = new int[] { 133, 0 };
	gbl_RootsPanel.rowHeights = new int[] { 20, 0 };
	gbl_RootsPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
	gbl_RootsPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	rootsPanel.setLayout(gbl_RootsPanel);

	JLabel lblGerreRootFolders = new JLabel("Genre Root Folders");
	gridBagHelper(lblGerreRootFolders, 0, 0, null, rootsPanel);

	refreshRootFolderPart();

	ArrayListComboBoxModel model = new ArrayListComboBoxModel(genres);

	JButton buttonAddRoot = new JButton("+");
	gridBagHelper(buttonAddRoot, 1, 0, null, rootsPanel);

	buttonAddRoot.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e)
	    {
		numberOfRoots++;

		ArrayList<GridBagConstraints> rowHolder =
			new ArrayList<GridBagConstraints>();

		JTextField rootTextField = new JTextField();
		gridBagHelper(rootTextField, 0, numberOfRoots, rowHolder,
			rootsPanel);
		rootTextField.setText(Integer.toString(numberOfRoots));
		rootTextField.setColumns(10);
		rootTextField.addFocusListener(new FocusAdapter() {
		    @Override
		    /*
		     * if the genreTextField is not a whitespace string, or if
		     * it is not already added to genres, add it to genres
		     */
		    public void focusLost(FocusEvent e)
		    {
			if (genreRoots.keySet().contains(
				rootTextField.getText()))
			    ;
			// do nothing
			else if (rootTextField.getText().matches("\\s*"))
			    System.out.println("wtf");
			else
			    genreRoots.put(rootTextField.getText(), "value");
		    }
		});

		JComboBox<String> genreComboBox = new JComboBox<String>();
		gridBagHelper(genreComboBox, 1, numberOfRoots, rowHolder,
			rootsPanel);
		genreComboBox.setModel(model);

		JButton btnBrowse = new JButton("...");
		gridBagHelper(btnBrowse, 2, numberOfRoots, rowHolder,
			rootsPanel);

		JButton btnRemoveRoot = new JButton("-");
		gridBagHelper(btnRemoveRoot, 3, numberOfRoots, rowHolder,
			rootsPanel);
		btnRemoveRoot.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
			genrePanel.remove(rootTextField);
			genrePanel.remove(btnRemoveRoot);

			rootRows.remove(rowHolder);
			genreRoots.remove(rootTextField.getText());

			for (ArrayList<GridBagConstraints> row : rootRows)
			    for (GridBagConstraints gbc : row)
				if (gbc.gridy > rootRows.indexOf(rowHolder) + 1)
				    gbc.gridy--;

			// numberOfGenres--;
			revalidate();
			repaint();
		    }
		});

		rootRows.add(rowHolder);

		revalidate();
		repaint();
	    }

	});

	JButton btnTest = new JButton("test");
	btnTest.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e)
	    {
		System.out.println(genres.toString());
	    }
	});
	btnTest.setBounds(12, 446, 115, 29);
	contentPane.add(btnTest);
    }





    /**
     * this is a helper method that will take a JComponent and create a
     * GridBagConstraints using the specified inputs and add it to the JPanel
     * param "panel". It will also add the newly created GridBagConstraints to
     * rowHolder if rowHolder is specified
     * 
     * @param component
     *            JComponent to be added to the panel
     * @param gridx
     *            x-coord where the component is to be added to the panel
     * @param gridy
     *            y-coord where the component is to be added to the panel
     * @param rowHolder
     *            Arraylist that holds the GridBagConstraints in the current row
     * @param panel
     *            Jpanel where the component is to be added
     */
    private void gridBagHelper(JComponent component, int gridx, int gridy,
	    ArrayList<GridBagConstraints> rowHolder, JPanel panel)
    {
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets = new Insets(0, 0, 5, 5);
	gbc.gridx = gridx;
	gbc.gridy = gridy;
	// rowHolder is null for static unchanging components like some labels
	if (rowHolder != null)
	    rowHolder.add(gbc);

	panel.add(component, gbc);
    }





    private String setupBrowseButton()
    {

	// show only directories
	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	// Show it.
	int returnVal = fc.showDialog(this, "Attach");

	// Process the results.
	if (returnVal == JFileChooser.APPROVE_OPTION)
	    return fc.getSelectedFile().getAbsolutePath();
	else
	    return null;
    }





    private void refreshRootFolderPart()
    {
	for (String s : genres)
	{

	}
    }
}
