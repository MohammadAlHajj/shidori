package mainFolder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTextField;

import objects.Episode;
import objects.MediaObject;
import objects.Season;
import objects.Show;
import exceptions.ContractBrokenException;


public class AddComponent extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAddTitle;	//input location for the title
	private JTextField textFieldAddFile;	//input location for the file path

	// radio buttons
	// specify the file type (if any) and the naming method to be used
	private JRadioButton rdbtnAddTitle;
	private JRadioButton rdbtnAddFile;
	private JRadioButton rdbtnAddAutomatically;

	private Shidori shidori;

	private String source;	// the file path (as string) of the file to add
	private String title;// the user entered string to represent the added title 
	private TypeHolderEnum sentEnum;	//the type of the MainObject to add

	public AddComponent(TypeHolderEnum sentEnum, Show sentShow, 
			Season sentSeason, Shidori shidori) 
	{
		// JFrame characteristics 
		{
			this.setResizable(false);
			this.setVisible(true);
		}

		// update instance variables
		{
			this.sentEnum = sentEnum;
			this.shidori = shidori;
		}

		// Content pane characteristics
		{
			setBounds(100, 100, 447, 334);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
		}


		// Layout characteristics
		final JLayeredPane layeredPane = new JLayeredPane();
		{
			layeredPane.setBounds(10, 11, 411, 195);
			contentPane.add(layeredPane);
		}


		/*panels to switch between depending on the selected radio button
		 note: add automatically doesn't take any input so it doesn't need
		 a panel
		 */
		final JPanel panelAddTitle = new JPanel();
		final JPanel panelAddFile = new JPanel();
		{
			panelAddTitle.setBounds(0, 0, 411, 195);
			panelAddFile.setBounds(0, 0, 411, 195);

			layeredPane.add(panelAddTitle);
			layeredPane.add(panelAddFile);

			panelAddTitle.setLayout(null);
			panelAddFile.setLayout(null);

			layeredPane.setLayer(panelAddTitle, JLayeredPane.DEFAULT_LAYER);
			layeredPane.setLayer(panelAddFile, JLayeredPane.PALETTE_LAYER);
		}



		//input location for the title
		textFieldAddTitle = new JTextField();
		{
			textFieldAddTitle.setBounds(66, 8, 335, 20);
			panelAddTitle.add(textFieldAddTitle);
			textFieldAddTitle.setColumns(10);
		}


		// title label
		JLabel lblTitle = new JLabel("Title");
		{
			lblTitle.setBounds(10, 11, 46, 14);
			panelAddTitle.add(lblTitle);
		}




		// file location label
		JLabel lblLocation = new JLabel("Location");
		{
			lblLocation.setBounds(10, 11, 46, 14);
			panelAddFile.add(lblLocation);
		}


		//input location for the file path
		textFieldAddFile = new JTextField();
		{
			textFieldAddFile.setBounds(67, 8, 235, 20);
			panelAddFile.add(textFieldAddFile);
			textFieldAddFile.setColumns(10);
		}


		// Button to press to browse for an already existing file
		JButton btnBrowse = new JButton("Browse");
		{
			btnBrowse.setBounds(312, 7, 89, 23);
			panelAddFile.add(btnBrowse);
		}

		//            -----Radio Buttons------

		rdbtnAddFile = new JRadioButton("Add File");
		rdbtnAddTitle = new JRadioButton("Add Title");
		rdbtnAddAutomatically = new JRadioButton("Add Automatically");
		{
			rdbtnAddFile.setBounds(10, 213, 109, 23);
			rdbtnAddTitle.setBounds(10, 239, 109, 23);
			rdbtnAddAutomatically.setBounds(10, 265, 163, 23);

			contentPane.add(rdbtnAddFile);
			contentPane.add(rdbtnAddTitle);
			contentPane.add(rdbtnAddAutomatically);

			// default selection
			rdbtnAddFile.setSelected(true);

			// The user is not allowed to add an automatic show
			if(sentEnum == TypeHolderEnum.Show)
				rdbtnAddAutomatically.setVisible(false);

			rdbtnAddFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					layeredPane.setVisible(true);
					layeredPane.setLayer(panelAddFile, 
							JLayeredPane.PALETTE_LAYER);
					layeredPane.setLayer(panelAddTitle, 
							JLayeredPane.DEFAULT_LAYER);
				}
			});

			rdbtnAddTitle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					layeredPane.setVisible(true);
					layeredPane.setLayer(panelAddFile, 
							JLayeredPane.DEFAULT_LAYER);
					layeredPane.setLayer(panelAddTitle, 
							JLayeredPane.PALETTE_LAYER);
				}
			});

			rdbtnAddAutomatically.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					layeredPane.setVisible(false);
				}
			});
		}// end of radion buttons




		//Container for the radio buttons
		ButtonGroup bG = new ButtonGroup();
		{
			bG.add(rdbtnAddAutomatically);
			bG.add(rdbtnAddTitle);
			bG.add(rdbtnAddFile);
		}

		// add and cancel Buttons

		JButton btnAdd = new JButton("Add");
		JButton btnCancel = new JButton("Cancel");
		{
			btnAdd.setBounds(233, 244, 89, 23);
			btnCancel.setBounds(332, 244, 89, 23);

			contentPane.add(btnAdd);
			contentPane.add(btnCancel);

			btnAdd.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					MediaObject mO = add(sentShow, sentSeason);
				}
			});

			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
		}//end of add and cancel buttons
	}//end of method



	/**
	 * this method takes a Show and a Season, creates a new MainObject and adds
	 * it to these Show and Season
	 * 
	 * @param show : current show : can be null to indicate adding a show
	 * @param season : current season : 
	 * @throws UnsupportedInput 
	 */
	private MediaObject add(Show show, Season season)
	{
		MediaObject mO = createMOUsingTypeHolder(show, season);

		// if the user-input (not params) is a Show, add it directly
		if (mO instanceof Show)
		{
			Show showToAdd = (Show)mO;
			shidori.addShow(showToAdd);
		}

		// if the user-input is not a Show, add it directly
		else 
		{
			// if there is no parent Show throw new ContractBrokenException
			if (show == null)
				JOptionPane.showMessageDialog(this, 
						"A show should be selected to be able to add a \n"
						+ "season/episode");
			/* if the show is specified and the season is not, then we are 
			 adding the newly created MainObject will be added to the show*/
			else if (show != null && season == null)
				try {
					show.add(mO);
				} catch (ContractBrokenException e) 
				{
					JOptionPane.showMessageDialog(this, 
							"logic error at AddComponent.add()");
				}
			/* if both the show and the season are specified, then the newly 
			 * created MainObject should be an episode and will be added to 
			 * "season" */
			else if (show != null && season != null)
				try {
					season.add(mO);
				} catch (ContractBrokenException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, 
							"you can't add a season to another season");
				}
			
		}
		return mO;
	}



	/**
	 * this method creates a mew main object depending on the selected radio
	 * button and the TypeHolderEnum that was passed to this class from shidori
	 * @param season 
	 * @param show 
	 * 
	 * @return the newly generated MainObject
	 */
	private MediaObject createMOUsingTypeHolder(Show show, Season season) 
	{
		MediaObject mO = null;
		if ( rdbtnAddFile.isSelected() )
		{
			File file = new File(source);
			mO =sentEnum == TypeHolderEnum.Episode? new Episode(file) 	:
				sentEnum == TypeHolderEnum.Season?  new Season(file)  	:
				sentEnum == TypeHolderEnum.Show?    new Show(file)    	:
				null;
		}
		else if (rdbtnAddTitle.isSelected())
		{
			mO =sentEnum == TypeHolderEnum.Episode? new Episode(title) 	:
				sentEnum == TypeHolderEnum.Season?  new Season(title)  	:
				sentEnum == TypeHolderEnum.Show?    new Show(title)		:	
				null;
		}
		else if ( rdbtnAddAutomatically.isSelected() )
		{
			mO =sentEnum == TypeHolderEnum.Episode? new Episode(show.getName() + 
					" - " + Integer.toString(show.getSize()+1) ):
				sentEnum == TypeHolderEnum.Season?  new Season("Season " +  
					Integer.toString(show.getSeasonNumber()+1)) :
				null;
		}
		return mO;	
	}
}
