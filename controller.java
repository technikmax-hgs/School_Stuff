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
		view.get_Append().addActionListener(this);
	}
	public void initController() {
	}
	
	
private void appendEmptyRow(ActionEvent e) {

this.model.appendEmptyRow();

int row = view.getTable().getRowCount()view.getTable().setRowSelectionInterval(???, ???);

view.getTable().editCellAt(???, 0);

} 

}