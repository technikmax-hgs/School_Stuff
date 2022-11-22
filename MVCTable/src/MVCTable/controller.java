package MVCTable;

import java.awt.event.ActionEvent;

public class controller {
	private model model;
	private view view;
	
	public controller (model m, view v) {
		this.model = m;
		this.view = v;
		initView();
	}
	public void initView() {
		view.getTable().setModel(this.model);
	}
	public void initController() {
		view.get_Append().addActionListener(e -> appendEmptyRow(e));
		view.get_Delete().addActionListener(e -> deleteRow(e));
	}
	
	private void appendEmptyRow(ActionEvent e) {
		// TODO Auto-generated method stub
		this.model.appendEmptyRow();
		int row = view.getTable().getRowCount();
		view.getTable().setRowSelectionInterval(row-1, row-1);
		view.getTable().editCellAt(row-1, 0);
		view.getTable().setSurrendersFocusOnKeystroke(true);
		view.getTable().getEditorComponent().requestFocus();
	}

	private void deleteRow(ActionEvent e) {
		int row = view.getTable().getSelectedRow();
		this.model.deleteRow(row);
	}
}