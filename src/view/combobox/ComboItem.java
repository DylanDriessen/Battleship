package view.combobox;

/**
 * @author Wouter
 */
public class ComboItem<T> {

	private String label;
	private T value;
	
	public ComboItem(String label) {
		this(label, null);
	}
	
	public ComboItem(String label, T value) {
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
		
}
