package mainFolder;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

class MyTreeModelListener implements TreeModelListener 
{
	public void treeNodesChanged(TreeModelEvent e) {

	}
	
	public void treeNodesInserted(TreeModelEvent e) 
	{
		DefaultMutableTreeNode node;
		node = (DefaultMutableTreeNode)
				(e.getTreePath().getLastPathComponent());

		/*
		 * If the event lists children, then the changed
		 * node is the child of the node we have already
		 * gotten.  Otherwise, the changed node and the
		 * specified node are the same.
		 */
		try {
			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode)	(node.getChildAt(index));
		} 
		catch (NullPointerException exc) 
		{
			exc.printStackTrace();
		}
	}
	
	public void treeNodesRemoved(TreeModelEvent e) {
	}
	
	public void treeStructureChanged(TreeModelEvent e) {
	}
}