package gui;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;


public class model extends AbstractTableModel {
	
	String[] columnNames = {"Name", "Priorität", "Zeit"}; 
	ArrayList<Prozess> data = new ArrayList<>(
		Arrays.asList(
			new Prozess("NameDesProzzeses", "12" , "2,4,6"),
			new Prozess("B", "10", "1,2,1,2,1"),
			new Prozess("C", "11" , "2,2,5")
		)
	);

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
			case 0:
				return data.get(row).name;
			case 1:
				return Integer.toString(data.get(row).prio);
			case 2:
				return data.get(row).zeit;
		}
		return null;	
	} 
			
	public void setValueAt(Object value, int row, int col) {
		Prozess p = data.get(row);
		switch (col) {
			case 0:
				p.name = (String) value;
				break;
			case 1:
				p.setPrio((String) value);
				break;
			case 2:
				p.setZeit((String) value);
				break;
		}
	}
			
	public boolean isCellEditable(int row, int col) {			
		return true;
	}

	public void appendEmptyRow() {
		data.add(new Prozess("", "" ,""));
		int count = getRowCount();
		fireTableRowsInserted(0, count);
	}	
			
	public void deleteRow(int rowIndex) {
		data.remove(data.get(rowIndex));
		int count = getRowCount();
		fireTableRowsDeleted(count, count);
		fireTableRowsInserted(0, count);
	}

	public ArrayList<Prozess> getData() {
		return data;
	}

	public void setData(ArrayList<Prozess> data) {
		this.data = data;
	}	
			
	/*Class[] columns = new Class[]{
		String.class, String.class, String.class
	}; 
	
	public Class getColumnClass(int c) {
		return columns[c];
	}*/
	
}
