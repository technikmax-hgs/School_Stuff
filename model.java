package MVCTable;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;


public class model extends AbstractTableModel {
	
	String[] columnNames = {"Vorname", "Nachname", "Klasse"}; 

	ArrayList<Person> data = new ArrayList<>(

			Arrays.asList(

			new Person("Peter", "Mustermann", "TG12/1"),

			new Person("Sandra", "Schmidt","TG12/1"),

			new Person("Tobias", "Müller","TG12/3")

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
			 return data.get(row).vorname;
			case 1:
				 return data.get(row).nachname;
			case 2:
				 return data.get(row).klasse;
			}
			return null;
						
			} 

			Class[] columns = new Class[]{
					String.class, String.class, String.class
					}; 
			
			public Class getColumnClass(int c) {
				return columns[c];
			}
	
			public boolean isCellEditable(int row, int col) {
			
			switch (col) {
			case 0:
				return true;
			 case 1:
					return true;
			case 2:
				return true;
			}
			return false;
			}

			public void setValueAt(Object value, int row, int col) {
				Person p = data.get(row);
				switch (col) {
				case 0:
					p.vorname = (String) value;
					break;
				 case 1:
					p.nachname = (String) value;
					break;
				case 2:
					p.klasse = (String) value; 
					break;
				}
			
			}

			public void appendEmptyRow() {
				// TODO Auto-generated method stub
				
			}	
			
}
