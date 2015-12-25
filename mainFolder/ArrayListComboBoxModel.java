package mainFolder;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 * 
 */

/**
 * @author Mohammad
 *
 */
public class ArrayListComboBoxModel extends AbstractListModel<String> 
implements ComboBoxModel<String>
{

	/**
	 * 
	 */
	private Object selectedItem;
	private ArrayList<String> anArrayList;

	public ArrayListComboBoxModel(ArrayList<String> arrayList) {
		anArrayList = arrayList;
	}

	public Object getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Object newValue) {
		selectedItem = newValue;
	}

	public int getSize() {
		return anArrayList.size();
	}

	public String getElementAt(int i) {
		return anArrayList.get(i);
	}
	/* (non-Javadoc)
	 * @see javax.swing.AbstractListModel#fireIntervalAdded(java.lang.Object, int, int)
	 */
	@Override
	protected void fireIntervalAdded(Object source, int index0, int index1) {
		// TODO Auto-generated method stub
		super.fireIntervalAdded(source, index0, index1);
	}
	/* (non-Javadoc)
	 * @see javax.swing.AbstractListModel#fireIntervalRemoved(java.lang.Object, int, int)
	 */
	@Override
	protected void fireIntervalRemoved(Object source, int index0, int index1) {
		// TODO Auto-generated method stub
		super.fireIntervalRemoved(source, index0, index1);
	}
}
