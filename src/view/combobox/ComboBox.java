package view.combobox;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboBox<T> extends JComboBox<T> {

	private static final long serialVersionUID = 1L;

	public ComboBox(T[] items, final T placeHolder) {
		setModel(new DefaultComboBoxModel<T>() {
			private static final long serialVersionUID = 1L;
			boolean selectionAllowed = true;

			@Override
			public void setSelectedItem(Object o) {
				if (!placeHolder.equals(o)) {
					super.setSelectedItem(o);
				} else if (selectionAllowed) {
					// Het mag max 1 keer geselecteerd zijn
					selectionAllowed = false;
					super.setSelectedItem(o);
				}
			}
		});
		addItem(placeHolder);
		for(T item : items) {
			addItem(item);
		}
	}

}
