package MVCTable;

import javax.swing.table.AbstractTableModel;

public class model extends AbstractTableModel {
	
	String[] columnNames = {"Vorname", "Nachname", "Klasse"}; 

			Object[][] data = { 
					{"Peter", "Mustermann", "TG12/1"}, 
				    {"Sandra", "Schmidt", "TG12/1"}, 
				    {"Tobias", "Müller",  "TG12/3"}};

			@Override
			public int getColumnCount() {
				return data.length;
		
			}

			@Override
			public int getRowCount() {
				return columnNames.length;
			}

			@Override
			public Object getValueAt(int arg0, int arg1) {
				return data[arg0][arg1];
			} 

			Class[] columns = new Class[]{
					String.class, String.class, String.class
					}; 
			
			public Class getColumnClass(int c) {
				return columns[c];
			}
	
			public boolean isCellEditable(int row, int col) {
				if(col == 1) {
					return true;
			}
				else return false; 
			}

			public void setValueAt(Object value, int row, int col) {
				data[row][col]=value;
			
			}
}
