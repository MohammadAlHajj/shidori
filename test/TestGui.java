package test;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;


public class TestGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGui frame = new TestGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Japan");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 142, 240);
		contentPane.add(scrollPane);
		
		JTree tree = new JTree(top);
		scrollPane.setViewportView(tree);
//		tree.revalidate();
//		treeScrallable.revalidate();
//		tree.repaint();
//		treeScrallable.repaint();
		
		
		DefaultMutableTreeNode manga = new DefaultMutableTreeNode("Manga");
		DefaultMutableTreeNode anime = new DefaultMutableTreeNode("Anime");
		DefaultMutableTreeNode mount = new DefaultMutableTreeNode("Mountain");
		
		top.add(manga);
		top.add(anime);
		top.add(mount);
		
		DefaultMutableTreeNode gto = new DefaultMutableTreeNode("gto");
		DefaultMutableTreeNode fma = new DefaultMutableTreeNode("fma");
		DefaultMutableTreeNode tmnt = new DefaultMutableTreeNode("tmnt");
		
		manga.add(gto);
		manga.add(fma);
		manga.add(tmnt);
		
		DefaultMutableTreeNode claymore = new DefaultMutableTreeNode("claymore");
		DefaultMutableTreeNode bleach = new DefaultMutableTreeNode("bleach");
		DefaultMutableTreeNode naruto = new DefaultMutableTreeNode("naruto");
		
		anime.add(claymore);
		anime.add(bleach);
		anime.add(naruto);
		
		DefaultMutableTreeNode fuji	 = new DefaultMutableTreeNode("fuji");
		DefaultMutableTreeNode fuji2	 = new DefaultMutableTreeNode("fuji");
		DefaultMutableTreeNode fuji6	 = new DefaultMutableTreeNode("fuji");
		DefaultMutableTreeNode fuji3	 = new DefaultMutableTreeNode("fuji");
		DefaultMutableTreeNode fuji4	 = new DefaultMutableTreeNode("fuji");
		DefaultMutableTreeNode fuji5	 = new DefaultMutableTreeNode("fuji");
		
		mount.add(fuji);
		mount.add(fuji2);
		mount.add(fuji3);
		mount.add(fuji4);
		mount.add(fuji5);
		mount.add(fuji6);
		
		
		
		
		System.out.println(fuji.getLevel());
	}
}
